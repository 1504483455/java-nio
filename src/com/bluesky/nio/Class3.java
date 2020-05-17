package com.bluesky.nio;

import java.nio.ByteBuffer;

/**
 * ****************************
 * 1、直接缓冲区和非直接缓冲区
 *  1）直接缓冲区，通过allocateDirect()分配的直接缓冲区，将缓冲区建立在物理内存中，可以提高效率
 *  2）非直接缓冲区,通过allocate()分配的缓冲区，将直接建立在JVM的内存中
 *  非直接缓冲区的数据读取：
 *      物理磁盘--read-->物理内存(内核地址空间，缓存)--copy-->jvm内存(用户地址空间)--read-->应用程序
 *  直接缓冲区的数据读取：
 *      物理磁盘--read-->物理内存映射文件--read-->应用程序
 * 2、直接缓冲区和非直接缓冲区优缺点
 *   非直接缓冲区的数据读取较慢，但是比较安全，操作性高
 *   直接缓冲区的数据读取较快，耗费内存，但是不安全，操作性低，进入物理内存映射文件的数据将不可操作，而且会有一个直接引用
 * 判断是否直接缓冲区： isDirect()
 * // VM.isDirectMemoryPageAligned();
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/2
 */
public class Class3 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(128);
        System.out.println(byteBuffer.isDirect());
    }
}
