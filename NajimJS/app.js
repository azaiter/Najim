var Tokenizer = require('./TokenizerIndex');
var RegexInterface = require("./RegexInterface.js");
var fs = require('fs');
var t = new Tokenizer();

// define tokenizer rules in regex that are equally equivalent to DFAs.
t.addRule(/^(\s|\r|\n)+$/, 'WHITE_SPACE');
t.addRule(/^bool$/, 'KEYWORD_BOOLEAN');
t.addRule(/^int$/, 'KEYWORD_INT');
t.addRule(/^real$/, 'KEYWORD_REAL');
t.addRule(/^string$/, 'KEYWORD_STRING');
t.addRule(/^if$/, 'KEYWORD_IF');
t.addRule(/^else$/, 'KEYWORD_ELSE');
t.addRule(/^for$/, 'KEYWORD_FOR');
t.addRule(/^while$/, 'KEYWORD_WHILE');
t.addRule(/^break$/, 'KEYWORD_BREAK');
t.addRule(/^continue$/, 'KEYWORD_CONTINUE');
t.addRule(/^function$/, 'KEYWORD_FUNCTION');
t.addRule(/^return$/, 'KEYWORD_RETURN');
t.addRule(/^=$/, 'OP_ASSIGN');
t.addRule(/^\+$/, 'OP_ADD');
t.addRule(/^\+=$/, 'OP_ADD_ASSIGN');
t.addRule(/^-$/, 'OP_SUB');
t.addRule(/^-=$/, 'OP_SUB_ASSIGN');
t.addRule(/^\*$/, 'OP_MUL');
t.addRule(/^\*=$/, 'OP_MUL_ASSIGN');
t.addRule(/^\\$/, 'OP_DIV');
t.addRule(/^\\=$/, 'OP_DIV_ASSIGN');
t.addRule(/^\%$/, 'OP_MOD');
t.addRule(/^\%=$/, 'OP_MOD_ASSIGN');
t.addRule(/^\*\*$/, 'OP_POW');
t.addRule(/^\*\*=$/, 'OP_POW_ASSIGN');
t.addRule(/^\&\&$/, 'OP_AND');
t.addRule(/^\&\&=$/, 'OP_AND_ASSIGN');
t.addRule(/^\|\|$/, 'OP_OR');
t.addRule(/^\|\|=$/, 'OP_OR_ASSIGN');
t.addRule(/^\!$/, 'OP_NOT');
t.addRule(/^<$/, 'OP_LESS');
t.addRule(/^<=$/, 'OP_LESS_EQUAL');
t.addRule(/^==$/, 'OP_EQUAL');
t.addRule(/^\!=$/, 'OP_NOT_EQUAL');
t.addRule(/^>=$/, 'OP_GREATER_EQUAL');
t.addRule(/^>$/, 'OP_GREATER');
t.addRule(/^[#]+.*$/, "COMMENT_SINGLE")
t.addRule(/^\[$/, "OPEN_BRACKET")
t.addRule(/^]$/, "CLOSE_BRACKET")
t.addRule(/^\($/, "OPEN_PARENTHESIS")
t.addRule(/^\)$/, "CLOSE_PARENTHESIS")
t.addRule(/^{$/, "OPEN_BRACE")
t.addRule(/^}$/, "CLOSE_BRACE")
t.addRule(/^\,$/, "LIST_SEPARATOR")
t.addRule(/^;$/, "TERMINAL")
t.addRule(new RegExp("^"+RegexInterface.RegexInterface.bool_literal+"$"), 'REAL_LITERAL');
t.addRule(new RegExp("^"+RegexInterface.RegexInterface.int_literal+"$"), 'INTEGER_LITERAL');
t.addRule(new RegExp("^"+RegexInterface.RegexInterface.real_literal+"$"), 'REAL_LITERAL');
t.addRule(/^"([^"]|\\")*"$/, 'STRING_LITERAL');

// events handlers on buffer raised events
t.on('data', function(token){
    if(token.type != "WHITE_SPACE")
        console.log('token:', token);
});

t.on('end', function(){
    console.log('Finished parsing Najim source file.');
});
   
//pipe in some data (it can be from anywhere ie: network buffer, filestream, socket, websocket, array buffer.. etc )
fs.createReadStream('./app.nj').pipe(t);
