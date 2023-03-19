package tptp_parser;

import com.articulate.sigma.utils.StringUtil;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class THFVisitor extends AbstractParseTreeVisitor<String> {

    public static boolean debug = false;

    /** ***************************************************************
     * thf_formula : thf_logic_formula | thf_sequent;   // no use of thf_sequent for SUMO
     */
    public static TPTPFormula visitThfFormula(TptpParser.Thf_formulaContext context) {

        if (debug) System.out.println("visitThfFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_logic_formulaContext")) {
                f = visitThfLogicFormula((TptpParser.Thf_logic_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitThfFormula() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * thf_logic_formula       : thf_binary_formula | thf_unitary_formula
     *                         | thf_type_formula | thf_subtype;
     */
    public static TPTPFormula visitThfLogicFormula(TptpParser.Thf_logic_formulaContext context) {

        if (debug) System.out.println("visitThfLogicFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfLogicFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_binary_formulaContext")) {
                f = visitThfBinaryFormula((TptpParser.Thf_binary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                f = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_type_formulaContext")) {
                f = visitThfTypeFormula((TptpParser.Thf_type_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_subtypeContext")) {
                f = visitThfSubtype((TptpParser.Thf_subtypeContext) c);
            }
        }
        if (debug) System.out.println("visitThfLogicFormula() returning: " + f);
        if (debug) System.out.println("visitThfLogicFormula() returning sumo: " + f.sumo);
        return f;
    }


    /** ***************************************************************
     * thf_subtype             : thf_atom Subtype_sign thf_atom;
     */
    public static TPTPFormula visitThfSubtype(TptpParser.Thf_subtypeContext context) {

        if (debug) System.out.println("visitThfSubtype(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfSubtype() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_typeable_formulaContext")) {
                f = visitThfAtom((TptpParser.Thf_atomContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Subtype_signContext")) {
                String term = c.getText();  // should be $<constant>
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfSubtype() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
        }
        if (debug) System.out.println("visitThfSubtype() returning: " + f);
        if (debug) System.out.println("visitThfSubtype() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_type_formula        : thf_typeable_formula ':' thf_top_level_type;
     */
    public static TPTPFormula visitThfTypeFormula(TptpParser.Thf_type_formulaContext context) {

        if (debug) System.out.println("visitThfTypeFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfTypeFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_typeable_formulaContext")) {
                f = visitThfTypeableFormula((TptpParser.Thf_typeable_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_top_level_typeContext")) {
                f = visitThfTopLevelType((TptpParser.Thf_top_level_typeContext) c);
            }
        }
        if (debug) System.out.println("visitThfTypeFormula() returning: " + f);
        if (debug) System.out.println("visitThfTypeFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_typeable_formula    : thf_atom | '(' thf_logic_formula ')';
     */
    public static TPTPFormula visitThfTypeableFormula(TptpParser.Thf_typeable_formulaContext context) {

        if (debug) System.out.println("visitThfTypeableFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfTypeableFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_typeable_formulaContext")) {
                f = visitThfAtom((TptpParser.Thf_atomContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_logicContext")) {
                f = visitThfLogicFormula((TptpParser.Thf_logic_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitThfTypeableFormula() returning: " + f);
        if (debug) System.out.println("visitThfTypeableFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_top_level_type      : thf_unitary_type | thf_mapping_type | thf_apply_type;
     */
    public static TPTPFormula visitThfTopLevelType(TptpParser.Thf_top_level_typeContext context) {

        if (debug) System.out.println("visitThfTopLevelType(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfTopLevelType() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_typeContext")) {
                f = visitThfUnitaryType((TptpParser.Thf_unitary_typeContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_mapping_typeContext")) {
                f = visitThfMappingType((TptpParser.Thf_mapping_typeContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_mapping_typeContext")) {
                f = visitThfApplyType((TptpParser.Thf_apply_typeContext) c);
            }
        }
        if (debug) System.out.println("visitThfTopLevelType() returning: " + f);
        if (debug) System.out.println("visitThfTopLevelType() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_unitary_type        : thf_unitary_formula;
     */
    public static TPTPFormula visitThfUnitaryType(TptpParser.Thf_unitary_typeContext context) {

        if (debug) System.out.println("visitThfUnitaryType(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfUnitaryType() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                f = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitThfUnitaryType() returning: " + f);
        if (debug) System.out.println("visitThfUnitaryType() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_mapping_type        : thf_unitary_type Arrow thf_unitary_type
     *                         | thf_unitary_type Arrow thf_mapping_type;
     */
    public static TPTPFormula visitThfMappingType(TptpParser.Thf_mapping_typeContext context) {

        if (debug) System.out.println("visitThfMappingType(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfMappingType() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                f = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$ArrowContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfMappingType() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
        }
        if (debug) System.out.println("visitThfMappingType() returning: " + f);
        if (debug) System.out.println("visitThfMappingType() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_apply_type          : thf_apply_formula;
     */
    public static TPTPFormula visitThfApplyType(TptpParser.Thf_apply_typeContext context) {

        if (debug) System.out.println("visitThfApplyType(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfApplyType() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_apply_formulaContext")) {
                f = visitThfApplyFormula((TptpParser.Thf_apply_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitThfApplyType() returning: " + f);
        if (debug) System.out.println("visitThfApplyType() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_apply_formula       : thf_unitary_formula App thf_unitary_formula
     *                         | thf_apply_formula App thf_unitary_formula;
     */
    public static TPTPFormula visitThfApplyFormula(TptpParser.Thf_apply_formulaContext context) {

        if (debug) System.out.println("visitThfApplyFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfApplyFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                TPTPFormula newf = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
                if (f.formula == null || f.formula.length() == 0) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + " @ " + newf.formula;
                    f.sumo = f.sumo + " " + newf.sumo;
                }
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_apply_formulaContext")) {
                TPTPFormula newf = visitThfApplyFormula((TptpParser.Thf_apply_formulaContext) c);
                if (f.formula == null || f.formula.length() == 0) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + " @ " + newf.formula;
                    f.sumo = f.sumo + " " + newf.sumo;
                }
            }
        }
        if (debug) System.out.println("visitThfApplyFormula() returning: " + f);
        if (debug) System.out.println("visitThfApplyFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_binary_formula      : thf_binary_pair | thf_binary_tuple
     *                         | thf_binary_type;
     */
    public static TPTPFormula visitThfBinaryFormula(TptpParser.Thf_binary_formulaContext context) {

        if (debug) System.out.println("visitThfBinaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfBinaryFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_binary_pairContext")) {
                f = visitThfBinaryPair((TptpParser.Thf_binary_pairContext) c);
                f.formula = "(" + f.formula + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_binary_tupleContext")) {
                f = visitThfBinaryTuple((TptpParser.Thf_binary_tupleContext) c);
            }
         //   if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_binary_typeContext")) {
         //       f = visitThfBinaryType((TptpParser.Thf_binary_typeContext) c);
         //   }
        }
        if (debug) System.out.println("visitThfBinaryFormula() returning: " + f);
        if (debug) System.out.println("visitThfBinaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_binary_pair         : thf_unitary_formula thf_pair_connective thf_unitary_formula;
     */
    public static TPTPFormula visitThfBinaryPair(TptpParser.Thf_binary_pairContext context) {

        if (debug) System.out.println("visitThfBinaryPair(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfBinaryPair() child: " + c.getClass().getName());
        //    if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_pair_connectiveContext")) {
        //        f = visitThfPairConnective((TptpParser.Thf_pair_connectiveContext) c);
        //    }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                TPTPFormula newf = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
                if (StringUtil.emptyString(f.formula)) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    if (newf.sumo.startsWith("(and") || newf.sumo.startsWith("(or"))
                        f.formula = f.formula + " (" + newf.formula + ")";
                    else
                        f.formula = f.formula + " " + newf.formula;
                    f.sumo = f.sumo + " " + newf.sumo;
                }
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_pair_connectiveContext") ||
                    c.getClass().getName().equals("org.antlr.v4.runtime.tree.TerminalNodeImpl")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfBinaryPair() term: " + term);
                if (f.sumo.startsWith("(and") || f.sumo.startsWith("(or"))
                    f.formula = "(" + f.formula + ") " + term + " ";
                else
                    f.formula = f.formula + term + " ";
                f.sumo = "(" + sumoTerm + " " + f.sumo;
            }
        }
        f.sumo = f.sumo + ")";
        if (debug) System.out.println("visitThfBinaryPair() returning: " + f);
        if (debug) System.out.println("visitThfBinaryPair() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_binary_tuple        : thf_or_formula | thf_and_formula
     *                         | thf_apply_formula;
     */
    public static TPTPFormula visitThfBinaryTuple(TptpParser.Thf_binary_tupleContext context) {

        if (debug) System.out.println("visitThfBinaryTuple(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfBinaryTuple() child: " + c.getClass().getName());
                if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_or_formulaContext")) {
                    f = visitThfOrFormula((TptpParser.Thf_or_formulaContext) c);
                }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_and_formulaContext")) {
                f = visitThfAndFormula((TptpParser.Thf_and_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_apply_formulaContext")) {
                if (debug) System.out.println("visitThfBinaryTuple() apply formula child: " + c.getText());
                f = visitThfApplyFormula((TptpParser.Thf_apply_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitThfBinaryTuple() returning: " + f);
        if (debug) System.out.println("visitThfBinaryTuple() returning sumo: " + f.sumo);
        return f;
    }


    /** ***************************************************************
     * thf_unitary_formula     : thf_quantified_formula | thf_unary_formula
     *                         | thf_atom | thf_conditional | thf_let
     *                         | thf_tuple | '(' thf_logic_formula ')';  // let and tuple not used in SUMO
     */
    public static TPTPFormula visitThfUnitaryFormula(TptpParser.Thf_unitary_formulaContext context) {

        if (debug) System.out.println("visitThfUnitaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfUnitaryFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_quantified_formulaContext")) {
                f = visitThfQuantifiedFormula((TptpParser.Thf_quantified_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unary_formulaContext")) {
                f = visitThfUnaryFormula((TptpParser.Thf_unary_formulaContext) c);
                if (f.sumo.trim().startsWith("("))
                    f.sumo = f.sumo;
                else
                    f.sumo = "(" + f.sumo + ")";
                if (f.formula.trim().startsWith("("))
                    f.formula = f.formula;
                else
                    f.formula = "(" + f.formula + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_atomContext")) {
                f = visitThfAtom((TptpParser.Thf_atomContext) c);
            }
       //     if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_conditionalContext")) {
       //         f = visitThfConditional((TptpParser.Thf_conditionalContext) c);
       //     }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_logic_formulaContext")) {
                f = visitThfLogicFormula((TptpParser.Thf_logic_formulaContext) c);
                if (f.sumo.trim().startsWith("("))
                    f.sumo = f.sumo;
                else
                    f.sumo = "(" + f.sumo + ")";
                if (f.formula.trim().startsWith("("))
                    f.formula = f.formula;
                else
                    f.formula = "(" + f.formula + ")";
            }
        }
        if (debug) System.out.println("visitThfUnitaryFormula() returning: " + f);
        if (debug) System.out.println("visitThfUnitaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_or_formula          : thf_unitary_formula Or thf_unitary_formula
     *                         | thf_or_formula Or thf_unitary_formula;
     */
    public static TPTPFormula visitThfOrFormula(TptpParser.Thf_or_formulaContext context) {

        if (debug) System.out.println("visitThfOrFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfOrFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                TPTPFormula newf = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
                if (newf.sumo.startsWith("(or")) {
                    int start = newf.sumo.indexOf("(",2);
                    int end = newf.sumo.trim().length() - 1;
                    newf.sumo = newf.sumo.substring(start,end);
                }
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + " | " + newf.formula;
                    if (f.sumo.startsWith("(or"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(or " + f.sumo + " " + newf.sumo + ")";
                }
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_or_formulaContext")) {
                TPTPFormula newf = visitThfOrFormula((TptpParser.Thf_or_formulaContext) c);
                if (newf.sumo.startsWith("(or")) {
                    int start = newf.sumo.indexOf("(",2);
                    int end = newf.sumo.trim().length() - 1;
                    newf.sumo = newf.sumo.substring(start,end);
                }
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + " | " + newf.formula;
                    if (f.sumo.startsWith("(or"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(or " + f.sumo + " " + newf.sumo + ")";
                }
            }
        }
        if (debug) System.out.println("visitThfOrFormula() returning: " + f);
        if (debug) System.out.println("visitThfOrFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_and_formula         : thf_unitary_formula And thf_unitary_formula
     *                         | thf_and_formula And thf_unitary_formula;
     */
    public static TPTPFormula visitThfAndFormula(TptpParser.Thf_and_formulaContext context) {

        if (debug) System.out.println("visitThfAndFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfAndFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                TPTPFormula newf = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
                if (newf.sumo.startsWith("(and")) {
                    int start = newf.sumo.indexOf("(",2);
                    int end = newf.sumo.trim().length() - 1;
                    newf.sumo = newf.sumo.substring(start,end);
                }
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + " & " + newf.formula;
                    if (f.sumo.startsWith("(and"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(and " + f.sumo + " " + newf.sumo + ")";
                }
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_and_formulaContext")) {
                TPTPFormula newf = visitThfAndFormula((TptpParser.Thf_and_formulaContext) c);
                if (newf.sumo.startsWith("(and")) {
                    int start = newf.sumo.indexOf("(",2);
                    int end = newf.sumo.trim().length() - 1;
                    newf.sumo = newf.sumo.substring(start,end);
                }
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + " & " + newf.formula;
                    if (f.sumo.startsWith("(and"))
                        f.sumo = f.sumo + " " + newf.sumo;
                    else
                        f.sumo = "(and " + f.sumo + " " + newf.sumo + ")";
                }
            }
        }
        if (debug) System.out.println("visitThfAndFormula() returning: " + f);
        if (debug) System.out.println("visitTffAndFvisitThfAndFormulaormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_unary_formula       : thf_unary_connective '(' thf_logic_formula ')';
     */
    public static TPTPFormula visitThfUnaryFormula(TptpParser.Thf_unary_formulaContext context) {

        if (debug) System.out.println("visitThfUnaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfUnaryFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unary_connectiveContext")) {
                f = visitThfUnaryConnective((TptpParser.Thf_unary_connectiveContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_logic_formulaContext")) {
                TPTPFormula newf = visitThfLogicFormula((TptpParser.Thf_logic_formulaContext) c);
                f.formula = f.formula + "(" + newf.formula + ")";
                f.sumo = f.sumo + "(" + newf.sumo + ")";
            }
        }
        if (debug) System.out.println("visitThfUnaryFormula() returning: " + f);
        if (debug) System.out.println("visitThfUnaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_unary_connective    : unary_connective | th1_unary_connective; // th1... not used in SUMO
     */
    public static TPTPFormula visitThfUnaryConnective(TptpParser.Thf_unary_connectiveContext context) {

        if (debug) System.out.println("visitThfUnaryConnective(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfUnaryConnective() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Unary_connectiveContext")) {
                f.formula = "~";
                f.sumo = "not ";
            }
        }
        if (debug) System.out.println("visitThfUnaryConnective() returning: " + f);
        if (debug) System.out.println("visitThfUnaryConnective() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_atom                : thf_function | variable | defined_term
     *                         | thf_conn_term;
     */
    public static TPTPFormula visitThfAtom(TptpParser.Thf_atomContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitThfAtom(): " + context.getText());
        if (debug) System.out.println("visitThfAtom() # children: " + context.children.size());
        if (debug) System.out.println("visitThfAtom() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfAtom() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_functionContext")) {
                f = visitThfFunction((TptpParser.Thf_functionContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$VariableContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfAtom() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
          //  if (c.getClass().getName().equals("tptp_parser.TptpParser$DefinedTermContext")) {
          //      f = visitDefinedTerm((TptpParser.Defined_termContext) c);
          //  }
          //  if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_conn_termContext")) {
          //      f = visitThfConnTerm((TptpParser.Thf_conn_termContext) c);
          //  }
        }
        if (debug) System.out.println("visitThfAtom() returning: " + f);
        if (debug) System.out.println("visitThfAtom() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_function            : atom | functor '(' thf_arguments ')'
     *                         | defined_functor '(' thf_arguments ')'
     *                         | system_functor '(' thf_arguments ')';
     */
    public static TPTPFormula visitThfFunction(TptpParser.Thf_functionContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitThfFunction(): " + context.getText());
        if (debug) System.out.println("visitThfFunction() # children: " + context.children.size());
        if (debug) System.out.println("visitThfFunction() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfFunction() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$AtomContext")) {
                f = visitAtom((TptpParser.AtomContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$FunctorContext")) {
                String term = c.getText();  // should be $<constant>
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfFunction() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Defined_functorContext")) {
                String term = c.getText();  // should be $<constant>
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfFunction() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$System_functorContext")) {
                String term = c.getText();  // should be $<constant>
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfFunction() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_argumentsContext")) {
                TPTPFormula newf = visitThfArguments((TptpParser.Thf_argumentsContext) c);
                f.formula = f.formula + "(" + newf.formula + ")";
                f.sumo = "(" + f.sumo + newf.sumo + ")";
            }
        }
        if (debug) System.out.println("visitThfFunction() returning: " + f);
        if (debug) System.out.println("visitThfFunction() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_arguments           : thf_formula_list;
     */
    public static TPTPFormula visitThfArguments(TptpParser.Thf_argumentsContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitThfArguments(): " + context.getText());
        if (debug) System.out.println("visitThfArguments() # children: " + context.children.size());
        if (debug) System.out.println("visitThfArguments() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfArguments() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_formula_listContext")) {
                f = THFVisitor.visitThfFormulaList((TptpParser.Thf_formula_listContext) c);
            }
        }
        if (debug) System.out.println("visitThfArguments() returning: " + f);
        if (debug) System.out.println("visitThfArguments() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_formula_list        : thf_logic_formula (',' thf_logic_formula)*;
     */
    public static TPTPFormula visitThfFormulaList(TptpParser.Thf_formula_listContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitThfFormulaList(): " + context.getText());
        if (debug) System.out.println("visitThfFormulaList() # children: " + context.children.size());
        if (debug) System.out.println("visitThfFormulaList() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfFormulaList() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_logic_formulaContext")) {
                TPTPFormula newf = THFVisitor.visitThfLogicFormula((TptpParser.Thf_logic_formulaContext) c);
                if (f.formula == null || f.formula.length() == 0) {
                    f.formula = f.formula + "," + newf.formula;
                    f.sumo =  f.sumo + " " + newf.sumo;
                }
                else {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
            }
        }
        if (debug) System.out.println("visitThfFormulaList() returning: " + f);
        if (debug) System.out.println("visitThfFormulaList() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * atom                    : untyped_atom | defined_constant;
     */
    public static TPTPFormula visitAtom(TptpParser.AtomContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitAtom(): " + context.getText());
        if (debug) System.out.println("visitAtom() # children: " + context.children.size());
        if (debug) System.out.println("visitAtom() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitAtom() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Untyped_atomContext")) {
                f = THFVisitor.visitUntypedAtom((TptpParser.Untyped_atomContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Defined_constantContext")) {
                String term = c.getText();  // should be $<constant>
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitAtom() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
        }
        if (debug) System.out.println("visitAtom() returning: " + f);
        if (debug) System.out.println("visitAtom() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * untyped_atom            : constant | system_constant;
     */
    public static TPTPFormula visitUntypedAtom(TptpParser.Untyped_atomContext context) {

        TPTPFormula f = new TPTPFormula();
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitUntypedAtom(): " + context.getText());
        if (debug) System.out.println("visitUntypedAtom() # children: " + context.children.size());
        if (debug) System.out.println("visitUntypedAtom() text: " + context.getText());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitUntypedAtom() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$ConstantContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitUntypedAtom() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$System_constantContext")) {
                String term = c.getText();  // should be $$<constant>
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitUntypedAtom() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
        }
        if (debug) System.out.println("visitUntypedAtom() returning: " + f);
        if (debug) System.out.println("visitUntypedAtom() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_quantified_formula  : thf_quantification thf_unitary_formula;
     */
    public static TPTPFormula visitThfQuantifiedFormula(TptpParser.Thf_quantified_formulaContext context) {

        if (debug) System.out.println("visitThfQuantifiedFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfQuantifiedFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_unitary_formulaContext")) {
                TPTPFormula newf = visitThfUnitaryFormula((TptpParser.Thf_unitary_formulaContext) c);
                f.formula = f.formula + newf.formula;
                f.sumo = f.sumo + newf.sumo;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_quantificationContext")) {
                f = visitThfQuantification((TptpParser.Thf_quantificationContext) c);
            }
        }
        if (debug) System.out.println("visitThfQuantifiedFormula() returning thf: " + f);
        if (debug) System.out.println("visitThfQuantifiedFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_quantification      : thf_quantifier '[' thf_variable_list ']' ':';
     */
    public static TPTPFormula visitThfQuantification(TptpParser.Thf_quantificationContext context) {

        if (debug) System.out.println("visitThfQuantification(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfQuantification() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_variable_listContext")) {
                TPTPFormula newf = visitThfVariableList((TptpParser.Thf_variable_listContext) c);
                f.formula = f.formula + "[" + newf.formula + "]:";
                f.sumo = f.sumo + "(" + newf.sumo + ") ";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_quantifierContext")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfQuantification() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm + " ";
            }
        }
        if (debug) System.out.println("visitThfQuantification() returning thf: " + f);
        if (debug) System.out.println("visitThfQuantification() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * thf_variable_list       : thf_variable (',' thf_variable)*;
     */
    public static TPTPFormula visitThfVariableList(TptpParser.Thf_variable_listContext context) {

        if (debug) System.out.println("visitThfVariableList(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfVariableList() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_variableContext")) {
                TPTPFormula newf = visitThfVariable((TptpParser.Thf_variableContext) c);
                if (!StringUtil.emptyString(f.formula)) {
                    f.formula = f.formula + ", ";
                    f.sumo = f.sumo + " ";
                }
                f.formula = f.formula + newf.formula;
                f.sumo = f.sumo + newf.sumo;
            }
        }
        if (debug) System.out.println("visitThfVariableList() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * thf_variable            : thf_typed_variable | variable;
     */
    public static TPTPFormula visitThfVariable(TptpParser.Thf_variableContext context) {

        if (debug) System.out.println("visitThfVariable(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfVariable() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_typed_variableContext")) {
                f = visitThfTypedVariable((TptpParser.Thf_typed_variableContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$VariableContext") ||
                    c.getClass().getName().equals("org.antlr.v4.runtime.tree.TerminalNodeImpl")) {
                String term = c.getText();
                String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                if (debug) System.out.println("visitThfVariable() term: " + term);
                f.formula = term;
                f.sumo = sumoTerm;
            }
        }
        if (debug) System.out.println("visitThfVariable() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * thf_typed_variable      : variable ':' thf_top_level_type;
     */
    public static TPTPFormula visitThfTypedVariable(TptpParser.Thf_typed_variableContext context) {

        if (debug) System.out.println("visitThfTypedVariable(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitThfTypedVariable() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_top_level_typeContext")) {
                if (debug) System.out.println("visitThfTypedVariable() type: " + ((TptpParser.Thf_top_level_typeContext) c).getText());
                if (debug) System.out.println("visitThfTypedVariable() formula: " + f.formula);
                f.formula = f.formula + " : " + ((TptpParser.Thf_top_level_typeContext) c).getText();
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$VariableContext") ||
                    c.getClass().getName().equals("org.antlr.v4.runtime.tree.TerminalNodeImpl")) {
                if (!c.getText().equals(":")) {
                    String term = c.getText();
                    String sumoTerm = TPTPVisitor.translateSUMOterm(c.getText());
                    if (debug) System.out.println("visitThfTypedVariable() term: " + term);
                    f.formula = term;
                    f.sumo = sumoTerm;
                }
            }
        }
        if (debug) System.out.println("visitThfTypedVariable() returning thf: " + f);
        if (debug) System.out.println("visitThfTypedVariable() returning sumo: " + f.sumo);
        return f;
    }
}
