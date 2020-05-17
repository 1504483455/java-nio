package com.bluesky.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * ****************************
 * 使用NIO完成网络通讯的三个核心
 * 1、通道（Channel）负责连接
 *      java.nio.channels.Channel
 *          SelectableChannel
 *              SocketChannel
 *              ServerSocketChannel
 *              DataGramChannel
 *
 *              Pie.SinkChannel
 *              Pie.SourceChannel
 *
 * 2、缓冲区（）负责数据的存储
 * 2、选择器（）用于监控channel（SelectableChannel）的IO状况
 *
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/4
 */
public class Class7 {

    public static void main(String[] args) throws Exception {

        System.out.println("-----------------客户端启动------------------>");
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9023));

        // 读取本地资源
        FileChannel fileChannel = FileChannel.open(Paths.get("e:\\1.avi"), StandardOpenOption.READ);

        ByteBuffer btf = ByteBuffer.allocate(1024);

        while (fileChannel.read(btf) != -1) {
            btf.flip();
            socketChannel.write(btf);
            btf.clear();
        }
        fileChannel.close();
        socketChannel.close();
        System.out.println("-----------------客户端结束------------------>");
    }

}
