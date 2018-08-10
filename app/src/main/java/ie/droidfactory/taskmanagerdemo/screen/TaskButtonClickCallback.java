package ie.droidfactory.taskmanagerdemo.screen;

import ie.droidfactory.taskmanagerdemo.data.AfsTaskEntity;

public interface TaskButtonClickCallback {
    void taskButtonListener(int position, AfsTaskEntity taskItem);
}
