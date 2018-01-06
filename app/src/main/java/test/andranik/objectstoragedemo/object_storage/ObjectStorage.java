package test.andranik.objectstoragedemo.object_storage;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import test.andranik.objectstoragedemo.App;
import test.andranik.objectstoragedemo.utils.ExceptionTracker;

/**
 * Created by andranik on 3/9/17.
 */

public class ObjectStorage {
    private static final String TAG = "ObjectStorage";

    private static ObjectStorage instance;

    public static ObjectStorage getInstance() {
        if (instance == null) {
            instance = new ObjectStorage();
        }

        return instance;
    }

    public static HashMap<String, StorageKey> keysMap = new HashMap<>();

    public static <T extends Serializable> StorageKey<T> key(String identifier, Class<T> type) {
        if (keysMap.containsKey(identifier)) {
            return keysMap.get(identifier);
        }

        StorageKey key = new StorageKey<>(identifier, type);
        keysMap.put(identifier, key);

        return key;
    }


    private ObjectStorageCache cache;

    private ObjectStorage() {
        cache = new ObjectStorageCache();
    }


    public <T extends Serializable> void put(StorageKey<T> key, T value) {
        cache.put(key, value);
        serialize(key.identifier, value);
    }

    public <T extends Serializable> T get(StorageKey<T> key) {
        Object obj = cache.get(key);

        if (obj == null) {
            obj = deserialize(key.identifier);

            if (obj != null) {
                cache.put(key, obj);
            }
        }

        if (obj == null) {
            return null;
        }

        return key.type.cast(obj);
    }

    private <T extends Serializable> void serialize(String fileName, T obj) {
        try (
            FileOutputStream fileOut = App.getInstance().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut); )
        {
            out.writeObject(obj);
        } catch (IOException e) {
            ExceptionTracker.trackException(e);
        }
    }

    private Object deserialize(String fileName) {
        Object obj = null;

        try (
            FileInputStream fileIn = App.getInstance().openFileInput(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);)
        {
            obj = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            ExceptionTracker.trackException(e);
        }

        return obj;
    }


}
