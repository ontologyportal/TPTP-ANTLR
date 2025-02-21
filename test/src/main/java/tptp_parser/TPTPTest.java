package tptp_parser;

import com.articulate.sigma.utils.*;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TPTPTest {

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
    public void test(String input, String expectTPTP, String expectSUMO, String label) {

        System.out.println("=============================");
        System.out.println("TPTPest: " + label);
        System.out.println();
        TPTPVisitor sv = new TPTPVisitor();
        sv.parseString(input);

        String actualTPTP = sv.result.values().iterator().next().formula;
        System.out.println("ActualTPTP:   " + actualTPTP);
        System.out.println("ExpectTPTP: " + expectTPTP);
        if (!StringUtil.emptyString(actualTPTP) && actualTPTP.equals(expectTPTP))
            System.out.println(label + " : Success on THF");
        else
            System.err.println(label + " : fail on THF");

        String actualSUMO = sv.result.values().iterator().next().sumo;
        System.out.println("ActualSUMO:   " + actualSUMO);
        System.out.println("ExpectSUMO: " + expectSUMO);
        if (!StringUtil.emptyString(actualSUMO) && actualSUMO.equals(expectSUMO))
            System.out.println(label + " : Success on SUMO");
        else
            System.err.println(label + " : fail on SUMO");

        assertEquals(expectTPTP, actualTPTP);
        assertEquals(expectSUMO, actualSUMO);
    }


    /** ***************************************************************
     * Check for Issue #13
     */
    @Test
    public void testNegConj() {

        String line = "fof(f393,negated_conjecture,( ~? [X16] : s__instance(X16,s__Relation)), inference(negated_conjecture,[],[f392])).";
        String expectTPTP = "(~?[X16] : s__instance(X16,s__Relation))";
        String expectSUMO = "(not (exists (?X16) (instance ?X16 Relation)))";
        String label = "testNegConj";
        test(line,expectTPTP,expectSUMO,label);
    }
}