package ua.nure.fedorenko.lab1;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Anastasia on 02.10.2017.
 */

public class FirstLabApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
