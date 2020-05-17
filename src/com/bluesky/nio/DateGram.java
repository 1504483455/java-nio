package com.bluesky.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * ****************************
 * <p>
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/7
 */
public class DateGram {

    @Test
    public void receive() throws IOException{
        // 获取dc通道
        DatagramChannel dc = DatagramChannel.open();
        // 设置成非堵塞迷失
        dc.configureBlocking(false);
        // 监听端口
        dc.bind(new InetSocketAddress(9880));
        // 绑定选择器 读模式
        Selector selector = Selector.open();
        dc.register(selector,SelectionKey.OP_READ);

        while (selector.select()>0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {

                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isReadable()){
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    dc.receive(byteBuffer);
                    byteBuffer.flip();
                    System.out.println("--------->"+new String(byteBuffer.array(),0,byteBuffer.limit()));
                    byteBuffer.clear();
                }

                iterator.remove();
            }

        }
        dc.close();
    }

    public static void main(String[] args) throws IOException{
        System.out.println("************* 客户端启动 *************");
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        ByteBuffer btf = ByteBuffer.allocate(1024);
        String name = "blueSky";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            String string = name+ "   " + new Date().toString() + "\r\n" + str;
            btf.put(string.getBytes());
            btf.flip();
            dc.send(btf,new InetSocketAddress("127.0.0.1",9880));
            btf.clear();
        }

        dc.close();
        System.out.println("************* 客户端结束 *************");
    }
}
