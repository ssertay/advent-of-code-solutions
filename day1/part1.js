var fs = require('fs');

var input = fs.readFileSync('./input.txt').toString('utf8');
input = input.substring(0, input.length - 1);
var result = 0;

for(var i=0; i < input.length ;i++) {
  var current = parseInt(input[i]);
  var next = parseInt(input[(i+1) % input.length]);

  if(current == next){ result += current; }
}

console.log(result);
