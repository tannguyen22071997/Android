package com.translate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.translate.data.Database;
import com.translate.model.Group_class;

import java.util.ArrayList;
import java.util.List;

public class NangcaoActivity extends AppCompatActivity {
    GridView gridView;
    List<String> stringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nangcao);
        gridView=findViewById(R.id.girdwnc);
        stringList=new ArrayList<>();
        Database database = new Database(this, "translate.sqlite", null, 1);

        final List<Group_class> group_classes=database.getListClass("up");
        for (int i=0;i<group_classes.size();i++){
            stringList.add(group_classes.get(i).getName());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(NangcaoActivity.this,android.R.layout.simple_list_item_1,stringList);
        gridView.setAdapter(arrayAdapter);
        database.close();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(NangcaoActivity.this,PartActivity.class);
                intent.putExtra("lop",group_classes.get(position).getId());
                startActivity(intent);
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
        if (item.getItemId() == R.id.home) {
            Intent intenth = new Intent(NangcaoActivity.this, MainActivity.class);
            startActivity(intenth);
        }
        return super.onOptionsItemSelected(item);
    }
}
