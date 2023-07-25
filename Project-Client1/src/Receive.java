import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader; //get msg from the server

    public Receive(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    @Override
    public void run() {
        try {
            String message;//stores msg from server
            while ((message = bufferedReader.readLine()) != null) {
                System.out.println("Receiving: " + message);
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }
}
