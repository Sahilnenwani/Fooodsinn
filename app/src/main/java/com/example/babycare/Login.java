package com.example.babycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button;
        Button button1;

        button = (Button) findViewById(R.id.btn1);
        button1 = (Button) findViewById(R.id.btn2);
        TextView email = (TextView) findViewById(R.id.name);
        TextView pass = (TextView) findViewById(R.id.password);
        pass.setTransformationMethod(new PasswordTransformationMethod());
        firebaseAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String p = pass.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent myintent = new Intent(Login.this, MainActivity.class);
                        startActivity(myintent);

                    }
                });

            }

        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent myintent = new Intent(Login.this, SignUp.class);
//                startActivity(myintent);

                 RequestQueue mRequestQueue;
                StringRequest mStringRequest;
                String url = "https://api.fda.gov/food/enforcement.json?limit=10";
                mRequestQueue = Volley.newRequestQueue(Login.this);

                //String Request initialized
                mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                        String jsonString =  response ; //assign your JSON String here
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(jsonString);
                            JSONArray arr = obj.getJSONArray("results");
                            for (int i = 0; i < arr.length(); i++)
                            {
                                String post_id = arr.getJSONObject(i).getString("product_description");
                                Log.d("neeha", "onResponse: "+post_id);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("Neeha","Error :" + error.toString());
                    }
                });

                mRequestQueue.add(mStringRequest);




//                foodAPI myAsyncTasks = new foodAPI();
//                myAsyncTasks.execute();
            }
        });
    }

}

