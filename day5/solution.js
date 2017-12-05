var fs = require('fs');

var input = fs.readFileSync('./input.txt').toString('utf8');
var numArray = input.substring(0, input.length - 1).split('\n');


for(var i=0; i<numArray.length ;i++){
  numArray[i] = parseInt(numArray[i]);
}

var index = 0;
var steps = 0;

while(index != numArray.length) {

  var prevIndex = index;

  index += numArray[prevIndex];
  steps++;

  if(numArray[prevIndex] > 2){
    numArray[prevIndex]--;
  } else { /* part 2 */
    numArray[prevIndex]++;
  }

}

console.log(steps);
