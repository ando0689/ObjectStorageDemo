package test.andranik.objectstoragedemo.object_storage;

import android.util.LruCache;

/**
 * Created by andranik on 3/9/17.
 */

public class ObjectStorageCache extends LruCache<StorageKey<?>, Object> {

    private static final int maxSize = 8 * 1024 * 1024; // 8MiB

    public ObjectStorageCache() {
        super(maxSize);
    }
}
