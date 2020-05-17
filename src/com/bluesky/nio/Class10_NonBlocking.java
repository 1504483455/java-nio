package com.bluesky.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * ****************************
 * <p>
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/5
 */
public class Class10_NonBlocking {

    @Test
    public void clientTest() throws IOException {
        System.out.println("************* 客户端启动 *************");
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9000));
        // 将模式设置为非堵塞
        socketChannel.configureBlocking(false);

        ByteBuffer btf = ByteBuffer.allocate(1024);

        String name = "blueSky";

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String str = scanner.next();
            btf.put((name+ "   " + new Date().toString() + "  " + str).getBytes());
            btf.flip();
            socketChannel.write(btf);
            btf.clear();
        }

        socketChannel.close();
        System.out.println("************* 客户端结束 *************");
    }


    @Test
    public void serverTest() throws IOException{
        System.out.println("************* 服务端启动 *************");
        // 获取通道
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        // 设置为非堵塞模式
        sChannel.configureBlocking(false);
        // 绑定监听端口
        sChannel.bind(new InetSocketAddress(9888));
        // 获取选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器中并选择监听的类型：四个类型 监听多个用"|"
        sChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select() > 0) {
            // 获取选择器上的所有注册的选择建，已就绪的监听事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                // 获取准备就绪的选择建
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    SocketChannel acceptChannel = sChannel.accept();
                    acceptChannel.configureBlocking(false);
                    acceptChannel.register(selector,SelectionKey.OP_READ);
                }
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // socketChannel.configureBlocking(false);
                    ByteBuffer btf = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = socketChannel.read(btf)) > 0) {
                        btf.flip();
                        System.out.println("======================"+ new String(btf.array(),0,len));
                        btf.clear();
                    }
                }
                // 取消选择建要不然会一直有效
                iterator.remove();
            }
        }
        System.out.println("************* 服务端结束 *************");
    }

    public static void main(String[] args) throws IOException{
        System.out.println("************* 客户端启动 *************");
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9888));
        // 将模式设置为非堵塞
        socketChannel.configureBlocking(false);

        ByteBuffer btf = ByteBuffer.allocate(1024);

        String name = "blueSky";

        btf.put(name.getBytes());
        btf.flip();
        socketChannel.write(btf);
        btf.clear();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String str = scanner.next();
            String string = name+ "   " + new Date().toString() + "  " + str;
            btf.put(string.getBytes());
            btf.flip();
            socketChannel.write(btf);
            btf.clear();
        }

        socketChannel.close();
        System.out.println("************* 客户端结束 *************");
    }
}
