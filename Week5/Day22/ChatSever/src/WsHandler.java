
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class WsHandler {
    Socket clientSocket_;
    String MESSAGE;
    String roomName_; // it is empty as of now

    int payloadLength;
    byte [] Decoded;

    public WsHandler(Socket clientSocket) {
        this.clientSocket_ = clientSocket;
    }

    public byte[] readWsRequest() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(clientSocket_.getInputStream());
        //a. read first 2 bytes and parse those bytes for masks, Opcode, and length
        byte[] ArrayFirstTwoBytes = new byte[2];
        dataInputStream.readNBytes(ArrayFirstTwoBytes, 0, 2);
        for (int i = 0; i < ArrayFirstTwoBytes.length; i++) {
            System.out.println("byte is " + ArrayFirstTwoBytes[i]);
        }
        System.out.println("ArrayFirstTwoBytes are: " + Arrays.toString(ArrayFirstTwoBytes));

        boolean Fin = (ArrayFirstTwoBytes[0] & 0x80) != 0; // fin ==1, this is the only packed in this msg
        if (Fin) {
            System.out.println("this is the only packet in this msg / the  final text");
        }

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
        if (isMasked) {
            System.out.println("It is masked");
        }
        //payload length
        int lengthGuess = ArrayFirstTwoBytes[1] & 0x7F; // get rid of the masking bit
        if (lengthGuess <= 125) {
            payloadLength = lengthGuess;
            System.out.println("The length that was sent to us is " + payloadLength);
        } else if (lengthGuess == 126) {
            // read in the next 2 bytes which is equivalent to a short
            payloadLength = (int) dataInputStream.readShort();
            System.out.println("The length that was sent to us is " + payloadLength);
        } else {
            // if length guess => 127, read in the long
            payloadLength = (int) dataInputStream.readLong();
            System.out.println("The payload length is: " + payloadLength);
        }

        // masking key, if it is masked, read in 4 bytes
        byte[] maskingKey = null;
        if (isMasked) {
            maskingKey = new byte[4];
            dataInputStream.readNBytes(maskingKey, 0, 4);
        }
        System.out.println("maskingKey is: " + Arrays.toString(maskingKey));

        // TODO fix the decoding formula and print the payload itself
        // payload itself
        byte[] payload = new byte[payloadLength];
        dataInputStream.readNBytes(payload, 0, payloadLength);
        System.out.println("payload is : " + Arrays.toString(payload));

        // decode the masking key
        Decoded = new byte[payloadLength];
        if (isMasked) {
            for (int i = 0; i < payloadLength; i++) {
                Decoded[i] = ((byte) (payload[i] ^ maskingKey[i % 4]));
            }
        }
        // TODO validation for Decoded message
        String decodeString = new String(Decoded);
        MESSAGE = new String(Decoded);

        System.out.println("Decoded is: " + decodeString);

        // TODO handle the closing when a client leaves the room
        return Decoded;

    }

    public String getJsonMessage (){
        String UserName = "";
        String chatRoom = "";
        String JsonString= "";
        String timeStamp = new SimpleDateFormat("HH:mm").format(new java.util.Date());
        String typeOfPayload = MESSAGE.split(" ", 2)[0];
        System.out.println("Type Of Payload is " + typeOfPayload);

        if (!typeOfPayload.equals("join") && (!typeOfPayload.equals("leave"))) {
            typeOfPayload = "MESSAGE";
            UserName = MESSAGE.split(" ",2)[0];
            String payLoadMessage = MESSAGE.split(" ",2)[1];
            System.out.println("Payload Message is " + payLoadMessage);

            JsonString += "{ " + "\"" + "type" + "\"" + " : " + "\"" + typeOfPayload + "\", " +
                    "\"" + "user" + "\"" + " : " + "\"" + UserName + "\", " + "\"" + "room" + "\""
                    + " : " + "\"" + roomName_ + "\", " + "\"" + "timeStamp" + "\"" + " : " + "\"" + timeStamp + "\", " +
                    "\"" + "MESSAGE" + "\"" + " : " + "\"" + payLoadMessage + "\"" +
                    " }";

            System.out.println("Json String is" + JsonString);

        } else{
            UserName = MESSAGE.split(" ", 3)[1];
            chatRoom = MESSAGE.split(" ", 3)[2];
            roomName_ = chatRoom;
            JsonString += "{ " + "\"" + "type" + "\"" + " : " + "\"" + typeOfPayload + "\", " +
                    "\"" + "room" + "\"" + " : " + "\"" + roomName_ + "\", " + "\"" + "timeStamp" + "\"" + " : " + "\"" +
                    timeStamp + "\", " + "\"" + "user" + "\"" + " : " + "\"" + UserName + "\"" + " }";

            System.out.println("Json String is" + JsonString);

        }
        return JsonString;
    }

    public void respondWsRequest( ) throws IOException {

        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket_.getOutputStream());
        dataOutputStream.writeByte(0x81);
        byte payloadLengthInBytes = (byte)payloadLength;
        dataOutputStream.writeByte(payloadLengthInBytes);
        dataOutputStream.write(Decoded);
        dataOutputStream.flush();
/*
        // bring in the message to send
        String JsonMessageToClient = getJsonMessage();
        dataOutputStream.write(OutPutResponse);

        dataOutputStream.write(JsonMessageToClient.getBytes());
        System.out.println("test1")*/;
    }

}
