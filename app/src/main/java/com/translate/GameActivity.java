package com.translate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.translate.model.Part;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent=getIntent();
        Part part= (Part) intent.getSerializableExtra("part");
        Toast.makeText(this, part.toString()+"game", Toast.LENGTH_SHORT).show();

    }
}
