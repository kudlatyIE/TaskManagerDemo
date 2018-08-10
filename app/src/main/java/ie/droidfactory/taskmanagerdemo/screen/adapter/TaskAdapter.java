package ie.droidfactory.taskmanagerdemo.screen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ie.droidfactory.taskmanagerdemo.R;
import ie.droidfactory.taskmanagerdemo.data.AfsTaskEntity;
import ie.droidfactory.taskmanagerdemo.screen.TaskButtonClickCallback;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = TaskAdapter.class.getSimpleName();
    private TaskButtonClickCallback mCallback;
    private Context mContext;
    private List<AfsTaskEntity> mTskList;

    public TaskAdapter(Context context, TaskButtonClickCallback callback){
        this.mContext=context;
        this.mCallback=callback;
    }

    /**
     * load a new task list
     * @param taskEntityList tasks
     */
    public void swapTaskList(List<AfsTaskEntity> taskEntityList){
        this.mTskList=taskEntityList;
        notifyDataSetChanged();
    }

    /**
     * update task when status changed
     * @param position task position
     * @param taskItem task updated
     */
    public void updateTask(int position, AfsTaskEntity taskItem){
        if(null!=mTskList){
            mTskList.set(position, taskItem);
            notifyItemChanged(position,taskItem);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_task_item, parent, false);
        return new TaskViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskViewHolder taskViewHolder = (TaskViewHolder) holder;
        taskViewHolder.cardView.setCardBackgroundColor(mContext.getResources().getColor(mTskList.get(position).getStatusColor()));
        taskViewHolder.setTaskItem(mTskList.get(position));
    }

    @Override
    public int getItemCount() {
        if(null!=mTskList) return mTskList.size();
        return 0;
    }
}
