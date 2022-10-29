"use strict";

// html tags
let RoomName = document.getElementById("RM_ID");
let UserName = document.getElementById("User_ID");
let MessageToServer = document.getElementById("Message_ID");

let room = RoomName.value;
let user_ = UserName.value;


RoomName.addEventListener("keypress", handleKeyPressedCB );
// UserName.addEventListener("keypress", handleKeyPressedCB );
MessageToServer.addEventListener("keypress",handleKeyPressedMessagingServer);



let webSocketIsOpen = false;
let ws = new WebSocket("ws://localhost:8080");

ws.onopen = handleOpen;
// ws.send("Sending msg to the server");
ws.onmessage = handleMessage;
ws.onerror = handleError;
// ws.onclose = handleClose;




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

function handleKeyPressedMessagingServer(event) {
    if(event.keyCode == 13 ) {
        console.log("hit enter for message button");
        console.log(MessageToServer.value);
        ws.send( UserName.value + " " + MessageToServer.value);
        MessageToServer.value= "";
        event.preventDefault();
    }
}


// The message event is fired when data is received through a WebSocket.
function handleMessage ( event ) {
    let msg = event.data;
    let messageObject = JSON.parse(msg);
    let type = messageObject.type; 
    let user = messageObject.user;
    let room = messageObject.room;

    // let dashboard = document.getElementsByTagName("div");
    let dashboard = document.getElementById("chatDashboard");
    if( type == "join") {
        
        let messageList1 = document.createElement('li');
        let messageItemText1 = document.createTextNode (user  + " has joined the " + room );
        messageList1.appendChild(messageItemText1);
        dashboard.appendChild(messageList1);
    } else if ( type == "message") {
        // display user and their message in the dashboard 
        let messageList2 = document.createElement('li');
        let messageItemText2 = document.createTextNode (user +" : " + messageObject.message);
        messageList2.appendChild(messageItemText2);
        dashboard.appendChild(messageList2);
    } else if ( type == "leave") {
        //dispaly user left the room 
        let messageList3 = document.createElement('li');
        let messageItemText3 = document.createTextNode (user + " has left the " + room);
        messageList3.appendChild(messageItemText3);
        dashboard.appendChild(messageList3);
    }

    // send the message as json
    // ws.send(JSON.stringify(messageObject));
    // blank the text input read to receive the next line from the user
}

function handleOpen ( event ) {
    // ws.send("hello server");
    console.log("connection openened...");
    alert("connection established");
    webSocketIsOpen = true;
}

function handleClose (event){
    dashboard.value = " The Server left the chat, Bye!";
    console.log("HandleClosing the webSocket");

}

function handleError (event) {
    console.log("Web socket Error: ", event);
    alert("[error]");
}



 


