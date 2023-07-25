import java.io.IOException;
import java.net.Socket;

public class Client {

private String serverAddress;
private int serverPort;

public Client(String serverAddress, int serverPort){
    this.serverAddress = serverAddress;
    this.serverPort = serverPort;
}

public void start(){
    try{
        Socket socket = new Socket(serverAddress, serverPort);
        System.out.println("Connected");

        Sender sender = new Sender(socket);
        Receive receive = new Receive(socket);

        Thread senderThread = new Thread(sender);
        Thread receiveThread = new Thread(receive);

        senderThread.start();
        receiveThread.start();
    }catch (IOException e){
        System.out.println("Not connected " + e.getMessage());
    }
}


}
