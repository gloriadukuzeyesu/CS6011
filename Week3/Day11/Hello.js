console.log("Hello World")
document.writeln("hello")

// with the console.log, the hello word appeared on the Console of the developer tool
// however with document.writeln insert the string to the document 

// you would want to use dconsole.log when you don't want the user to see what's being log in to the console
// since that is considered as another interface and also use console.log if you want to use developer tool to debug or run the test. 
// document.writeln can be used if you want to insert of embed content in the document 

let myObj = 1;
const myArray = [ "Cars", true , 6, 6.99, myObj ];

console.log( myArray );
// prints out "Cars", true , 6, 6.99, 1

let myFunction = function (a,b){
  return a+b;
}

let sum = myFunction(9,10);
console.log("Sum is: " + sum); // prints 19

// I prefer c++ synatax cz it is looks straight forward to me and I don't have to write function keyword

let sumOfFloats = myFunction( 5.66, 9.22);
console.log("Sum of floats is: " + sumOfFloats); // prints 14.88


let sumStrings = myFunction( "Hello", "World");
console.log("Sum of Strings is: " + sumStrings); // concantinattes the strings to HelloWorld 

let mix = myFunction( "hello", 7);
console.log("Sum of mix is: " + mix); // concantinate hello and 7 together  which ends up being hello7












