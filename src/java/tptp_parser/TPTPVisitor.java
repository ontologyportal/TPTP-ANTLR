package tptp_parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.*;

public class TPTPVisitor extends AbstractParseTreeVisitor<String> {

    public static boolean debug = false;
    public Map<String,TPTPFormula> result = new HashMap<>();

    public static Map<String,String> sumoTable = new HashMap<String,String>() {{
        put(">", "greaterThan");
        put("<", "lessThan");
        put(">=", "greaterThanOrEqualTo");
        put("<=", "lessThanOrEqualTo");
        put("<=>", "<=>");
        put("=>", "=>");
        put("@", " ");
        put("=", "equal");
        put("!", "forall");
        put("?", "exists");
        put("~", "not");
        put("$false", "false");
        put("$true", "true");
        put("$greater", "greaterThan");
        put("$less", "lessThan");
        put("$greaterEq", "greaterThanOrEqualTo");
        put("$lessEq", "lessThanOrEqualTo");
        put("$sum", "AdditionFn");
        put("$difference", "SubtractionFn");
        put("$product", "MultiplicationFn");
        put("$quotient", "DivisionFn");
        put("$ceiling", "CeilingFn");
        put("$floor", "FloorFn");
    }};;

    /** ***************************************************************
     */
    public static String translateSUMOterm(String t) {

        if (t == null || t.equals(""))
            return t;
        if (Character.isUpperCase(t.charAt(0)))
            t = "?" + t;
        if (t.contains("_THFTYPE_")) {
            int i = t.indexOf("_THFTYPE_");
            int start = 0;
            if (t.startsWith("l"))
                start = 1;
            t = t.substring(start,i);
        }
        if (t.startsWith("s__"))
            t = t.substring(3);
        if (t.endsWith("__m"))
            t = t.substring(0,t.length()-3);
        if (sumoTable.get(t) != null)
            t = sumoTable.get(t);
        return t;
    }

    /** ***************************************************************
     */
    public void parseFile(String fname) {

        CharStream inputStream = null;
        try {
            inputStream = CharStreams.fromFileName(fname);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        parse_common(inputStream);
    }

    /** ***************************************************************

    public void parseFile(Reader r) {

        CharStream inputStream = null;
        try {
            inputStream = CharStreams.fromFileName(fname);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        parse_common(inputStream);
    }
*/

    /** ***************************************************************
     * Parse a single formula
     * @return a Map that should have a single formula
     */
    public Map<String,TPTPFormula> parseString(String input) {

        if (debug) System.out.println("parseString(): " + input);
        CharStream inputStream = CharStreams.fromString(input);
        parse_common(inputStream);
        Map<String,TPTPFormula> hm = this.result;
        result = hm;
        if (hm == null || hm.values().isEmpty())
            System.err.println("Error in TPTPVisitor.parseString(): no results for input: "  + input);
        return hm;
    }

    /** ***************************************************************
     * Parse a single formula and use this Visitor to process
     * as the cached information for the formula. Copy the formula
     * meta-data to the new formulas.
     * @return a Map that should have a single formula
     */
    public Map<String,TPTPFormula> parseFormula(TPTPFormula input) {

        if (debug) System.out.println(input);
        Map<String,TPTPFormula> hm = parseString(input.getFormula());
        result = hm;
        if (hm != null && !hm.values().isEmpty())
            for (TPTPFormula f : hm.values()) {
                f.startLine = input.startLine;
                f.endLine = input.endLine;
                f.sourceFile = input.sourceFile;
            }
        return hm;
    }

    private void parse_common(CharStream inputStream) {

        TptpLexer tptpLexer = new TptpLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(tptpLexer);
        TptpParser tptpParser = new TptpParser(commonTokenStream);
        TptpParser.Tptp_fileContext fileContext = tptpParser.tptp_file();
        visitFile(fileContext);
    }

    /** ***************************************************************
     * tptp_file               : tptp_input* EOF;
     */
    public void visitFile(TptpParser.Tptp_fileContext context) {

        //if (debug) System.out.println("visitFile() Visiting file: " + context.getText());
        if (debug) System.out.println("visitFile() # children: " + context.children.size());
//        int counter;
        TPTPFormula f;
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFile() child: " + c.getClass().getName());
            if (debug) System.out.println("visitFile() Visiting child: " + c.getText());
            if (c instanceof TptpParser.Tptp_inputContext) {
                f = visitInput((TptpParser.Tptp_inputContext) c);
            }
        }
        if (debug) System.out.println("visitFile() return file: " + result);
        if (debug) System.out.println();
    }

