"use strict";

// html tags
let RoomName = document.getElementById("RM_ID");
let UserName = document.getElementById("User_ID");
let MessageToServer = document.getElementById("Message_ID");

RoomName.addEventListener("keypress", handleKeyPressedCB );
// UserName.addEventListener("keypress", handleKeyPressedCB );
MessageToServer.addEventListener("keypress",handleKeyPressedMessagingServer);

let webSocketIsOpen = false;

function handleKeyPressedCB (event) {
    console.log("key presseed");
    if ( event.keyCode == 13 ){
        let room = RoomName.value;
        event.preventDefault(); // don't create another line after pressing enter
        for (let ch of room) {
            if (!(ch >= 'a' && ch <= 'z')) {
                alert("Incorrect format. The room name should be lowercase and no space!");
                return ;
            }
        }
        if (webSocketIsOpen){
            ws.send(`join ${UserName.value} ${RoomName.value}`);
            console.log(UserName.value + " " + RoomName.value);
            }else{ 
                alert("websocket is not open.");
        }
    }
}

// sending the message to the server
function handleKeyPressedMessagingServer(event) {
    if(event.keyCode == 13 ) {
        console.log("messaging the server");
        console.log(MessageToServer.value);
        ws.send( UserName.value + " " + MessageToServer.value);
        MessageToServer.value= "";
        event.preventDefault();
    }
}
/*function handleMessage ( event ) {
    console.log(event.data);
}*/

// The message event is fired when data is received through a WebSocket. server sending msg back

function handleMessage ( event ) {
    let msg = event.data;
    let messageObject = JSON.parse(msg);
    // let messageObject = encodeURIComponent( JSON.stringify( msg ) )
    console.log("messageObject +" + messageObject);
    let type = messageObject.type; 
    let user = messageObject.user;
    let room = messageObject.room;

    let dashboard = document.getElementById("chatDashboard");
    if( type === "join") {
        let messageList1 = document.createElement('li');
        let messageItemText1 = document.createTextNode (user  + " has joined the " + room );
        messageList1.appendChild(messageItemText1);
        dashboard.appendChild(messageList1);
    } else if ( type === "message") {
        // display user and their message in the dashboard 
        let messageList2 = document.createElement('li');
        let messageItemText2 = document.createTextNode (user +" : " + messageObject.message);
        messageList2.appendChild(messageItemText2);
        dashboard.appendChild(messageList2);
    } else if ( type === "leave") {
        //dispaly user left the room 
        let messageList3 = document.createElement('li');
        let messageItemText3 = document.createTextNode (user +" has left the " + room);
        messageList3.appendChild(messageItemText3);
        dashboard.appendChild(messageList3);
    }

    console.log(event.data);
}


function handleOpen ( event ) {
    webSocketIsOpen = true;
    console.log("connection openened...");
    alert("connection established");
}

function handleError (event) {
    console.log("Web socket Error: ", event);
    alert("[error]");
}

function main () {
    ws.onopen = handleOpen;
    ws.onmessage = handleMessage;
    ws.onerror = handleError;
}


let ws = new WebSocket("ws://localhost:8080");
window.onload = main;

window.addEventListener("beforeunload", function(event) {
    event.preventDefault();
    ws.send( "leave " + UserName.value + " " + RoomName.value);
    ws.close();
    webSocketIsOpen = false;
})




