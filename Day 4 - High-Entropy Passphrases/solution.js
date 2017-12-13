var fs = require('fs');

var input = fs.readFileSync('./input.txt').toString('utf8');
input = input.substring(0, input.length - 1);

var phrases = input.split('\n');

/* part 1. */
/* Uses a set to get O(n) solution for each line. */
function checkValidityPart1(phrase){
  var words = phrase.split(" ");
  var set = new Set();

  for(var i=0; i<words.length ;i++){

    if(set.has(words[i])){
      return false;
    } else {
      set.add(words[i]);
      if(i == words.length - 1){
        return true;
      }
    }
  }
}

/*  part 2 helper function.
    this assumes that the words are lower case letters.
*/
function isAnagram(word1, word2) {
  if(word1.length != word2.length) return false;

  /* An array of 26 for every letter. */
  var alphabet = new Array(26);

  /* Increment the letter count for each letter in word1. */
  for(var i=0; i<word1.length ;i++) {
    alphabet[word1[i]charCodeAt(0) - 97]++;
  }

  /* Decrement the letter count for each letter in word2. */
  for(var i=0; i<word2.length ;i++) {
    alphabet[word2[i].charCodeAt(0) - 97]++;
  }

  /* Check if every item in alphabet array is 0. */
  for(var i=0; i<alphabet.length ;i++){
    if(alphabet[i] != 0) return false;
  }

  return true;
}

function checkValidityPart2(phrase){
  var words = phrase.split(" ");
  var set = new Set();

  for(var i=0; i<words.length ;i++){
    var sortedWord = words[i].split('').sort().join('');

    if(set.has(sortedWord)){
      return false;
    } else {
      set.add(sortedWord);
      if(i == words.length - 1){
        return true;
      }
    }
  }
}

function countValidPhrases(phrases) {
  var result = 0;

  for(var i=0; i<phrases.length ;i++){
    if(checkValidityPart2(phrases[i])){
      result++;
    }
  }

  return result;
}

console.log(countValidPhrases(phrases));
