package com.bluesky.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ****************************
 * 一、Channel（通道）用于源节点和目标节点的连接，在javaIO中负责话缓冲区的数据传输
 * 1、第一代：操作系统的IO接口都是被CPU独立处理的
 * 2、第二代：DMA总线向CPU申请读写资源，大量的读写也会造成DMA总线充足
 * 3、第三代：通道channel，是一个完全独立的处理器，附属于CPU,专门用于IO操作的
 *
 * 二、通道的主要实现类
 * java.nio.channels.Channel
 *      |--FileChannel
 *      |--SocketChannel
 *      |--ServerSocketChannel
 *      |--DatagramChannel
 *
 * 三、Java中获取通道的方法
 *  1、getChannel()
 *   本地IO:
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *   网络IO:
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 * 2、JDK 1.7 NIO.2提供了静态方法open()
 * 3、JDK 1.7 Files工具类的newByteChannel()
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/2
 */
public class Class4 {

    public static void main(String[] args) throws IOException {
        // 使用直接缓冲区
        // 通道获取方式为

        FileInputStream fis = new FileInputStream("e:\\1.avi");
        FileOutputStream fos = new FileOutputStream("e:\\2.avi");


        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer bf = ByteBuffer.allocate(1024);

        while(fisChannel.read(bf) != -1){
            bf.flip();
            fosChannel.write(bf);
            bf.clear();
        }

        fosChannel.close();
        fisChannel.close();

        fos.close();
        fis.close();
    }

}
