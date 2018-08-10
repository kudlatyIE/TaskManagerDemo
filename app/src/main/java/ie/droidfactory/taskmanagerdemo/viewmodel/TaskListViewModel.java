package ie.droidfactory.taskmanagerdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.List;
import ie.droidfactory.taskmanagerdemo.TaskManagerApplication;
import ie.droidfactory.taskmanagerdemo.data.AfsTaskEntity;
import ie.droidfactory.taskmanagerdemo.data.DataUpdate;

public class TaskListViewModel  extends AndroidViewModel{

    private static final String TAG  = TaskListViewModel.class.getSimpleName();

    private final MediatorLiveData<List<AfsTaskEntity>> mObservableList;
    private final Application application;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        mObservableList = new MediatorLiveData<>();
        mObservableList.setValue(null);

        LiveData<List<AfsTaskEntity>> tasks = ((TaskManagerApplication)application).getRepository().getAllTasks();

        // observe the changes of the tasks from the database
        mObservableList.addSource(tasks, mObservableList::setValue);
    }

    //provide tasks for UI
    public LiveData<List<AfsTaskEntity>> loadTaskList(){
        if(mObservableList.getValue()!=null) Log.d(TAG, "observableList size: "+mObservableList.getValue().size());
        return mObservableList;
    }

    /**
     * DB here and return updated observable list
     * @return observable list
     */
    public LiveData<List<AfsTaskEntity>>  updateTaskWithChanges(AfsTaskEntity taskEntity){
        List<AfsTaskEntity> list=null;
        if(mObservableList.getValue()!=null) {
            list = mObservableList.getValue();
            Log.d(TAG, "observableList size: "+list.size());

            list = DataUpdate.getUpdatedTaskList(list, taskEntity);
            //update DB:
            ((TaskManagerApplication)application).getRepository().updateAllTasksStatus(list);
        }
        mObservableList.setValue(list);
        return mObservableList;
    }

}