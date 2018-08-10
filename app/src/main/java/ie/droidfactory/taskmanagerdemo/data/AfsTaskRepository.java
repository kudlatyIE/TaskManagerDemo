package ie.droidfactory.taskmanagerdemo.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * provide tasks and handle task update
 */
public class AfsTaskRepository {

    private static final String TAG = AfsTaskRepository.class.getSimpleName();

    private static AfsTaskRepository mRepositoryInstance;
    private final AfsTasksDatabase mDatabase;
//    private AfsTaskDao taskDao;
    private MediatorLiveData<List<AfsTaskEntity>> observableTaskList;

//    public AfsTaskRepository(Application application){
//        AfsTasksDatabase database = AfsTasksDatabase.getDatabase(application);
//        taskDao = database.afsTaskDao();
//        allTasks = taskDao.getAllTasks();
//    }
    public AfsTaskRepository(final AfsTasksDatabase afsTasksDatabase){
        this.mDatabase=afsTasksDatabase;
        this.observableTaskList = new MediatorLiveData<>();
//        this.observableTaskList.setValue(mDatabase.afsTaskDao().getAllTasks());
        this.observableTaskList.addSource(mDatabase.afsTaskDao().getAllTasks(),
                productEntities -> {
                    if (mDatabase.isDatabaseCreated().getValue() != null) {
                        observableTaskList.postValue(productEntities);
                    }
                });
    }

    public static AfsTaskRepository getRepositoryInstance(final AfsTasksDatabase tasksDatabase ){
        if(null==mRepositoryInstance){
            synchronized (AfsTaskRepository.class){
                if(null==mRepositoryInstance){
                    mRepositoryInstance = new AfsTaskRepository(tasksDatabase);
                }
            }
        }
        return mRepositoryInstance;
    }

    public LiveData<List<AfsTaskEntity>> getAllTasks(){
        if(observableTaskList.getValue()!=null) Log.d(TAG, "getAllTasks list size: "+observableTaskList.getValue().size());
        return observableTaskList;
    }


    /**
     * TODO: RUN AS ASYNC TASK!!!!!!!!!!!!!
     * @param list
     */
    public void updateAllTasksStatus(List<AfsTaskEntity> list){
        new updateAsyncTask(mDatabase).execute(list);
    }

    public LiveData<AfsTaskEntity> getTask(int taskId){
        return mDatabase.afsTaskDao().getTaskById(taskId);
    }

    private static class updateAsyncTask extends AsyncTask<List<AfsTaskEntity>, Void, Void>{
        private AfsTasksDatabase database;
        updateAsyncTask(AfsTasksDatabase tasksDatabase){
            this.database=tasksDatabase;
        }
        @Override
        protected Void doInBackground(List<AfsTaskEntity>... args) {
            database.afsTaskDao().updateAllTasks(args[0]);
            return null;
        }
    }

}
