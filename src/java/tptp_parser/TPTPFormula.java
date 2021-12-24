package tptp_parser;

import com.articulate.sigma.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class TPTPFormula {

    public static boolean debug = false;

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

    /** ***************************************************************
     * Take an ArrayList of TPTPFormulas and renumber them consecutively
     * starting at 1.  Update the ArrayList of premises so that they
     * reflect the renumbering.
     */
    public static ArrayList<TPTPFormula> normalizeProofStepNumbers(ArrayList<TPTPFormula> proofSteps) {

        // old number, new number
        HashMap<String,String> numberingMap = new HashMap<>();

        if (debug) System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): before: " + proofSteps);
        String newIndex = "1";
        for (int i = 0; i < proofSteps.size(); i++) {
            //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): numberingMap: " + numberingMap);
            TPTPFormula ps = proofSteps.get(i);
            //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): Checking proof step: " + ps);
            String oldIndex = ps.name;
            if (numberingMap.containsKey(oldIndex))
                ps.name = numberingMap.get(oldIndex).toString();
            else {
                //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): adding new step: " + newIndex);
                ps.name = newIndex;
                numberingMap.put(oldIndex,StringUtil.incStrInteger(newIndex));
            }
            for (int j = 0; j < ps.supports.size(); j++) {
                String premiseNum = ps.supports.get(j);
                //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): old premise num: " + premiseNum);
                String newNumber = null;
                if (numberingMap.get(premiseNum) != null)
                    newNumber = numberingMap.get(premiseNum);
                else {
                    newNumber = StringUtil.incStrInteger(newIndex);
                    numberingMap.put(premiseNum,newNumber);
                }
                //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): new premise num: " + newNumber);
                ps.supports.set(j,newNumber);
            }
        }
        if (debug) System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): after: " + proofSteps);
        return proofSteps;
    }

}

