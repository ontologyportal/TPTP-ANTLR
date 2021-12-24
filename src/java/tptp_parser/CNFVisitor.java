package tptp_parser;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashMap;

public class CNFVisitor extends AbstractParseTreeVisitor<String> {

    public static boolean debug = false;

    /** ***************************************************************
     * cnf_formula             : cnf_disjunction | '(' cnf_disjunction ')';
     */
    public static TPTPFormula visitCNFFormula(TptpParser.Cnf_formulaContext context) {

        if (debug) System.out.println("\n");
        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitCNFFormula(): " + context.getText());
        if (debug) System.out.println("visitCNFFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitCNFFormula() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitCNFFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Cnf_disjunctionContext")) {
                f = visitCnfDisjunction((TptpParser.Cnf_disjunctionContext) c);
            }
        }
        if (debug) System.out.println("visitCNFFormula() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * cnf_disjunction         : cnf_literal | cnf_disjunction Or cnf_literal;
     */
    public static TPTPFormula visitCnfDisjunction(TptpParser.Cnf_disjunctionContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitCnfDisjunction(): " + context.getText());
        if (debug) System.out.println("visitCnfDisjunction() # children: " + context.children.size());
        if (debug) System.out.println("visitCnfDisjunction() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitCnfDisjunction() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Cnf_disjunctionContext")) {
                f = visitCnfDisjunction((TptpParser.Cnf_disjunctionContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Cnf_literalContext")) {
                TPTPFormula newf = visitCnfLiteral((TptpParser.Cnf_literalContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + "|" + newf.formula;
                    if (f.sumo.startsWith("(or"))
                        f.sumo = f.sumo + " " + newf.sumo + ")";
                    else
                        f.sumo = "(or " + f.sumo + " " + newf.sumo + ")";
                }
            }
        }
        if (debug) System.out.println("visitCnfDisjunction() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * cnf_literal             : fof_atomic_formula | Not fof_atomic_formula
     *                         | fof_infix_unary;
     */
    public static TPTPFormula visitCnfLiteral(TptpParser.Cnf_literalContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitCnfLiteral(): " + context.getText());
        if (debug) System.out.println("visitCnfLiteral() # children: " + context.children.size());
        if (debug) System.out.println("visitCnfLiteral() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitCnfLiteral() child: " + c.getClass().getName());
            if (debug) System.out.println("visitCnfLiteral() child: " + c.getText());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_atomic_formulaContext")) {
                f = visitFofAtomicFormula((TptpParser.Fof_atomic_formulaContext) c);
            }
            if (c.getText().equals("~")) {
                if (debug) System.out.println("visitCnfLiteral() negated: ");
                negated = true;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_infix_unaryContext")) {
                f = visitFofInfixUnary((TptpParser.Fof_infix_unaryContext) c);
            }
        }
        if (negated) {
            f.sumo = "(not " + f.sumo + ")";
            f.formula = "~" + f.formula;
        }
        if (debug) System.out.println("visitCnfLiteral() returning: " + f);
        if (debug) System.out.println("visitCnfLiteral() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_atomic_formula          : fof_plain_atomic_formula
     *                             | fof_defined_atomic_formula
     *                             | fof_system_atomic_formula;
     */
    public static TPTPFormula visitFofAtomicFormula(TptpParser.Fof_atomic_formulaContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofAtomicFormula(): " + context.getText());
        if (debug) System.out.println("visitFofAtomicFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitFofAtomicFormula() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofAtomicFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_plain_atomic_formulaContext")) {
                f = visitFofPlainAtomicFormula((TptpParser.Fof_plain_atomic_formulaContext) c);
                if (debug) System.out.println("visitFofPlainAtomicFormula() after returning: " + f);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_defined_atomic_formulaContext")) {
                f = visitFofDefinedAtomicFormula((TptpParser.Fof_defined_atomic_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_system_atomic_formulaContext")) {
                f = visitFofSystemAtomicFormula((TptpParser.Fof_system_atomic_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitFofAtomicFormula() returning: " + f);
        if (debug) System.out.println("visitFofAtomicFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_plain_atomic_formula    : fof_plain_term;
     */
    public static TPTPFormula visitFofPlainAtomicFormula(TptpParser.Fof_plain_atomic_formulaContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofPlainAtomicFormula(): " + context.getText());
        if (debug) System.out.println("visitFofPlainAtomicFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitFofPlainAtomicFormula() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofPlainAtomicFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_plain_termContext")) {
                f = visitFofPlainTerm((TptpParser.Fof_plain_termContext) c);
            }
        }
        if (debug) System.out.println("visitFofPlainAtomicFormula() returning: " + f);
        if (debug) System.out.println("visitFofPlainAtomicFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_plain_term           : constant
     *                         | functor '(' fof_arguments ')';
     */
    public static TPTPFormula visitFofPlainTerm(TptpParser.Fof_plain_termContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofPlainTerm(): " + context.getText());
        if (debug) System.out.println("visitFofPlainTerm() # children: " + context.children.size());
        if (debug) System.out.println("visitFofPlainTerm() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofPlainTerm() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$ConstantContext")) {
                f.sumo = TPTPVisitor.translateSUMOterm(c.getText());
                f.formula = c.getText();
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$FunctorContext")) {
                f.sumo = "(" + TPTPVisitor.translateSUMOterm(c.getText());
                f.formula = c.getText() + "(";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_argumentsContext")) {
                TPTPFormula newf = visitFofArguments((TptpParser.Fof_argumentsContext) c);
                f.sumo = f.sumo + " " + newf.sumo + ")";
                f.formula = f.formula + newf.formula + ")";
            }
        }
        if (debug) System.out.println("visitFofPlainTerm() returning: " + f);
        if (debug) System.out.println("visitFofPlainTerm() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_arguments           : fof_term (',' fof_term)*;
     */
    public static TPTPFormula visitFofArguments(TptpParser.Fof_argumentsContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofArguments(): " + context.getText());
        if (debug) System.out.println("visitFofArguments() # children: " + context.children.size());
        if (debug) System.out.println("visitFofArguments() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofArguments() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_termContext")) {
                TPTPFormula newf = visitFofTerm((TptpParser.Fof_termContext) c);
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
        f.sumo = f.sumo;
        f.formula = f.formula;
        if (debug) System.out.println("visitFofArguments() returning: " + f);
        if (debug) System.out.println("visitFofArguments() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_term                : fof_function_term | variable
     *                         | tff_conditional_term | tff_let_term
     *                         | tff_tuple_term;
     *
     *                         TODO: ignoring tff for now, and forever unless used in TF0
     */
    public static TPTPFormula visitFofTerm(TptpParser.Fof_termContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofTerm(): " + context.getText());
        if (debug) System.out.println("visitFofTerm() # children: " + context.children.size());
        if (debug) System.out.println("visitFofTerm() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofTerm() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$VariableContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                f.formula = term;
                f.sumo = sumoTerm;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_function_termContext")) {
                f = visitFunctionTerm((TptpParser.Fof_function_termContext) c);
            }
        }
        f.sumo = f.sumo;
        f.formula = f.formula;
        if (debug) System.out.println("visitFofTerm() returning: " + f);
        if (debug) System.out.println("visitFofTerm() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_function_term       : fof_plain_term | fof_defined_term
     *                         | fof_system_term;
     */
    public static TPTPFormula visitFunctionTerm(TptpParser.Fof_function_termContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFunctionTerm(): " + context.getText());
        if (debug) System.out.println("visitFunctionTerm() # children: " + context.children.size());
        if (debug) System.out.println("visitFunctionTerm() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFunctionTerm() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_plain_termContext")) {
                f = visitFofPlainTerm((TptpParser.Fof_plain_termContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_defined_termContext")) {
                f = visitFofDefinedTerm((TptpParser.Fof_defined_termContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_system_termContext")) {
                f = visitFofSystemTerm((TptpParser.Fof_system_termContext) c);
            }
        }
        f.sumo = f.sumo;
        f.formula = f.formula;
        if (debug) System.out.println("visitFunctionTerm() returning: " + f);
        if (debug) System.out.println("visitFunctionTerm() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_system_term         : system_constant
     *                         | system_functor '(' fof_arguments ')';
     */
    public static TPTPFormula visitFofSystemTerm(TptpParser.Fof_system_termContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofSystemTerm(): " + context.getText());
        if (debug) System.out.println("visitFofSystemTerm() # children: " + context.children.size());
        if (debug) System.out.println("visitFofSystemTerm() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofSystemTerm() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$System_constantContext")) {
                String term = c.getText();
                f.formula = term;
                f.sumo = TPTPVisitor.translateSUMOterm(term);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$System_functorContext")) {
                String term = c.getText();
                f.formula = term + "(";
                f.sumo = "(" + TPTPVisitor.translateSUMOterm(term);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_argumentsContext")) {
                TPTPFormula newf = visitFofArguments((TptpParser.Fof_argumentsContext) c);
                f.formula = f.formula + newf.formula + ")";
                f.sumo = f.sumo + newf.sumo + ")";
            }
        }
        f.sumo = f.sumo;
        f.formula = f.formula;
        if (debug) System.out.println("visitFofSystemTerm() returning: " + f);
        if (debug) System.out.println("visitFofSystemTerm() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_defined_atomic_formula  : fof_defined_plain_formula | fof_defined_infix_formula;
     */
    public static TPTPFormula visitFofDefinedAtomicFormula(TptpParser.Fof_defined_atomic_formulaContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofDefinedAtomicFormula(): " + context.getText());
        if (debug) System.out.println("visitFofDefinedAtomicFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitFofDefinedAtomicFormula() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofDefinedAtomicFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_defined_plain_formulaContext")) {
                f = visitFofDefinedPlainFormula((TptpParser.Fof_defined_plain_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_defined_infix_formulaContext")) {
                f = visitFofDefinedInfixFormula((TptpParser.Fof_defined_infix_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitFofDefinedAtomicFormula() returning: " + f);
        if (debug) System.out.println("visitFofDefinedAtomicFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_defined_infix_formula   : fof_term defined_infix_pred fof_term;
     */
    public static TPTPFormula visitFofDefinedInfixFormula(TptpParser.Fof_defined_infix_formulaContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofDefinedInfixFormula(): " + context.getText());
        if (debug) System.out.println("visitFofDefinedInfixFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitFofDefinedInfixFormula() text: " + context.getText());
        String ineq = context.defined_infix_pred().getText();
        String sumoIneq = TPTPVisitor.sumoTable.get(ineq);
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofDefinedInfixFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_termContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitFofInfixUnary() term: " + term);
                if (f.formula.equals("")) {
                    f.formula = term;
                    f.sumo = "(" + sumoIneq + " " + sumoTerm;
                }
                else {
                    f.formula = f.formula + ineq + term;
                    f.sumo = f.sumo + " " + sumoTerm + ")";
                }
            }
        }
        if (debug) System.out.println("visitFofDefinedInfixFormula() returning: " + f);
        if (debug) System.out.println("visitFofDefinedInfixFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_defined_plain_formula   : fof_defined_term;
     */
    public static TPTPFormula visitFofDefinedPlainFormula(TptpParser.Fof_defined_plain_formulaContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofDefinedPlainFormula(): " + context.getText());
        if (debug) System.out.println("visitFofDefinedPlainFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitFofDefinedPlainFormula() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofDefinedPlainFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_defined_termContext")) {
                f = visitFofDefinedTerm((TptpParser.Fof_defined_termContext) c);
            }
        }
        if (debug) System.out.println("visitFofDefinedPlainFormula() returning: " + f);
        if (debug) System.out.println("visitFofDefinedPlainFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_defined_term        : defined_term | fof_defined_atomic_term;
     */
    public static TPTPFormula visitFofDefinedTerm(TptpParser.Fof_defined_termContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofDefinedTerm(): " + context.getText());
        if (debug) System.out.println("visitFofDefinedTerm() # children: " + context.children.size());
        if (debug) System.out.println("visitFofDefinedTerm() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofDefinedTerm() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Defined_termContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                f.sumo = sumoTerm;
                f.formula = term;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_defined_atomic_termContext")) {
                f = visitFofDefinedAtomicTerm((TptpParser.Fof_defined_atomic_termContext) c);
            }
        }
        if (debug) System.out.println("visitFofDefinedTerm() returning: " + f);
        if (debug) System.out.println("visitFofDefinedTerm() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_defined_atomic_term : fof_defined_plain_term;
     */
    public static TPTPFormula visitFofDefinedAtomicTerm(TptpParser.Fof_defined_atomic_termContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofDefinedAtomicTerm(): " + context.getText());
        if (debug) System.out.println("visitFofDefinedAtomicTerm() # children: " + context.children.size());
        if (debug) System.out.println("visitFofDefinedAtomicTerm() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofDefinedAtomicTerm() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_defined_plain_termContext")) {
                f = visitFofDefinedPlainTerm((TptpParser.Fof_defined_plain_termContext) c);
            }
        }
        if (debug) System.out.println("visitFofDefinedAtomicTerm() returning: " + f);
        if (debug) System.out.println("visitFofDefinedPlainTerm() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_defined_plain_term  : defined_constant
     *                         | defined_functor '(' fof_arguments ')';
     */
    public static TPTPFormula visitFofDefinedPlainTerm(TptpParser.Fof_defined_plain_termContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofDefinedPlainTerm(): " + context.getText());
        if (debug) System.out.println("visitFofDefinedPlainTerm() # children: " + context.children.size());
        if (debug) System.out.println("visitFofDefinedPlainTerm() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofDefinedPlainTerm() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Defined_constantContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                f.sumo = sumoTerm;
                f.formula = term;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Defined_functorContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                f.sumo = "(" + sumoTerm;
                f.formula = term + "(";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_argumentsContext")) {
                TPTPFormula newf = visitFofArguments((TptpParser.Fof_argumentsContext) c);
                f.sumo = f.sumo + " " + newf.sumo + ")";
                f.formula = f.formula + newf.formula + ")";
            }
        }
        if (debug) System.out.println("visitFofDefinedPlainTerm() returning: " + f);
        if (debug) System.out.println("visitFofDefinedPlainTerm() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_system_atomic_formula   : fof_system_term;
     */
    public static TPTPFormula visitFofSystemAtomicFormula(TptpParser.Fof_system_atomic_formulaContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofSystemAtomicFormula(): " + context.getText());
        if (debug) System.out.println("visitFofSystemAtomicFormula() # children: " + context.children.size());
        if (debug) System.out.println("visitFofSystemAtomicFormula() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofSystemAtomicFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_system_termContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                f.sumo = sumoTerm;
                f.formula = c.getText();
            }
        }
        if (debug) System.out.println("visitFofSystemAtomicFormula() returning: " + f);
        if (debug) System.out.println("visitFofSystemAtomicFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_infix_unary             : fof_term Infix_inequality fof_term;
     */
    public static TPTPFormula visitFofInfixUnary(TptpParser.Fof_infix_unaryContext context) {

        TPTPFormula f = new TPTPFormula();
        boolean negated = false;
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFofInfixUnary(): " + context.getText());
        if (debug) System.out.println("visitFofInfixUnary() # children: " + context.children.size());
        if (debug) System.out.println("visitFofInfixUnary() text: " + context.getText());
        String ineq = context.Infix_inequality().getText();
        String sumoIneq = TPTPVisitor.sumoTable.get(ineq);
        if (debug) System.out.println("visitFofInfixUnary() ineq: " + ineq);
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofInfixUnary() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_termContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitFofInfixUnary() term: " + term);
                if (f.formula.equals("")) {
                    f.formula = term;
                    if (ineq != null && ineq.equals("!="))
                        f.sumo = "(not (equal " + sumoTerm;
                    else
                        f.sumo = "(" + sumoIneq + " " + sumoTerm;
                }
                else {
                    f.formula = f.formula + ineq + term;
                    if (ineq != null && ineq.equals("!="))
                        f.sumo = f.sumo + " " + sumoTerm + "))";
                    else
                        f.sumo = f.sumo + " " + sumoTerm + ")";
                }
            }
        }
        if (debug) System.out.println("visitFofInfixUnary() returning: " + f);
        if (debug) System.out.println("visitFofInfixUnary() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     */
    public static void showHelp() {

        System.out.println("CNFVisitor class");
        System.out.println("  options:");
        System.out.println("  -h - show this help screen");
    }

    /** ***************************************************************
     */
    public static void main(String[] args) {

        System.out.println("INFO in TPTPVisitor.main()");
        if (args != null && args.length > 0 && args[0].equals("-h"))
            showHelp();
        else {
            /* KBmanager.getMgr().initializeOnce();
            String kbName = KBmanager.getMgr().getPref("sumokbname");
            KB kb = KBmanager.getMgr().getKB(kbName); */
            if (args != null && args.length > 1 && args[0].equals("-f")) {
                System.out.println("INFO in TPTPVisitor.main(): parse file: " + args[1]);
                TPTPVisitor sv = new TPTPVisitor();
                sv.parseFile(args[1]);
                HashMap<String,TPTPFormula> hm = sv.result;
                for (String s : hm.keySet()) {
                    TPTPFormula tf = hm.get(s);
                    if (tf.type.equals("cnf")) {
                        System.out.println(hm.get(s) + "\n");
                        CNFVisitor cv = new CNFVisitor();
                    }
                }
            }
            else
                showHelp();
        }
    }
}

