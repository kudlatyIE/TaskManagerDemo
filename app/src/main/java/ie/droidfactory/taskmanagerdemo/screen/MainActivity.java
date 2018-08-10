package ie.droidfactory.taskmanagerdemo.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ie.droidfactory.taskmanagerdemo.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(null==savedInstanceState){
            Log.d(TAG, "create new fragment "+TaskListFragment.TAG);
            TaskListFragment fragment = TaskListFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, TaskListFragment.TAG).commit();
        }
    }
}
