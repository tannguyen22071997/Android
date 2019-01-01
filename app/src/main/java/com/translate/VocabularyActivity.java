package com.translate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        Intent intent = getIntent();
        Part part = (Part) intent.getSerializableExtra("part");
        Database database = new Database(this, "translate.sqlite", null, 1);
        TextView textViewunit = findViewById(R.id.textViewunit);
        textViewunit.setText(part.getName());
        listViewvocabu = findViewById(R.id.listviewvocabu);
        vocabularyList = database.getListVocabu(part.getId());
        vocabularyAdapter = new VocabularyAdapter(this, R.layout.custom_row_vocabulary, vocabularyList);
        listViewvocabu.setAdapter(vocabularyAdapter);

    }
}
