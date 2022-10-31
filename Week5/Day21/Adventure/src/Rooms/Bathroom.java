package Rooms;
import Game.Adventure;
import Items.Item;

// Name: Gloria Dukuzeyesu
public class Bathroom extends Room {

    // a player will enter the room and pick up the sprayBottle and water the pot plant
    // the pot plant will come from the garden as

    private boolean locked_ = true;

    public Bathroom() {
        super("Bathroom", "wash away your troubles with bubble bath");
        Item sprayBottle = new Item("spraybottle", " Some times, I wet my plants....");
        items_.add(sprayBottle);
    }


    // let the player enter the door
    @Override
    public void playerEntered() {
        if (locked_) {
            System.out.println(" You entered the bathroom");
        }
    }

    @Override
    public Room goThroughDoor(int doorNum) {
        if (locked_) {
            System.out.println("The door is locked!");
            return null;
        } else {
            return super.goThroughDoor(doorNum);
        }
    }

    public boolean handleCommand(String[] subcommands) {

        if (subcommands.length <= 1) {
            return false;
        }
        String cmd = subcommands[0];
        String attr = subcommands[1];

        // unlock, use grand hall and go the garden pickup spraybottle
        if (cmd.equals("pickup") && attr.equals("spraybottle")) {

            boolean carryBottle = false;

            for (Item item : Adventure.inventory) {
                if (item.getName().equals("sprayBottle")) {
                    carryBottle = true;
                    break;
                }
            }
            if (carryBottle) {
                System.out.println("You grabbed the spray bottle .");
                carryBottle = false;
            } else {
                System.out.println("You don't have the Bottle.");
            }
            return true;
        }
        return false;
    }
}

