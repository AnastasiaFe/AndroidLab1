package ua.nure.fedorenko.lab1;

import android.app.Application;
import com.raizlabs.android.dbflow.config.FlowManager;

public class FirstLabApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
