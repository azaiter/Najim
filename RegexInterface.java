public interface RegexInterface {
        //(* A list of uppercase and lowercase letters. *)
        String letter = "(A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)";

        //(* Rules for all numbers. *)
        String sign = "((\\+|\\-)?)";

        //(* Rules for binary numbers. *)
        String binary_digit = "(0|1)";
        String binary_prefix = "(0b)";
        String binary_number = "(" + sign + binary_prefix + "(" + binary_digit + "+?))";

        //(* Rules for octal numbers. *)
        String octal_digit = "(" + binary_digit + "|2|3|4|5|6|7)";
        String octal_prefix = "(0o)";
        String octal_number = "(" + sign + octal_prefix + "(" + octal_digit + "+?))";

        //(* Rules for decimal numbers. *)
        String decimal_digit = "(" + octal_digit + "|8|9)";
        String decimal_number = "(" + sign + "(" + decimal_digit + "+?))";

        //(* Rules for hexidecimal numbers. *)
        String hex_digit = "(" + decimal_digit + "|A|B|C|D|E|F|a|b|c|d|e|f)";
        String hex_prefix = "(0x)";
        String hex_number = "(" + sign + hex_prefix + "(" + hex_digit + "+?))";

        //(* Rules for string literal characters. *)
        String string_chars_double_quote = "((?:[^\\\"]|\\\")*)";
        String string_chars_single_quote = "((?:[^\\']|\\')*)";

        //(* Rules defining basic type literals. *)
        String int_literal = "(" + binary_number + "|" + octal_number + "|" + decimal_number + "|" + hex_number + ")";
        String real_literal = "(" + decimal_number + "." + decimal_number + ")";
        String bool_literal = "true|false";
        String string_literal = "(('" + string_chars_single_quote + "')" + "|(\"string_chars_double_quote\"))";
        String literal_value = "(" + int_literal + "|" + real_literal + "|" + bool_literal + "|" + string_literal + ")";

        //(* Rules for variables *)
        String var_name = "(" + letter + "((" + letter + "|" + decimal_digit + "|_)*))";
        String var_or_literal = "(" + var_name + "|" + literal_value + ")";
        String var_or_int = "(" + var_name + "|" + int_literal + ")";
        String var_type = "(int|real|string|bool)";

        
        //(* Rules for operators *)
        String operator_1 = "(" + sign + "|" + "\\|\\|)";
        String  operator_2 = "(*|\\/|%|&&)";
        String operator_3 = "(\\*\\*)";
        String operator_comparison = "(==|!=|<|<=|>=|>)";
        
        //(* Rules for function declarations *)
        String parameter = "(" + var_type + var_name + ")";
        String default_parameter = "(" + parameter + "=" + literal_value + ")";
        String parameter_list = "(" + "((" + parameter + "((," + parameter + ")*)" + "((," + default_parameter + ")*)" + ")?)" + "|" + "((" + default_parameter + "((," + default_parameter + ")*)" + ")?))";
}
