public class Room {
    private String RoomName;
    private static Room room_;

    private Room (){

    }


    public synchronized static Room getRoom (String room) {
        System.out.println("roomname");

        return room_;

    }

/*
    public synchronized broadCastMessage() {

    }
*/







}
