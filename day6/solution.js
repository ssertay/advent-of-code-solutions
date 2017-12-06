var inputArray = [ 4, 1, 15, 12, 0, 9, 9, 5, 5, 8, 7, 3, 14, 5, 12, 3 ];

function findMaxIndex(array){
  var max = array[0];
  var index = 0;

  for(var i=1; i<array.length ;i++){
    if(array[i] > max){
      max = array[i];
      index = i;
    }
  }

  return index;
}

var redistributed = 0;
var set = new Set();

var seen = false;
var secondCounter = 0;
var lookFor = "";

set.add(String(inputArray));

while(1){
  var maxIndex = findMaxIndex(inputArray);

  var count = inputArray[maxIndex];
  inputArray[maxIndex] = 0;

  var i = (maxIndex + 1) % inputArray.length;
  while(count > 0){
    inputArray[i]++;
    count--;
    i = (i+1) % inputArray.length;
  }

  redistributed++;

  if(seen){
    secondCounter++;
  }

  if(set.has(String(inputArray))){
    if(!seen){
      console.log("First time looped at : " + redistributed);
      set.clear();
      set.add(String(inputArray));
      lookFor = String(inputArray);
      seen = true;
    } else if(seen){
      console.log("Second time looped at : " + secondCounter);
      break;
    }
  }
  else{
    set.add(String(inputArray));
  }
}
