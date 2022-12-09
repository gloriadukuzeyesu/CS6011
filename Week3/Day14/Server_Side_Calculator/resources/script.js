"use strict";
/**************** AJAX version *****************/
let xTA = document.getElementById( "x-textArea");
let yTA = document.getElementById( "y-textArea");
let resultTA = document.getElementById( "resultTA_ID");
let WSresultTA = document.getElementById( "WSResults_ID");


let button = document.getElementById( "buttonID");

xTA.addEventListener( "keypress", handleKeypressedCB );
yTA.addEventListener( "keypress", handleKeypressedCB );
button.addEventListener( "click", handleKeypressedCB );

/**************** create a webscocket *****************/
let ws = new WebSocket ("ws://localhost:8080" );
ws.onopen = handleConnectCallBack;
// web_scocket.onclose = handleCloseCallBack;
// web_scocket.onerror = handleErrorCallBack;
ws.onmessage = handleMessageCallBack;

// callback functions
let Web_Socket_Open = false;

function handleConnectCallBack (event) {
    Web_Socket_Open = true;
    console.log("it is working");
}

function handleMessageCallBack (event) {
    // 9 is the result 
    WSresultTA.value = event.data; // event.data
}

function handleAjaxSuccessCB () {
    console.log("Got the response from the server ");
    resultTA.value = this.responseText;
}

function handleAjaxErrorCB () {
    console.log("Error occured ");
}

function handleKeypressedCB ( event ) {
    console.log ( "pressed" );

    if ( event.type == "click" || event.keyCode == 13 ) {
        let x = parseFloat ( xTA.value);
        event.preventDefault();
        if ( isNaN (x) ) {
            alert(" please make sure is a number ");
            xTA.value = "<Enter a Number>";
            xTA.select();
            return;
        }   
        let y = parseFloat ( yTA.value);
        event.preventDefault();
        if ( isNaN (y) ) {
            alert(" please make sure is a number ");
            yTA.value = "<Enter y Number>";
            yTA.select();
            return;
        }
         /**************** AJAX version *****************/
        let httpRequest = new XMLHttpRequest();
        httpRequest.open("GET", "http://localhost:8080/calculate?x=" + x + "&y=" + y);
        httpRequest.addEventListener( "error", handleAjaxErrorCB );
        httpRequest.addEventListener( "load", handleAjaxSuccessCB );
        httpRequest.send();

        /**************** create a webscocket *****************/
        if (Web_Socket_Open ) {
            ws.send( x + " " + y); // send the message to the server
            console.log(" ws is open");
        } 
        else{
            WSresultTA.value = "Web socket is not open...";   
        }  
    }    
}