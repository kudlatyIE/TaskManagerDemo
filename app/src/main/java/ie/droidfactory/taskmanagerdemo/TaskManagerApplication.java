package ie.droidfactory.taskmanagerdemo;

import android.app.Application;

import ie.droidfactory.taskmanagerdemo.data.AfsTaskRepository;
import ie.droidfactory.taskmanagerdemo.data.AfsTasksDatabase;

public class TaskManagerApplication extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate(){
        super.onCreate();
        mAppExecutors =new AppExecutors();

    }

    public AfsTasksDatabase getDatabase(){
        return AfsTasksDatabase.getDatabaseInstance(this, mAppExecutors);
    }
    public AfsTaskRepository getRepository(){
        return AfsTaskRepository.getRepositoryInstance(getDatabase());
    }

}
