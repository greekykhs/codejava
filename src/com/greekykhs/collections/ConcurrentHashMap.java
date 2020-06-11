static final class Segment<K,V> extends ReentrantLock implements Serializable {
        
        transient volatile HashEntry<K,V>[] table;
        transient int threshold;
        final float loadFactor;

        final V put(K key, int hash, V value, boolean onlyIfAbsent) {
            // Obtain the lock on the current Segment
            HashEntry<K,V> node = tryLock() ? null : scanAndLockForPut(key, hash, value); 
            try {
                // Do normal Put operation like in hashmap
            } finally {
                // Release the lock after put is finished.
                unlock();
            }
        }

        /**
         * Remove; match on key only if value null, else match both.
         */
        final V remove(Object key, int hash, Object value) {
            // Obtain the lock on the current Segment
            if (!tryLock())
                scanAndLock(key, hash);
            
            try {
                // Normal Remove operation as in HashMap.
            } finally {
                // Release the lock once the remove is done.
                unlock();
            }
            
        }

        final boolean replace(K key, int hash, V oldValue, V newValue) {
            // Obtain the lock on the current Segment
            if (!tryLock())
                scanAndLock(key, hash);
            try {
                // Normal Replace operation as in HashMap.
            } finally {
                 // Release the lock once the remove is done.
                unlock();
            }
            return replaced;
        }

        final void clear() {
           // Obtain the lock on the current Segment
            lock();
            try {
                // Normal clear operation as in HashMap.
            } finally {
                    // Release the lock once the clear is done.
                unlock();
            }
        }
    }