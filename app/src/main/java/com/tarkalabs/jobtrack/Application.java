package com.tarkalabs.jobtrack;

import com.parse.Parse;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "BMgq9uGLVMdZEzQbh51aDyBwIyNxIVXHFQiTJ3ek", "nk6xPawaC6WiA5VPI6ok86obOozVq0ztMJcrU2yM");
    }
}