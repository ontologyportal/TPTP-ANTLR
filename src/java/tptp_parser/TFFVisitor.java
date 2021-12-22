package tptp_parser;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class TFFVisitor extends AbstractParseTreeVisitor<String> {

    public static boolean debug = false;

    /** ***************************************************************
     * tff_formula             : tff_logic_formula | tff_typed_atom
     *                         | tff_sequent;  // no use of tff_sequent for SUMO
     */
    public static TPTPFormula visitTffFormula(TptpParser.Tff_formulaContext context) {

        if (debug) System.out.println("visitTffFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_logic_formulaContext")) {
                f = visitTffLogicFormula((TptpParser.Tff_logic_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitTffFormula() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * tff_logic_formula       : tff_binary_formula | tff_unitary_formula
     *                         | tff_subtype; // no use of tff_subtype, not supported in TF0
     */
    public static TPTPFormula visitTffLogicFormula(TptpParser.Tff_logic_formulaContext context) {

        if (debug) System.out.println("visitTffLogicFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffLogicFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_binary_formulaContext")) {
                f = visitTffBinaryFormula((TptpParser.Tff_binary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unitary_formulaContext")) {
                f = visitTffUnitaryFormula((TptpParser.Tff_unitary_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitTffLogicFormula() returning: " + f);
        if (debug) System.out.println("visitTffLogicFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * tff_binary_formula      : tff_binary_nonassoc | tff_binary_assoc;
     */
    public static TPTPFormula visitTffBinaryFormula(TptpParser.Tff_binary_formulaContext context) {

        if (debug) System.out.println("visitTffBinaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffBinaryFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_binary_nonassocContext")) {
                f = visitTffBinaryNonassoc((TptpParser.Tff_binary_nonassocContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_binary_assocContext")) {
                f = visitTffBinaryAssoc((TptpParser.Tff_binary_assocContext) c);
            }
        }
        if (debug) System.out.println("visitTffBinaryFormula() returning: " + f);
        if (debug) System.out.println("visitTffBinaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * tff_binary_nonassoc     : tff_unitary_formula binary_connective tff_unitary_formula;
     */
    public static TPTPFormula visitTffBinaryNonassoc(TptpParser.Tff_binary_nonassocContext context) {

        if (debug) System.out.println("visitTffBinaryNonassoc(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffBinaryNonassoc() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unitary_formulaContext")) {
                TPTPFormula newf = visitTffUnitaryFormula((TptpParser.Tff_unitary_formulaContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + conn + newf.formula;
                    f.sumo = "(" + conn + " " + f.sumo + " " + newf.sumo + ")";
                }
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Binary_connectiveContext")) {
                conn = ((TptpParser.Binary_connectiveContext) c).getText();
            }
        }
        if (debug) System.out.println("visitTffBinaryNonassoc() returning: " + f);
        if (debug) System.out.println("visitTffBinaryNonassoc() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * tff_binary_assoc        : tff_or_formula | tff_and_formula;
     */
    public static TPTPFormula visitTffBinaryAssoc(TptpParser.Tff_binary_assocContext context) {

        if (debug) System.out.println("visitTffBinaryAssoc(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffBinaryAssoc() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_or_formulaContext")) {
                f = visitTffOrFormula((TptpParser.Tff_or_formulaContext) c);
                f.sumo = f.sumo + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_and_formulaContext")) {
                f = visitTffAndFormula((TptpParser.Tff_and_formulaContext) c);
                f.sumo = f.sumo + ")";
            }
        }
        if (debug) System.out.println("visitTffBinaryAssoc() returning: " + f);
        if (debug) System.out.println("visitTffBinaryAssoc() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * tff_or_formula          : tff_unitary_formula Or tff_unitary_formula
     *                         | tff_or_formula Or tff_unitary_formula;
     */
    public static TPTPFormula visitTffOrFormula(TptpParser.Tff_or_formulaContext context) {

        if (debug) System.out.println("visitTffOrFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffOrFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unitary_formulaContext")) {
                TPTPFormula newf = visitTffUnitaryFormula((TptpParser.Tff_unitary_formulaContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + "|" + newf.formula;
                    if (f.sumo.startsWith("(or"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(or " + f.sumo + " " + newf.sumo;
                }
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_or_formulaContext")) {
                TPTPFormula newf = visitTffOrFormula((TptpParser.Tff_or_formulaContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + "|" + newf.formula;
                    if (f.sumo.startsWith("(or"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(or " + f.sumo + " " + newf.sumo;
                }
            }
        }
        if (debug) System.out.println("visitTffOrFormula() returning: " + f);
        if (debug) System.out.println("visitTffOrFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * tff_and_formula         : tff_unitary_formula And tff_unitary_formula
     *                         | tff_and_formula And tff_unitary_formula;
     */
    public static TPTPFormula visitTffAndFormula(TptpParser.Tff_and_formulaContext context) {

        if (debug) System.out.println("visitTffAndFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffAndFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unitary_formulaContext")) {
                TPTPFormula newf = visitTffUnitaryFormula((TptpParser.Tff_unitary_formulaContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + "&" + newf.formula;
                    if (f.sumo.startsWith("(and"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(and " + f.sumo + " " + newf.sumo;
                }
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_and_formulaContext")) {
                TPTPFormula newf = visitTffAndFormula((TptpParser.Tff_and_formulaContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + "&" + newf.formula;
                    if (f.sumo.startsWith("(and"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(and " + f.sumo + " " + newf.sumo;
                }
            }
        }
        if (debug) System.out.println("visitTffAndFormula() returning: " + f);
        if (debug) System.out.println("visitTffAndFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * tff_unitary_formula     : tff_quantified_formula | tff_unary_formula
     *                         | tff_atomic_formula | tff_conditional
     *                         | tff_let | '(' tff_logic_formula ')';
     */
    public static TPTPFormula visitTffUnitaryFormula(TptpParser.Tff_unitary_formulaContext context) {

        if (debug) System.out.println("visitTffUnitaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffUnitaryFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_quantified_formulaContext")) {
                f = visitTffQuantifiedFormula((TptpParser.Tff_quantified_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unary_formulaContext")) {
                f = visitTffUnaryFormula((TptpParser.Tff_unary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unitary_formulaContext")) {
                f = visitTffUnitaryFormula((TptpParser.Tff_unitary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_atomic_formulaContext")) {
                f = visitTffAtomicFormula((TptpParser.Tff_atomic_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_logic_formulaContext")) {
                f = visitTffLogicFormula((TptpParser.Tff_logic_formulaContext) c);
                f.formula = "(" + f.formula + ")";
            }
        }
        if (debug) System.out.println("visitTffUnitaryFormula() returning: " + f);
        if (debug) System.out.println("visitTffUnitaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * tff_atomic_formula      : fof_atomic_formula;
     */
    public static TPTPFormula visitTffAtomicFormula(TptpParser.Tff_atomic_formulaContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitTffAtomicFormula(): " + context.getText());
        if (debug) System.out.println("visitTffAtomicFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitTffAtomicFormula() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffAtomicFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_atomic_formulaContext")) {
                f = CNFVisitor.visitFofAtomicFormula((TptpParser.Fof_atomic_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitTffAtomicFormula() returning: " + f);
        if (debug) System.out.println("visitTffAtomicFormula() returning sumo: " + f.sumo);
        return f;
    }


    /** ***************************************************************
     * tff_unary_formula       : unary_connective tff_unitary_formula
     *                         | fof_infix_unary;
     */
    public static TPTPFormula visitTffUnaryFormula(TptpParser.Tff_unary_formulaContext context) {

        if (debug) System.out.println("visitTffUnaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffUnaryFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unitary_formulaContext")) {
                TPTPFormula newf = visitTffUnitaryFormula((TptpParser.Tff_unitary_formulaContext) c);
                f.formula = conn + newf.formula;
                f.sumo = "(" + TPTPVisitor.sumoTable.get(conn) + " " + newf.sumo + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Unary_connectiveContext")) {
                conn = ((TptpParser.Unary_connectiveContext) c).getText();
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_infix_unaryContext")) {
                f = CNFVisitor.visitFofInfixUnary((TptpParser.Fof_infix_unaryContext) c);
            }
        }
        if (debug) System.out.println("visitTffUnaryFormula() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * tff_quantified_formula  : fof_quantifier '[' tff_variable_list ']' ':' tff_unitary_formula;
     */
    public static TPTPFormula visitTffQuantifiedFormula(TptpParser.Tff_quantified_formulaContext context) {

        if (debug) System.out.println("visitTffQuantifiedFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffQuantifiedFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_unitary_formulaContext")) {
                TPTPFormula newf = visitTffUnitaryFormula((TptpParser.Tff_unitary_formulaContext) c);
                f.formula = f.formula + newf.formula;
                f.sumo = f.sumo + newf.sumo + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_quantifierContext")) {
                String quant = ((TptpParser.Fof_quantifierContext) c).getText();
                f.formula = quant;
                f.sumo = "(" + TPTPVisitor.sumoTable.get(quant);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_variable_listContext")) {
                TPTPFormula newf = visitTffVariableList((TptpParser.Tff_variable_listContext) c);
                f.formula = f.formula + "[" + newf.formula + "] : ";
                f.sumo = f.sumo + " (" + newf.sumo + ") ";
            }
        }
        if (debug) System.out.println("visitTffQuantifiedFormula() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * tff_variable_list       : tff_variable (',' tff_variable)*;
     */
    public static TPTPFormula visitTffVariableList(TptpParser.Tff_variable_listContext context) {

        if (debug) System.out.println("visitTffVariableList(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffVariableList() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_variableContext")) {
                TPTPFormula newf = visitTffVariable((TptpParser.Tff_variableContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + "," + newf.formula;
                    f.sumo = f.sumo + " " + newf.sumo;
                }
            }
        }
        if (debug) System.out.println("visitTffVariableList() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * tff_variable            : tff_typed_variable | variable;
     */
    public static TPTPFormula visitTffVariable(TptpParser.Tff_variableContext context) {

        if (debug) System.out.println("visitTffVariable(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffVariable() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_typed_variableContext")) {
                f = visitTffTypedVariable((TptpParser.Tff_typed_variableContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$VariableContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitTffVariable() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
        }
        if (debug) System.out.println("visitTffVariable() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * tff_typed_variable      : variable ':' tff_atomic_type;
     */
    public static TPTPFormula visitTffTypedVariable(TptpParser.Tff_typed_variableContext context) {

        if (debug) System.out.println("visitTffTypedVariable(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTffTypedVariable() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_atomic_typeContext")) {
                f.formula = f.formula + " : " + ((TptpParser.Tff_atomic_typeContext) c).getText();
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$VariableContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitTffTypedVariable() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
        }
        if (debug) System.out.println("visitTffTypedVariable() returning: " + f);
        return f;
    }
}
