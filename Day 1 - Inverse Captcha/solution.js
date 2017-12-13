var fs = require('fs');

var input = fs.readFileSync('./input.txt').toString('utf8');
input = input.substring(0, input.length - 1);
var len = input.length;

function part1(input) {
  var result = 0;

  for(var i=0; i < input.length ;i++) {
    var current = parseInt(input[i]);
    var next = parseInt(input[(i+1) % input.length]);

    if(current == next){ result += current; }
  }

  return result;
}

function part2(input) {
  var result = 0;
  for(var i=0; i < len ;i++) {
    var current = parseInt(input[i]);
    var next = parseInt(input[(i+(len/2)) % len]);

    if(current == next){ result += current; }
  }

  return result;
}

console.log("Solution to Part 1: " + part1(input));
console.log("Solution to Part 2: " + part2(input));
