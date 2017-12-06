/* Some useful global variables. */
var totalLevels = 7;
var gridSideLength = (totalLevels * 2) - 1;
var midRow = Math.floor(gridSideLength/2);
var midCol = midRow;

function initializeGrid(level){

  var grid = new Array(gridSideLength);

  for(var i=0; i<gridSideLength ;i++){
    grid[i] = new Array(gridSideLength);

    for(var j=0; j<gridSideLength ;j++){
      grid[i][j] = 0;
    }
  }

  grid[midRow][midCol] = 1;

  return grid;
}

function move(direction, levelSideLength){

}

function fillLevel(grid, level){
  if(level == 1) return;

  var levelSideLength = (level * 2) - 1;
  var levelMaxValue = Math.pow(levelSideLength,2);
  var levelMinValue = levelMaxValue - ((level-1) * 8) + 1;

  var currentRow = midRow;
  if(level > 2) { currentRow += level-2; }
  var currentCol = midCol + level - 1;
  var currentNum = levelMinValue;

  /* TODO
     Get rid of the repetitive code, write a move(direction) function.
  */

  /* Move up. */
  for(var i=0; i<levelSideLength-1 ;i++){
    grid[currentRow][currentCol] = currentNum;
    currentNum++;
    if(i+1 < levelSideLength-1){
      currentRow--;
    }
  }
  currentCol--;
  /* Move left. */
  for(var i=0; i<levelSideLength-1 ;i++){
    grid[currentRow][currentCol] = currentNum;
    currentNum++;
    if(i+1 < levelSideLength-1){
      currentCol--;
    }
  }
  currentRow++;
  /* Move down. */
  for(var i=0; i<levelSideLength-1 ;i++){
    grid[currentRow][currentCol] = currentNum;
    currentNum++;
    if(i+1 < levelSideLength-1){
      currentRow++;
    }
  }
  currentCol++;
  /* Move right. */
  for(var i=0; i<levelSideLength-1 ;i++){
    grid[currentRow][currentCol] = currentNum;
    currentNum++;
    currentCol++;
  }
}

function generateBasicGrid(grid){
  for(var currentLevel=1; currentLevel<=totalLevels ;currentLevel++){
    fillLevel(grid, currentLevel);
  }
}

var grid = initializeGrid(totalLevels);
generateBasicGrid(grid);

console.log(grid);
