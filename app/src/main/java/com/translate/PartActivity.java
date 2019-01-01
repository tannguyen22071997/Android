package com.translate;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.translate.adapterFile.PartAdapter;
import com.translate.data.Database;
import com.translate.model.Part;
import com.translate.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class PartActivity extends AppCompatActivity {
    ListView listView;
    List<Part> partArrayList;
    PartAdapter partAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);
        listView = findViewById(R.id.listpart);
        Intent intent = getIntent();
        Database database = new Database(this, "translate.sqlite", null, 1);
        int lop = intent.getIntExtra("lop", 3);
        TextView textViewlop = findViewById(R.id.textViewlop);
        textViewlop.setText("lớp" + lop);
        partArrayList = database.getListPart(lop);
        database.close();
        partAdapter = new PartAdapter(this, R.layout.custom_row_part, partArrayList);
        listView.setAdapter(partAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createdialog(partArrayList.get(position));
            }
        });

    }

    private void createdialog(final Part part) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_part);
        TextView textView = dialog.findViewById(R.id.textunit);
        Button buttonlearn = dialog.findViewById(R.id.btnlearn);
        buttonlearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartActivity.this, VocabularyActivity.class);
                intent.putExtra("part", part);
                startActivity(intent);
            }
        });
        Button buttongame = dialog.findViewById(R.id.btngame);
        buttongame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartActivity.this, GameActivity.class);
                intent.putExtra("part", part);
                startActivity(intent);
            }
        });
        textView.setText(part.getName());
        dialog.show();

    }
}
