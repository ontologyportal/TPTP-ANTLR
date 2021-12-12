package tptp_parser;

import java.util.ArrayList;

public class TPTPFormula {

    public String name;
    public String role = "";
    public String type = ""; // fof,cnf, tff etc
    public ArrayList<String> supports = new ArrayList<>();
    public String infRule = "";
    public int startLine = 0;
    public int endLine = 0;
    public String sourceFile = "";
    public String formula = "";
    public TptpParser.Annotated_formulaContext parsedFormula = null;

    /** ***************************************************************
     */
    public TPTPFormula() {

    }

    /** ***************************************************************
    */
    public TPTPFormula(TptpParser.Annotated_formulaContext item, int id) {

      /*
      this.item = item;
      this.id = id;
      type = TPTPParser.getType(item);
      parents = new ArrayList();
      children = new ArrayList();

      if (item.getKind() == SimpleTptpParserOutput.TopLevelItem.Kind.Formula) {
          SimpleTptpParserOutput.AnnotatedFormula AF = ((SimpleTptpParserOutput.AnnotatedFormula) item);
          annotations = AF.getAnnotations();
      }
      else if (item.getKind() == SimpleTptpParserOutput.TopLevelItem.Kind.Clause) {
          SimpleTptpParserOutput.AnnotatedClause AC = ((SimpleTptpParserOutput.AnnotatedClause) item);
          annotations = AC.getAnnotations();
      }
      if (annotations != null) {
          source = annotations.getSource();
      }
       */
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
    * given a cnf formula, turn it into an fof formula (universally quantify all variables)
    */
    public String fofify () {

      String UniversalQuantifier = "!";
      StringBuffer res = new StringBuffer();

      /*

      if (item.getKind() == SimpleTptpParserOutput.TopLevelItem.Kind.Formula) {
          SimpleTptpParserOutput.AnnotatedFormula AF = ((SimpleTptpParserOutput.AnnotatedFormula)item);
          res.append("fof(");
          res.append(TPTPParser.getName(item) + ",");
          res.append(AF.getRole().toString());
          res.append(",(" + "\n");
          res.append("    " + AF.getFormula().toString(4));
          res.append(" )).");
      }
      else if (item.getKind() == SimpleTptpParserOutput.TopLevelItem.Kind.Clause) {
          SimpleTptpParserOutput.AnnotatedClause AC = ((SimpleTptpParserOutput.AnnotatedClause)item);
          ArrayList<String> variables = new ArrayList();
          variables = TPTPParser.identifyClauseVariables(AC.getClause(), variables);
          res.append("fof(");
          res.append(TPTPParser.getName(item) + ",");
          //      res += (conjecture) ? AC.getRole().toString() : "axiom";
          res.append(AC.getRole().toString());
          res.append(", (" + "\n");
          if (!variables.isEmpty()) {
              res.append("    " + UniversalQuantifier + " [");
              res.append(variables.get(0));
              for (int i = 1; i < variables.size(); i++) {
                  res.append("," + variables.get(i));
              }
              res.append("] : " + "\n");
          }
          res.append((!variables.isEmpty()) ? "      ( " : "    ");
          res.append((!variables.isEmpty()) ? AC.getClause().toString(8) : AC.getClause().toString(4));
          res.append((!variables.isEmpty()) ? " )" : "");
          res.append(" )).");
      }
       */
      return res.toString();
    }

    /** ***************************************************************
    */
    public String getRole () {

        return "plain";
    }
}

