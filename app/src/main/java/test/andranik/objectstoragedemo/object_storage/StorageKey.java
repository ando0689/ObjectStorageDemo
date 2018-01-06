package test.andranik.objectstoragedemo.object_storage;

import java.io.Serializable;

/**
 * Created by andranik on 3/9/17.
 */

public class StorageKey<T extends Serializable> {

    final String identifier;
    final Class<T> type;

    public StorageKey(String identifier, Class<T> type ) {
        this.identifier = identifier;
        this.type = type;
    }

}
