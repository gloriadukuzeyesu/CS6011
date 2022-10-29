import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

public class MyRunnable implements Runnable {
    private Socket clientSocket = null;

    MyRunnable(Socket Client) {
        this.clientSocket = Client;
    }

    @Override
    public void run() {

        //handle the request header
        try {
            HTTPRequest request = new HTTPRequest(clientSocket);
            String filename = request.getTheFileName();
            HashMap<String, String> header = request.getTheBodyOfTheHeader();

            // send the response,
            HTTPResponse response = new HTTPResponse(clientSocket, filename, header);
            response.SendResponseHeader();
//            response.SendResponseBody();

            /*while ( true ) {
                // read message
                // respond to the message
            }*/

            if (header.containsKey("Sec-WebSocket-Key")){
                while (true){
                    //TODO read message

                    // 1. get the input stream from the socket
                    //  2. Turn the data input stream into a Data input stream. Data input stream is just like a scanner. It wraps the normal inputStream

                    DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                    //a. read first 2 bytes and parse those bytes for masks, Opcode, and length
                    byte [] ArrayFirstTwoBytes = new byte[2];
                    dataInputStream.readNBytes(ArrayFirstTwoBytes,0,2);
                    for ( int i = 0; i< ArrayFirstTwoBytes.length; i++ ){
                        System.out.println("byte is " + ArrayFirstTwoBytes[i]);
                    }
                    System.out.println("ArrayFirstTwoBytes are: " + Arrays.toString(ArrayFirstTwoBytes));

                    boolean Fin = (ArrayFirstTwoBytes[0] & 0x80) != 0; // fin ==1, this is the only packed in this msg
                    if ( Fin ){
                        System.out.println("this is the only packet in this msg / the  final text");
                    }

                    byte Opcode = (byte) (ArrayFirstTwoBytes [0] & 0x0F);
                    if (Opcode == 0x1){
                        System.out.println("the data is a text");
                    } else if (Opcode == 0x2) {
                        System.out.println(" data is a binary");
                    } else if ( Opcode == 0x8) {
                        System.out.println(" This is the end of the line");
                    }

                    // mask
                    boolean isMasked = (ArrayFirstTwoBytes[1] & 0x80) != 0; // masked = 1, it is masked.
                    if (isMasked){
                        System.out.println("It is masked");
                    }
                    //payload length
                    int lengthGuess = ArrayFirstTwoBytes[1] & 0x7F; // get rid of the masking bit
                    int payloadLength;
                    if (lengthGuess <= 125 ){
                        payloadLength = lengthGuess;
                        System.out.println("The length that was sent to us is " + payloadLength );
                    }else if(lengthGuess == 126 ){
                        // read in the next 2 bytes which is equivalent to a short
                        payloadLength = (int)dataInputStream.readShort();
                        System.out.println("The length that was sent to us is " + payloadLength );
                    } else {
                        // if length guess => 127, read in the long
                        payloadLength = (int) dataInputStream.readLong();
                        System.out.println("The payload length is: " + payloadLength );
                    }

                    // masking key, if it is masked, read in 4 bytes
                    byte[] maskingKey = null;
                    if ( isMasked) {
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
                    byte[] Decoded = new byte[payloadLength];
                    if(isMasked) {
                        for( int i=0; i< payloadLength; i++ ) {
                            Decoded[i] = ((byte) (payload[i] ^ maskingKey[i%4]));
                        }
                    }
                    // TODO validation for Decoded message
                    String decodeString = new String(Decoded);
                    System.out.println("Decoded is: " + decodeString);


                    // TODO Respond message



                }
            } else {
                clientSocket.close();
            }



        } catch (IOException | NoSuchAlgorithmException | InterruptedException e) {
            throw new RuntimeException(e);


        }
    }
}