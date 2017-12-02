var fs = require('fs');

var input = fs.readFileSync('./input.txt').toString('utf8');
input = input.substring(0, input.length - 1);
var result = 0;
var len = input.length;

for(var i=0; i < len ;i++) {
  var current = parseInt(input[i]);
  var next = parseInt(input[(i+(len/2)) % len]);

  if(current == next){ result += current; }
}

console.log(result);
