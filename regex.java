(* A list of uppercase and lowercase letters. *)
letter = "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" | "J" | "K" | "L" | "M"
	   | "N" | "O" | "P" | "Q" | "R" | "S" | "T" | "U" | "V" | "W" | "X" | "Y" | "Z"
	   | "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m"
	   | "n" | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z";

(* Rules for all numbers. *)
sign = ["+" | "-"];

(* Rules for binary numbers. *)
binary_digit = "0" | "1";
binary_prefix = "0b";
binary_number = sign, binary_prefix, binary_digit, {binary_digit};

(* Rules for octal numbers. *)
octal_digit = binary_digit | "2" | "3" | "4" | "5" | "6" | "7";
octal_prefix = "0o";
octal_number = sign, octal_prefix, octal_digit, {octal_digit};

(* Rules for decimal numbers. *)
decimal_digit = octal_digit | "8" | "9";
decimal_number = sign, decimal_digit, {decimal_digit};

(* Rules for hexidecimal numbers. *)
hex_digit = decimal_digit | "A" | "B" | "C" | "D" | "E" | "F" | "a" | "b" | "c" | "d" | "e" | "f";
hex_prefix = "0x";
hex_number = sign, hex_prefix, hex_digit, {hex_digit};

(* Rules for string literal characters. *)
string_chars_double_quote = ? Any character except double quote ?;
string_chars_single_quote = ? Any character except single quote ?;

(* Rules defining basic type literals. *)
int_literal = binary_number | octal_number | decimal_number | hex_number;
real_literal = decimal_number, ".", decimal_number;
bool_literal = "true" | "false";
string_literal = ("'", {string_chars_single_quote}, "'") | ('"', {string_chars_double_quote}, '"');
literal_value = int_literal | real_literal | bool_literal | string_literal;

(* Rules for variables *)
var_name = letter, {letter | number | "_"};
var_or_literal = var_name | literal_value;
var_or_int = var_name | int_literal;
var_type = "int" | "real" | "string" | "bool";

(* Rules for statements *)
statement_block = "{", {statement} "}";
statement = statement_flow | (statement_function_call | statement_var_declare | statement_var_assign | statement_comment), ";";
statement_function_call = var_name, "(", [var_or_literal, {",", var_or_literal}], ")";
statement_var_declare = ["const"], var_type, "=", expression;
statement_var_assign = var_name, ("=" | "+=" | "-=" | "*=" | "/=" | "%=" | "**="), expression;
statement_comment = comment;
statement_flow = statement_if | statement_for | statement_while;

(* Rules for Flow Control *)
statement_if = "if", "(", expression, ")", statement_block, [else, statement_block];
statement_for = "for", "(", [var_name, "="], var_or_int, ("to" | "downto"), var_or_int, ["step", var_or_int], ")", statement_block;
statement_while = "while", "(", expression, ")", statement_block;

(* Rules for operators *)
operator_1 = sign | "||";
operator_2 = "*" | "/" | "%" | "&&";
operator_3 = "**";
operator_comparison = "==" | "!=" | "<" | "<=" | ">=" | ">";

(* Rules for Expressions *)
expression = expression_1, [operator_comparison, expression_1];
expression_1 = [sign], expression_2, {operator_1, expression_2};
expression_2 = expression_3, {operator_2, expression_3};
expression_3 = var_or_literal | "(", expression, ")" | "!", expression_3 | expression_3, operator_3, expression3;

(* Rules for function declarations *)
parameter_list = [parameter, {",", parameter}, {"," default_parameter}] | [default_parameter, {",", default_parameter}];
parameter = data_type, var_name;
default_parameter = parameter, "=", literal_value;
function_def = "function", var_name, "(", parameter_list, ")", statement_block;

(* Rules for comment declarations *)
comment_block = "/*", {? Any character sequence not containing */ ?}, "*/";
comment_single = "//", [{? Any character except \n ?}, "\n"];
comment = comment_block | comment_single;

(* Rules for the program *)
program = {comment | function_def | statement_var_assign};