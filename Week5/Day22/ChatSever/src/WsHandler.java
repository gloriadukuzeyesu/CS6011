import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class WsHandler {

    private Socket clientSocket_;
    private String MESSAGE;
    private String roomName_;
    long payloadLength;
    byte[] Decoded;

    public WsHandler(Socket clientSocket) {
        this.clientSocket_ = clientSocket;
    }

    public void readWsRequest() throws IOException {

        DataInputStream dataInputStream = new DataInputStream(clientSocket_.getInputStream());
        //a. read first 2 bytes and parse those bytes for masks, Opcode, and length
        byte[] ArrayFirstTwoBytes = new byte[2];
        dataInputStream.readNBytes(ArrayFirstTwoBytes, 0, 2);
        for (int i = 0; i < ArrayFirstTwoBytes.length; i++) {
            System.out.println("byte is " + ArrayFirstTwoBytes[i]);
        }
        System.out.println("ArrayFirstTwoBytes are: " + Arrays.toString(ArrayFirstTwoBytes));

        boolean Fin = (ArrayFirstTwoBytes[0] & 0x80) != 0;
        if (Fin) { System.out.println("this is the only packet in this msg / the  final text");}

        byte Opcode = (byte) (ArrayFirstTwoBytes[0] & 0x0F);
        if (Opcode == 0x1) {
            System.out.println("the data is a text");
        } else if (Opcode == 0x2) {
            System.out.println(" data is a binary");
        } else if (Opcode == 0x8) {
            System.out.println(" This is the close frame");
        }

        // mask
        boolean isMasked = (ArrayFirstTwoBytes[1] & 0x80) != 0; // masked = 1, it is masked.
        if (isMasked) {System.out.println("It is masked");}

        //payload length
        int lengthGuess = ArrayFirstTwoBytes[1] & 0x7F; // get rid of the masking bit
        if (lengthGuess <= 125) {
            payloadLength = lengthGuess;
        } else if (lengthGuess == 126) {
            payloadLength = dataInputStream.readShort();   // read in the next 2 bytes which is equivalent to a short
        } else {  // if length guess => 127, read in the long
            payloadLength = dataInputStream.readLong();
        }

        // masking key, if it is masked, read in 4 bytes
        byte[] maskingKey = null;
        if (isMasked) {
            maskingKey = new byte[4];
            dataInputStream.readNBytes(maskingKey, 0, 4);
        }
        System.out.println("maskingKey is: " + Arrays.toString(maskingKey));

        // payload itself
        byte[] payload = new byte[(int) payloadLength];
        dataInputStream.readNBytes(payload, 0, (int) payloadLength);
        System.out.println("payload is : " + Arrays.toString(payload));

        // decode the masking key
        Decoded = new byte[(int) payloadLength];
        if (isMasked) {
            for (int i = 0; i < payloadLength; i++) {
                Decoded[i] = ((byte) (payload[i] ^ maskingKey[i % 4]));
            }
        }
        // TODO validation for Decoded message
        MESSAGE = new String(Decoded);
        System.out.println("Decoded is: " + MESSAGE);
    }

   public String getJsonMessage() {
        String UserName = "";
        String chatRoom = "";
        String JsonString = "";
        String timeStamp = new SimpleDateFormat("HH:mm").format(new java.util.Date());

        String typeOfPayload = null;
        if (MESSAGE.length() > 0) {
            typeOfPayload = MESSAGE.split(" ", 2)[0];
        }
        // type message
        if (typeOfPayload != null) {
            if (!typeOfPayload.equals("join") && (!typeOfPayload.equals("leave"))) {
                typeOfPayload = "message";
                UserName = MESSAGE.split(" ", 2)[0];
                String payLoadMessage = MESSAGE.split(" ", 2)[1];
                System.out.println("Payload Message is " + payLoadMessage);

                JsonString += "{" + "\"" + "type" + "\"" + ": " + "\"" + typeOfPayload + "\", " +
                        "\"" + "user" + "\"" + ": " + "\"" + UserName + "\", " + "\"" + "room" + "\""
                        + ": " + "\"" + roomName_ + "\", " + "\"" + "timeStamp" + "\"" + ": " + "\"" + timeStamp + "\", " +
                        "\"" + "message" + "\"" + ": " + "\"" + payLoadMessage + "\"" +
                        "}";

                System.out.println("Json String is" + JsonString);

            } else {
                // join or leave
                UserName = MESSAGE.split(" ", 3)[1];
                chatRoom = MESSAGE.split(" ", 3)[2];
                roomName_ = chatRoom;

                // need to handle leave slightly differenty
                // assuming just join msgs here
                Room room = Room.getRoom( roomName_ );
                room.addClientSocket( clientSocket_ );

                JsonString += "{" + "\"" + "type" + "\"" + ": " + "\"" + typeOfPayload + "\", " +
                        "\"" + "room" + "\"" + ": " + "\"" + roomName_ + "\", " + "\"" + "timeStamp" + "\"" + ": " + "\"" +
                        timeStamp + "\", " + "\"" + "user" + "\"" + ": " + "\"" + UserName + "\"" + "}";

                System.out.println("Json String is" + JsonString);

            }
            return JsonString;
        }
        return null;
    }


    public void respondWsRequest() throws IOException {

        String JsonMessageToClient = getJsonMessage();
        // The getJsonMessage actually determines the "roomName_" so we
        // have to ask for the room after we call getJsonMessage().
        Room room = Room.getRoom(roomName_);

        System.out.println("Server, about to send msg to " + room.getClients().size() + " clients");
        for (Socket socket : room.getClients()) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeByte(0x81);
            byte payloadLengthInBytes = (byte) JsonMessageToClient.length();
            dataOutputStream.writeByte(payloadLengthInBytes);
            dataOutputStream.write(JsonMessageToClient.getBytes());
            dataOutputStream.flush();
        }


    }

}
