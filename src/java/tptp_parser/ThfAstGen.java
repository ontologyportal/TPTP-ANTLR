package tptp_parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.text.ParseException;

public class ThfAstGen {

    /**
     * parse CharStream containing thf and return ast
     * @param inputStream ANTLRInputStream object
     * @param rule start parsing at this rule
     * @param name the parse context name
     * @return ast
     * @throws ParseException if there is no such rule
     */
    public static ParseContext parse(CharStream inputStream, String rule, String name) throws ParseException {

        TptpLexer lexer = new TptpLexer(inputStream);
        lexer.removeErrorListeners(); // only for production
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        TptpParser parser = new TptpParser(tokens);
        parser.removeErrorListeners(); // only for production
        ParseContext parseContext = new ParseContext();

        DefaultTreeListener treeListener = new DefaultTreeListener();

        parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
        parser.setBuildParseTree(true);
        parser.setTokenStream(tokens);

        // parsing starting from a rule requires invoking that rulename as a parser method
        ParserRuleContext parserRuleContext = null;
//        try {
//            Class<?> parserClass = parser.getClass();
//            Method method = parserClass.getMethod(rule, (Class<?>[]) null);
//            parserRuleContext = (ParserRuleContext) method.invoke(parser, (Object[]) null);
//
//        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
//                InvocationTargetException e) {
//            e.printStackTrace();
//            throw new ParseException(e.getMessage(),0);
//        } // the above or the below

        try {
            parserRuleContext = parser.tptp_file();
        } catch (RecognitionException e) {
            throw new ParseException(e.getMessage(),0);
        } // the above or the below

        // create ast
        ParseTreeWalker.DEFAULT.walk(treeListener, parserRuleContext);

        // create and return tptp_parser.ParseContext
        parseContext.parserRuleContext = parserRuleContext;
        parseContext.name = name;
        parseContext.error = treeListener.error;
        return parseContext;
    }

    /**
     * parse String containing thf and return ast
     * @param inputString String object
     * @param rule start parsing at this rule
     * @param name the parse context name
     * @return ast
     * @throws ParseException if there is no such rule
     */
    public static ParseContext parse(String inputString, String rule, String name) throws ParseException {
        CharStream inputStream = CharStreams.fromString(inputString);
        return ThfAstGen.parse(inputStream, rule, name);
    }

}
