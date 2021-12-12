// import ANTLR's runtime libraries
package tptp_parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class SimpleTest {

    public static final String inputString = "tff(f12,axiom,(\n" +
            "  ! [X16,X17] : ((s__instance(X17,s__Class) & s__instance(X16,s__Class)) => " +
            "(s__disjoint(X17,X16) => ! [X18] : ~(s__instance(X18,X16) & s__instance(X18,X17))))),\n" +
            "  file('/home/apease/.sigmakee/KBs/SUMO.tff',kb_SUMO_12)).";

    public static final String input2 = "tff(f579,plain,(\n" +
            "  ! [X0,X1] : ((s__instance(X1,s__Class) & s__instance(X0,s__Class)) => " +
            "(s__disjoint(X1,X0) => ! [X2] : ~(s__instance(X2,X0) & s__instance(X2,X1))))),\n" +
            "  inference(rectify,[],[f12])).";

    public static void main(String[] args) throws Exception {

        // create a CharStream that reads from standard input
        CodePointCharStream input = CharStreams.fromString(input2);
        // create a lexer that feeds off of input CharStream
        TptpLexer lexer = new TptpLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        TptpParser parser = new TptpParser(tokens);
        ParseTree tree = parser.annotated_formula(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}

