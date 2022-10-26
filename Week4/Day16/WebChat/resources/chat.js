//Function to handle click and ENTER
function handleRoomAndUserNamesCB(event) {
    console.log( “In the handleRoomNameCB function”);
    //If the user hits ENTER(13) key on keyboard
    if (event.keyCode == 13) {
        //Don’t create another line after pressing ENTER
        event.preventDefault();
        let roomName = roomname.value;
        //Check if the the room name is in the correct format
        //Verify that room name is valid (all lowercase and no spaces)
        //for loop that runs through everyletter one at a time, is it between lowercase a and z
        for (let c of roomName) {
            //if the letter is a space or uppercase, output an error
            if (!(c >= ‘a’ && c <=‘z’)) {
                roomName.value = (“Incorrect roomname format.Please enter a roomname in lowercase letters with no spaces”);
                return false;
            }
        }
        //////WebSocket Request to get the message from server////////
        //Ensure websocket is open before you send a message to the server
        if (wsOpen) {
            console.log(“Opening websocket”)
            //Send a join message to the server - concate string and variable together
            ws.send(`join ${username.value} ${roomname.value}`);
        }
        else {
            //error message
            wsResultTA.value = “Websocket is not open.“;
        }
    }
}





10: 26
This is my function to handle messages from the server
10: 26
//Function to collect message data from an event
function handleMsgFromServerCB(event) {
    //Msg should contain the data about the message(event) that casused the callback to be called.
    //It has the data sent from the server
    console.log( “In the handleMsg function”);
    //Format of data:
    //{ “type” : “message”, “user” : “theNameOfTheUserWhoSentTheMessage”, “room” : “nameOfRoom”, “message” : “the message”}
    let msg = event.data;
    console.log(msg);
    let msgObj = JSON.parse(msg);
    console.log(msgObj);
    let type = msgObj.type;
    let user = msgObj.user;
    let room = msgObj.room;
    //Create a paragraph to put text in to display in
    let p = document.createElement(‘p’);
    if (type === “join”) {
        // display user in user list
        let userList = document.getElementById(“userList”);
        let userlistItem = document.createElement(‘li’);
        userlistItem.id = user;
        let userlistItemText = document.createTextNode(user);
        userlistItem.appendChild(userlistItemText)
        userList.appendChild(userlistItem)
        // display user joined room in message board
        let msgList = document.getElementById(“msgBoard”);
        let msglistItem = document.createElement(‘li’);
        // msglistItem.id = msg;
        let msglistItemText = document.createTextNode(user + ” has joined the ” + room + ” room”);
        msglistItem.appendChild(msglistItemText);
        msgList.appendChild(msglistItem);
    } else if (type === “message”) {
        console.log(‘in the message elseif block’)
        // display user and their message in message board
        let msgList = document.getElementById(“msgBoard”);
        let msglistItem = document.createElement(‘li’);
        // msglistItem.id = msg;
        let msglistItemText = document.createTextNode(user + ” : ” + msgObj.message);
        msglistItem.appendChild(msglistItemText);
        msgList.appendChild(msglistItem);
    } else if (type === “leave”) {
        // display user leaves room in message board
        let msgList = document.getElementById(“msgBoard”);
        let msglistItem = document.createElement(‘li’);
        //msglistItem.id = msg;
        let msglistItemText = document.createTextNode(user + ” has left the ” + room + ” room”);
        msglistItem.appendChild(msglistItemText);
        msgList.appendChild(msglistItem);
        // remove user from the user list
        let userLI = document.getElementById(user);
        userLI.remove();
    }
}