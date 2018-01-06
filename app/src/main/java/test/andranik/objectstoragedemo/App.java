package test.andranik.objectstoragedemo;

import android.app.Application;

/**
 * Created by andranik on 1/6/18.
 */

public class App extends Application {

    private static App instance;

    public App() {
        super();
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
