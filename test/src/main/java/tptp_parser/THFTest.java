package tptp_parser;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

import com.articulate.sigma.utils.*;

public class THFTest {

    /** ***************************************************************
     */
    public void test(String input, String expectTHF, String expectSUMO, String label) {

        System.out.println("=============================");
        System.out.println("THFTest: " + label);
        System.out.println();
        TPTPVisitor sv = new TPTPVisitor();
        sv.parseString(input);

        String actualTHF = sv.result.values().iterator().next().formula;
        System.out.println("ActualTHF:   " + actualTHF);
        System.out.println("ExpectTHF: " + expectTHF);
        if (!StringUtil.emptyString(actualTHF) && actualTHF.equals(expectTHF))
            System.out.println(label + " : Success on THF");
        else
            System.out.println(label + " : fail on THF");

        String actualSUMO = sv.result.values().iterator().next().sumo;
        System.out.println("ActualSUMO:   " + actualSUMO);
        System.out.println("ExpectSUMO: " + expectSUMO);
        if (!StringUtil.emptyString(actualSUMO) && actualSUMO.equals(expectSUMO))
            System.out.println(label + " : Success on SUMO");
        else
            System.out.println(label + " : fail on SUMO");

        assertEquals(expectTHF, actualTHF);
        assertEquals(expectSUMO, actualSUMO);
    }

    /** ***************************************************************
     */
    @Test
    public void test1() {

        String line = "thf(551,axiom,((knows_THFTYPE_IiooI @ lBill_THFTYPE_i @ ((father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJoe_THFTYPE_i) & " +
                "(father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJane_THFTYPE_i)))),file('/home/apease/.sigmakee/KBs/SUMO.thf',ax228)).";
        String expectTHF = "(knows_THFTYPE_IiooI @ lBill_THFTYPE_i @ ((father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJoe_THFTYPE_i) & " +
                "(father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJane_THFTYPE_i)))";
        String expectSUMO = "(knows Bill (and (father Bill Joe) (father Bill Jane)))";
        String label = "test1";
        test(line,expectTHF,expectSUMO,label);
    }

    /** ***************************************************************
     */
    @Test
    public void test2() {

        String line = "thf(52737,plain,($false),inference(simp,[status(thm)],[52736])).";
        String expectTHF = "($false)";
        String expectSUMO = "(false)";
        String label = "test2";
        test(line,expectTHF,expectSUMO,label);
    }

    /** ***************************************************************
     */
    @Test
    public void testNeg() {

        String line = "thf(2,negated_conjecture,((~ (knows_THFTYPE_IiooI @ lBill_THFTYPE_i @ (father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJoe_THFTYPE_i)))),inference(neg_conjecture,[status(cth)],[1])).";
        String expectTHF = "(~(knows_THFTYPE_IiooI @ lBill_THFTYPE_i @ (father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJoe_THFTYPE_i)))";
        String expectSUMO = "(not (knows Bill (father Bill Joe)))";
        String label = "testNeg";
        test(line,expectTHF,expectSUMO,label);
    }
}