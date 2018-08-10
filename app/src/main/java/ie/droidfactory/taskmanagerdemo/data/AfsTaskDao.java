package ie.droidfactory.taskmanagerdemo.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AfsTaskDao {

    @Query("SELECT * FROM tasks WHERE id=:taskId")
    LiveData<AfsTaskEntity> getTaskById(int taskId);

    //TODO: add SELECT ALL, UPDATE, INSERT, DELETE....

    @Query(("SELECT * FROM tasks"))
    LiveData<List<AfsTaskEntity>> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTasks(List<AfsTaskEntity> listTasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(AfsTaskEntity afsTaskEnity);

    @Update
    void updateTask(AfsTaskEntity afsTaskEnity);
    @Update
    void updateAllTasks(List<AfsTaskEntity> taskEntityList);

//    @Query("DELETE FROM tasks WHERE id=:taskId")
//    void deleteTask(int taskId);
//
//    @Query("DELETE FROM tasks")
//    void deleteAllTasks();
}
