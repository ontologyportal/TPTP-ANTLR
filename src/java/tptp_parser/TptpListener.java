package tptp_parser;

// Generated from tptp_v7_0_0_0.g4 by ANTLR 4.9.3

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TptpParser}.
 */
public interface TptpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TptpParser#tptp_file}.
	 * @param ctx the parse tree
	 */
	void enterTptp_file(TptpParser.Tptp_fileContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tptp_file}.
	 * @param ctx the parse tree
	 */
	void exitTptp_file(TptpParser.Tptp_fileContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tptp_input}.
	 * @param ctx the parse tree
	 */
	void enterTptp_input(TptpParser.Tptp_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tptp_input}.
	 * @param ctx the parse tree
	 */
	void exitTptp_input(TptpParser.Tptp_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#annotated_formula}.
	 * @param ctx the parse tree
	 */
	void enterAnnotated_formula(TptpParser.Annotated_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#annotated_formula}.
	 * @param ctx the parse tree
	 */
	void exitAnnotated_formula(TptpParser.Annotated_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tpi_annotated}.
	 * @param ctx the parse tree
	 */
	void enterTpi_annotated(TptpParser.Tpi_annotatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tpi_annotated}.
	 * @param ctx the parse tree
	 */
	void exitTpi_annotated(TptpParser.Tpi_annotatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tpi_formula}.
	 * @param ctx the parse tree
	 */
	void enterTpi_formula(TptpParser.Tpi_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tpi_formula}.
	 * @param ctx the parse tree
	 */
	void exitTpi_formula(TptpParser.Tpi_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_annotated}.
	 * @param ctx the parse tree
	 */
	void enterThf_annotated(TptpParser.Thf_annotatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_annotated}.
	 * @param ctx the parse tree
	 */
	void exitThf_annotated(TptpParser.Thf_annotatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tfx_annotated}.
	 * @param ctx the parse tree
	 */
	void enterTfx_annotated(TptpParser.Tfx_annotatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tfx_annotated}.
	 * @param ctx the parse tree
	 */
	void exitTfx_annotated(TptpParser.Tfx_annotatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_annotated}.
	 * @param ctx the parse tree
	 */
	void enterTff_annotated(TptpParser.Tff_annotatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_annotated}.
	 * @param ctx the parse tree
	 */
	void exitTff_annotated(TptpParser.Tff_annotatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tcf_annotated}.
	 * @param ctx the parse tree
	 */
	void enterTcf_annotated(TptpParser.Tcf_annotatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tcf_annotated}.
	 * @param ctx the parse tree
	 */
	void exitTcf_annotated(TptpParser.Tcf_annotatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_annotated}.
	 * @param ctx the parse tree
	 */
	void enterFof_annotated(TptpParser.Fof_annotatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_annotated}.
	 * @param ctx the parse tree
	 */
	void exitFof_annotated(TptpParser.Fof_annotatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#cnf_annotated}.
	 * @param ctx the parse tree
	 */
	void enterCnf_annotated(TptpParser.Cnf_annotatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#cnf_annotated}.
	 * @param ctx the parse tree
	 */
	void exitCnf_annotated(TptpParser.Cnf_annotatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(TptpParser.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(TptpParser.AnnotationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#formula_role}.
	 * @param ctx the parse tree
	 */
	void enterFormula_role(TptpParser.Formula_roleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#formula_role}.
	 * @param ctx the parse tree
	 */
	void exitFormula_role(TptpParser.Formula_roleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_formula(TptpParser.Thf_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_formula(TptpParser.Thf_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_logic_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_logic_formula(TptpParser.Thf_logic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_logic_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_logic_formula(TptpParser.Thf_logic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_binary_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_binary_formula(TptpParser.Thf_binary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_binary_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_binary_formula(TptpParser.Thf_binary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_binary_pair}.
	 * @param ctx the parse tree
	 */
	void enterThf_binary_pair(TptpParser.Thf_binary_pairContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_binary_pair}.
	 * @param ctx the parse tree
	 */
	void exitThf_binary_pair(TptpParser.Thf_binary_pairContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_binary_tuple}.
	 * @param ctx the parse tree
	 */
	void enterThf_binary_tuple(TptpParser.Thf_binary_tupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_binary_tuple}.
	 * @param ctx the parse tree
	 */
	void exitThf_binary_tuple(TptpParser.Thf_binary_tupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_or_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_or_formula(TptpParser.Thf_or_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_or_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_or_formula(TptpParser.Thf_or_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_and_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_and_formula(TptpParser.Thf_and_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_and_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_and_formula(TptpParser.Thf_and_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_apply_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_apply_formula(TptpParser.Thf_apply_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_apply_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_apply_formula(TptpParser.Thf_apply_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_unitary_formula(TptpParser.Thf_unitary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_unitary_formula(TptpParser.Thf_unitary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_quantified_formula(TptpParser.Thf_quantified_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_quantified_formula(TptpParser.Thf_quantified_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_quantification}.
	 * @param ctx the parse tree
	 */
	void enterThf_quantification(TptpParser.Thf_quantificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_quantification}.
	 * @param ctx the parse tree
	 */
	void exitThf_quantification(TptpParser.Thf_quantificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_variable_list}.
	 * @param ctx the parse tree
	 */
	void enterThf_variable_list(TptpParser.Thf_variable_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_variable_list}.
	 * @param ctx the parse tree
	 */
	void exitThf_variable_list(TptpParser.Thf_variable_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_variable}.
	 * @param ctx the parse tree
	 */
	void enterThf_variable(TptpParser.Thf_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_variable}.
	 * @param ctx the parse tree
	 */
	void exitThf_variable(TptpParser.Thf_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_typed_variable}.
	 * @param ctx the parse tree
	 */
	void enterThf_typed_variable(TptpParser.Thf_typed_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_typed_variable}.
	 * @param ctx the parse tree
	 */
	void exitThf_typed_variable(TptpParser.Thf_typed_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_unary_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_unary_formula(TptpParser.Thf_unary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_unary_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_unary_formula(TptpParser.Thf_unary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_atom}.
	 * @param ctx the parse tree
	 */
	void enterThf_atom(TptpParser.Thf_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_atom}.
	 * @param ctx the parse tree
	 */
	void exitThf_atom(TptpParser.Thf_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_function}.
	 * @param ctx the parse tree
	 */
	void enterThf_function(TptpParser.Thf_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_function}.
	 * @param ctx the parse tree
	 */
	void exitThf_function(TptpParser.Thf_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_conn_term}.
	 * @param ctx the parse tree
	 */
	void enterThf_conn_term(TptpParser.Thf_conn_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_conn_term}.
	 * @param ctx the parse tree
	 */
	void exitThf_conn_term(TptpParser.Thf_conn_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_conditional}.
	 * @param ctx the parse tree
	 */
	void enterThf_conditional(TptpParser.Thf_conditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_conditional}.
	 * @param ctx the parse tree
	 */
	void exitThf_conditional(TptpParser.Thf_conditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_let}.
	 * @param ctx the parse tree
	 */
	void enterThf_let(TptpParser.Thf_letContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_let}.
	 * @param ctx the parse tree
	 */
	void exitThf_let(TptpParser.Thf_letContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_arguments}.
	 * @param ctx the parse tree
	 */
	void enterThf_arguments(TptpParser.Thf_argumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_arguments}.
	 * @param ctx the parse tree
	 */
	void exitThf_arguments(TptpParser.Thf_argumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_type_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_type_formula(TptpParser.Thf_type_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_type_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_type_formula(TptpParser.Thf_type_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_typeable_formula}.
	 * @param ctx the parse tree
	 */
	void enterThf_typeable_formula(TptpParser.Thf_typeable_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_typeable_formula}.
	 * @param ctx the parse tree
	 */
	void exitThf_typeable_formula(TptpParser.Thf_typeable_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_subtype}.
	 * @param ctx the parse tree
	 */
	void enterThf_subtype(TptpParser.Thf_subtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_subtype}.
	 * @param ctx the parse tree
	 */
	void exitThf_subtype(TptpParser.Thf_subtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_top_level_type}.
	 * @param ctx the parse tree
	 */
	void enterThf_top_level_type(TptpParser.Thf_top_level_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_top_level_type}.
	 * @param ctx the parse tree
	 */
	void exitThf_top_level_type(TptpParser.Thf_top_level_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_unitary_type}.
	 * @param ctx the parse tree
	 */
	void enterThf_unitary_type(TptpParser.Thf_unitary_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_unitary_type}.
	 * @param ctx the parse tree
	 */
	void exitThf_unitary_type(TptpParser.Thf_unitary_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_apply_type}.
	 * @param ctx the parse tree
	 */
	void enterThf_apply_type(TptpParser.Thf_apply_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_apply_type}.
	 * @param ctx the parse tree
	 */
	void exitThf_apply_type(TptpParser.Thf_apply_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_binary_type}.
	 * @param ctx the parse tree
	 */
	void enterThf_binary_type(TptpParser.Thf_binary_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_binary_type}.
	 * @param ctx the parse tree
	 */
	void exitThf_binary_type(TptpParser.Thf_binary_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_mapping_type}.
	 * @param ctx the parse tree
	 */
	void enterThf_mapping_type(TptpParser.Thf_mapping_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_mapping_type}.
	 * @param ctx the parse tree
	 */
	void exitThf_mapping_type(TptpParser.Thf_mapping_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_xprod_type}.
	 * @param ctx the parse tree
	 */
	void enterThf_xprod_type(TptpParser.Thf_xprod_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_xprod_type}.
	 * @param ctx the parse tree
	 */
	void exitThf_xprod_type(TptpParser.Thf_xprod_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_union_type}.
	 * @param ctx the parse tree
	 */
	void enterThf_union_type(TptpParser.Thf_union_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_union_type}.
	 * @param ctx the parse tree
	 */
	void exitThf_union_type(TptpParser.Thf_union_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_sequent}.
	 * @param ctx the parse tree
	 */
	void enterThf_sequent(TptpParser.Thf_sequentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_sequent}.
	 * @param ctx the parse tree
	 */
	void exitThf_sequent(TptpParser.Thf_sequentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_tuple}.
	 * @param ctx the parse tree
	 */
	void enterThf_tuple(TptpParser.Thf_tupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_tuple}.
	 * @param ctx the parse tree
	 */
	void exitThf_tuple(TptpParser.Thf_tupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_formula_list}.
	 * @param ctx the parse tree
	 */
	void enterThf_formula_list(TptpParser.Thf_formula_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_formula_list}.
	 * @param ctx the parse tree
	 */
	void exitThf_formula_list(TptpParser.Thf_formula_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tfx_formula}.
	 * @param ctx the parse tree
	 */
	void enterTfx_formula(TptpParser.Tfx_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tfx_formula}.
	 * @param ctx the parse tree
	 */
	void exitTfx_formula(TptpParser.Tfx_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tfx_logic_formula}.
	 * @param ctx the parse tree
	 */
	void enterTfx_logic_formula(TptpParser.Tfx_logic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tfx_logic_formula}.
	 * @param ctx the parse tree
	 */
	void exitTfx_logic_formula(TptpParser.Tfx_logic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_formula(TptpParser.Tff_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_formula(TptpParser.Tff_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_logic_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_logic_formula(TptpParser.Tff_logic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_logic_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_logic_formula(TptpParser.Tff_logic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_binary_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_binary_formula(TptpParser.Tff_binary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_binary_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_binary_formula(TptpParser.Tff_binary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_binary_nonassoc}.
	 * @param ctx the parse tree
	 */
	void enterTff_binary_nonassoc(TptpParser.Tff_binary_nonassocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_binary_nonassoc}.
	 * @param ctx the parse tree
	 */
	void exitTff_binary_nonassoc(TptpParser.Tff_binary_nonassocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_binary_assoc}.
	 * @param ctx the parse tree
	 */
	void enterTff_binary_assoc(TptpParser.Tff_binary_assocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_binary_assoc}.
	 * @param ctx the parse tree
	 */
	void exitTff_binary_assoc(TptpParser.Tff_binary_assocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_or_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_or_formula(TptpParser.Tff_or_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_or_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_or_formula(TptpParser.Tff_or_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_and_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_and_formula(TptpParser.Tff_and_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_and_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_and_formula(TptpParser.Tff_and_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_unitary_formula(TptpParser.Tff_unitary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_unitary_formula(TptpParser.Tff_unitary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_quantified_formula(TptpParser.Tff_quantified_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_quantified_formula(TptpParser.Tff_quantified_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_variable_list}.
	 * @param ctx the parse tree
	 */
	void enterTff_variable_list(TptpParser.Tff_variable_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_variable_list}.
	 * @param ctx the parse tree
	 */
	void exitTff_variable_list(TptpParser.Tff_variable_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_variable}.
	 * @param ctx the parse tree
	 */
	void enterTff_variable(TptpParser.Tff_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_variable}.
	 * @param ctx the parse tree
	 */
	void exitTff_variable(TptpParser.Tff_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_typed_variable}.
	 * @param ctx the parse tree
	 */
	void enterTff_typed_variable(TptpParser.Tff_typed_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_typed_variable}.
	 * @param ctx the parse tree
	 */
	void exitTff_typed_variable(TptpParser.Tff_typed_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_unary_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_unary_formula(TptpParser.Tff_unary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_unary_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_unary_formula(TptpParser.Tff_unary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_atomic_formula(TptpParser.Tff_atomic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_atomic_formula(TptpParser.Tff_atomic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_conditional}.
	 * @param ctx the parse tree
	 */
	void enterTff_conditional(TptpParser.Tff_conditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_conditional}.
	 * @param ctx the parse tree
	 */
	void exitTff_conditional(TptpParser.Tff_conditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let}.
	 * @param ctx the parse tree
	 */
	void enterTff_let(TptpParser.Tff_letContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let}.
	 * @param ctx the parse tree
	 */
	void exitTff_let(TptpParser.Tff_letContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_term_defns}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_term_defns(TptpParser.Tff_let_term_defnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_term_defns}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_term_defns(TptpParser.Tff_let_term_defnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_term_list}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_term_list(TptpParser.Tff_let_term_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_term_list}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_term_list(TptpParser.Tff_let_term_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_term_defn}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_term_defn(TptpParser.Tff_let_term_defnContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_term_defn}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_term_defn(TptpParser.Tff_let_term_defnContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_term_binding}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_term_binding(TptpParser.Tff_let_term_bindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_term_binding}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_term_binding(TptpParser.Tff_let_term_bindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_formula_defns}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_formula_defns(TptpParser.Tff_let_formula_defnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_formula_defns}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_formula_defns(TptpParser.Tff_let_formula_defnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_formula_list}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_formula_list(TptpParser.Tff_let_formula_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_formula_list}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_formula_list(TptpParser.Tff_let_formula_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_formula_defn}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_formula_defn(TptpParser.Tff_let_formula_defnContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_formula_defn}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_formula_defn(TptpParser.Tff_let_formula_defnContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_formula_binding}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_formula_binding(TptpParser.Tff_let_formula_bindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_formula_binding}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_formula_binding(TptpParser.Tff_let_formula_bindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_sequent}.
	 * @param ctx the parse tree
	 */
	void enterTff_sequent(TptpParser.Tff_sequentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_sequent}.
	 * @param ctx the parse tree
	 */
	void exitTff_sequent(TptpParser.Tff_sequentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_formula_tuple}.
	 * @param ctx the parse tree
	 */
	void enterTff_formula_tuple(TptpParser.Tff_formula_tupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_formula_tuple}.
	 * @param ctx the parse tree
	 */
	void exitTff_formula_tuple(TptpParser.Tff_formula_tupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_formula_tuple_list}.
	 * @param ctx the parse tree
	 */
	void enterTff_formula_tuple_list(TptpParser.Tff_formula_tuple_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_formula_tuple_list}.
	 * @param ctx the parse tree
	 */
	void exitTff_formula_tuple_list(TptpParser.Tff_formula_tuple_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_typed_atom}.
	 * @param ctx the parse tree
	 */
	void enterTff_typed_atom(TptpParser.Tff_typed_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_typed_atom}.
	 * @param ctx the parse tree
	 */
	void exitTff_typed_atom(TptpParser.Tff_typed_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_subtype}.
	 * @param ctx the parse tree
	 */
	void enterTff_subtype(TptpParser.Tff_subtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_subtype}.
	 * @param ctx the parse tree
	 */
	void exitTff_subtype(TptpParser.Tff_subtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_top_level_type}.
	 * @param ctx the parse tree
	 */
	void enterTff_top_level_type(TptpParser.Tff_top_level_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_top_level_type}.
	 * @param ctx the parse tree
	 */
	void exitTff_top_level_type(TptpParser.Tff_top_level_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tf1_quantified_type}.
	 * @param ctx the parse tree
	 */
	void enterTf1_quantified_type(TptpParser.Tf1_quantified_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tf1_quantified_type}.
	 * @param ctx the parse tree
	 */
	void exitTf1_quantified_type(TptpParser.Tf1_quantified_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_monotype}.
	 * @param ctx the parse tree
	 */
	void enterTff_monotype(TptpParser.Tff_monotypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_monotype}.
	 * @param ctx the parse tree
	 */
	void exitTff_monotype(TptpParser.Tff_monotypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_unitary_type}.
	 * @param ctx the parse tree
	 */
	void enterTff_unitary_type(TptpParser.Tff_unitary_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_unitary_type}.
	 * @param ctx the parse tree
	 */
	void exitTff_unitary_type(TptpParser.Tff_unitary_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_atomic_type}.
	 * @param ctx the parse tree
	 */
	void enterTff_atomic_type(TptpParser.Tff_atomic_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_atomic_type}.
	 * @param ctx the parse tree
	 */
	void exitTff_atomic_type(TptpParser.Tff_atomic_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_type_arguments}.
	 * @param ctx the parse tree
	 */
	void enterTff_type_arguments(TptpParser.Tff_type_argumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_type_arguments}.
	 * @param ctx the parse tree
	 */
	void exitTff_type_arguments(TptpParser.Tff_type_argumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_mapping_type}.
	 * @param ctx the parse tree
	 */
	void enterTff_mapping_type(TptpParser.Tff_mapping_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_mapping_type}.
	 * @param ctx the parse tree
	 */
	void exitTff_mapping_type(TptpParser.Tff_mapping_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_xprod_type}.
	 * @param ctx the parse tree
	 */
	void enterTff_xprod_type(TptpParser.Tff_xprod_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_xprod_type}.
	 * @param ctx the parse tree
	 */
	void exitTff_xprod_type(TptpParser.Tff_xprod_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tcf_formula}.
	 * @param ctx the parse tree
	 */
	void enterTcf_formula(TptpParser.Tcf_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tcf_formula}.
	 * @param ctx the parse tree
	 */
	void exitTcf_formula(TptpParser.Tcf_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tcf_logic_formula}.
	 * @param ctx the parse tree
	 */
	void enterTcf_logic_formula(TptpParser.Tcf_logic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tcf_logic_formula}.
	 * @param ctx the parse tree
	 */
	void exitTcf_logic_formula(TptpParser.Tcf_logic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tcf_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void enterTcf_quantified_formula(TptpParser.Tcf_quantified_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tcf_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void exitTcf_quantified_formula(TptpParser.Tcf_quantified_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_formula(TptpParser.Fof_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_formula(TptpParser.Fof_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_logic_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_logic_formula(TptpParser.Fof_logic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_logic_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_logic_formula(TptpParser.Fof_logic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_binary_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_binary_formula(TptpParser.Fof_binary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_binary_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_binary_formula(TptpParser.Fof_binary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_binary_nonassoc}.
	 * @param ctx the parse tree
	 */
	void enterFof_binary_nonassoc(TptpParser.Fof_binary_nonassocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_binary_nonassoc}.
	 * @param ctx the parse tree
	 */
	void exitFof_binary_nonassoc(TptpParser.Fof_binary_nonassocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_binary_assoc}.
	 * @param ctx the parse tree
	 */
	void enterFof_binary_assoc(TptpParser.Fof_binary_assocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_binary_assoc}.
	 * @param ctx the parse tree
	 */
	void exitFof_binary_assoc(TptpParser.Fof_binary_assocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_or_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_or_formula(TptpParser.Fof_or_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_or_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_or_formula(TptpParser.Fof_or_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_and_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_and_formula(TptpParser.Fof_and_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_and_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_and_formula(TptpParser.Fof_and_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_unitary_formula(TptpParser.Fof_unitary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_unitary_formula(TptpParser.Fof_unitary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_quantified_formula(TptpParser.Fof_quantified_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_quantified_formula(TptpParser.Fof_quantified_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_variable_list}.
	 * @param ctx the parse tree
	 */
	void enterFof_variable_list(TptpParser.Fof_variable_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_variable_list}.
	 * @param ctx the parse tree
	 */
	void exitFof_variable_list(TptpParser.Fof_variable_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_unary_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_unary_formula(TptpParser.Fof_unary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_unary_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_unary_formula(TptpParser.Fof_unary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_infix_unary}.
	 * @param ctx the parse tree
	 */
	void enterFof_infix_unary(TptpParser.Fof_infix_unaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_infix_unary}.
	 * @param ctx the parse tree
	 */
	void exitFof_infix_unary(TptpParser.Fof_infix_unaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_atomic_formula(TptpParser.Fof_atomic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_atomic_formula(TptpParser.Fof_atomic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_plain_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_plain_atomic_formula(TptpParser.Fof_plain_atomic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_plain_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_plain_atomic_formula(TptpParser.Fof_plain_atomic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_defined_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_defined_atomic_formula(TptpParser.Fof_defined_atomic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_defined_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_defined_atomic_formula(TptpParser.Fof_defined_atomic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_defined_plain_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_defined_plain_formula(TptpParser.Fof_defined_plain_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_defined_plain_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_defined_plain_formula(TptpParser.Fof_defined_plain_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_defined_infix_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_defined_infix_formula(TptpParser.Fof_defined_infix_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_defined_infix_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_defined_infix_formula(TptpParser.Fof_defined_infix_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_system_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void enterFof_system_atomic_formula(TptpParser.Fof_system_atomic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_system_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void exitFof_system_atomic_formula(TptpParser.Fof_system_atomic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_plain_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_plain_term(TptpParser.Fof_plain_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_plain_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_plain_term(TptpParser.Fof_plain_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_defined_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_defined_term(TptpParser.Fof_defined_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_defined_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_defined_term(TptpParser.Fof_defined_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_defined_atomic_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_defined_atomic_term(TptpParser.Fof_defined_atomic_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_defined_atomic_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_defined_atomic_term(TptpParser.Fof_defined_atomic_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_defined_plain_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_defined_plain_term(TptpParser.Fof_defined_plain_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_defined_plain_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_defined_plain_term(TptpParser.Fof_defined_plain_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_system_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_system_term(TptpParser.Fof_system_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_system_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_system_term(TptpParser.Fof_system_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_arguments}.
	 * @param ctx the parse tree
	 */
	void enterFof_arguments(TptpParser.Fof_argumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_arguments}.
	 * @param ctx the parse tree
	 */
	void exitFof_arguments(TptpParser.Fof_argumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_term(TptpParser.Fof_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_term(TptpParser.Fof_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_function_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_function_term(TptpParser.Fof_function_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_function_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_function_term(TptpParser.Fof_function_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_conditional_term}.
	 * @param ctx the parse tree
	 */
	void enterTff_conditional_term(TptpParser.Tff_conditional_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_conditional_term}.
	 * @param ctx the parse tree
	 */
	void exitTff_conditional_term(TptpParser.Tff_conditional_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_let_term}.
	 * @param ctx the parse tree
	 */
	void enterTff_let_term(TptpParser.Tff_let_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_let_term}.
	 * @param ctx the parse tree
	 */
	void exitTff_let_term(TptpParser.Tff_let_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_tuple_term}.
	 * @param ctx the parse tree
	 */
	void enterTff_tuple_term(TptpParser.Tff_tuple_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_tuple_term}.
	 * @param ctx the parse tree
	 */
	void exitTff_tuple_term(TptpParser.Tff_tuple_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_sequent}.
	 * @param ctx the parse tree
	 */
	void enterFof_sequent(TptpParser.Fof_sequentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_sequent}.
	 * @param ctx the parse tree
	 */
	void exitFof_sequent(TptpParser.Fof_sequentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_formula_tuple}.
	 * @param ctx the parse tree
	 */
	void enterFof_formula_tuple(TptpParser.Fof_formula_tupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_formula_tuple}.
	 * @param ctx the parse tree
	 */
	void exitFof_formula_tuple(TptpParser.Fof_formula_tupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_formula_tuple_list}.
	 * @param ctx the parse tree
	 */
	void enterFof_formula_tuple_list(TptpParser.Fof_formula_tuple_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_formula_tuple_list}.
	 * @param ctx the parse tree
	 */
	void exitFof_formula_tuple_list(TptpParser.Fof_formula_tuple_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#cnf_formula}.
	 * @param ctx the parse tree
	 */
	void enterCnf_formula(TptpParser.Cnf_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#cnf_formula}.
	 * @param ctx the parse tree
	 */
	void exitCnf_formula(TptpParser.Cnf_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#cnf_disjunction}.
	 * @param ctx the parse tree
	 */
	void enterCnf_disjunction(TptpParser.Cnf_disjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#cnf_disjunction}.
	 * @param ctx the parse tree
	 */
	void exitCnf_disjunction(TptpParser.Cnf_disjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#cnf_literal}.
	 * @param ctx the parse tree
	 */
	void enterCnf_literal(TptpParser.Cnf_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#cnf_literal}.
	 * @param ctx the parse tree
	 */
	void exitCnf_literal(TptpParser.Cnf_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_quantifier}.
	 * @param ctx the parse tree
	 */
	void enterThf_quantifier(TptpParser.Thf_quantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_quantifier}.
	 * @param ctx the parse tree
	 */
	void exitThf_quantifier(TptpParser.Thf_quantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#th0_quantifier}.
	 * @param ctx the parse tree
	 */
	void enterTh0_quantifier(TptpParser.Th0_quantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#th0_quantifier}.
	 * @param ctx the parse tree
	 */
	void exitTh0_quantifier(TptpParser.Th0_quantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#th1_quantifier}.
	 * @param ctx the parse tree
	 */
	void enterTh1_quantifier(TptpParser.Th1_quantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#th1_quantifier}.
	 * @param ctx the parse tree
	 */
	void exitTh1_quantifier(TptpParser.Th1_quantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_pair_connective}.
	 * @param ctx the parse tree
	 */
	void enterThf_pair_connective(TptpParser.Thf_pair_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_pair_connective}.
	 * @param ctx the parse tree
	 */
	void exitThf_pair_connective(TptpParser.Thf_pair_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#thf_unary_connective}.
	 * @param ctx the parse tree
	 */
	void enterThf_unary_connective(TptpParser.Thf_unary_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#thf_unary_connective}.
	 * @param ctx the parse tree
	 */
	void exitThf_unary_connective(TptpParser.Thf_unary_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#th1_unary_connective}.
	 * @param ctx the parse tree
	 */
	void enterTh1_unary_connective(TptpParser.Th1_unary_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#th1_unary_connective}.
	 * @param ctx the parse tree
	 */
	void exitTh1_unary_connective(TptpParser.Th1_unary_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#tff_pair_connective}.
	 * @param ctx the parse tree
	 */
	void enterTff_pair_connective(TptpParser.Tff_pair_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#tff_pair_connective}.
	 * @param ctx the parse tree
	 */
	void exitTff_pair_connective(TptpParser.Tff_pair_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#fof_quantifier}.
	 * @param ctx the parse tree
	 */
	void enterFof_quantifier(TptpParser.Fof_quantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#fof_quantifier}.
	 * @param ctx the parse tree
	 */
	void exitFof_quantifier(TptpParser.Fof_quantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#binary_connective}.
	 * @param ctx the parse tree
	 */
	void enterBinary_connective(TptpParser.Binary_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#binary_connective}.
	 * @param ctx the parse tree
	 */
	void exitBinary_connective(TptpParser.Binary_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#assoc_connective}.
	 * @param ctx the parse tree
	 */
	void enterAssoc_connective(TptpParser.Assoc_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#assoc_connective}.
	 * @param ctx the parse tree
	 */
	void exitAssoc_connective(TptpParser.Assoc_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#unary_connective}.
	 * @param ctx the parse tree
	 */
	void enterUnary_connective(TptpParser.Unary_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#unary_connective}.
	 * @param ctx the parse tree
	 */
	void exitUnary_connective(TptpParser.Unary_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#type_constant}.
	 * @param ctx the parse tree
	 */
	void enterType_constant(TptpParser.Type_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#type_constant}.
	 * @param ctx the parse tree
	 */
	void exitType_constant(TptpParser.Type_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#type_functor}.
	 * @param ctx the parse tree
	 */
	void enterType_functor(TptpParser.Type_functorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#type_functor}.
	 * @param ctx the parse tree
	 */
	void exitType_functor(TptpParser.Type_functorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#defined_type}.
	 * @param ctx the parse tree
	 */
	void enterDefined_type(TptpParser.Defined_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#defined_type}.
	 * @param ctx the parse tree
	 */
	void exitDefined_type(TptpParser.Defined_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#system_type}.
	 * @param ctx the parse tree
	 */
	void enterSystem_type(TptpParser.System_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#system_type}.
	 * @param ctx the parse tree
	 */
	void exitSystem_type(TptpParser.System_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(TptpParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(TptpParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#untyped_atom}.
	 * @param ctx the parse tree
	 */
	void enterUntyped_atom(TptpParser.Untyped_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#untyped_atom}.
	 * @param ctx the parse tree
	 */
	void exitUntyped_atom(TptpParser.Untyped_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#defined_proposition}.
	 * @param ctx the parse tree
	 */
	void enterDefined_proposition(TptpParser.Defined_propositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#defined_proposition}.
	 * @param ctx the parse tree
	 */
	void exitDefined_proposition(TptpParser.Defined_propositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#defined_predicate}.
	 * @param ctx the parse tree
	 */
	void enterDefined_predicate(TptpParser.Defined_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#defined_predicate}.
	 * @param ctx the parse tree
	 */
	void exitDefined_predicate(TptpParser.Defined_predicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#defined_infix_pred}.
	 * @param ctx the parse tree
	 */
	void enterDefined_infix_pred(TptpParser.Defined_infix_predContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#defined_infix_pred}.
	 * @param ctx the parse tree
	 */
	void exitDefined_infix_pred(TptpParser.Defined_infix_predContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(TptpParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(TptpParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#functor}.
	 * @param ctx the parse tree
	 */
	void enterFunctor(TptpParser.FunctorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#functor}.
	 * @param ctx the parse tree
	 */
	void exitFunctor(TptpParser.FunctorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#system_constant}.
	 * @param ctx the parse tree
	 */
	void enterSystem_constant(TptpParser.System_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#system_constant}.
	 * @param ctx the parse tree
	 */
	void exitSystem_constant(TptpParser.System_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#system_functor}.
	 * @param ctx the parse tree
	 */
	void enterSystem_functor(TptpParser.System_functorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#system_functor}.
	 * @param ctx the parse tree
	 */
	void exitSystem_functor(TptpParser.System_functorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#defined_constant}.
	 * @param ctx the parse tree
	 */
	void enterDefined_constant(TptpParser.Defined_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#defined_constant}.
	 * @param ctx the parse tree
	 */
	void exitDefined_constant(TptpParser.Defined_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#defined_functor}.
	 * @param ctx the parse tree
	 */
	void enterDefined_functor(TptpParser.Defined_functorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#defined_functor}.
	 * @param ctx the parse tree
	 */
	void exitDefined_functor(TptpParser.Defined_functorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#defined_term}.
	 * @param ctx the parse tree
	 */
	void enterDefined_term(TptpParser.Defined_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#defined_term}.
	 * @param ctx the parse tree
	 */
	void exitDefined_term(TptpParser.Defined_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(TptpParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(TptpParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(TptpParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(TptpParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#sources}.
	 * @param ctx the parse tree
	 */
	void enterSources(TptpParser.SourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#sources}.
	 * @param ctx the parse tree
	 */
	void exitSources(TptpParser.SourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#dag_source}.
	 * @param ctx the parse tree
	 */
	void enterDag_source(TptpParser.Dag_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#dag_source}.
	 * @param ctx the parse tree
	 */
	void exitDag_source(TptpParser.Dag_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#inference_record}.
	 * @param ctx the parse tree
	 */
	void enterInference_record(TptpParser.Inference_recordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#inference_record}.
	 * @param ctx the parse tree
	 */
	void exitInference_record(TptpParser.Inference_recordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#inference_rule}.
	 * @param ctx the parse tree
	 */
	void enterInference_rule(TptpParser.Inference_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#inference_rule}.
	 * @param ctx the parse tree
	 */
	void exitInference_rule(TptpParser.Inference_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#inference_parents}.
	 * @param ctx the parse tree
	 */
	void enterInference_parents(TptpParser.Inference_parentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#inference_parents}.
	 * @param ctx the parse tree
	 */
	void exitInference_parents(TptpParser.Inference_parentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#parent_list}.
	 * @param ctx the parse tree
	 */
	void enterParent_list(TptpParser.Parent_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#parent_list}.
	 * @param ctx the parse tree
	 */
	void exitParent_list(TptpParser.Parent_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#parent_info}.
	 * @param ctx the parse tree
	 */
	void enterParent_info(TptpParser.Parent_infoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#parent_info}.
	 * @param ctx the parse tree
	 */
	void exitParent_info(TptpParser.Parent_infoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#parent_details}.
	 * @param ctx the parse tree
	 */
	void enterParent_details(TptpParser.Parent_detailsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#parent_details}.
	 * @param ctx the parse tree
	 */
	void exitParent_details(TptpParser.Parent_detailsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#internal_source}.
	 * @param ctx the parse tree
	 */
	void enterInternal_source(TptpParser.Internal_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#internal_source}.
	 * @param ctx the parse tree
	 */
	void exitInternal_source(TptpParser.Internal_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#intro_type}.
	 * @param ctx the parse tree
	 */
	void enterIntro_type(TptpParser.Intro_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#intro_type}.
	 * @param ctx the parse tree
	 */
	void exitIntro_type(TptpParser.Intro_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#external_source}.
	 * @param ctx the parse tree
	 */
	void enterExternal_source(TptpParser.External_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#external_source}.
	 * @param ctx the parse tree
	 */
	void exitExternal_source(TptpParser.External_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#file_source}.
	 * @param ctx the parse tree
	 */
	void enterFile_source(TptpParser.File_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#file_source}.
	 * @param ctx the parse tree
	 */
	void exitFile_source(TptpParser.File_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#file_info}.
	 * @param ctx the parse tree
	 */
	void enterFile_info(TptpParser.File_infoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#file_info}.
	 * @param ctx the parse tree
	 */
	void exitFile_info(TptpParser.File_infoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#theory}.
	 * @param ctx the parse tree
	 */
	void enterTheory(TptpParser.TheoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#theory}.
	 * @param ctx the parse tree
	 */
	void exitTheory(TptpParser.TheoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#theory_name}.
	 * @param ctx the parse tree
	 */
	void enterTheory_name(TptpParser.Theory_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#theory_name}.
	 * @param ctx the parse tree
	 */
	void exitTheory_name(TptpParser.Theory_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#creator_source}.
	 * @param ctx the parse tree
	 */
	void enterCreator_source(TptpParser.Creator_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#creator_source}.
	 * @param ctx the parse tree
	 */
	void exitCreator_source(TptpParser.Creator_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#creator_name}.
	 * @param ctx the parse tree
	 */
	void enterCreator_name(TptpParser.Creator_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#creator_name}.
	 * @param ctx the parse tree
	 */
	void exitCreator_name(TptpParser.Creator_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#optional_info}.
	 * @param ctx the parse tree
	 */
	void enterOptional_info(TptpParser.Optional_infoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#optional_info}.
	 * @param ctx the parse tree
	 */
	void exitOptional_info(TptpParser.Optional_infoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#useful_info}.
	 * @param ctx the parse tree
	 */
	void enterUseful_info(TptpParser.Useful_infoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#useful_info}.
	 * @param ctx the parse tree
	 */
	void exitUseful_info(TptpParser.Useful_infoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#info_items}.
	 * @param ctx the parse tree
	 */
	void enterInfo_items(TptpParser.Info_itemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#info_items}.
	 * @param ctx the parse tree
	 */
	void exitInfo_items(TptpParser.Info_itemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#info_item}.
	 * @param ctx the parse tree
	 */
	void enterInfo_item(TptpParser.Info_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#info_item}.
	 * @param ctx the parse tree
	 */
	void exitInfo_item(TptpParser.Info_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#formula_item}.
	 * @param ctx the parse tree
	 */
	void enterFormula_item(TptpParser.Formula_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#formula_item}.
	 * @param ctx the parse tree
	 */
	void exitFormula_item(TptpParser.Formula_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#description_item}.
	 * @param ctx the parse tree
	 */
	void enterDescription_item(TptpParser.Description_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#description_item}.
	 * @param ctx the parse tree
	 */
	void exitDescription_item(TptpParser.Description_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#iquote_item}.
	 * @param ctx the parse tree
	 */
	void enterIquote_item(TptpParser.Iquote_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#iquote_item}.
	 * @param ctx the parse tree
	 */
	void exitIquote_item(TptpParser.Iquote_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#inference_item}.
	 * @param ctx the parse tree
	 */
	void enterInference_item(TptpParser.Inference_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#inference_item}.
	 * @param ctx the parse tree
	 */
	void exitInference_item(TptpParser.Inference_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#inference_status}.
	 * @param ctx the parse tree
	 */
	void enterInference_status(TptpParser.Inference_statusContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#inference_status}.
	 * @param ctx the parse tree
	 */
	void exitInference_status(TptpParser.Inference_statusContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#status_value}.
	 * @param ctx the parse tree
	 */
	void enterStatus_value(TptpParser.Status_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#status_value}.
	 * @param ctx the parse tree
	 */
	void exitStatus_value(TptpParser.Status_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#inference_info}.
	 * @param ctx the parse tree
	 */
	void enterInference_info(TptpParser.Inference_infoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#inference_info}.
	 * @param ctx the parse tree
	 */
	void exitInference_info(TptpParser.Inference_infoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#assumptions_record}.
	 * @param ctx the parse tree
	 */
	void enterAssumptions_record(TptpParser.Assumptions_recordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#assumptions_record}.
	 * @param ctx the parse tree
	 */
	void exitAssumptions_record(TptpParser.Assumptions_recordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#refutation}.
	 * @param ctx the parse tree
	 */
	void enterRefutation(TptpParser.RefutationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#refutation}.
	 * @param ctx the parse tree
	 */
	void exitRefutation(TptpParser.RefutationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#new_symbol_record}.
	 * @param ctx the parse tree
	 */
	void enterNew_symbol_record(TptpParser.New_symbol_recordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#new_symbol_record}.
	 * @param ctx the parse tree
	 */
	void exitNew_symbol_record(TptpParser.New_symbol_recordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#new_symbol_list}.
	 * @param ctx the parse tree
	 */
	void enterNew_symbol_list(TptpParser.New_symbol_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#new_symbol_list}.
	 * @param ctx the parse tree
	 */
	void exitNew_symbol_list(TptpParser.New_symbol_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#principal_symbol}.
	 * @param ctx the parse tree
	 */
	void enterPrincipal_symbol(TptpParser.Principal_symbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#principal_symbol}.
	 * @param ctx the parse tree
	 */
	void exitPrincipal_symbol(TptpParser.Principal_symbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(TptpParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(TptpParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#formula_selection}.
	 * @param ctx the parse tree
	 */
	void enterFormula_selection(TptpParser.Formula_selectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#formula_selection}.
	 * @param ctx the parse tree
	 */
	void exitFormula_selection(TptpParser.Formula_selectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#name_list}.
	 * @param ctx the parse tree
	 */
	void enterName_list(TptpParser.Name_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#name_list}.
	 * @param ctx the parse tree
	 */
	void exitName_list(TptpParser.Name_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#general_term}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_term(TptpParser.General_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#general_term}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_term(TptpParser.General_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#general_data}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_data(TptpParser.General_dataContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#general_data}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_data(TptpParser.General_dataContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#general_function}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_function(TptpParser.General_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#general_function}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_function(TptpParser.General_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#formula_data}.
	 * @param ctx the parse tree
	 */
	void enterFormula_data(TptpParser.Formula_dataContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#formula_data}.
	 * @param ctx the parse tree
	 */
	void exitFormula_data(TptpParser.Formula_dataContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#general_list}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_list(TptpParser.General_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#general_list}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_list(TptpParser.General_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#general_terms}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_terms(TptpParser.General_termsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#general_terms}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_terms(TptpParser.General_termsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(TptpParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(TptpParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#atomic_word}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_word(TptpParser.Atomic_wordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#atomic_word}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_word(TptpParser.Atomic_wordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#atomic_defined_word}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_defined_word(TptpParser.Atomic_defined_wordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#atomic_defined_word}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_defined_word(TptpParser.Atomic_defined_wordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#atomic_system_word}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_system_word(TptpParser.Atomic_system_wordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#atomic_system_word}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_system_word(TptpParser.Atomic_system_wordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(TptpParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(TptpParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link TptpParser#file_name}.
	 * @param ctx the parse tree
	 */
	void enterFile_name(TptpParser.File_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TptpParser#file_name}.
	 * @param ctx the parse tree
	 */
	void exitFile_name(TptpParser.File_nameContext ctx);
}