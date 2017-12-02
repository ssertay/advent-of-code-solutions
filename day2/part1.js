var fs = require('fs');

var result = 0;

var lines = fs.readFileSync('./input.txt').toString('utf8').split('\n');

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

console.log(result);
