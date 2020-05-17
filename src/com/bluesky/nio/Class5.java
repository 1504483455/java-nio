package com.bluesky.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * ****************************
 * 使用直接缓冲区
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/2
 */
public class Class5 {

    public static void main(String[] args) throws IOException {
        test3();
    }

    /**
     * 正常构建直接缓存区进行读写
     * @throws IOException
     */
    private static void test1() throws IOException{
        FileChannel readChannel = FileChannel.open(Paths.get("e:\\1.avi"), StandardOpenOption.READ);
        FileChannel writeChannel = FileChannel.open(Paths.get("e:\\3.avi"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        ByteBuffer bf = ByteBuffer.allocateDirect(1024);


        while(readChannel.read(bf) != -1){
            bf.flip();
            writeChannel.write(bf);
            bf.clear();
        }

        writeChannel.close();
        readChannel.close();
    }


    /**
     * 通过map构建直接缓存--->MappedByteBuffer
     * @throws IOException
     */
    private static void test2() throws IOException{

        FileChannel readChannel = FileChannel.open(Paths.get("e:\\1.avi"), StandardOpenOption.READ);
        FileChannel writeChannel = FileChannel.open(Paths.get("e:\\5.avi"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

        // 内存映射文件，缓存区在物理内存中，和allocateDirect()创建的缓存一样
        MappedByteBuffer intMap = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());
        MappedByteBuffer outMap = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, readChannel.size());

        byte[] dst = new byte[intMap.limit()];
        intMap.get(dst);
        outMap.put(dst);

        writeChannel.close();
        readChannel.close();

    }


    /**
     * 通道之间的数据传输
     * @throws IOException
     */
    private static void test3() throws IOException{

        FileChannel readChannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
        FileChannel writeChannel = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

        // readChannel.transferTo(0,readChannel.size(),writeChannel);

        writeChannel.transferFrom(readChannel,0,readChannel.size());

        writeChannel.close();
        readChannel.close();
    }
}
