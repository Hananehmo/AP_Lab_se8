import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String args[]) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("Server Started.");
        System.out.println("Waiting For User To Connect...");
        try(ServerSocket serverSocket = new ServerSocket(9090)) {
            for (int i = 1; i < 11; i++) {
                Socket clientConn = serverSocket.accept();
                System.out.println("Client Number " + i + " Connected Successfully :]");
                pool.execute(new ClientHandler(clientConn, i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
