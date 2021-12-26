package tptp_parser;

import com.articulate.sigma.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class TPTPFormula {

    public static boolean debug = true;

    public String name;
    public int id = 0; // ordered integer ids are used for proofs
    public String role = ""; // plain, axiom, type, etc
    public String type = ""; // fof, cnf, tff etc
    public ArrayList<String> supports = new ArrayList<>();
    public ArrayList<Integer> intsupports = new ArrayList<>();
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

        // old name, new number
        HashMap<String,Integer> numberingMap = new HashMap<>();

        //if (debug) System.out.println("INFO in TPTPFormula.normalizeProofStepNumbers(): before: " + proofSteps);
        int newIndex = 1;
        for (int i = 0; i < proofSteps.size(); i++) {
            //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): numberingMap: " + numberingMap);
            TPTPFormula ps = proofSteps.get(i);
            //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): Checking proof step: " + ps);
            String oldIndex = ps.name;
            if (numberingMap.containsKey(oldIndex))
                ps.id = numberingMap.get(oldIndex);
            else {
                //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): adding new step: " + newIndex);
                ps.id = newIndex;
                numberingMap.put(oldIndex,newIndex);
                newIndex++;
            }
            for (int j = 0; j < ps.supports.size(); j++) {
                String premiseNum = ps.supports.get(j);
                //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): old premise num: " + premiseNum);
                int newNumber = 0;
                if (numberingMap.get(premiseNum) != null) {
                    newNumber = numberingMap.get(premiseNum);
                }
                else {
                    newNumber = newIndex;
                    newIndex++;
                    numberingMap.put(premiseNum,newNumber);
                }
                ps.intsupports.add(newNumber);
                //System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): new premise num: " + newNumber);
            }
        }
        //if (debug) System.out.println("INFO in ProofStep.normalizeProofStepNumbers(): after: " + proofSteps);
        return proofSteps;
    }

}

