package ie.droidfactory.taskmanagerdemo;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * declare executors for app
 */
public class AppExecutors {

    private final Executor databaseExecutor;

    private AppExecutors(Executor dbExecutor, Executor maiThreadExecutor){
        this.databaseExecutor=dbExecutor;
    }

    public AppExecutors(){
        this(Executors.newSingleThreadExecutor(), new MainThreadExecutor());
    }

    public Executor databaseExecutor(){
        return databaseExecutor;
    }

    private static class MainThreadExecutor implements Executor{

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }

}
