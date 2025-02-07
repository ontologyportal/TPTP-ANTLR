// import ANTLR's runtime libraries
package tptp_parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class FileTest {

    public static void main(String[] args) throws Exception {

        CodePointCharStream input = (CodePointCharStream) CharStreams.fromFileName(args[1]);
        // create a lexer that feeds off of input CharStream
        TptpLexer lexer = new TptpLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        TptpParser parser = new TptpParser(tokens);
        ParseTree tree = parser.tptp_file(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}

