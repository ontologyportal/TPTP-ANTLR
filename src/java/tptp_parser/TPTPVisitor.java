package tptp_parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

public class TPTPVisitor extends AbstractParseTreeVisitor<String> {

    public static boolean debug = false;
    public HashMap<String,TPTPFormula> result = new HashMap<>();

    public static HashMap<String,String> sumoTable = new HashMap<String,String>() {{
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

        CodePointCharStream inputStream = null;
        try {
            inputStream = (CodePointCharStream) CharStreams.fromFileName(fname);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        TptpLexer tptpLexer = new TptpLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(tptpLexer);
        TptpParser tptpParser = new TptpParser(commonTokenStream);
        TptpParser.Tptp_fileContext fileContext = tptpParser.tptp_file();
        visitFile(fileContext);
    }

    /** ***************************************************************

    public void parseFile(Reader r) {

        CodePointCharStream inputStream = null;
        try {
            inputStream = (CodePointCharStream) CharStreams.fromFileName(fname);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        TptpLexer tptpLexer = new TptpLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(tptpLexer);
        TptpParser tptpParser = new TptpParser(commonTokenStream);
        TptpParser.Tptp_fileContext fileContext = tptpParser.tptp_file();
        visitFile(fileContext);
    }
*/

    /** ***************************************************************
     * Parse a single formula
     * @return a Map that should have a single formula
     */
    public HashMap<String,TPTPFormula> parseString(String input) {

        if (debug) System.out.println("parseString(): " + input);
        CodePointCharStream inputStream = CharStreams.fromString(input);
        TptpLexer tptpLexer = new TptpLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(tptpLexer);
        TptpParser tptpParser = new TptpParser(commonTokenStream);
        TptpParser.Tptp_fileContext fileContext = tptpParser.tptp_file();
        TPTPVisitor visitor = new TPTPVisitor();
        visitor.visitFile(fileContext);
        HashMap<String,TPTPFormula> hm = visitor.result;
        result = hm;
        if (hm == null || hm.values().size() == 0)
            System.out.println("Error in TPTPVisitor.parseString(): no results for input: "  + input);
        return hm;
    }

    /** ***************************************************************
     * Parse a single formula and use this Visitor to process
     * as the cached information for the formula. Copy the formula
     * meta-data to the new formulas.
     * @return a Map that should have a single formula
     */
    public HashMap<String,TPTPFormula> parseFormula(TPTPFormula input) {

        if (debug) System.out.println(input);
        CodePointCharStream inputStream = CharStreams.fromString(input.getFormula());
        TptpLexer tptpLexer = new TptpLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(tptpLexer);
        TptpParser tptpParser = new TptpParser(commonTokenStream);
        TptpParser.Tptp_fileContext fileContext = tptpParser.tptp_file();
        TPTPVisitor visitor = new TPTPVisitor();
        visitor.visitFile(fileContext);
        HashMap<String,TPTPFormula> hm = visitor.result;
        result = hm;
        if (hm == null || hm.values().size() == 0)
            System.out.println("Error in TPTPVisitor.parseString(): no results for input: "  + input);
        else {
            for (TPTPFormula f : hm.values()) {
                f.startLine = input.startLine;
                f.endLine = input.endLine;
                f.sourceFile = input.sourceFile;
            }
        }
        return hm;
    }

    /** ***************************************************************
     * tptp_file               : tptp_input* EOF;
     */
    public void visitFile(TptpParser.Tptp_fileContext context) {

        //if (debug) System.out.println("visitFile() Visiting file: " + context.getText());
        if (debug) System.out.println("visitFile() # children: " + context.children.size());
        int counter = 0;
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFile() child: " + c.getClass().getName());
            if (debug) System.out.println("visitFile() Visiting child: " + c.getText());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tptp_inputContext")) {
                TPTPFormula f = null;
                f = visitInput((TptpParser.Tptp_inputContext) c);
            }
        }
        if (debug) System.out.println("visitFile() return file: " + result);
        if (debug) System.out.println();
        return;
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Annotated_formulaContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_annotatedContext"))
                f = visitTFFSent((TptpParser.Tff_annotatedContext) c);
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_annotatedContext"))
                f = visitTHFSent((TptpParser.Thf_annotatedContext) c);
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_annotatedContext"))
                f = visitFOFSent((TptpParser.Fof_annotatedContext) c);
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Cnf_annotatedContext"))
                f = visitCNFSent((TptpParser.Cnf_annotatedContext) c);
            if (f != null) {
                f.startLine = context.getStart().getLine();
                f.startLine = context.getStop().getLine();
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
        TPTPFormula newf = null;
        f.type = "tff";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTFFSent() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$NameContext"))
                f.name = ((TptpParser.NameContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Formula_roleContext"))
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_formulaContext")) {
                newf = TFFVisitor.visitTffFormula((TptpParser.Tff_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$AnnotationsContext")) {
                TPTPFormula f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
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
        TPTPFormula newf = null;
        f.type = "thf";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTHFSent() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$NameContext"))
                f.name = ((TptpParser.NameContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Formula_roleContext"))
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Thf_formulaContext")) {
                if (debug) System.out.println("visitTHFSent() found formula ");
                newf = THFVisitor.visitThfFormula((TptpParser.Thf_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$AnnotationsContext")) {
                TPTPFormula f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
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
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFOFSent() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$NameContext"))
                f.name = ((TptpParser.NameContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Formula_roleContext"))
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_formulaContext")) {
                TPTPFormula newf = visitFofFormula((TptpParser.Fof_formulaContext) c);
                f.formula = newf.formula;
                f.sumo = newf.sumo;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$AnnotationsContext")){
                TPTPFormula f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
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
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitCNFSent() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$NameContext"))
                f.name = ((TptpParser.NameContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Formula_roleContext"))
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Cnf_formulaContext")) {
                CNFVisitor cnfv = new CNFVisitor();
                TPTPFormula cnf = cnfv.visitCNFFormula((TptpParser.Cnf_formulaContext) c);
                f.formula = cnf.formula;
                f.sumo = cnf.sumo;
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$AnnotationsContext")){
                TPTPFormula f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$SourceContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Dag_sourceContext"))
                f = visitDagSource((TptpParser.Dag_sourceContext) c);
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Internal_sourceContext"))
                f = visitInternalSource((TptpParser.Internal_sourceContext) c);
            if (c.getClass().getName().equals("tptp_parser.TptpParser$External_sourceContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$NameContext"))
                f.supports.add(((TptpParser.NameContext) c).getText());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Inference_recordContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Intro_typeContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$File_sourceContext"))
                f.infRule = ((TptpParser.File_sourceContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$TheoryContext"))
                f.infRule = ((TptpParser.TheoryContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Creator_sourceContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Inference_ruleContext"))
                f.infRule = (((TptpParser.Inference_ruleContext) c).getText());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Inference_parentsContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Parent_listContext")) {
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Parent_infoContext"))
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$SourceContext")) {
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_logic_formulaContext")) {
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_binary_formulaContext")) {
                f = visitFofBinaryFormula((TptpParser.Fof_binary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_unitary_formulaContext")) {
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_binary_nonassocContext")) {
                f = visitFofBinaryNonassoc((TptpParser.Fof_binary_nonassocContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_binary_assocContext")) {
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
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofBinaryNonassoc() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_unitary_formulaContext")) {
                TPTPFormula newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
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
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofBinaryAssoc() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_or_formulaContext")) {
                f = visitFofOrFormula((TptpParser.Fof_or_formulaContext) c);
                f.sumo = f.sumo + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_and_formulaContext")) {
                f = visitFofAndFormula((TptpParser.Fof_and_formulaContext) c);
                f.sumo = f.sumo + ")";
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
    public static TPTPFormula visitFofOrFormula(TptpParser.Fof_or_formulaContext context) {

        if (debug) System.out.println("visitFofOrFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofOrFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_unitary_formulaContext")) {
                TPTPFormula newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_or_formulaContext")) {
                TPTPFormula newf = visitFofOrFormula((TptpParser.Fof_or_formulaContext) c);
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
        if (debug) System.out.println("visitFofOrFormula() returning: " + f);
        if (debug) System.out.println("visitFofOrFormula() returning sumo: " + f.sumo);
        return f;
    }

    /** ***************************************************************
     * fof_and_formula         : fof_unitary_formula And fof_unitary_formula
     *                         | fof_and_formula And fof_unitary_formula;
     */
    public static TPTPFormula visitFofAndFormula(TptpParser.Fof_and_formulaContext context) {

        if (debug) System.out.println("visitFofAndFormula(): " + context.getText());
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofAndFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_unitary_formulaContext")) {
                TPTPFormula newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_and_formulaContext")) {
                TPTPFormula newf = visitFofAndFormula((TptpParser.Fof_and_formulaContext) c);
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
        if (debug) System.out.println("visitFofAndFormula() returning: " + f);
        if (debug) System.out.println("visitFofAndFormula() returning sumo: " + f.sumo);
        return f;
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
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_quantified_formulaContext")) {
                f = visitFofQuantifiedFormula((TptpParser.Fof_quantified_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_unary_formulaContext")) {
                f = visitFofUnaryFormula((TptpParser.Fof_unary_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_atomic_formulaContext")) {
                f = CNFVisitor.visitFofAtomicFormula((TptpParser.Fof_atomic_formulaContext) c);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_logic_formulaContext")) {
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
        TPTPFormula f = new TPTPFormula();
        String conn = "";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofUnaryFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_unitary_formulaContext")) {
                TPTPFormula newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
                f.formula = conn + newf.formula;
                f.sumo = "(not " + newf.sumo + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_binary_assocContext")) {
                conn = ((TptpParser.Fof_binary_assocContext) c).getText();
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_infix_unaryContext")) {
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
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofQuantifiedFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_unitary_formulaContext")) {
                TPTPFormula newf = visitFofUnitaryFormula((TptpParser.Fof_unitary_formulaContext) c);
                f.formula = f.formula + newf.formula;
                f.sumo = f.sumo + newf.sumo + ")";
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_quantifierContext")) {
                String quant = ((TptpParser.Fof_quantifierContext) c).getText();
                f.formula = quant;
                f.sumo = "(" + sumoTable.get(quant);
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_variable_listContext")) {
                TPTPFormula newf = visitFofVariableList((TptpParser.Fof_variable_listContext) c);
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
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFofVariableList() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$VariableContext")) {
                String term = c.getText();
                String sumoTerm = translateSUMOterm(c.getText());
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
                    System.out.println(hm.get(s));
                    System.out.println("\t" + hm.get(s).sumo + "\n");
                }
            }
            else
                showHelp();
        }
    }
}

