import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientConn;
    private int number;

    public ClientHandler(Socket clientConn, int number) {
        this.clientConn = clientConn;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = clientConn.getOutputStream();
            InputStream inputStream = clientConn.getInputStream();
            byte[] buffer = new byte[2048];
            String msg = "";
            while (msg.equals("Exit")) {
                int rd = inputStream.read(buffer);
                msg = new String(buffer, 0, rd);
                System.out.println("Message Receive From Client Num " + number + " : " + msg);
                outputStream.write(msg.getBytes());
                System.out.println("Message Sent From Client Num " + number + " : " + msg);
                Thread.sleep(2000);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
