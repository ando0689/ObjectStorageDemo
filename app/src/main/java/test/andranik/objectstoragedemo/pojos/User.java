package test.andranik.objectstoragedemo.pojos;

import android.support.annotation.Nullable;

import java.io.Serializable;

import test.andranik.objectstoragedemo.object_storage.ObjectStorage;

/**
 * Created by andranik on 1/6/18.
 */

public class User implements Serializable {

    private static final String FILE_NAME = "current_user.ser";

    private String fName;
    private String lName;
    private int age;


    public void save(){
        ObjectStorage.getInstance().put(ObjectStorage.key(FILE_NAME, User.class), this);
    }

    public static @Nullable User restore(){
        return ObjectStorage.getInstance().get(ObjectStorage.key(FILE_NAME, User.class));
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
