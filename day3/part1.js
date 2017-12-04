if(process.argv.length != 3) {
  throw new Error("Pass the input as the first argument.");
}

var input = process.argv[2];

var n = Math.ceil( Math.sqrt(input) ) | 1;
var biggestNumberOnLayer = Math.pow(n, 2);
var layer = Math.floor(n/2);

var mid1 = biggestNumberOnLayer - (n-1)/2;
var mid2 = (biggestNumberOnLayer - (n-1)) - (n-1)/2;
var mid3 = (biggestNumberOnLayer - (n-1)*2) - (n-1)/2;
var mid4 = (biggestNumberOnLayer - (n-1)*3) - (n-1)/2;

var edgeDistance = Math.min(
  Math.abs(input - mid1),
  Math.abs(input - mid2),
  Math.abs(input - mid3),
  Math.abs(input - mid4)
);

var manhattanDistance = layer + edgeDistance;

console.log(manhattanDistance);
