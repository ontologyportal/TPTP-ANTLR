package tptp_parser;

import com.articulate.sigma.utils.*;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class FOFTest {

    /** ***************************************************************
     */
    @BeforeClass
    public static void init() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current time and date: " + dtf.format(now));
    }

    /** ***************************************************************
     */
    public void test(String input, String expectFOF, String expectSUMO, String label) {

        System.out.println("=============================");
        System.out.println("FOFTest: " + label);
        System.out.println();
        TPTPVisitor sv = new TPTPVisitor();
        sv.parseString(input);

        String actualFOF = sv.result.values().iterator().next().formula;
        System.out.println("ActualTPTP:   " + actualFOF);
        System.out.println("ExpectTPTP: " + expectFOF);
        if (!StringUtil.emptyString(actualFOF) && actualFOF.equals(expectFOF))
            System.out.println(label + " : Success on FOF");
        else
            System.err.println(label + " : fail on FOF");

        String actualSUMO = sv.result.values().iterator().next().sumo;
        System.out.println("ActualSUMO:   " + actualSUMO);
        System.out.println("ExpectSUMO: " + expectSUMO);
        if (!StringUtil.emptyString(actualSUMO) && actualSUMO.equals(expectSUMO))
            System.out.println(label + " : Success on SUMO");
        else
            System.err.println(label + " : fail on SUMO");

        assertEquals(expectFOF, actualFOF);
        assertEquals(expectSUMO, actualSUMO);
    }

    /** ***************************************************************
     * Check for Issue #13
     */
    @Test
    public void testNegConj() {

        String line = "fof(f393,negated_conjecture,( ~? [X16] : s__instance(X16,s__Relation)), inference(negated_conjecture,[],[f392])).";
        String expectFOF = "(~?[X16] : s__instance(X16,s__Relation))";
        String expectSUMO = "(not (exists (?X16) (instance ?X16 Relation)))";
        String label = "testNegConj";
        test(line,expectFOF,expectSUMO,label);
    }
}