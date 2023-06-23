package com.qf.bootzkclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@Slf4j
@SpringBootTest
class BootZkClientApplicationTests {

  @Autowired
  CuratorFramework curatorFramework;


  @Test
  void createNode() throws Exception {

    //添加持久节点
    String path = curatorFramework.create().forPath("/curator-node");
    //添加临时序号节点
    //String path1 = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/curator-node", "some-data".getBytes());
    System.out.println(String.format("curator create node :%s  successfully.",path));

    System.in.read();

  }

  @Test
  public void testGetData() throws Exception {
    byte[] bytes = curatorFramework.getData().forPath("/curator-node");
    System.out.println(new String(bytes));
  }

  @Test
  public void testSetData() throws Exception {
    curatorFramework.setData().forPath("/curator-node","changed!".getBytes());
    byte[] bytes = curatorFramework.getData().forPath("/curator-node");
    System.out.println(new String(bytes));
  }

  @Test
  public void testCreateWithParent() throws Exception {
    String pathWithParent="/node-parent/sub-node-1";
    String path = curatorFramework.create().creatingParentsIfNeeded().forPath(pathWithParent);
    System.out.println(String.format("curator create node :%s  successfully.",path));
  }

  @Test
  public void testDelete() throws Exception {
    String pathWithParent="/node-parent";
    curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(pathWithParent);
  }

  @Test
  public void addNodeListener() throws Exception {

    NodeCache nodeCache = new NodeCache(curatorFramework, "/curator-node");
    nodeCache.getListenable().addListener(new NodeCacheListener() {
      @Override
      public void nodeChanged() throws Exception {
        log.info("{} path nodeChanged: ","/curator-node");
        printNodeData();
      }
    });

    nodeCache.start();

    System.in.read();

  }

  public void printNodeData() throws Exception {
    byte[] bytes = curatorFramework.getData().forPath("/curator-node");
    log.info("data: {}",new String(bytes));
  }



}
