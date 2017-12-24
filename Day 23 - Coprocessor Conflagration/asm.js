/*
set b 57
set c b
jnz a 2
jnz 1 5
mul b 100
sub b -100000
set c b
sub c -17000
set f 1
set d 2
set e 2
set g d
mul g e
sub g b
jnz g 2
set f 0
sub e -1
set g e
sub g b
jnz g -8
sub d -1
set g d
sub g b
jnz g -13
jnz f 2
sub h -1
set g b
sub g c
jnz g 2
jnz 1 3
sub b -17
jnz 1 -23

    Turns out that this piece of assembly code in part 2 of the puzzle is trying to find the non-primes 
between two numbers very inefficiently.
Below is an implementation that runs in a reasonable amount of time.
Part 1 was only a small modification of Day 18 so i'm omitting that part.
*/

function isPrime(number) {
    for(var i=2; i<number ;i++)
        if(number % i == 0)
            return false;
    return true;
}

function findNonPrimesInBetween(num1, num2) {
    var result = 0;
    while(num1 <= num2){
        if(!isPrime(num1)) {
            result++;
        }
        num1 += 17;
    }
    return result;
}

var x = 105700;
var y = 122700;

console.log(findNonPrimesInBetween(x,y));