package zhangjie.basic.buffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 1. 通过相应类型Buffer的allocate的静态方法来分配指定类型大小的缓冲数据区域（此处为buf）;
 * 2. 写入数据到Buffer;
 * 3. 调用flip()方法：Buffer从写模式切换到读模式;
 * 4. 从buffer读取数据;
 * 5. 调用clear()方法或则compact()方法。
 * @Author zhangjie
 * @Date 2020/7/13 20:56
 **/
public class BufferDemo {

    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
