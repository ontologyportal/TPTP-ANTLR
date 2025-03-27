package tptp_parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <a href="mailto:tdnorbra@nps.edu?subject=tptp_parser.ParseTestCases">Terry Norbraten, NPS MOVES</a>
 */
public class ParseTestCases {

    private void parsePath(String path) throws IOException {
        Path inPath = Paths.get(path);
        try (Stream<Path> paths = Files.walk(inPath)) {
            paths.filter(f -> f.toString().endsWith(".p")).sorted().forEach( f ->  {
                TPTPVisitor tv = Main.process(f.toFile());
                Main.showResults(tv);
                assertFalse(tv.result.isEmpty());
            });
        }
    }

    @Test
    public void test_generated_semantics() {
        try {
            parsePath(System.getenv("ONTOLOGYPORTAL_GIT") + "/TPTP-ANTLR/test_cases/modal_logic/generated_semantics");
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void test_semantic_casess() {
        try {
            parsePath(System.getenv("ONTOLOGYPORTAL_GIT") + "/TPTP-ANTLR/test_cases/modal_logic/semantic_cases");
        } catch (IOException ex) {
            fail();
        }
    }

} // end class file ParseTestCases.java