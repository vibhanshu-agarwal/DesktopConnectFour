import java.util.Scanner;

abstract class House {

    // write your code here ...
    public void build() {
        chooseLocation();
        placeFoundations();
        placeWalls();
        placeWindows();
        placeDoors();
        placeRoofs();
        connectElectricity();
    }

    protected abstract void placeDoors();

    protected abstract void placeWindows();

    protected abstract void placeWalls();

    protected abstract void placeFoundations();

    // Do not change the code below
    public void chooseLocation() {
        System.out.println("Choose the best location for the new house");
    }

    public void placeRoofs() {
        System.out.println("Place metal sheet roofs");
    }

    public void connectElectricity() {
        System.out.println("Connect the house to the electrical grid");
    }
}

class Wooden extends House {
    @Override
    protected void placeDoors() {
        System.out.println("Place wooden doors");
    }

    @Override
    protected void placeWindows() {
        System.out.println("Place wooden windows");
    }

    @Override
    protected void placeWalls() {
        System.out.println("Place wooden walls");
    }

    @Override
    protected void placeFoundations() {
        System.out.println("Place a wooden foundation");
    }
    // write your code here ...
}

// Do not change the code below
class Stone extends House {

    @Override
    public void placeFoundations() {
        System.out.println("Place a stone foundation");
    }

    @Override
    public void placeWalls() {
        System.out.println("Place stone walls");
    }

    @Override
    public void placeWindows() {
        System.out.println("Place reinforced windows");
    }

    @Override
    public void placeDoors() {
        System.out.println("Place reinforced doors");
    }
}

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String type = scanner.nextLine();
        scanner.close();
        House house = null;
        if ("wooden".equalsIgnoreCase(type)) {
            house = new Wooden();
        } else if ("stone".equalsIgnoreCase(type)) {
            house = new Stone();
        } else {
            System.out.println("Error!");
            System.exit(0);
        }
        house.build();
    }
}