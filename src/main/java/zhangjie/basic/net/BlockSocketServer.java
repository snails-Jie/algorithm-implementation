package zhangjie.basic.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1. 使用给定的端口创建ServerSocket 以进行侦听。
 * 2. 服务器将在accept()调用时阻塞，并开始侦听客户端连接。
 * 3. 如果客户端请求连接，则通过accept()返回Socket
 * 4. 现在，我们可以从客户端（InputStream）读取数据并将其发送回客户端（OutputStream）。
 * @Author zhangjie
 * @Date 2020/7/9 10:37
 **/
public class BlockSocketServer {
    final static int PORT_NUMBER = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("接收到请求.....");
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
        }
    }
}
