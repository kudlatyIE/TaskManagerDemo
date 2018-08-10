package ie.droidfactory.taskmanagerdemo.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * generate list  of tasks from asset json file
 */
public class DataGenerator {

    private static final String TAG = DataGenerator.class.getSimpleName();

    /**
     *
     * @param context application context
     * @return list of tasks
     */
    public static List<AfsTaskEntity> getTaskFromAsset(Context context){
        Gson gson = new GsonBuilder().create();
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(context.getAssets().open("tasks_json.json")));
            List<AfsTaskEntity> list = gson.fromJson(jsonReader,new TypeToken<ArrayList<AfsTaskEntity>>(){}.getType());
            StringBuilder buf=new StringBuilder();
            InputStream json=context.getAssets().open("tasks_json.json");
            BufferedReader in=
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str=in.readLine()) != null) {
                buf.append(str);
            }
            String jsonString = new String(buf);
            Log.d(TAG, "inputStremReader:\n"+jsonString);
            in.close();

            List<AfsTaskEntity> lista = gson.fromJson(jsonString, new TypeToken<ArrayList<AfsTaskEntity>>(){}.getType());
            for (AfsTaskEntity t: lista){
                Log.d(TAG, "name: "+t.getTaskName()+", date start: "+t.getDateStart());
            }
            return lista;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
