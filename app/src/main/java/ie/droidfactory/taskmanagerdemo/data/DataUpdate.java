package ie.droidfactory.taskmanagerdemo.data;

import android.util.Log;

import java.util.List;

public class DataUpdate {

    private static final String TAG = DataUpdate.class.getSimpleName();

    public static List<AfsTaskEntity> getUpdatedTaskList(List<AfsTaskEntity> list, AfsTaskEntity task){
        TaskType type = task.getTaskStatusType();
        if(type==TaskType.TRAVELLING){
            for (AfsTaskEntity t: list){
                if(t.getId()==task.getId()) t.setTaskStatus(TaskType.WORKING.getTaskStatus());
            }

            return list;
        }
        if(type==TaskType.OPEN){
            for (AfsTaskEntity t: list){
                if(t.getId()!=task.getId()) t.setTaskStatus(TaskType.INACTIVE.getTaskStatus());
                else t.setTaskStatus(TaskType.TRAVELLING.getTaskStatus());
            }
            return list;
        }
        if (type==TaskType.WORKING){
            for (AfsTaskEntity t: list){
                t.setTaskStatus(TaskType.OPEN.getTaskStatus());
            }
            return list;
        }
        return null;
    }
}
