package ie.droidfactory.taskmanagerdemo.screen.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ie.droidfactory.taskmanagerdemo.R;
import ie.droidfactory.taskmanagerdemo.data.AfsTaskEntity;
import ie.droidfactory.taskmanagerdemo.screen.TaskButtonClickCallback;

public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TaskButtonClickCallback callback;

    @BindView(R.id.adapter_item_task_name_text)
    TextView tvTaskName;

    @BindView(R.id.adapter_item_task_status_text)
    TextView tvTaskStatus;

    @BindView(R.id.adapter_item_task_action_button)
    Button btnTaskAction;

    @BindView(R.id.adapter_item_task_card_view)
    CardView cardView;

    private AfsTaskEntity taskEnity;

    public TaskViewHolder(View itemView, TaskButtonClickCallback callback) {
        super(itemView);
        this.callback=callback;
        ButterKnife.bind(this, itemView);
        btnTaskAction.setOnClickListener(this);
    }

    public void setTaskItem(AfsTaskEntity taskEnityItem){
        this.taskEnity=taskEnityItem;
        tvTaskName.setText(taskEnity.getTaskName());
        tvTaskStatus.setText(taskEnity.getTaskStatusType().getTaskStatus());
        if(taskEnity.getTaskStatusType().isShowingStatus()){
            btnTaskAction.setVisibility(View.VISIBLE);
            btnTaskAction.setText(taskEnityItem.getTaskStatusType().getButtonName());
        }else {
            btnTaskAction.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.adapter_item_task_action_button){
            if(callback!=null) callback.taskButtonListener(getAdapterPosition(), taskEnity);
        }
    }
}

