var fs = require('fs');
var lines = fs.readFileSync('./input.txt').toString('utf8').split('\n');

function part1(lines) {
  var result = 0;

  for(var i=0; i < lines.length-1 ;i++){
    var min = Number.MAX_SAFE_INTEGER;
    var max = Number.MIN_SAFE_INTEGER;
    var nums = lines[i].split('\t');

    for(var j=0; j < nums.length ;j++){

      if(parseInt(nums[j]) > max){
        max = parseInt(nums[j]);
      }

      if(parseInt(nums[j]) < min){
        min = parseInt(nums[j]);
      }
    }

    result += (max - min);
  }

  return result;
}

function part2(lines) {
  var result = 0;

  for(var i=0; i < lines.length-1 ;i++){
    var nums = lines[i].split('\t');

    for(var j=0; j < nums.length ;j++){
      for(var k=0; k < nums.length ;k++){

        var num1 = parseInt(nums[j]);
        var num2 = parseInt(nums[k]);

        if(num1 != num2) {
          if(num1 % num2 == 0){
            result += num1/num2;
          } else if (num2 % num1 == 0){
            result += num2/num1;
          }
        }
      }
    }
  }

  return result/2;
}

console.log("Solution to Part 1 is: " + part1(lines));
console.log("Solution to Part 2 is: " + part2(lines));
