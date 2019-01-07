package com.translate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.translate.adapterFile.VocabularyAdapter;
import com.translate.data.Database;
import com.translate.model.Part;
import com.translate.model.Vocabulary;

import java.util.List;

public class VocabularyActivity extends AppCompatActivity {
    ListView listViewvocabu;
    VocabularyAdapter vocabularyAdapter;
    List<Vocabulary> vocabularyList;
    Button buttonlinkgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        final Intent intent = getIntent();
        final Part part = (Part) intent.getSerializableExtra("part");
        Database database = new Database(this, "translate.sqlite", null, 1);
        TextView textViewunit = findViewById(R.id.textViewunit);
        textViewunit.setText(part.getName());
        listViewvocabu = findViewById(R.id.listviewvocabu);
        vocabularyList = database.getListVocabu(part.getId());
        vocabularyAdapter = new VocabularyAdapter(this, R.layout.custom_row_vocabulary, vocabularyList);
        listViewvocabu.setAdapter(vocabularyAdapter);
        buttonlinkgame=findViewById(R.id.buttonlinkgame);
        buttonlinkgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(VocabularyActivity.this,GameActivity.class);

                intent1.putExtra("part",part);
                startActivity(intent1);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.home){
            Intent intenth=new Intent(VocabularyActivity.this,MainActivity.class);
            startActivity(intenth);
        }
        return super.onOptionsItemSelected(item);
    }
}
