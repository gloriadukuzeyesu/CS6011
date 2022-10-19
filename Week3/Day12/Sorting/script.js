"use strict";

// find the smallestIndex 
function findMinLocation( array, iteration) {
    let smallestIndex = iteration;
    for ( let i = iteration + 1; i < array.length; i ++) {
        if ( array[i] < array[smallestIndex ] ) {
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

// test if my function work
let myarr = [-9,4,8,90,3,8,-19];
selectionSort( myarr );
console.log (myarr);

// floats and integers 
let array2 = [-9.9,4.6,8.5,90.1,3.5,8.66,-19.6];
selectionSort( array2 );
console.log (array2);

// floats and integers and strings 
let array3 = ["cats",4.6,8.5,"mouse", "dogs", 5]; // the fx isn't properly sorting with strings 
selectionSort( array3 );
console.log (" Floats and integers and strings:  " + array3); 

let array4 = [ "mouse", "CATS","dogS"]; 
selectionSort( array4 );
console.log (array4);

// add a comparator 

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
let person3 = { firstName: "Christian", lastName:"Green"};
let person5 = { firstName: "Christian", lastName:"Adam"};


let people = [person1, person4,person2,person3,person5];

selectionSortWithCompare(people);
console.log(people);



















/*

//my notes 
//  function that sort straight // selection sort 
function sortingArray ( array ) {
    for ( let i = 0; i< array.length; i++ ) {
        let minVal = array[i];
        let minIndex = i;
        for ( let j = i+1; j < array.length; j++ ) {
            if ( array[j] < minVal ) {
                minVal = array[j];
                minIndex = j;
            }
        }
        //do the swapping 
        let temp = array[i];
        array[i] = minVal;
        array[minIndex] = temp;
    }
}

let myarr2 = [-9,4,8,90,3,8,-19];
sortingArray( myarr2 );
console.log( myarr2);

let array10 = ["mouse", "cats", "dogs"]; 
sortingArray( array10 );
console.log( array10);

*/







