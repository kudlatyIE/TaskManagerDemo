package ie.droidfactory.taskmanagerdemo.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ie.droidfactory.taskmanagerdemo.AppExecutors;

@Database(entities = {AfsTaskEntity.class}, version = 1)
public abstract class AfsTasksDatabase extends RoomDatabase {

    private static final String TAG  = AfsTasksDatabase.class.getSimpleName();

    private static AfsTasksDatabase DB_INSTANCE;
    private static final String DB_NAME = "afs-database";
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public abstract AfsTaskDao afsTaskDao();

    /**
     * create DB  instance, id DB not exists: crate DB and generate data from asset json file
     * @param context application context
     * @param executors applications executors pool
     * @return database instance
     */
    public static AfsTasksDatabase getDatabaseInstance(final Context context, final AppExecutors executors){
        if(null==DB_INSTANCE){
            synchronized (AfsTasksDatabase.class){
                if(null==DB_INSTANCE){
                    DB_INSTANCE = buildDatabase(context.getApplicationContext(),executors);
//                    DB_INSTANCE.setDatabaseCreated();
                    DB_INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return DB_INSTANCE;
    }

    /**
     * create database instance and generate data from asset json file
     */
    private static AfsTasksDatabase buildDatabase(final Context context, final AppExecutors executors){
        return Room.databaseBuilder(context, AfsTasksDatabase.class, DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.databaseExecutor().execute(()->{
                            AfsTasksDatabase database = AfsTasksDatabase.getDatabaseInstance(context, executors);
                            List<AfsTaskEntity> taskList = DataGenerator.getTaskFromAsset(context);
                            insertTasks(database, taskList);
                            database.setDatabaseCreated();
                        });
                    }
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        Log.d(TAG, "db open: "+db.getPath());
                    }
                })
                .build();
    }

    private void updateDatabaseCreated(final Context context){
        if (context.getDatabasePath(DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }
    
    private void setDatabaseCreated(){
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> isDatabaseCreated(){
        return isDatabaseCreated;
    }

    private static void insertTasks(AfsTasksDatabase db, List<AfsTaskEntity> list){
        db.runInTransaction(()->{
            db.afsTaskDao().insertAllTasks(list);
        });
    }

}
