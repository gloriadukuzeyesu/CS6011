"use strict";


let canvases = document.getElementsByTagName('canvas');
let canvas = canvases [0];

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;
let context = canvas.getContext('2d');
let cWidth = canvas.width;
let cHeight = canvas.height;

let pot = {};
pot.xPos = 10;
pot.yPos = 10;
pot.Width = 40;
pot.Height = 40;
pot.imag = new Image ();
pot.imag.src ="pot.png";

let bees = [];
for ( let i =0; i< 6; i++ ) { 
    let bee1 = {};
    bee1.xPos = 0;
    bee1.yPos = 0;
    bee1.Width = 50;
    bee1.Height = 50;
    bee1.imag = new Image ();
    bee1.imag.src ="bumblebee.png";
    bees.push(bee1);
}

for ( let i= 0; i<bees.length; i++ ) {
    bees[i].xPos = Math.random() * cWidth;
    bees[i].yPos = Math.random() * cHeight;
}

console.log(bees);

//game over pic 
let gameOver = {};
gameOver.xPos = 30;
gameOver.yPos = 30;
gameOver.Width = 50;
gameOver.Height = 50;
gameOver.imag = new Image ();
gameOver.imag.src ="game over.png";

function main () {  
window.requestAnimationFrame (animate);
}

window.onload = main;

function animate () {
 
    erase();
    context.drawImage (pot.imag, pot.xPos, pot.yPos,pot.Width,pot.Height);
    for ( let bee of bees )  {
        context.drawImage ( bee.imag, bee.xPos, bee.yPos, bee.Width, bee.Height);
    }
    moveBees();
    stop();
    window.requestAnimationFrame (animate);
}

function moveBees () 
{
    for ( let bee of bees )
    {
        if ( pot.xPos > bee.xPos ){
            bee.xPos  += Math.random() * 10;
        } else {
            bee.xPos -= Math.random() * 10;
        }

        if ( pot.yPos > bee.yPos) {
            bee.yPos += Math.random() * 10;

        } else {
            bee.yPos -= Math.random() * 10;
        }

    }
}

function stop () {
    let distance ;
    for ( let bee of bees ) {
        distance = Math .sqrt ( Math .pow( (pot.xPos - bee.xPos), 2) +  Math .pow( (pot.yPos - bee.yPos), 2) );
        if ( distance < 20) {
            gameOver.xPos = pot.xPos;
            gameOver.yPos = pot.yPos;
            erase();
            context.drawImage (gameOver.imag, gameOver.xPos, gameOver.yPos,90,90);  
            window.stop();
        }
    }
}

function erase () {
    context.fillStyle = "yellow" ;
    context.fillRect ( 0, 0, cWidth, cHeight);
}

function handledragPot (e) {
    pot.xPos= e.x -40;
    pot.yPos= e.y-90;
}

document.onmousemove = handledragPot;
