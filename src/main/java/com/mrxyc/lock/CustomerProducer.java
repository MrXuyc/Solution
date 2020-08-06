package com.mrxyc.lock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomerProducer {

    private Queue<String> product = new ArrayDeque<>();
    private AtomicInteger inventory = new AtomicInteger(0);// 实时库存

    private int maxInventory = 10; // 最大库存

    private Lock consumerLock = new ReentrantLock();// 资源锁
    private Lock productLock = new ReentrantLock();// 资源锁

    private Condition notEmptyCondition = consumerLock.newCondition();// 库存满和空条件
    private Condition notFullCondition = productLock.newCondition();// 库存满和空条件

    /**
     * 新增商品库存
     */
    public void produce(String e) {
        productLock.lock();
        try {
            while (inventory.get() == maxInventory) {
                notFullCondition.await();
            }

            product.add(e);

            System.out.println(" 放入一个商品" + e + "库存，总库存为：" + inventory.incrementAndGet());

//            if (inventory.get() < maxInventory) {
//                notFullCondition.signalAll();
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            productLock.unlock();
        }

        if (inventory.get() > 0) {
            try {
                consumerLock.lockInterruptibly();
                notEmptyCondition.signalAll();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                consumerLock.unlock();
            }
        }

    }

    /**
     * 消费商品
     */
    public String consume() {
        String result = null;
        consumerLock.lock();
        try {
            while (inventory.get() == 0) {
                notEmptyCondition.await();
            }

            result = product.poll();
            System.out.println(" 消费一个商品" + result + "，总库存为：" + inventory.decrementAndGet());

//            if (inventory.get() > 0) {
//                notEmptyCondition.signalAll();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumerLock.unlock();
        }

        if (inventory.get() < maxInventory) {

            try {
                productLock.lockInterruptibly();
                notFullCondition.signalAll();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                productLock.unlock();
            }
        }
        return result;
    }

    /**
     * 生产者
     *
     * @author admin
     */
    private class Producer implements Runnable {

        public void run() {
            for (int i = 0; i < 20; i++) {
                produce(" 商品 " + i);
            }
        }
    }

    /**
     * 消费者
     *
     * @author admin
     */
    private class Customer implements Runnable {

        public void run() {
            for (int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {

        CustomerProducer lc = new CustomerProducer();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();

    }
}
