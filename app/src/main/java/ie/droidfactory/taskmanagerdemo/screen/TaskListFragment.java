package ie.droidfactory.taskmanagerdemo.screen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ie.droidfactory.taskmanagerdemo.R;
import ie.droidfactory.taskmanagerdemo.data.AfsTaskEntity;
import ie.droidfactory.taskmanagerdemo.screen.adapter.TaskAdapter;
import ie.droidfactory.taskmanagerdemo.viewmodel.TaskListViewModel;

public class TaskListFragment extends Fragment implements TaskButtonClickCallback{

    public static final String TAG = TaskListFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    TaskListViewModel viewModel;

    public static TaskListFragment newInstance(){
        return new TaskListFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.task_list_fragment, container, false);

        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.task_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.task_list_recycleView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mTaskAdapter = new TaskAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mTaskAdapter);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);

        viewModel.loadTaskList().observe(this, new Observer<List<AfsTaskEntity>>() {
            @Override
            public void onChanged(@Nullable List<AfsTaskEntity> taskEntityList) {
                Log.d(TAG, "onActivityCreated - onChanged VM");
                mTaskAdapter.swapTaskList(taskEntityList);
            }
        });
        Log.d(TAG, "onActivityCreated - end");
    }

    @Override
    public void taskButtonListener(int position, AfsTaskEntity taskItem) {
        Log.d(TAG, "clicked task: "+taskItem.getTaskName());
        //TODO: update tasks: remove buttons, change color,
        //TODO: update clicked task: change status, color, change button


        //TODO: load new task list in view model!!!
        viewModel.updateTaskWithChanges(taskItem).observe(this, new Observer<List<AfsTaskEntity>>() {
            @Override
            public void onChanged(@Nullable List<AfsTaskEntity> taskEntityList) {
                Log.d(TAG, "status update on task: "+taskItem.getTaskName());
                mTaskAdapter.swapTaskList(taskEntityList);
            }
        });
    }
}
