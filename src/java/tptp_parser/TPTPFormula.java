package tptp_parser;

import java.util.ArrayList;

public class TPTPFormula {

    public String name;
    public String role = ""; // plain, axiom, type, etc
    public String type = ""; // fof, cnf, tff etc
    public ArrayList<String> supports = new ArrayList<>();  // was just source
    public String infRule = ""; // was just source
    public int startLine = 0;
    public int endLine = 0;
    public String sourceFile = ""; // was source
    public String formula = "";
    public TptpParser.Annotated_formulaContext parsedFormula = null; //was item
    public String sumo = "";  // SUMO syntax equivalent of TPTP statement

    /** ***************************************************************
     */
    public TPTPFormula() {
    }

    /** ***************************************************************
    */
    public TPTPFormula(TPTPFormula tf) {

        name = tf.name;
        role = tf.role;
        type = tf.type = ""; // fof,cnf, tff etc
        supports.addAll(tf.supports);  // was just source
        infRule = tf.infRule; // was just source
        startLine = tf.startLine;
        endLine = tf.endLine;
        sourceFile = tf.sourceFile; // was source
        formula = tf.formula;
        parsedFormula = tf.parsedFormula = null; //was item
        sumo = tf.sumo;  // SUMO syntax equivalent of TPTP statement
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

