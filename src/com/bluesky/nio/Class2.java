package com.bluesky.nio;

import java.nio.ByteBuffer;

/**
 * ****************************
 * 1、NIO中的缓冲区
 *      负责数据的存取，缓冲区就是数组，用于存储不同数据类型的数据
 *      根据不同的数据类型（boolean除外），提供了相应类型的缓冲区
 *      ByteBuffer
 *      CharBuffer
 *      ShortBuffer
 *      IntBuffer
 *      LongBuffer
 *      FloatBuffer
 *      DoubleBuffer
 *      这些缓存区的管理方法几乎一样，通过allocate()获取
 * 2、缓冲区的核心方法
 *      put() : 存入数据到缓冲区
 *      get() : 获取缓冲区中的数据
 * 3、缓冲区的四个核心属性
 *      capacity: 容量，缓存区中最大存储的数据容量，一旦声明不可改变
 *      limit: 界限，可操作的数据大小
 *      position: 位置，表示缓冲区中正在操作的数据位置
 *      mark: 标记position的位置，可以通过reset恢复到mark的位置
 *      0 <= mark <= position <= limit <= capacity
 * 4、核心方法
 *  1）put(): 设值
 *  2) get(): 取值
 *  3) position(): 当前位置
 *  4) limit(): 最大可操作长度
 *  5) capacity(): 最大容量
 *  6) flip(): 操作反转，读写操作反转
 *  7) clear(): 清空缓存区，之前数据在遗忘状态
 *  8) mark(): 标记，将mark值设置成position
 *  9) reset(): 恢复将position值设置成mark
 * ****************************
 *
 * @author blueSky
 * @version 1.0
 * @date 2020/3/2
 */
public class Class2 {

    public static void main(String[] args) {
        ByteBuffer bbf = ByteBuffer.allocate(64);

        System.out.println("---------------缓冲区初始化------------------");
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        String str = "中国，chine";
        bbf.put(str.getBytes());

        System.out.println("---------------缓冲区设置完------------------");
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        bbf.flip();
        System.out.println("---------------操作反转------------------");
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

//        byte[] bt = new byte[bbf.limit()];
//        bbf.get(bt);
//        System.out.println("------------全文------------>: " + new String(bt,0,bt.length));

        byte[] bt2 = new byte[3];
        bbf.get(bt2);
        System.out.println("------------1------------>: " + new String(bt2,0,3));
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        byte[] bt3 = new byte[3];
        bbf.get(bt3);
        System.out.println("------------2------------>: " + new String(bt3,0,3));
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        bbf.rewind();
        System.out.println("---------------rewind 可重复读将重置当前位置------------------");
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        byte[] bt4 = new byte[3];
        bbf.get(bt4);
        System.out.println("------------3------------>: " + new String(bt4,0,3));
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        // bbf.clear();
        System.out.println("---------------clear，clear后缓冲中仍然存在数据------------------");
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        System.out.println("---------------clear之后读取------------------");
        byte[] bt5 = new byte[3];
        bbf.get(bt5);
        System.out.println("------------bt5------------>: " + new String(bt5,0,3));

        bbf.mark();
        System.out.println("---------------mark------------------");
        System.out.println("------------------------>mark: " + bbf.mark());
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());
        byte[] bt6 = new byte[3];
        bbf.get(bt6);
        System.out.println("------------bt6------------>: " + new String(bt6,0,3));

        System.out.println("---------------reset之前--------->position: " + bbf.position());
        bbf.reset();
        System.out.println("---------------reset之后--------->position: " + bbf.position());

        System.out.println("---------------操作反转------------------");
        bbf.flip();
        System.out.println("------------------------>mark: " + bbf.mark());
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());

        bbf.put("0000".getBytes());
        System.out.println("------------------------>position: " + bbf.position());
        System.out.println("------------------------>limit: " + bbf.limit());
        System.out.println("------------------------>capacity: " + bbf.capacity());
    }

}
