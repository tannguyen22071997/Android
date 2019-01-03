package com.translate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TanslateActivity extends AppCompatActivity {
    EditText editTextel;
    EditText editTextvn;
    Button buttond;
    String word;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanslate);
        editTextel = findViewById(R.id.editTextvb);
        editTextvn = findViewById(R.id.editTextbd);
        buttond = findViewById(R.id.buttondich);
        requestQueue = Volley.newRequestQueue(this);
        buttond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextvn.setText("");
                word = editTextel.getText().toString().trim();
                if (word.equals("")) {
                    Toast.makeText(TanslateActivity.this, "Chưa nhập dữ liệu", Toast.LENGTH_SHORT).show();
                } else {

                    final String url = "https://refapi.herokuapp.com/translate/google?word=" + word + "&slang=en&tlang=vi";
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (!response.isNull("mean")) {
                                    editTextvn.setText(response.getString("mean"));

                                }
                               else
                                    Toast.makeText(TanslateActivity.this, "Dữ liệu không tồn tại", Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener()

                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("AAA", error.getMessage() + "AAA");
                            Toast.makeText(TanslateActivity.this, "Không có kết nối internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }
            }
        });


    }
}
