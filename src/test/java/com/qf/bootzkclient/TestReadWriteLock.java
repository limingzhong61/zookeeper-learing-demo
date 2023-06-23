package com.qf.bootzkclient;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Thor
 */
@SpringBootTest
public class TestReadWriteLock {

    @Autowired
    private CuratorFramework client;

    @Test
    void testGetReadLock() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock=new InterProcessReadWriteLock(client, "/lock1");
        // 获取读锁对象
        InterProcessLock interProcessLock=interProcessReadWriteLock.readLock();
        System.out.println("等待获取读锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");

    }
    @Test
    void testGetReadLock2() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock=new InterProcessReadWriteLock(client, "/lock1");
        // 获取读锁对象
        InterProcessLock interProcessLock=interProcessReadWriteLock.readLock();
        System.out.println("等待获取读锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");

    }

    @Test
    void testGetWriteLock() throws Exception {

        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock=new InterProcessReadWriteLock(client, "/lock1");
        // 获取写锁对象
        InterProcessLock interProcessLock=interProcessReadWriteLock.writeLock();
        System.out.println("等待获取写锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");
    }




}
