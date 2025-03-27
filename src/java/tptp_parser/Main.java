package tptp_parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Map;
import java.util.stream.Stream;

import org.antlr.v4.runtime.CharStreams;

/** Example App showing 3 various ways to parse TPTP and show the results */
public class Main {

    /**
     * Process the given TPTP
     * @param input the TPTP to process
     * @return and instance of the TPTPVisitor
     */
    public static TPTPVisitor process(String input) {
        TPTPVisitor tv = new TPTPVisitor();
        tv.parseString(input);
        return tv;
    }

    /**
     * Process the given TPTP
     * @param fast the TPTP formula to process
     * @return and instance of the TPTPVisitor
     */
    public static TPTPVisitor process(TPTPFormula fast) {
        TPTPVisitor tv = new TPTPVisitor();
        tv.parseFormula(fast);
        return tv;
    }

    /**
     * Process the given TPTP
     * @param file the TPTP file to process
     * @return and instance of the TPTPVisitor
     */
    public static TPTPVisitor process(File file) {
        TPTPVisitor tv = new TPTPVisitor();
        tv.parseFile(file.getPath());
        return tv;
    }

    private static void showResults(TPTPVisitor tv) {

        Map<String, TPTPFormula> hm = tv.result;
        StringBuilder sb = new StringBuilder();
        for (TPTPFormula f : hm.values())
            sb.append(f.getFormula()).append("\n");
        System.out.println("result: " + sb);
    }

    /** ***************************************************************
     */
    public static void showHelp() {

        System.out.println("Main class");
        System.out.println("  options:");
        System.out.println("  -h - show this help screen");
        System.out.println("  -f <fname> - parse a thf file");
        System.out.println("  -p <pname> - parse a path to thf files");
        System.out.println("  -d - demo 2 other inputs to the parser");
        System.out.println("       process(input)   - parse a string input");
        System.out.println("       process(formula) - parse a formula");
    }

    /** Command line entry point for this class
     *
     * @param args common line arguments if any
     * @throws IOException if TPTP reading goes south
     */
    public static void main(String[] args) throws IOException {
        if (args != null && args.length > 0 && args[0].equals("-h"))
            showHelp();
        else if (args != null && args.length > 1 && (args[0].equals("-f") || args[0].equals("-p"))) {
            Path inPath = Paths.get(args[1]);
            try (Stream<Path> paths = Files.walk(inPath)) {
                paths.filter(f -> f.toString().endsWith(".p")).sorted().forEach( f ->  {
                    try {
                        ParseContext pc = ThfAstGen.parse(CharStreams.fromPath(f),"tptp_file","nname");
                        if (pc.error) {
                            System.err.println("ERR " + f.toString());
                        } else {
                            System.out.println("SUC " + f.toString());
                        }
                    } catch (IOException e) {
                        System.err.println("EIO " + f.toString());
                        e.printStackTrace();
                    }
                    catch (ParseException e) {
                        System.err.println("Eparse" + f.toString());
                        e.printStackTrace();
                    }
                });
            }
        }
        else if (args != null && args.length == 1 && (args[0].equals("-d"))) {

            System.out.println();
            String input = "thf(d_constant_flexible_global,logic, ( $modal :=\n" +
                           "    [$constants := $flexible,\n" +
                           "     $quantification := $constant,\n" +
                           "     $consequence := $global,\n" +
                           "     $modalities := $modal_system_D\n" +
                           "    ] )).";
            TPTPVisitor tv = process(input);
            System.out.println("String input:\n " + input);
            showResults(tv);
            System.out.println();

            TPTPFormula tf = new TPTPFormula();
            tf.startLine = 1;
            tf.endLine = 6;
            tf.sourceFile = "";
            tf.formula = input;
            tf.role = "thf";
            tf.name = "test";
            tv = process(tf);
            System.out.println("TPTPFormula input:\n " + tf);
            showResults(tv);
            System.out.println();

        }
        else
            showHelp();
    }
}
