// This is an interface to hold all regular expressions.
var RegexInterface;
(function (RegexInterface) {
    RegexInterface.letter = "(A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)";
    RegexInterface.sign = "((\\+|\\-)?)";
    RegexInterface.binary_digit = "(0|1)";
    RegexInterface.binary_prefix = "(0b)";
    RegexInterface.binary_number = "(" + RegexInterface.sign + RegexInterface.binary_prefix + "(" + RegexInterface.binary_digit + "+?))";
    RegexInterface.octal_digit = "(" + RegexInterface.binary_digit + "|2|3|4|5|6|7)";
    RegexInterface.octal_prefix = "(0o)";
    RegexInterface.octal_number = "(" + RegexInterface.sign + RegexInterface.octal_prefix + "(" + RegexInterface.octal_digit + "+?))";
    RegexInterface.decimal_digit = "(" + RegexInterface.octal_digit + "|8|9)";
    RegexInterface.decimal_number = "(" + RegexInterface.sign + "(" + RegexInterface.decimal_digit + "+?))";
    RegexInterface.hex_digit = "(" + RegexInterface.decimal_digit + "|A|B|C|D|E|F|a|b|c|d|e|f)";
    RegexInterface.hex_prefix = "(0x)";
    RegexInterface.hex_number = "(" + RegexInterface.sign + RegexInterface.hex_prefix + "(" + RegexInterface.hex_digit + "+?))";
    RegexInterface.string_chars_double_quote = "((?:[^\\\"]|\\\")*)";
    RegexInterface.string_chars_single_quote = "((?:[^\\\']|\\\')*)";
    RegexInterface.int_literal = "(" + RegexInterface.binary_number + "|" + RegexInterface.octal_number + "|" + RegexInterface.decimal_number + "|" + RegexInterface.hex_number + ")";
    RegexInterface.real_literal = "(" + RegexInterface.decimal_number + "." + RegexInterface.decimal_number + ")";
    RegexInterface.bool_literal = "true|false";
    RegexInterface.string_literal = "((\'" + RegexInterface.string_chars_single_quote + "\')" + "|(\"" + RegexInterface.string_chars_double_quote + "\"))";
    RegexInterface.literal_value = "(" + RegexInterface.int_literal + "|" + RegexInterface.real_literal + "|" + RegexInterface.bool_literal + "|" + RegexInterface.string_literal + ")";
    RegexInterface.var_name = "(" + RegexInterface.letter + "((" + RegexInterface.letter + "|" + RegexInterface.decimal_digit + "|_)*))";
    RegexInterface.var_or_literal = "(" + RegexInterface.var_name + "|" + RegexInterface.literal_value + ")";
    RegexInterface.var_or_int = "(" + RegexInterface.var_name + "|" + RegexInterface.int_literal + ")";
    RegexInterface.var_type = "(int|real|string|bool)";
    RegexInterface.operator_1 = "(" + RegexInterface.sign + "|" + "\\|\\|)";
    RegexInterface.operator_2 = "(*|\\/|%|&&)";
    RegexInterface.operator_3 = "(\\*\\*)";
    RegexInterface.operator_comparison = "(==|!=|<|<=|>=|>)";
    RegexInterface.parameter = "(" + RegexInterface.var_type + RegexInterface.var_name + ")";
    RegexInterface.default_parameter = "(" + RegexInterface.parameter + "=" + RegexInterface.literal_value + ")";
    RegexInterface.parameter_list = "(" + "((" + RegexInterface.parameter + "((," + RegexInterface.parameter + ")*)" + "((," + RegexInterface.default_parameter + ")*)" + ")?)" + "|" + "((" + RegexInterface.default_parameter + "((," + RegexInterface.default_parameter + ")*)" + ")?))";

    RegexInterface.statement_block = "(" + "\{" + "(" + RegexInterface.statement + ")*" + "\})";
    RegexInterface.statement = "(" + RegexInterface.statement_flow +"|"+ "(" + RegexInterface.statement_function_call +"|"+ RegexInterface.statement_var_declare +"|"+ RegexInterface.statement_var_assign +"|" + RegexInterface.statement_comment + ")" + ";)";
    RegexInterface.statement_function_call = "(" + RegexInterface.var_name + "\(" + "(" + RegexInterface.var_or_literal + "("+","+ RegexInterface.var_or_literal + ")*" + ")?" + "\))";
    RegexInterface.statement_var_declare = "(" + "(const)?" + RegexInterface.var_type + "=" + RegexInterface.expression + ")";
    RegexInterface.statement_var_assign = "("+RegexInterface.var_name + "("+"=" +"|"+ "\+=" +"|"+ "\-=" +"|"+ "\*=" +"|"+ "\/=" +"|"+ "\%=" +"|"+ "\*\*="+")"+ RegexInterface.expression+")";
    RegexInterface.statement_comment = RegexInterface.comment;
    RegexInterface.statement_flow = "("+RegexInterface.statement_if + "|"+ RegexInterface.statement_for +"|"+ RegexInterface.statement_while +")";

    RegexInterface.statement_if = "("+"if"+ "\("+ RegexInterface.expression+ "\)"+ RegexInterface.statement_block+ "("+ "else" + RegexInterface.statement_block + ")?)";
    RegexInterface.statement_for = "(" + "for" + "\(" + "("+RegexInterface.var_name+ "="+")?" + RegexInterface.var_or_int + "("+"to"+ "|"+ "downto"+")"+ RegexInterface.var_or_int+ "("+"step"+ RegexInterface.var_or_int+")?"+ "\)"+ RegexInterface.statement_block + ")";
    RegexInterface.statement_while = "("+"while"+ "\("+ RegexInterface.expression+ "\)"+ RegexInterface.statement_block + ")";

    RegexInterface.expression = "(" + RegexInterface.expression_1 + "(" + RegexInterface.operator_comparison + RegexInterface.expression_1 + ")?)";
    RegexInterface.expression_1 = "(("+ RegexInterface.sign +")?" + RegexInterface.expression_2 + "("+RegexInterface.operator_1+ RegexInterface.expression_2+")*)";
    RegexInterface.expression_2 = "("+RegexInterface.expression_3+ "(" + RegexInterface.operator_2 + RegexInterface.expression_3 + ")*)";
    RegexInterface.expression_3 = "("+RegexInterface.var_or_literal+ "|"+ "\("+ RegexInterface.expression+ "\)" + "|"+ "\!" + RegexInterface.expression_3 + "|"+ RegexInterface.expression_3 + RegexInterface.operator_3 + RegexInterface.expression3 + ")";

    RegexInterface.function_def = "("+"function"+ RegexInterface.var_name+ "\("+ RegexInterface.parameter_list + "\)"+ RegexInterface.statement_block + ")";

})(RegexInterface || (RegexInterface = {}));

exports.RegexInterface = RegexInterface;