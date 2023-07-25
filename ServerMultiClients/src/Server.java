import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {


    private final int port;
    private List<ServerWorker> serverWorkers;
    private BufferedReader in;
    private PrintWriter out;

    public Server(int port) {
        this.port = port;
        this.serverWorkers = new ArrayList<>();
    }

    public class ServerWorker implements Runnable {
        private final Socket clientSocket;

        public ServerWorker(Socket clientSocket) {
            this.clientSocket = clientSocket;
            //this.out = out;
        }

        @Override
        public void run() {

            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Receiving from the client " + clientSocket + ": " + message);
                }

            } catch (IOException e) {
                System.out.println("ERROR handling client " + clientSocket + ": " + e.getMessage());
            } finally {
                serverWorkers.remove(this);
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /*public void sendMessageToAll(String message){

            out.println(message);
        }*/
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Port is open waiting to connect");

            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected " + clientSocket);

                ServerWorker serverWorker = new ServerWorker(clientSocket);

                Thread thread = new Thread(serverWorker);
                thread.start();
            }


        } catch (IOException e) {
            System.out.println("Connection ERROR " + e.getMessage());
        }
    }

  /*  public static void main(String[] args) {
        Server server = new Server(6661);
        server.start();
    }*/

}
