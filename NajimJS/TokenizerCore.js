// Credits to farskipper from github for basic idea.
// a function to find matching regex rule from a specefic text buffer
var findMatchingRule = function(rules, text){
  var i;
  for(i=0; i<rules.length; i++)
    if(rules[i].regex.test(text)){
      return rules[i];
    }
  return undefined;
};

// a function that identifies string's max index for next and current token of a specefic text
// to allow the system to log and identify upcoming tokens
var findMaxIndexAndRule = function(rules, text){
  var i, rule, last_matching_rule, delta;
  delta = 0;
  for(i=0; i<text.length; i++){
    rule = findMatchingRule(rules, text.substring(0, i + 1));
    rule2 = findMatchingRule(rules, text.substring(i, i + 1));
    rule3 = findMatchingRule(rules, text.substring(0, i + 3));

    if(rule && !rule3)
      last_matching_rule = rule;
    else if(last_matching_rule)
      return {max_index: i+delta, rule: last_matching_rule};

    if(!rule && !rule3 && rule2 && !text.substring(0, i + 1).includes(`"`)){
      last_matching_rule = {"regex":{},"type":"IDENTIFIER"}
      delta = -1;
    }
  }
  return last_matching_rule ? {max_index: text.length, rule: last_matching_rule} : undefined;
};

// export above functions as module and assign events.
module.exports = function(onToken_orig){
  var buffer = "";
  var rules = [];
  var line = 1;
  var col = 1;

  // onToken event.
  var onToken = function(src, type){
    onToken_orig({
      type: type,
      src: src,
      line: line,
      col: col
    });
    var lines = src.split("\n");
    line += lines.length - 1;
    col = (lines.length > 1 ? 1 : col) + lines[lines.length - 1].length;
  };

  return {
    // adding regex rules function.
    addRule: function(regex, type){
      rules.push({regex: regex, type: type});
    },

    // event template handler of incoming text
    onText: function(text){
      var str = buffer + text;
      var m = findMaxIndexAndRule(rules, str);
      while(m && m.max_index !== str.length){
        onToken(str.substring(0, m.max_index), m.rule.type);
        //now find the next token
        str = str.substring(m.max_index);
        m = findMaxIndexAndRule(rules, str);
      }
      buffer = str;
    },
    
    // event to handle last token or throw an error otherwise.
    end: function(){
      if(buffer.length === 0)
        return;

      var rule = findMatchingRule(rules, buffer);
      if(!rule){
        var err = new Error("unable to tokenize given buffer");
        err.tokenizer2 = {
          buffer: buffer,
          line: line,
          col: col
        };
        throw err;
      }
      onToken(buffer, rule.type);
    }
  };
};
