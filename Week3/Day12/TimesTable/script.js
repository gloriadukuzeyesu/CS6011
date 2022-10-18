"use strict";
document.body.style.backgroundColor = "pink";
document.body.setAttribute("id", "backgroundID");

let header1 = document.createElement("h1");
header1.textContent = " Lab 8: Time Table ";
document.body.appendChild( header1 );

// create the whole table 
let table = document.createElement("TABLE");
table.setAttribute("id", "tableID");
table.style.borderColor = "green";
table.style.fontSize= "40px";
table.border = "6px, solid";

for( let i = 1; i <= 10; i++  ) {
    let row1 = document.createElement("tr");
    
    for (let j=1; j <= 10; j++ ) {
        let tableData1 = document.createElement("td");
        tableData1.addEventListener("click", highlightCell);

        tableData1.setAttribute("class", "cell_Class");
        let dataContent = document.createTextNode(i*j);
        tableData1.appendChild(dataContent);
        row1.appendChild( tableData1 );
    }
table.appendChild(row1);
}
document.body.appendChild(table);

// functions 
function highlightTable() {
    let  table = document.getElementById("tableID");
    table.style.backgroundColor = "yellow";
    table.style.fontWeight= "bold";
}
table.addEventListener("click", highlightTable);

function highlightCell() {
    let cell = document.getElementsByTagName ("td");
    for( let i= 0; i<cell.length; i++){
        cell[i].style.backgroundColor = "transparent";
    }
    this.style.backgroundColor = "red";
} 

// create a button at the end of the file 
let button1 = document.createElement("button");
button1.textContent="Click here to toggle background color";
button1.style.padding = "10px";
button1.style.margin = "20px";
button1.style.backgroundColor = "lightblue";
button1.style.fontSize = "20px";
button1.addEventListener ("click", animateBackground);
document.body.appendChild(button1);

let count = 0;
function animateBackground () {
    let doc = document.getElementById ("backgroundID");
    let color = ["blue", "yellow", "pink", "brown", "red"];
    doc.style.backgroundColor = color[count];
    count++;
    if ( count > color.length){
        count = 0;
    }
    window.setInterval(animateBackground,1000);
}
