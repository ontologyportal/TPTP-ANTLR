package tptp_parser;

import java.util.ArrayList;

public class TPTPFormula {

    public String name;
    public String role = "";
    public String type = ""; // fof,cnf, tff etc
    public ArrayList<String> supports = new ArrayList<>();  // was just source
    public String infRule = ""; // was just source
    public int startLine = 0;
    public int endLine = 0;
    public String sourceFile = ""; // was source
    public String formula = "";
    public TptpParser.Annotated_formulaContext parsedFormula = null; //was item
    public ArrayList<TPTPFormula> parent;
    public ArrayList<TPTPFormula> child;
    public String sumo = "";  // SUMO syntax equivalent of TPTP statement
    public TptpParser.Cnf_formulaContext cnf = null;
    public TptpParser.Fof_formulaContext fof = null;
    public TptpParser.Tff_formulaContext tff = null;

    /** ***************************************************************
     */
    public TPTPFormula() {
    }

    /** ***************************************************************
    */
    public TPTPFormula(TptpParser.Annotated_formulaContext item, int id) {
    }

    /** ***************************************************************
     */
    public String toString () {

        StringBuffer result = new StringBuffer();
        result.append(type + "(" + name + "," + role + "," + formula);
        if (supports != null && supports.size() > 0) {
            result.append(",[");
            for (String f : supports)
                result.append(f + ",");
            result.deleteCharAt(result.length()-1);
            result.append("]");
        }
        if (infRule != "")
            result.append(",[" + infRule + "]");
        result.append(")");
        return result.toString();
    }

    /** ***************************************************************
     */
    public String getFormula () {

        return formula;
    }

    /** ***************************************************************
    */
    public String getRole () {

        return "plain";
    }
}

