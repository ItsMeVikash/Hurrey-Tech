package vikash.kumar.hurrey_tech_vikash.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vikash.kumar.hurrey_tech_vikash.R;
import vikash.kumar.hurrey_tech_vikash.util.SharedPreference_Teacher;

public class View3 extends AppCompatActivity {

    private List<String> teacherList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String jsonString=SharedPreference_Teacher.getTeacherTimeTable(getApplicationContext());
        try{
            JSONObject json = new JSONObject(jsonString);
            JSONObject jData = json.getJSONObject("timetable");

            Iterator keysToCopyIterator = jData.keys();
            while(keysToCopyIterator.hasNext()) {
                String key = (String) keysToCopyIterator.next();
                teacherList.add(key);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, teacherList);

            final ListView listView = (ListView) findViewById(R.id.teacher_timetable_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String day=(String)listView.getItemAtPosition(i);
                    // Launch SingleItemView.java using intent
                    Intent intent = new Intent(View3.this, View4.class);
                    intent.putExtra("day", day);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "Oops !! Something Wrong", Toast.LENGTH_SHORT).show();
            Log.d("cskk",e.toString());
            e.printStackTrace();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
