var core = require("./TokenizerCore");
var through2 = require("through2");

// export tokenizer function based on tokenizer core module.
module.exports = function(){
  // define t to push tokens into tokens stream
  var t = core(function(token){
    token_stream.push(token);
  });

  // define events for the core stream tokenizer module.
  var token_stream = through2.obj(function(chunk, enc, done){
    t.onText(chunk.toString());
    done();
  }, function(done){
    try{
      t.end();
      done();
    }catch(err){
      done(err);
    }
  });

  // define addRule function singleton.
  token_stream.addRule = t.addRule;
  return token_stream;
};
