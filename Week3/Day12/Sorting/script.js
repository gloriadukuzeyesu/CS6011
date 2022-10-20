"use strict";


function compare (a,b){
    if (a<b)
    return true;
}

// find the smallestIndex 
// function findMinLocation( array, iteration) {
//     let smallestIndex = iteration;
//     for ( let i = iteration + 1; i < array.length; i ++) {
//         if ( array[i] < array[smallestIndex ] ) {
//             smallestIndex = i;
//         }
//     }
//     return smallestIndex;
// }

function findMinLocation( array, iteration) {
    let smallestIndex = iteration;
    for ( let i = iteration + 1; i < array.length; i ++) {
        if ( compare ( array[i], array[smallestIndex ]) ) 
        {
            smallestIndex = i;
        }
        
    }
    return smallestIndex;
}


// fx will take an array and sort it 
function selectionSort( array ) {
    for ( let i = 0; i < array.length; i ++ ) {
        let minIndex = findMinLocation( array, i );
        // do the swap
        let temp = array [i];
        array [i] = array[minIndex];
        array[ minIndex ] = temp;
    }
    
}

console.log ("testing purposes");

// Integers 

console.log("sort integers");
// test if my function work
let myarr = [-9,4,8,90,3,8,-19];
selectionSort( myarr );
console.log (myarr);

console.log("sort floats");
// floats and integers 
let array2 = [-9.9,4.6,8.5,90.1,3.7,8.66,-19.6];
selectionSort( array2 );
console.log (array2);

console.log("sort strings");
let arrayString = ["tomatoes", "apples", "oranges", "banana"];
selectionSort(arrayString);
console.log(arrayString);


console.log("sort floats and strings");
// floats and integers and strings 
let array3 = ["cats",4.6,8.5,"mouse", "dogs", 5]; // the fx isn't properly sorting with strings 
selectionSort( array3 );
console.log ( array3 ); 

console.log("sort strings with capital and small letter");
let array4 = [ "mouse", "CATS","dogS"]; 
selectionSort( array4 );
console.log (array4);

// add a comparator for people 
function compareTo(p1, p2){

    if ( p1.lastName < p2.lastName ){
        return true;
    } else if ( p1.lastName == p2.lastName ){
            if ( p1.firstName < p2.firstName){
                return true;
            }
    }else {
        return false;
    }   
}


function findMinLocationComp( array, iteration){
    let smallestIndex = iteration;
    for ( let i = iteration + 1; i < array.length; i ++) {

        if ( compareTo ( array[i], array[smallestIndex ]) ) 
        {
            smallestIndex = i;
        }
    }
    return smallestIndex;
}


function selectionSortWithCompare( array ) {
    for ( let i = 0; i < array.length; i ++ ) {
        let minIndex = findMinLocationComp( array, i);
        // do the swap
        let temp = array [i];
        array [i] = array[minIndex];
        array[ minIndex ] = temp;
    }
}

let person1 = {firstName: "Gloria",lastName:"Wells"};
let person2 = { firstName: "Angel", lastName: "Brooks"};
let person4 = { firstName: "Zackarie", lastName: "Angeliqua"};
let person3 = { firstName: "Nicole", lastName:"Green"};
let person5 = { firstName: "Christian", lastName:"Green"};

let people = [person1, person4,person2,person3,person5];

selectionSortWithCompare(people);
console.log(people);



function compareToWithFirstName(p1, p2){
    if ( p1.firstName < p2.firstName ){
        return true;
    } else if ( p1.firstName == p2.firstName ){
            if ( p1.lastName < p2.lastName){
                return true;
            }
    }else {
        return false;
    }   
}

function findMinLocationCompWithFirstName( array, iteration){
    let smallestIndex = iteration;
    for ( let i = iteration + 1; i < array.length; i ++) {

        if ( compareToWithFirstName ( array[i], array[smallestIndex ]) ) 
        {
            smallestIndex = i;
        }
    }
    return smallestIndex;
}


function selectionSortWithCompareWithFirstName( array ) {
    for ( let i = 0; i < array.length; i ++ ) {
        let minIndex = findMinLocationCompWithFirstName( array, i);
        // do the swap
        let temp = array [i];
        array [i] = array[minIndex];
        array[ minIndex ] = temp;
    }
}


let personA = {firstName: "Gloria",lastName:"Willow"};
let personB = { firstName: "Angel", lastName: "Brooks"};
let personC = { firstName: "Zack", lastName: "Angela"};
let personD = { firstName: "Christian", lastName:"Green"};
let personE = { firstName: "Christian", lastName:"Gandy"};


let persons = [personA,personB,personE,personC,personD];

console.log(persons);
selectionSortWithCompareWithFirstName(persons); // sorting 
console.log(persons);
