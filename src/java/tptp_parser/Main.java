package tptp_parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.stream.Stream;

import org.antlr.v4.runtime.CharStreams;

public class Main {

    /** ***************************************************************
     */
    public static void showHelp() {

        System.out.println("TPTPVisitor class");
        System.out.println("  options:");
        System.out.println("  -h - show this help screen");
        System.out.println("  -f <fname> - parse a thr file");
        System.out.println("  -p <pname> - parse a path of thf files");
    }

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
        else
            showHelp();
    }
}
