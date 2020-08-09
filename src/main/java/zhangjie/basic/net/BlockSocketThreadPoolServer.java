package zhangjie.basic.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhangjie
 * @Date 2020/7/9 10:56
 **/
public class BlockSocketThreadPoolServer {
    final static int PORT_NUMBER = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        while (true) {
            Socket client = serverSocket.accept();

            ExecutorService threadPool = Executors.newFixedThreadPool(100);
            threadPool.execute(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(client.getInputStream())
                    );
                    OutputStream out = client.getOutputStream();
                    in.lines().forEach(line -> {
                        try {
                            out.write(line.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });



        }
    }
}
