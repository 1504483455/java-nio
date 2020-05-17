package com.bluesky.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * ****************************
 * 一、堵塞和非堵塞的网络IO
 *      1）传统IO的堵塞模式
 *      客户端向服务端发送IO请求时，当服务端无法确定客户端的数据真实性是，会造成堵塞
 *      解决方法：多线程（也会有问题）
 *      2）NIO的非堵塞模式
 *      选择器（Selector）：将每个传输数据的通道，注册在选择器上，用于监控通道的状态（连接，读，写），
 *          当某个通道上的请求准备完全就绪时，才会将这个任务分配到线程上
 *
 * 二、分散与聚集
 *      分散读取（Scattering Reads）：将通道中的数据分散道多个缓冲区中,有序的
 *      聚集写入（Gathering Writes）：将多个缓冲区的数据聚集道通道中
 *
 * 三、字符集（Charset）
 *  编码：字符串->字节数组
 *  解码：字节数据->
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/3
 */
public class Class6 {

    /**
     * 分散与聚集
     * @throws IOException
     */
    private static void scatteringAndGathering()throws IOException{
        RandomAccessFile raf = new RandomAccessFile("test.txt","rw");
        FileChannel rafChannel = raf.getChannel();

        ByteBuffer bft1 = ByteBuffer.allocate(129);
        ByteBuffer bft2 = ByteBuffer.allocate(1024);

        ByteBuffer[] btArray = {bft1,bft2};
        rafChannel.read(btArray);

        bft1.flip();
        bft2.flip();
        System.out.println("--------------分散读取---------------");
        System.out.println("--------------bft1---------------" + new String(bft1.array(),0,bft1.limit()));
        System.out.println("--------------bft2---------------" + new String(bft2.array(),0,bft2.limit()));

        System.out.println("--------------聚集写入---------------");
        RandomAccessFile raf2 = new RandomAccessFile("test2.txt","rw");
        FileChannel rafChannel2 = raf2.getChannel();
        rafChannel2.write(btArray);

        rafChannel2.close();
        rafChannel.close();
    }

    /**
     * 字符集
     * @throws IOException
     */
    private static void charSet()throws IOException{
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        System.out.println("---------------------所有字符集-----------------------");
        for (Map.Entry<String, Charset> me:entries) {
            System.out.println(me.getKey()+"------->"+me.getValue());
        }
        System.out.println("---------------------编码解码格式不同-----------------------");

        Charset utf_charset = Charset.forName("UTf-8");
        Charset gbk_charset = Charset.forName("GBK");

//        CharBuffer charBuffer = CharBuffer.allocate(1024);
//        charBuffer.put("============设计开发=============");
//        charBuffer.flip();
//
//        ByteBuffer encode1 = utf_charset.encode(charBuffer);
//        for (int i=0;i<10; i++) {
//            System.out.println("encode1-------------->" +encode1.get());
//        }


        String str = "加油，chine";
        ByteBuffer encode = utf_charset.encode(str);

        CharBuffer decode = gbk_charset.decode(encode);

        System.out.println("-------------->"+ new String(decode.array(),0,decode.limit()));

    }

    public static void main(String[] args) throws IOException{
        scatteringAndGathering();
        charSet();
    }
}
