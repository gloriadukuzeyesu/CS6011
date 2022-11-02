import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    // only one map of rooms for all room objects - static thus they all share this
    public static HashMap<String, Room> MapOfRooms_ = new HashMap<>();

    // Member variables - each (and every) Room object has their own version of these:
    String NameOfRoom;
    //static Socket clientSocket_;
    private ArrayList<Socket> ConnectedSockets_ = new ArrayList<>();

    private Room( String name ) {
        NameOfRoom = name;
    }

    public synchronized static Room getRoom(String NameOfTheRoom) {

        if (MapOfRooms_.containsKey(NameOfTheRoom)) {
            return MapOfRooms_.get(NameOfTheRoom); // return that room, if it still exists
        }
        else { // the "NameOfTheRoom" room does not yet exist
            Room room = new Room( NameOfTheRoom );
            MapOfRooms_.put(NameOfTheRoom, room);
            return room;
        }
    }


    // remove client
    public synchronized void removeClient( Socket client ) {
        ConnectedSockets_.remove( client );
    }

    public synchronized void addClientSocket( Socket client ) {
        ConnectedSockets_.add( client );
    }

    public ArrayList<Socket> getClients() { return ConnectedSockets_ ; }
}