    /** ***************************************************************
     * tptp_input              : annotated_formula | include;
     */
    public TPTPFormula visitInput(TptpParser.Tptp_inputContext context) {

        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitInput() Visiting input: " + context.getText());
        if (debug) System.out.println("visitInput() # children: " + context.children.size());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitInput() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Annotated_formulaContext)
                f = visitFormula((TptpParser.Annotated_formulaContext) c);
        }
        return f;
    }

    /** ***************************************************************
     * annotated_formula       : thf_annotated | tfx_annotated | tff_annotated
     *                         | tcf_annotated | fof_annotated | cnf_annotated
     *                         | tpi_annotated;
     */
    public TPTPFormula visitFormula(TptpParser.Annotated_formulaContext context) {

        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFormula() Visiting formula: " + context.getText());
        if (debug) System.out.println("visitFormula() # children: " + context.children.size());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug)  System.out.println("visitFormula() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Tff_annotatedContext)
                f = visitTFFSent((TptpParser.Tff_annotatedContext) c);
            if (c instanceof TptpParser.Thf_annotatedContext)
                f = visitTHFSent((TptpParser.Thf_annotatedContext) c);
            if (c instanceof TptpParser.Fof_annotatedContext)
                f = visitFOFSent((TptpParser.Fof_annotatedContext) c);
            if (c instanceof TptpParser.Cnf_annotatedContext)
                f = visitCNFSent((TptpParser.Cnf_annotatedContext) c);
            if (f != null) {
                f.startLine = context.getStart().getLine();
                f.endLine = context.getStop().getLine(); // 2/19/25 tdn
                f.sourceFile = context.start.getTokenSource().getSourceName();
            }
        }
        if (debug) System.out.println("visitFormula() return sentence: " + f);
        f.parsedFormula = context;
        return f;
    }

    /** ***************************************************************
     * tff_annotated           : 'tff(' name ',' formula_role ',' tff_formula annotations? ').';
     * Put each formula in the result map
     */
    public TPTPFormula visitTFFSent(TptpParser.Tff_annotatedContext context) {

        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitTFFSent() Visiting tff sentence: " + context.getText());
        if (debug) System.out.println("visitTFFSent() # children: " + context.children.size());
        TPTPFormula f = new TPTPFormula();
        TPTPFormula newf = null, f2;
        f.type = "tff";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTFFSent() child: " + c.getClass().getName());
            if (c instanceof TptpParser.NameContext)
                f.name = ((TptpParser.NameContext) c).getText();
            if (c instanceof TptpParser.Formula_roleContext)
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c instanceof TptpParser.Tff_formulaContext) {
                newf = TFFVisitor.visitTffFormula((TptpParser.Tff_formulaContext) c);
            }
            if (c instanceof TptpParser.AnnotationsContext) {
                f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
                f.supports = f2.supports;
                f.infRule = f2.infRule;
            }
        }
        f.formula = newf.formula;
        f.sumo = newf.sumo;
        if (debug) System.out.println("result: " + f);
        result.put(f.name, f);
        return f;
    }

    /** ***************************************************************
     thf_annotated           : 'thf(' name ',' formula_role ',' thf_formula annotations? ').';
     * Put each formula in the result map
     */
    public TPTPFormula visitTHFSent(TptpParser.Thf_annotatedContext context) {

        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitTHFSent() Visiting thf sentence: " + context.getText());
        if (debug) System.out.println("visitTHFSent() # children: " + context.children.size());
        TPTPFormula f = new TPTPFormula();
        TPTPFormula newf = null, f2;
        f.type = "thf";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTHFSent() child: " + c.getClass().getName());
            if (c instanceof TptpParser.NameContext)
                f.name = ((TptpParser.NameContext) c).getText();
            if (c instanceof TptpParser.Formula_roleContext)
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c instanceof TptpParser.Thf_formulaContext) {
                if (debug) System.out.println("visitTHFSent() found formula ");
                newf = THFVisitor.visitThfFormula((TptpParser.Thf_formulaContext) c);
            }
            if (c instanceof TptpParser.AnnotationsContext) {
                f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
                f.supports = f2.supports;
                f.infRule = f2.infRule;
            }
        }
        f.formula = newf.formula;
        f.sumo = newf.sumo;
        if (debug) System.out.println("result: " + f);
        result.put(f.name, f);
        return f;
    }

    /** ***************************************************************
     * fof_annotated           : 'fof(' name ',' formula_role ',' fof_formula annotations? ').';
     */
    public TPTPFormula visitFOFSent(TptpParser.Fof_annotatedContext context) {

        TPTPFormula f = new TPTPFormula();
        f.type = "fof";
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("\nvisitFOFSent() Visiting fof sentence: " + context.getText());
        if (debug) System.out.println("visitFOFSent() # children: " + context.children.size());

        TPTPFormula newf, f2;
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFOFSent() child: " + c.getClass().getName());
            if (c instanceof TptpParser.NameContext)
                f.name = ((TptpParser.NameContext) c).getText();
            if (c instanceof TptpParser.Formula_roleContext)
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c instanceof TptpParser.Fof_formulaContext) {
                newf = visitFofFormula((TptpParser.Fof_formulaContext) c);
                f.formula = newf.formula;
                f.sumo = newf.sumo;
            }
            if (c instanceof TptpParser.AnnotationsContext){
                f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
                f.supports = f2.supports;
                f.infRule = f2.infRule;
            }
        }
        result.put(f.name, f);
        return f;
    }


    /** ***************************************************************
     * cnf_annotated           : 'cnf(' name ',' formula_role ',' cnf_formula annotations? ').';
     */
    public TPTPFormula visitCNFSent(TptpParser.Cnf_annotatedContext context) {

        TPTPFormula f = new TPTPFormula();
        f.type = "cnf";
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitCNFSent(): " + context.getText());
        if (debug) System.out.println("visitCNFSent() # children: " + context.children.size());
        if (debug) System.out.println("visitCNFSent() text: " + context.getText());

        TPTPFormula cnf, f2;
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitCNFSent() child: " + c.getClass().getName());
            if (c instanceof TptpParser.NameContext)
                f.name = ((TptpParser.NameContext) c).getText();
            if (c instanceof TptpParser.Formula_roleContext)
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c instanceof TptpParser.Cnf_formulaContext) {
                cnf = CNFVisitor.visitCNFFormula((TptpParser.Cnf_formulaContext) c);
                f.formula = cnf.formula;
                f.sumo = cnf.sumo;
            }
            if (c instanceof TptpParser.AnnotationsContext){
                f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
                f.supports = f2.supports;
                f.infRule = f2.infRule;
            }
        }
        result.put(f.name, f);
        return f;
    }

    /** ***************************************************************
     * annotations             : ',' source optional_info?;
     */
    public static TPTPFormula visitAnnotations(TptpParser.AnnotationsContext context) {

        if (debug) System.out.println("visitAnnotations() " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitAnnotations() child: " + c.getClass().getName());
            if (c instanceof TptpParser.SourceContext)
                f = visitSource((TptpParser.SourceContext) c);
        }
        return f;
    }

    /** ***************************************************************
     * source                  : dag_source | internal_source
     *                         | external_source
     *                         | Lower_word // #RES | 'unknown'
     *                         | '[' sources ']';
     */
    public static TPTPFormula visitSource(TptpParser.SourceContext context) {

        if (debug) System.out.println("visitSource(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        if (context.Lower_word() != null)
            f.supports.add(context.Lower_word().toString());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitSource() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Dag_sourceContext)
                f = visitDagSource((TptpParser.Dag_sourceContext) c);
            if (c instanceof TptpParser.Internal_sourceContext)
                f = visitInternalSource((TptpParser.Internal_sourceContext) c);
            if (c instanceof TptpParser.External_sourceContext)
                f = visitExternalSource((TptpParser.External_sourceContext) c);
        }
        if (debug) System.out.println("visitSource() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * dag_source              : name | inference_record;
     */
    public static TPTPFormula visitDagSource(TptpParser.Dag_sourceContext context) {

        if (debug) System.out.println("visitDagSource(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitDagSource() child: " + c.getClass().getName());
            if (c instanceof TptpParser.NameContext)
                f.supports.add(((TptpParser.NameContext) c).getText());
            if (c instanceof TptpParser.Inference_recordContext)
                f = visitInferenceRecordSource((TptpParser.Inference_recordContext) c);
        }
        if (debug) System.out.println("visitDagSource() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * internal_source         : 'introduced(' intro_type optional_info? ')';
     */
    public static TPTPFormula visitInternalSource(TptpParser.Internal_sourceContext context) {

        if (debug) System.out.println("visitInternalSource(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitInternalSource() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Intro_typeContext)
                f.infRule = ((TptpParser.Intro_typeContext) c).getText();
        }
        if (debug) System.out.println("visitInternalSource() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * external_source          : file_source | theory | creator_source;
     */
    public static TPTPFormula visitExternalSource(TptpParser.External_sourceContext context) {

        if (debug) System.out.println("visitExternalSource(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitExternalSource() child: " + c.getClass().getName());
            if (c instanceof TptpParser.File_sourceContext)
                f.infRule = ((TptpParser.File_sourceContext) c).getText();
            if (c instanceof TptpParser.TheoryContext)
                f.infRule = ((TptpParser.TheoryContext) c).getText();
            if (c instanceof TptpParser.Creator_sourceContext)
                f.infRule = ((TptpParser.Creator_sourceContext) c).getText();
        }
        if (debug) System.out.println("visitExternalSource() returning: " + f);
        return f;
    }
    /** ***************************************************************
     * inference_record        : 'inference(' inference_rule ',' useful_info ',' inference_parents ')';
     */
    public static TPTPFormula visitInferenceRecordSource(TptpParser.Inference_recordContext context) {

        if (debug) System.out.println("visitInferenceRecordSource(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitInferenceRecordSource() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Inference_ruleContext)
                f.infRule = (((TptpParser.Inference_ruleContext) c).getText());
            if (c instanceof TptpParser.Inference_parentsContext)
                f.supports = visitInferenceParents((TptpParser.Inference_parentsContext) c).supports;
        }
        if (debug) System.out.println("visitInferenceRecordSource() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * inference_parents       : '[]' | '[' parent_list ']';
     */
    public static TPTPFormula visitInferenceParents(TptpParser.Inference_parentsContext context) {

        if (debug) System.out.println("visitInferenceParents(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitInferenceParents() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Parent_listContext) {
                f.supports.addAll(visitParentList((TptpParser.Parent_listContext) c).supports);
                if (debug) System.out.println("visitInferenceParents() f: " + f);
            }
        }
        if (debug) System.out.println("visitInferenceParents() returning: " + f);
        return f;
    }

    /** ***************************************************************
     parent_list             : parent_info ( ',' parent_info )*; // #INFO flattened
     */
    public static TPTPFormula visitParentList(TptpParser.Parent_listContext context) {

        if (debug) System.out.println("visitParentList(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitParentList() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Parent_infoContext)
                f.supports.addAll(visitParentInfo((TptpParser.Parent_infoContext) c).supports);
        }
        if (debug) System.out.println("visitParentList() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * parent_info             : source parent_details?; // #INFO ? because parent_details may be empty
     */
    public static TPTPFormula visitParentInfo(TptpParser.Parent_infoContext context) {

        if (debug) System.out.println("visitParentInfo(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitParentInfo() child: " + c.getClass().getName());
            if (c instanceof TptpParser.SourceContext) {
                f.supports.add(((TptpParser.SourceContext) c).getText());
                if (debug) System.out.println("visitParentInfo() f: " + f);
            }
        }
        if (debug) System.out.println("visitParentInfo() returning: " + f);
        return f;
    }

    /** ***************************************************************
     * fof_formula             : fof_logic_formula | fof_sequent;  // no use of fof_sequent for SUMO
     */
    public static TPTPFormula visitFofFormula(TptpParser.Fof_formulaContext context) {

        if (debug) System.out.println("visitFofFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofFormula() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Fof_logic_formulaContext) {
                f = visitFofLogicFormula((TptpParser.Fof_logic_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitFofFormula() returning: " + f);
        if (debug) System.out.println("visitFofFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_logic_formula       : fof_binary_formula | fof_unitary_formula;
     */
    public static TPTPFormula visitFofLogicFormula(TptpParser.Fof_logic_formulaContext context) {

        if (debug) System.out.println("visitFofLogicFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofLogicFormula() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Fof_binary_formulaContext) {
                f = visitFofBinaryFormula((TptpParser.Fof_binary_formulaContext) c);
            }
            if (c instanceof TptpParser.Fof_unitary_formulaContext) {
                f = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
            }
        }
        if (debug) System.out.println("visitFofLogicFormula() returning: " + f);
        if (debug) System.out.println("visitFofLogicFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_binary_formula      : fof_binary_nonassoc | fof_binary_assoc;
     */
    public static TPTPFormula visitFofBinaryFormula(TptpParser.Fof_binary_formulaContext context) {

        if (debug) System.out.println("visitFofBinaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofBinaryFormula() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Fof_binary_nonassocContext) {
                f = visitFofBinaryNonassoc((TptpParser.Fof_binary_nonassocContext) c);
            }
            if (c instanceof TptpParser.Fof_binary_assocContext) {
                f = visitFofBinaryAssoc((TptpParser.Fof_binary_assocContext) c);
            }
        }
        if (debug) System.out.println("visitFofBinaryFormula() returning: " + f);
        if (debug) System.out.println("visitFofBinaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_binary_nonassoc     : fof_unitary_formula binary_connective fof_unitary_formula;
     */
    public static TPTPFormula visitFofBinaryNonassoc(TptpParser.Fof_binary_nonassocContext context) {

        if (debug) System.out.println("visitFofBinaryNonassoc(): " + context.getText());
        TPTPFormula f = new TPTPFormula(),newf;
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofBinaryNonassoc() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Fof_unitary_formulaContext) {
                newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
                if (f.formula.equals("")) {
                    f.formula = newf.formula;
                    f.sumo = newf.sumo;
                }
                else {
                    f.formula = f.formula + conn + newf.formula;
                    f.sumo = "(" + conn + " " + f.sumo + " " + newf.sumo + ")";
                }
            }
            if (c instanceof TptpParser.Binary_connectiveContext) {
                conn = ((TptpParser.Binary_connectiveContext) c).getText();
            }
        }
        if (debug) System.out.println("visitFofBinaryNonassoc() returning: " + f);
        if (debug) System.out.println("visitFofBinaryNonassoc() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_binary_assoc        : fof_or_formula | fof_and_formula;
     */
    public static TPTPFormula visitFofBinaryAssoc(TptpParser.Fof_binary_assocContext context) {

        if (debug) System.out.println("visitFofBinaryAssoc(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofBinaryAssoc() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Fof_or_formulaContext) {
                f = visitFofOrFormula((TptpParser.Fof_or_formulaContext) c);
//                f.sumo = f.sumo + ")";
            }
            if (c instanceof TptpParser.Fof_and_formulaContext) {
                f = visitFofAndFormula((TptpParser.Fof_and_formulaContext) c);
//                f.sumo = f.sumo + ")";
            }
        }
        if (debug) System.out.println("visitFofBinaryAssoc() returning: " + f);
        if (debug) System.out.println("visitFofBinaryAssoc() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_or_formula          : fof_unitary_formula Or fof_unitary_formula
     *                         | fof_or_formula Or fof_unitary_formula;
     */
//    public static TPTPFormula visitFofOrFormula(TptpParser.Fof_or_formulaContext context) {
//
//        if (debug) System.out.println("visitFofOrFormula(): " + context.getText());
//        TPTPFormula f = new TPTPFormula(), newf;
//        for (ParseTree c : context.children) {
//            if (debug) System.out.println("visitFofOrFormula() child: " + c.getClass().getName());
//            if (c instanceof TptpParser.Fof_unitary_formulaContext) {
//                newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
//                if (f.formula.equals("")) {
//                    f.formula = newf.formula;
//                    f.sumo = newf.sumo;
//                }
//                else {
//                    f.formula = f.formula + "|" + newf.formula;
//                    if (f.sumo.startsWith("(or"))
//                        f.sumo = f.sumo + " " + newf.sumo;
//                    else
//                        f.sumo = "(or " + f.sumo + " " + newf.sumo;
//                }
//            }
//            if (c instanceof TptpParser.Fof_or_formulaContext) {
//                newf = visitFofOrFormula((TptpParser.Fof_or_formulaContext) c);
//                if (f.formula.equals("")) {
//                    f.formula = newf.formula;
//                    f.sumo = newf.sumo;
//                }
//                else {
//                    f.formula = f.formula + "|" + newf.formula;
//                    if (f.sumo.startsWith("(or"))
//                        f.sumo = f.sumo + " " + newf.sumo;
//                    else
//                        f.sumo = "(or " + f.sumo + " " + newf.sumo;
//                }
//            }
//        }
//        if (debug) System.out.println("visitFofOrFormula() returning: " + f);
//        if (debug) System.out.println("visitFofOrFormula() returning sumo: " + f.sumo);
//        return f;
//    }

    /**
     * Translates a TPTP conjunction (fof_and_formula) into a SUO-KIF expression.
     *
     * <p>
     * This method constructs a <b>single, well-formed</b> SUO-KIF {@code (and ...)} expression
     * while preserving the associative semantics of conjunction. In particular, it:
     * </p>
     *
     * <ul>
     *   <li>Returns a complete KIF expression, including balanced parentheses
     *       (i.e., this method both opens and closes {@code (and ...)}).</li>
     *
     *   <li>Delays introducing the {@code (and ...)} wrapper until a second conjunct
     *       is encountered, so a single conjunct is returned unchanged.</li>
     *
     *   <li><b>Flattens nested conjunctions</b>, producing
     *       {@code (and A B C)} instead of {@code (and (and A B) C)}.</li>
     *
     *   <li>Handles nested conjunctions that appear either as explicit
     *       {@code fof_and_formula} nodes <em>or</em> as parenthesized sub-formulas
     *       returned from {@code visitFofUnitaryFormula()}.</li>
     * </ul>
     *
     * <p>
     * Flattening is implemented by detecting {@code (and ...)} sub-expressions,
     * extracting their top-level arguments using {@code splitTopLevelKifArgs()},
     * and splicing those arguments directly into the current conjunction.
     * </p>
     *
     * <p>
     * This design avoids malformed output and ensures that conjunctions are
     * always represented as a single top-level KIF expression, which is required
     * for correct handling under quantifiers and other higher-level constructs.
     * </p>
     *
     * @param context the ANTLR parse context for a TPTP conjunction
     * @return a {@link TPTPFormula} whose {@code sumo} field contains a valid,
     *         flattened SUO-KIF {@code (and ...)} expression
     */
    public static TPTPFormula visitFofOrFormula(TptpParser.Fof_or_formulaContext context) {

        if (debug) System.out.println("visitFofOrFormula(): " + context.getText());

        TPTPFormula f = new TPTPFormula();
        StringBuilder sumo = new StringBuilder();
        StringBuilder tptp = new StringBuilder();

        boolean openedOr = false;   // did we already write "(or " ?
        int disjCount = 0;          // number of disjuncts appended

        for (ParseTree c : context.children) {

            if (debug) System.out.println("visitFofOrFormula() child: " + c.getClass().getName());

            // 1) Unitary disjunct
            if (c instanceof TptpParser.Fof_unitary_formulaContext) {

                TPTPFormula newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);

                // TPTP-side reconstruction (best-effort)
                if (tptp.length() > 0) tptp.append("|");
                tptp.append(newf.formula);

                // IMPORTANT: unitary formula may already be "(or ...)" due to parentheses/grouping
                String ns = (newf.sumo == null) ? "" : newf.sumo.trim();

                if (ns.startsWith("(or ") && ns.endsWith(")")) {
                    String inside = ns.substring(4, ns.length() - 1).trim(); // remove "(or " and trailing ")"
                    java.util.List<String> args = splitTopLevelKifArgs(inside);

                    if (!args.isEmpty()) {
                        for (String a : args) {
                            disjCount++;

                            if (disjCount == 2 && !openedOr) {
                                String first = sumo.toString().trim();
                                if (first.isEmpty()) {
                                    sumo.setLength(0);
                                    sumo.append("(or ");
                                } else {
                                    sumo.setLength(0);
                                    sumo.append("(or ").append(first).append(" ");
                                }
                                openedOr = true;
                            }

                            if (openedOr) sumo.append(a).append(" ");
                            else {
                                sumo.setLength(0);
                                sumo.append(a);
                            }
                        }
                        continue; // done splicing
                    }
                    // fall through: if split fails, treat ns as atomic disjunct
                }

                // Atomic disjunct path
                disjCount++;

                if (disjCount == 2 && !openedOr) {
                    String first = sumo.toString().trim();
                    if (first.isEmpty()) {
                        sumo.setLength(0);
                        sumo.append("(or ");
                    } else {
                        sumo.setLength(0);
                        sumo.append("(or ").append(first).append(" ");
                    }
                    openedOr = true;
                }

                if (openedOr) sumo.append(ns).append(" ");
                else {
                    sumo.setLength(0);
                    sumo.append(ns);
                }

                continue;
            }

            // 2) Nested OR context (keep it; depending on grammar you might still see it)
            if (c instanceof TptpParser.Fof_or_formulaContext) {

                TPTPFormula newf = visitFofOrFormula((TptpParser.Fof_or_formulaContext) c);

                // TPTP-side reconstruction
                if (tptp.length() > 0) tptp.append("|");
                tptp.append(newf.formula);

                String ns = (newf.sumo == null) ? "" : newf.sumo.trim();

                if (ns.startsWith("(or ") && ns.endsWith(")")) {
                    String inside = ns.substring(4, ns.length() - 1).trim();
                    java.util.List<String> args = splitTopLevelKifArgs(inside);

                    if (!args.isEmpty()) {
                        for (String a : args) {
                            disjCount++;

                            if (disjCount == 2 && !openedOr) {
                                String first = sumo.toString().trim();
                                if (first.isEmpty()) {
                                    sumo.setLength(0);
                                    sumo.append("(or ");
                                } else {
                                    sumo.setLength(0);
                                    sumo.append("(or ").append(first).append(" ");
                                }
                                openedOr = true;
                            }

                            if (openedOr) sumo.append(a).append(" ");
                            else {
                                sumo.setLength(0);
                                sumo.append(a);
                            }
                        }
                        continue;
                    }
                    // fall through if split fails
                }

                // Atomic disjunct path
                disjCount++;

                if (disjCount == 2 && !openedOr) {
                    String first = sumo.toString().trim();
                    if (first.isEmpty()) {
                        sumo.setLength(0);
                        sumo.append("(or ");
                    } else {
                        sumo.setLength(0);
                        sumo.append("(or ").append(first).append(" ");
                    }
                    openedOr = true;
                }

                if (openedOr) sumo.append(ns).append(" ");
                else {
                    sumo.setLength(0);
                    sumo.append(ns);
                }
            }
        }

        f.formula = tptp.toString();

        // Close "(or ...)" if we opened it.
        if (openedOr) {
            int len = sumo.length();
            if (len > 0 && Character.isWhitespace(sumo.charAt(len - 1))) sumo.setLength(len - 1);
            sumo.append(")");
        }

        f.sumo = sumo.toString();

        if (debug) System.out.println("visitFofOrFormula() returning: " + f);
        if (debug) System.out.println("visitFofOrFormula() returning sumo: " + f.sumo);

        return f;
    }


    /** ***************************************************************
     * fof_and_formula         : fof_unitary_formula And fof_unitary_formula
     *                         | fof_and_formula And fof_unitary_formula;
     */
//    public static TPTPFormula visitFofAndFormula(TptpParser.Fof_and_formulaContext context) {
//
//        if (debug) System.out.println("visitFofAndFormula(): " + context.getText());
//        TPTPFormula f = new TPTPFormula(), newf;
//        for (ParseTree c : context.children) {
//            if (debug) System.out.println("visitFofAndFormula() child: " + c.getClass().getName());
//            if (c instanceof TptpParser.Fof_unitary_formulaContext) {
//                newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
//                if (f.formula.equals("")) {
//                    f.formula = newf.formula;
//                    f.sumo = newf.sumo;
//                }
//                else {
//                    f.formula = f.formula + "&" + newf.formula;
//                    if (f.sumo.startsWith("(and"))
//                        f.sumo = f.sumo + " " + newf.sumo;
//                    else
//                        f.sumo = "(and " + f.sumo + " " + newf.sumo;
//                }
//            }
//            if (c instanceof TptpParser.Fof_and_formulaContext) {
//                newf = visitFofAndFormula((TptpParser.Fof_and_formulaContext) c);
//                if (f.formula.equals("")) {
//                    f.formula = newf.formula;
//                    f.sumo = newf.sumo;
//                }
//                else {
//                    f.formula = f.formula + "&" + newf.formula;
//                    if (f.sumo.startsWith("(and"))
//                        f.sumo = f.sumo + " " + newf.sumo;
//                    else
//                        f.sumo = "(and " + f.sumo + " " + newf.sumo;
//                }
//            }
//        }
//        if (debug) System.out.println("visitFofAndFormula() returning: " + f);
//        if (debug) System.out.println("visitFofAndFormula() returning sumo: " + f.sumo);
//        return f;
//    }

    /**
     * Translates a TPTP conjunction (fof_and_formula) into a SUO-KIF expression.
     *
     * <p>
     * This method constructs a <b>single, well-formed</b> SUO-KIF {@code (and ...)} expression
     * while preserving the associative semantics of conjunction. In particular, it:
     * </p>
     *
     * <ul>
     *   <li>Returns a complete KIF expression, including balanced parentheses
     *       (i.e., this method both opens and closes {@code (and ...)}).</li>
     *
     *   <li>Delays introducing the {@code (and ...)} wrapper until a second conjunct
     *       is encountered, so a single conjunct is returned unchanged.</li>
     *
     *   <li><b>Flattens nested conjunctions</b>, producing
     *       {@code (and A B C)} instead of {@code (and (and A B) C)}.</li>
     *
     *   <li>Handles nested conjunctions that appear either as explicit
     *       {@code fof_and_formula} nodes <em>or</em> as parenthesized sub-formulas
     *       returned from {@code visitFofUnitaryFormula()}.</li>
     * </ul>
     *
     * <p>
     * Flattening is implemented by detecting {@code (and ...)} sub-expressions,
     * extracting their top-level arguments using {@code splitTopLevelKifArgs()},
     * and splicing those arguments directly into the current conjunction.
     * </p>
     *
     * <p>
     * This design avoids malformed output and ensures that conjunctions are
     * always represented as a single top-level KIF expression, which is required
     * for correct handling under quantifiers and other higher-level constructs.
     * </p>
     *
     * @param context the ANTLR parse context for a TPTP conjunction
     * @return a {@link TPTPFormula} whose {@code sumo} field contains a valid,
     *         flattened SUO-KIF {@code (and ...)} expression
     */
    public static TPTPFormula visitFofAndFormula(TptpParser.Fof_and_formulaContext context) {

        if (debug) System.out.println("visitFofAndFormula(): " + context.getText());

        TPTPFormula f = new TPTPFormula();
        StringBuilder sumo = new StringBuilder();
        StringBuilder tptp = new StringBuilder();

        boolean openedAnd = false;
        int conjunctCount = 0;

        for (ParseTree c : context.children) {

            // 1) Unitary conjunct
            if (c instanceof TptpParser.Fof_unitary_formulaContext) {

                TPTPFormula part = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);

                if (tptp.length() > 0) tptp.append("&");
                tptp.append(part.formula);

                // IMPORTANT: a unitary formula can already be "(and ...)" (due to parentheses/grouping).
                String ns = (part.sumo == null) ? "" : part.sumo.trim();

                if (ns.startsWith("(and ") && ns.endsWith(")")) {
                    String inside = ns.substring(5, ns.length() - 1).trim();
                    java.util.List<String> args = splitTopLevelKifArgs(inside);

                    if (!args.isEmpty()) {
                        for (String a : args) {
                            conjunctCount++;

                            if (conjunctCount == 2 && !openedAnd) {
                                String first = sumo.toString().trim();
                                if (first.isEmpty()) {
                                    sumo.setLength(0);
                                    sumo.append("(and ");
                                } else {
                                    sumo.setLength(0);
                                    sumo.append("(and ").append(first).append(" ");
                                }
                                openedAnd = true;
                            }

                            if (openedAnd) sumo.append(a).append(" ");
                            else {
                                sumo.setLength(0);
                                sumo.append(a);
                            }
                        }
                        continue; // done splicing
                    }
                    // fall through: if split fails, treat as atomic conjunct
                }

                // Atomic conjunct path
                conjunctCount++;

                if (conjunctCount == 2 && !openedAnd) {
                    String first = sumo.toString().trim();
                    if (first.isEmpty()) {
                        sumo.setLength(0);
                        sumo.append("(and ");
                    } else {
                        sumo.setLength(0);
                        sumo.append("(and ").append(first).append(" ");
                    }
                    openedAnd = true;
                }

                if (openedAnd) sumo.append(ns).append(" ");
                else {
                    sumo.setLength(0);
                    sumo.append(ns);
                }

                continue;
            }

            // 2) Nested AND context (rare depending on grammar, but keep it)
            if (c instanceof TptpParser.Fof_and_formulaContext) {

                TPTPFormula nested = visitFofAndFormula((TptpParser.Fof_and_formulaContext) c);

                if (tptp.length() > 0) tptp.append("&");
                tptp.append(nested.formula);

                String ns = (nested.sumo == null) ? "" : nested.sumo.trim();

                if (ns.startsWith("(and ") && ns.endsWith(")")) {
                    String inside = ns.substring(5, ns.length() - 1).trim();
                    java.util.List<String> args = splitTopLevelKifArgs(inside);

                    if (!args.isEmpty()) {
                        for (String a : args) {
                            conjunctCount++;

                            if (conjunctCount == 2 && !openedAnd) {
                                String first = sumo.toString().trim();
                                if (first.isEmpty()) {
                                    sumo.setLength(0);
                                    sumo.append("(and ");
                                } else {
                                    sumo.setLength(0);
                                    sumo.append("(and ").append(first).append(" ");
                                }
                                openedAnd = true;
                            }

                            if (openedAnd) sumo.append(a).append(" ");
                            else {
                                sumo.setLength(0);
                                sumo.append(a);
                            }
                        }
                        continue;
                    }
                    // fall through if split fails
                }

                // Atomic conjunct path
                conjunctCount++;

                if (conjunctCount == 2 && !openedAnd) {
                    String first = sumo.toString().trim();
                    if (first.isEmpty()) {
                        sumo.setLength(0);
                        sumo.append("(and ");
                    } else {
                        sumo.setLength(0);
                        sumo.append("(and ").append(first).append(" ");
                    }
                    openedAnd = true;
                }

                if (openedAnd) sumo.append(ns).append(" ");
                else {
                    sumo.setLength(0);
                    sumo.append(ns);
                }
            }
        }

        f.formula = tptp.toString();

        if (openedAnd) {
            int len = sumo.length();
            if (len > 0 && Character.isWhitespace(sumo.charAt(len - 1))) sumo.setLength(len - 1);
            sumo.append(")");
        }

        f.sumo = sumo.toString();

        if (debug) System.out.println("visitFofAndFormula() returning sumo: " + f.sumo);
        return f;
    }


    private static java.util.List<String> splitTopLevelKifArgs(String s) {
        java.util.List<String> out = new java.util.ArrayList<>();
        if (s == null) return out;

        String str = s.trim();
        if (str.isEmpty()) return out;

        int depth = 0;
        int start = -1;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(') {
                if (depth == 0) start = i;
                depth++;
            }
            else if (ch == ')') {
                depth--;
                if (depth == 0 && start >= 0) {
                    out.add(str.substring(start, i + 1).trim());
                    start = -1;
                }
            }
            else if (Character.isWhitespace(ch)) {
                // ignore whitespace separators at depth 0
            }
            else {
                // Handle atomic args at depth 0 (rare in your output, but safe)
                if (depth == 0 && start < 0) {
                    int j = i;
                    while (j < str.length() && !Character.isWhitespace(str.charAt(j))) j++;
                    out.add(str.substring(i, j).trim());
                    i = j - 1;
                }
            }
        }

        // If something went wrong (unbalanced parentheses), fall back to “whole string”
        if (out.isEmpty() && !str.isEmpty()) out.add(str);
        return out;
    }




    /** ***************************************************************
     * fof_unitary_formula     : fof_quantified_formula | fof_unary_formula
     *                         | fof_atomic_formula | '(' fof_logic_formula ')';
     */
    public static TPTPFormula visitFofUnitaryFormula(TptpParser.Fof_unitary_formulaContext context) {

        if (debug) System.out.println("visitFofUnitaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofUnitaryFormula() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Fof_quantified_formulaContext) {
                f = visitFofQuantifiedFormula((TptpParser.Fof_quantified_formulaContext) c);
            }
            if (c instanceof TptpParser.Fof_unary_formulaContext) {
                f = visitFofUnaryFormula((TptpParser.Fof_unary_formulaContext) c);
            }
            if (c instanceof TptpParser.Fof_atomic_formulaContext) {
                f = CNFVisitor.visitFofAtomicFormula((TptpParser.Fof_atomic_formulaContext) c);
            }
            if (c instanceof TptpParser.Fof_logic_formulaContext) {
                f = visitFofLogicFormula((TptpParser.Fof_logic_formulaContext) c);
                f.formula = "(" + f.formula + ")";
            }
        }
        if (debug) System.out.println("visitFofUnitaryFormula() returning: " + f);
        if (debug) System.out.println("visitFofUnitaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_unary_formula       : unary_connective fof_unitary_formula | fof_infix_unary;
     */
    public static TPTPFormula visitFofUnaryFormula(TptpParser.Fof_unary_formulaContext context) {

        if (debug) System.out.println("visitFofUnaryFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula(), newf;
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofUnaryFormula() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Unary_connectiveContext) { // fix for issue #13
                conn = ((TptpParser.Unary_connectiveContext) c).getText();
            }
            if (c instanceof TptpParser.Fof_unitary_formulaContext) {
                newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
                f.formula = conn + newf.formula;
                f.sumo = "(not " + newf.sumo + ")";
            }
            if (c instanceof TptpParser.Fof_infix_unaryContext) {
                f = CNFVisitor.visitFofInfixUnary((TptpParser.Fof_infix_unaryContext) c);
            }
        }
        if (debug) System.out.println("visitFofUnaryFormula() returning: " + f);
        if (debug) System.out.println("visitFofUnaryFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_quantified_formula  : fof_quantifier '[' fof_variable_list ']' ':' fof_unitary_formula;
     */
    public static TPTPFormula visitFofQuantifiedFormula(TptpParser.Fof_quantified_formulaContext context) {

        if (debug) System.out.println("visitFofQuantifiedFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula(), newf;
        String quant;
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofQuantifiedFormula() child: " + c.getClass().getName());
            if (c instanceof TptpParser.Fof_unitary_formulaContext) {
                newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
                f.formula = f.formula + newf.formula;
                f.sumo = f.sumo + newf.sumo + ")";
            }
            if (c instanceof TptpParser.Fof_quantifierContext) {
                quant = ((TptpParser.Fof_quantifierContext) c).getText();
                f.formula = quant;
                f.sumo = "(" + sumoTable.get(quant);
            }
            if (c instanceof TptpParser.Fof_variable_listContext) {
                newf = visitFofVariableList((TptpParser.Fof_variable_listContext) c);
                f.formula = f.formula + "[" + newf.formula + "] : ";
                f.sumo = f.sumo + " (" + newf.sumo + ") ";
            }
        }
        if (debug) System.out.println("visitFofQuantifiedFormula() returning: " + f);
        if (debug) System.out.println("visitFofQuantifiedFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_variable_list       : variable (',' variable)*;
     */
    public static TPTPFormula visitFofVariableList(TptpParser.Fof_variable_listContext context) {

        if (debug) System.out.println("visitFofVariableList(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String term, sumoTerm;
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofVariableList() child: " + c.getClass().getName());
            if (c instanceof TptpParser.VariableContext) {
                term = c.getText();
                sumoTerm = translateSUMOterm(c.getText());
                if (debug) System.out.println("visitFofInfixUnary() term: " + term);
                if (f.formula.equals("")) {
                    f.formula = term;
                    f.sumo = sumoTerm;
                }
                else {
                    f.formula = f.formula + "," + term;
                    f.sumo = f.sumo + " " + sumoTerm;
                }
            }
        }
        if (debug) System.out.println("visitFofVariableList() returning: " + f);
        if (debug) System.out.println("visitFofVariableList() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     */
    public static void showHelp() {

        System.out.println("TPTPVisitor class");
        System.out.println("  options:");
        System.out.println("  -h - show this help screen");
        System.out.println("  -f <fname> - parse the file");
    }

    /** ***************************************************************
     */
    public static void main(String[] args) {

        System.out.println("INFO in TPTPVisitor.main()");
        if (args != null && args.length > 0 && args[0].equals("-h"))
            showHelp();
        else if (args != null && args.length > 1 && args[0].equals("-f")) {
            System.out.println("INFO in TPTPVisitor.main(): parse file: " + args[1]);
            TPTPVisitor sv = new TPTPVisitor();
            sv.parseFile(args[1]);
            Map<String,TPTPFormula> hm = sv.result;
            for (String s : hm.keySet()) {
                System.out.println(hm.get(s));
                System.out.println("\t" + hm.get(s).sumo + "\n");
            }
        }
        else
            showHelp();
    }
}

