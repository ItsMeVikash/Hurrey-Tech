package vikash.kumar.hurrey_tech_vikash.views;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.parser.JSONArrayParser;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import vikash.kumar.hurrey_tech_vikash.R;
import vikash.kumar.hurrey_tech_vikash.util.SharedPreference_Teacher;

public class View2 extends AppCompatActivity {
    private EditText url;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        url=findViewById(R.id.url);
        //showing ProgressDialog
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("We are verifying Url");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

    }
    public void apply(View view){
        String url_text=url.getText().toString();
        if(!url_text.isEmpty()){
            progressDialog.show();
            Ion.with(getApplicationContext())
                    .load(url_text)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            // do stuff with the result or error
                            progressDialog.dismiss();
                            try{
                                if(result.isJsonNull())
                                    Toast.makeText(View2.this, "Please Check Url", Toast.LENGTH_SHORT).show();
                                else {
                                    Log.d("csk", result.toString());
                                    SharedPreference_Teacher.saveTeacherTimeTable(getApplicationContext(), result.toString());
                                    Toast.makeText(View2.this, "Time Table Downloaded. View it in Home View ", Toast.LENGTH_LONG).show();
                                    url.setText("");
                                }
                            }catch (Exception ex){
                                Toast.makeText(View2.this, "Please Check Url", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }else
            Toast.makeText(this, "Please enter url first", Toast.LENGTH_SHORT).show();
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
