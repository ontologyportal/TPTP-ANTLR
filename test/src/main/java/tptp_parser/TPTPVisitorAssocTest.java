package tptp_parser;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

public class TPTPVisitorAssocTest {

    private static String norm(String s) {
        if (s == null) return null;
        return s.replaceAll("\\s+", " ").trim();
    }

    private static String toSumo(String annotated) {
        TPTPVisitor v = new TPTPVisitor();
        Map<String, TPTPFormula> res = v.parseString(annotated);

        assertNotNull("parseString() must not return null", res);
        assertFalse("parseString() must return at least one result", res.isEmpty());
        assertEquals("Expected exactly one parsed formula", 1, res.size());

        TPTPFormula f = res.values().iterator().next();
        assertNotNull("Parsed TPTPFormula must not be null", f);
        assertNotNull("Parsed TPTPFormula.sumo must not be null", f.sumo);

        return f.sumo;
    }

    @Test
    public void or_twoDisjuncts_isSingleOrExpression() {
        String sumo = toSumo(
                "fof(f1,plain,(s__orientation(s__A,s__C,s__Right)|~s__instance(s__A,s__Object)))."
        );
        assertEquals(
                norm("(or (orientation A C Right) (not (instance A Object)))"),
                norm(sumo)
        );
    }

    @Test
    public void and_twoConjuncts_isSingleAndExpression() {
        String sumo = toSumo(
                "fof(f2,plain,(s__instance(s__A,s__Object)&s__instance(s__C,s__Object)))."
        );
        assertEquals(
                norm("(and (instance A Object) (instance C Object))"),
                norm(sumo)
        );
    }

    @Test
    public void or_nestedOr_isFlattened() {
        String sumo = toSumo(
                "fof(f3,plain,((s__p(s__A)|s__q(s__B))|s__r(s__C)))."
        );
        assertEquals(
                norm("(or (p A) (q B) (r C))"),
                norm(sumo)
        );
    }

    @Test
    public void and_nestedAnd_isFlattened() {
        String sumo = toSumo(
                "fof(f4,plain,((s__p(s__A)&s__q(s__B))&s__r(s__C)))."
        );
        assertEquals(
                norm("(and (p A) (q B) (r C))"),
                norm(sumo)
        );
    }

    @Test
    public void forall_withOr_bodyIsSingleExpression() {
        String sumo = toSumo(
                "fof(f5,plain,(![X0] : (s__p(X0)|~s__q(X0))))."
        );
        String n = norm(sumo);
        assertTrue("Expected forall expression", n.startsWith("(forall"));
        assertTrue("Expected body to contain (or ...)", n.contains("(or "));
        assertTrue("Expected balanced parentheses", n.endsWith(")"));
    }

    @Test
    public void regression_originalBug_noSiblingTopLevelForms() {
        String sumo = toSumo(
                "fof(f6,plain,(s__orientation(s__A,s__C,s__Right)|~s__instance(s__A,s__Object)))."
        );
        String n = norm(sumo);

        System.out.println(n);

        // Old buggy output looked like: "(orientation ...) (not (...)))"
        assertTrue("Must be wrapped in a single (or ...)", n.startsWith("(or "));
    }
}
