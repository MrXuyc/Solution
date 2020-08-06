package com.mrxyc.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedSample {
    private final StampedLock sl = new StampedLock();

    public void mutate() {
        long stamp = sl.writeLock();
        try {
            write();
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    public String access() {
        long stamp = sl.tryOptimisticRead();
        String data = read();
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                data = read();
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return data;
    }

    private void write() {

    }

    private String read() {
        return "";
    }
}