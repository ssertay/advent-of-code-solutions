var fs = require('fs');

var result = 0;

var lines = fs.readFileSync('./input.txt').toString('utf8').split('\n');

for(var i=0; i < lines.length-1 ;i++){
  var nums = lines[i].split('\t');

  for(var j=0; j < nums.length ;j++){
    for(var k=0; k < nums.length ;k++){

      var num1 = parseInt(nums[j]);
      var num2 = parseInt(nums[k]);

      if(num1 != num2) {
        if(num1 % num2 == 0){
          console.log(nums[j] + " / " + nums[k]+" = "+num1/num2);
          result += num1/num2;
        } else if (num2 % num1 == 0){
          console.log(nums[k] + " / " + nums[j]+" = "+num2/num1);
          result += num2/num1;
        }
      }
    }
  }


}

console.log(result/2);
