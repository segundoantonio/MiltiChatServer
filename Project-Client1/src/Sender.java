import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Sender implements Runnable {

    //ter sender dentro do client


    private Socket socket;
    private PrintWriter printWriterOut;
    //private BufferedWriter bufferedWriterOut;
    private BufferedReader bufferedReader;


    public Sender(Socket socket) throws IOException {
        this.socket = socket;
        this.printWriterOut = new PrintWriter(socket.getOutputStream(), true);
        //this.bufferedWriterOut = new BufferedWriter(new OutputStreamWriter(System.out));
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            //String message = bufferedWriterOut.toString();
            String message = null;
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //bufferedWriterOut.write(message);
            printWriterOut.println(message);
        }
    }
}

//"message not sent: " + e.getMessage()