package com.mrxyc.cas;

public class CasDemo {
//    private static final AtomicLongFieldUpdater<AtomicBTreePartition> lockFieldUpdater =
//            AtomicLongFieldUpdater.newUpdater(AtomicBTreePartition.class, "lock");
//
//    private void acquireLock() {
//        long t = Thread.currentThread().getId();
//        while (!lockFieldUpdater.compareAndSet(this, 0L, t)) {
//            // 等待一会儿，数据库操作可能比较慢
//        }
//    }
//
//    private static final VarHandle HANDLE = MethodHandles.lookup().findStaticVarHandle
//            (AtomicBTreePartition.class, "lock");
//
//    private void acquireLock() {
//        long t = Thread.currentThread().getId();
//        while (!HANDLE.compareAndSet(this, 0L, t)) {
//            // 等待一会儿，数据库操作可能比较慢
//
//        }
//    }


}
