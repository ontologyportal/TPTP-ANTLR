package tptp_parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

public class TPTPVisitor extends AbstractParseTreeVisitor<String> {

    public static boolean debug = false;
    public static HashMap<String,TPTPFormula> result = new HashMap<>();

    /** ***************************************************************
     */
    public static void parseFile(String fname) {

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
    public static HashMap<String,TPTPFormula> parseString(String input) {

        if (debug) System.out.println(input);
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
            System.out.println("Error in SuokifVisitor.parseString(): no results for input: "  + input);
        return hm;
    }

    /** ***************************************************************
     * Parse a single formula and use this Visitor to process
     * as the cached information for the formula. Copy the formula
     * meta-data to the new formulas.
     * @return a Map that should have a single formula
     */
    public static HashMap<String,TPTPFormula> parseFormula(TPTPFormula input) {

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
            System.out.println("Error in SuokifVisitor.parseString(): no results for input: "  + input);
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
    public static void visitFile(TptpParser.Tptp_fileContext context) {

        if (debug) System.out.println("visitFile() Visiting file: " + context.getText());
        if (debug) System.out.println("visitFile() # children: " + context.children.size());
        int counter = 0;
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFile() child: " + c.getClass().getName());
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
    public static TPTPFormula visitInput(TptpParser.Tptp_inputContext context) {

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
    public static TPTPFormula visitFormula(TptpParser.Annotated_formulaContext context) {

        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFormula() Visiting formula: " + context.getText());
        if (debug) System.out.println("visitFormula() # children: " + context.children.size());
        TPTPFormula f = new TPTPFormula();
        for (ParseTree c : context.children) {
            if (debug)  System.out.println("visitFormula() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_annotatedContext"))
                f = visitTFFSent((TptpParser.Tff_annotatedContext) c);
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
    public static TPTPFormula visitTFFSent(TptpParser.Tff_annotatedContext context) {

        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitTFFSent() Visiting tff sentence: " + context.getText());
        if (debug) System.out.println("visitTFFSent() # children: " + context.children.size());
        TPTPFormula f = new TPTPFormula();
        f.type = "tff";
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitTFFSent() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$NameContext"))
                f.name = ((TptpParser.NameContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Formula_roleContext"))
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Tff_formulaContext")) {
                f.tff = ((TptpParser.Tff_formulaContext) c);
                f.formula = ((TptpParser.Tff_formulaContext) c).getText();
            }
            if (c.getClass().getName().equals("tptp_parser.TptpParser$AnnotationsContext")) {
                TPTPFormula f2 = visitAnnotations((TptpParser.AnnotationsContext) c);
                f.supports = f2.supports;
                f.infRule = f2.infRule;
            }
        }
        if (debug) System.out.println("result: " + f);
        result.put(f.name, f);
        return f;
    }

    /** ***************************************************************
     * fof_annotated           : 'fof(' name ',' formula_role ',' fof_formula annotations? ').';
     */
    public static TPTPFormula visitFOFSent(TptpParser.Fof_annotatedContext context) {

        TPTPFormula f = new TPTPFormula();
        f.type = "fof";
        if (context == null || context.children == null) return null;
        if (debug) System.out.println("visitFOFSent() Visiting fof sentence: " + context.getText());
        if (debug) System.out.println("visitFOFSent() # children: " + context.children.size());
        for (ParseTree c : context.children) {
            if (debug) System.out.println("visitFOFSent() child: " + c.getClass().getName());
            if (c.getClass().getName().equals("tptp_parser.TptpParser$NameContext"))
                f.name = ((TptpParser.NameContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Formula_roleContext"))
                f.role = ((TptpParser.Formula_roleContext) c).getText();
            if (c.getClass().getName().equals("tptp_parser.TptpParser$Fof_formulaContext")) {
                f.fof = ((TptpParser.Fof_formulaContext) c);
                f.formula = ((TptpParser.Fof_formulaContext) c).getText();
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
    public static TPTPFormula visitCNFSent(TptpParser.Cnf_annotatedContext context) {

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
                f.cnf = ((TptpParser.Cnf_formulaContext) c);
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
                HashMap<String,TPTPFormula> hm = TPTPVisitor.result;
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

