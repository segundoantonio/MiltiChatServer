public class Main {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 6666;

        Client client = new Client(serverAddress, serverPort);
        client.start();

    }


}
