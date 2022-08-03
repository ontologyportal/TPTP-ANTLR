package tptp_parser;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import com.articulate.sigma.utils.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class THFTest {

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
        String expectTHF = "(knows_THFTYPE_IiooI @ lBill_THFTYPE_i @ (father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJoe_THFTYPE_i) & " +
                "(father_THFTYPE_IiioI @ lBill_THFTYPE_i @ lJane_THFTYPE_i))";
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


    /** ***************************************************************
     */
    @Test
    public void testImp() {

        String line = "thf(ax30,axiom,((! [ROW3: $i,ROW4: $i,ROW2: $i,NUMBER: $i,CLASS: $i]: (((instance_THFTYPE_IiioI @ ROW4 @ lSymbolicString_THFTYPE_i) & (instance_THFTYPE_IiioI @ ROW2 @ lHumanLanguage_THFTYPE_i) & (instance_THFTYPE_IiioI @ NUMBER @ lPositiveInteger_THFTYPE_i) & (instance_THFTYPE_IiioI @ CLASS @ lClass_THFTYPE_i)) => (((domain_THFTYPE_IiiioI @ format_THFTYPE_i @ NUMBER @ CLASS) & (instance_THFTYPE_IiioI @ format_THFTYPE_i @ lPredicate_THFTYPE_i)) => (instance_THFTYPE_IiioI @ (lListOrderFn_THFTYPE_IiiiI @ (lListFn__3Fn_THFTYPE_IiiiiI @ ROW2 @ ROW3 @ ROW4) @ NUMBER) @ CLASS)))))).";
        String expectTHF = "(![ROW3 : $i, ROW4 : $i, ROW2 : $i, NUMBER : $i, CLASS : $i]:(((instance_THFTYPE_IiioI @ ROW4 @ lSymbolicString_THFTYPE_i) & (instance_THFTYPE_IiioI @ ROW2 @ lHumanLanguage_THFTYPE_i) & (instance_THFTYPE_IiioI @ NUMBER @ lPositiveInteger_THFTYPE_i) & (instance_THFTYPE_IiioI @ CLASS @ lClass_THFTYPE_i)) =>  (((domain_THFTYPE_IiiioI @ format_THFTYPE_i @ NUMBER @ CLASS) & (instance_THFTYPE_IiioI @ format_THFTYPE_i @ lPredicate_THFTYPE_i)) =>  (instance_THFTYPE_IiioI @ (lListOrderFn_THFTYPE_IiiiI @ (lListFn__3Fn_THFTYPE_IiiiiI @ ROW2 @ ROW3 @ ROW4) @ NUMBER) @ CLASS))))";
        String expectSUMO = "(forall (?ROW3 ?ROW4 ?ROW2 ?NUMBER ?CLASS) (=> (and (instance ?ROW4 SymbolicString) (instance ?ROW2 HumanLanguage) (instance ?NUMBER PositiveInteger) (instance ?CLASS Class)) (=> (and (domain format ?NUMBER ?CLASS) (instance format Predicate)) (instance (ListOrderFn (ListFn__3Fn ?ROW2 ?ROW3 ?ROW4) ?NUMBER) ?CLASS))))";
        String label = "testImp";
        test(line,expectTHF,expectSUMO,label);
    }

    /** ***************************************************************
     */
    @Test
    public void testNegConj() {

        String line = "thf(2,negated_conjecture,((~ (? [A:$i,B:$o]: " +
                "~ (modalAttribute_THFTYPE_IoioI @ (~ (B)) @ A)))),inference(neg_conjecture,[status(cth)],[1])).";
        String expectTHF = "(~(?[A : $i, B : $o]:(~(modalAttribute_THFTYPE_IoioI @ (~(B)) @ A))))";
        String expectSUMO = "(not (exists (?A ?B) (not (modalAttribute (not (?B)) ?A))))";
        String label = "testNegConj";
        test(line,expectTHF,expectSUMO,label);
    }
}