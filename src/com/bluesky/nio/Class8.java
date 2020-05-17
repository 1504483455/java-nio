package com.bluesky.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * ****************************
 * <p>
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/4
 */
public class Class8 {

    public static void main(String[] args) throws IOException {
        System.out.println("-----------------服务端启动------------------>");
        ServerSocketChannel sChannel = ServerSocketChannel.open();

        FileChannel fileChannel = FileChannel.open(Paths.get("e:\\2.avi"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);


        sChannel.bind(new InetSocketAddress(9023));

        SocketChannel acceptChannel = sChannel.accept();

        ByteBuffer bft = ByteBuffer.allocate(1024);

        while(acceptChannel.read(bft) != -1){
            bft.flip();
            fileChannel.write(bft);
            bft.clear();
        }

        acceptChannel.close();
        fileChannel.close();
        sChannel.close();
        System.out.println("-----------------服务端结束------------------>");

    }
}
