package com.example.emptracker_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class taskListActivity extends AppCompatActivity {

    SessionHandler sessionHandler;
    private Button taskBtn;
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        sessionHandler = new SessionHandler(taskListActivity.this);
        sessionHandler.checkLogin();

        HashMap<String, String> user = sessionHandler.getUserDetail();
        String uname = user.get(SessionHandler.NAME);

        String logInURL = "http://192.168.56.1/website/AppOperation.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, logInURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);


                    Log.i("JSON", String.valueOf(jsonArray));
                    buttons = new Button[10];

                    if(jsonArray.getBoolean("error")==false){

                        JSONObject jsonArrayTask = (JSONObject) jsonArray.getJSONObject(Integer.parseInt("TaskData"));

                        for(int i=0; i<jsonArray.length();i++){

                            String buttonID = "assignedTask" + (i+1);
                            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                            buttons[i] = ((Button) findViewById(resID));


                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Iterator it = jsonObject1.keys();
                            String key = (String) it.next();
                            String value = jsonObject1.getString(key);
                            buttons[i].setText(value);

                            //JSONObject json_data = jsonArray.getJSONObject(i);
                           // taskBtn = (Button)findViewById(R.id.resID);
                            //json_data.getInt("account");
                        }
                    }else {

                        Toast.makeText(getApplicationContext(), jsonArray.getString(Integer.parseInt("Msg")), Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("err",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("username", userNm);
                params.put("ActionType", "checkLogIn");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    public void taskOne(View view){
        Intent startNewActivity = new Intent(this, taskDetails.class);
        startActivity(startNewActivity);
    }
}
