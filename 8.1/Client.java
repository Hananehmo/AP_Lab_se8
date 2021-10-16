import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Port Num: ");
        int port = sc.nextInt();
        if (port != 9090) {
            System.out.println("Wrong Port Num!!!");
            System.out.println("Please Enter Port Num Again: ");
            port = sc.nextInt();
        }
        try(Socket clientConn = new Socket("localhost", port)) {
            System.out.println("Connected To Server Successfully :] ");
            System.out.println("Type your message");
            String message;
            OutputStream out = clientConn.getOutputStream();
            InputStream in = clientConn.getInputStream();
            Scanner scanner = new Scanner(System.in);
            //receive and send messages until user types over
            while (!( message = scanner.nextLine()).equals("Exit")) {
                if (message.length() != 0) {
                    out.write(message.getBytes());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("SENT: " + message);
                    byte[] buffer = new byte[1024];
                    int read = in.read(buffer);
                    message = new String(buffer, 0, read);
                    System.out.println("RECV: " + message);
                }
                TimeUnit.SECONDS.sleep(1);
            }
            out.write("Exit".getBytes());
            System.out.println("connection closed");
        } catch (IOException | InterruptedException e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

}
