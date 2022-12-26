import java.util.Scanner;

abstract class SocialNetwork {

    public void connect() {
        // write your code here ...
        logIn();
        postAMessage("Hello, world!");
        logOut();
    }

    protected abstract void logOut();

    protected abstract void postAMessage(String s);

    protected abstract void logIn();

    // write your code here ...

}

class Instagram extends SocialNetwork {
    @Override
    protected void logOut() {
        System.out.println("Log out of Instagram");
    }

    @Override
    protected void postAMessage(String s) {
        System.out.println("Post: Hello, Instagram!");
    }

    @Override
    protected void logIn() {
        System.out.println("Log into Instagram");
    }
    // write your code here ...
}


class Facebook extends SocialNetwork {
    @Override
    protected void logOut() {
        System.out.println("Log out of Facebook");
    }

    @Override
    protected void postAMessage(String s) {
        System.out.println("Post: Hello, Facebook!");
    }

    @Override
    protected void logIn() {
        System.out.println("Log into Facebook");
    }
    // write your code here ...
    // write your code here ...
}

// Do not change the code below
class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String type = scanner.nextLine();
        scanner.close();
        SocialNetwork network = null;
        if ("facebook".equalsIgnoreCase(type)) {
            network = new Facebook();
        } else if ("instagram".equalsIgnoreCase(type)) {
            network = new Instagram();
        } else {
            System.out.println("Error!");
            System.exit(0);
        }
        network.connect();
    }
}