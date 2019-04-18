package vikash.kumar.hurrey_tech_vikash.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import vikash.kumar.hurrey_tech_vikash.R;
import vikash.kumar.hurrey_tech_vikash.util.SharedPreference_Teacher;

public class View4 extends AppCompatActivity {
    private TextView details;
    private String timedetails="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        details=findViewById(R.id.details);
        String jsonString=SharedPreference_Teacher.getTeacherTimeTable(getApplicationContext());
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONObject jData = json.getJSONObject("timetable");
            String day=getIntent().getExtras().getString("day","");
            JSONArray jsonArray=jData.getJSONArray(day);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject childJSONObject = jsonArray.getJSONObject(i);
                String clas=childJSONObject.getString("class");
                String section=childJSONObject.getString("section");
                String start=childJSONObject.getString("start");
                String end=childJSONObject.getString("end");
                String subject=childJSONObject.getString("subject");
                timedetails=timedetails+"\n"+"Class:\t"+clas+"\n"+
                        "Section:\t"+section+"\n"+
                        "Start:\t"+start+"\n"+
                        "End:\t"+end+"\n"+
                        "Subject:\t"+subject+"\n";
            }
            details.setText(timedetails);
        }catch (Exception e){
            Toast.makeText(this, "Oops!! Something went wrong", Toast.LENGTH_SHORT).show();
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
