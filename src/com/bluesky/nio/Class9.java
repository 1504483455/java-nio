package com.bluesky.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * ****************************
 * <p>
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/5
 */
public class Class9 {

    @Test
    public void clientTest() throws IOException{
        System.out.println("======================客户端启动======================");
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9023));
        // 将模式设置为非堵塞
        // socketChannel.configureBlocking(false);
        // 读取本地资源
        FileChannel fileChannel = FileChannel.open(Paths.get("e:\\1.png"), StandardOpenOption.READ);

        ByteBuffer btf = ByteBuffer.allocate(1024);

        while (fileChannel.read(btf) != -1) {
            btf.flip();
            socketChannel.write(btf);
            btf.clear();
        }
        // 关闭输出，告知服务器我写完了
        // socketChannel.shutdownOutput();
        // 接受服务器反馈
        int len;
        while ((len = socketChannel.read(btf)) != -1) {
            btf.flip();
            System.out.println("======================"+ new String(btf.array(),0,len));
            btf.clear();
        }

        fileChannel.close();
        socketChannel.close();
        System.out.println("======================客户端结束======================");
    }


    @Test
    public void serverTest() throws IOException{
        System.out.println("======================服务端启动======================");
        ServerSocketChannel sChannel = ServerSocketChannel.open();

        FileChannel fileChannel = FileChannel.open(Paths.get("e:\\2.png"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);


        sChannel.bind(new InetSocketAddress(9023));

        SocketChannel acceptChannel = sChannel.accept();

        ByteBuffer bft = ByteBuffer.allocate(1024);

        while(acceptChannel.read(bft) != -1){
            bft.flip();
            fileChannel.write(bft);
            bft.clear();
        }

        bft.put("----->接受成功.......".getBytes());
        bft.flip();
        acceptChannel.write(bft);

        acceptChannel.close();
        fileChannel.close();
        sChannel.close();
        System.out.println("======================服务端结束======================");
    }

}
