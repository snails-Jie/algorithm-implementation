package zhangjie.basic.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @Author zhangjie
 * @Date 2020/7/9 11:29
 **/
public class NioSocektServer {
    /** 为了跟踪每个通道的数据 */
    static ConcurrentHashMap<SocketChannel, LinkedList<ByteBuffer>> dataMap = new ConcurrentHashMap();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9090));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //select()为阻塞方法
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) keys.next();
                if (selectionKey.isAcceptable()) {
                    createChannel(serverSocketChannel, selectionKey);
                } else if (selectionKey.isReadable()) {
                    doRead(selectionKey);
                } else if (selectionKey.isWritable()) {
                    doWrite(selectionKey);
                }
                keys.remove();
            }

        }
    }

    /** 每次创建新连接时，该连接为isAcceptable()true，并且新连接  Channel将注册到中Selector */
    private static void createChannel(ServerSocketChannel serverSocketChannel, SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Accepted connection from " + socketChannel);
        socketChannel.configureBlocking(false);
        socketChannel.write(ByteBuffer.wrap(("Welcome: " + socketChannel.getRemoteAddress() +
                "\nThe thread assigned to you is: " + Thread.currentThread().getId() + "\n").getBytes()));
        dataMap.put(socketChannel, new LinkedList<>()); // store socket connection
        System.out.println("Total clients connected: " + dataMap.size());
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ); // selector pointing to READ operation
    }

    /**
     * 1. 将缓冲区设置为读模式，flip()并添加到Map中
     * 2. interestOps()被调用以指向WRITE操作
     * @param selectionKey
     * @throws IOException
     */
    private static void doRead(SelectionKey selectionKey) throws IOException {
        LOGGER.info("Reading...");
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024); // pos=0 & lim=1024
        int read = socketChannel.read(byteBuffer); // pos=numberOfBytes & lim=1024
        if (read == -1) { // if connection is closed by the client
            doClose(socketChannel);
        } else {
            byteBuffer.flip(); // put buffer in read mode by setting pos=0 and lim=numberOfBytes
            dataMap.get(socketChannel).add(byteBuffer); // find socket channel and add new byteBuffer queue
            selectionKey.interestOps(SelectionKey.OP_WRITE); // set mode to WRITE to send data
        }
    }


    private static void doWrite(SelectionKey selectionKey) throws IOException {
        LOGGER.info("Writing...");
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        LinkedList<ByteBuffer> pendingData = dataMap.get(socketChannel); // find channel
        while (!pendingData.isEmpty()) { // start sending to client from queue
            ByteBuffer buf = pendingData.poll();
            socketChannel.write(buf);
        }
        selectionKey.interestOps(SelectionKey.OP_READ); // change the key to READ
    }

    private static void doClose(SocketChannel socketChannel) throws IOException {
        dataMap.remove(socketChannel);
        Socket socket = socketChannel.socket();
        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
        LOGGER.info("Connection closed by client: " + remoteSocketAddress);
        socketChannel.close(); // closes channel and cancels selection key
    }


}