package com.translate;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.translate.data.Database;
import com.translate.data.ReadFile;
import com.translate.model.Group_class;
import com.translate.model.Part;
import com.translate.model.Vocabulary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Button> buttonArrayList;
    Button buttonHoc;
    Button buttontrans;
    Database database;
    Dialog dialog;

    private void insertData(Database database) {
        Resources resources = getResources();
        BufferedReader bufferedReaderGroup = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.groupclass)));
        BufferedReader bufferedReaderPart = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.part)));
        BufferedReader bufferedReaderVocab = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.vocab)));

        try {
            ReadFile readFile = new ReadFile(bufferedReaderGroup, bufferedReaderPart, bufferedReaderVocab);
            for (Group_class group_class : readFile.getGroup_classList()) {
                database.insertGroup_class(group_class);
            }
            for (Part part : readFile.getPartList()) {
                database.insertPart(part);
            }
            for (Vocabulary vocabulary : readFile.getVocabularyList()) {
                database.insertVocub(vocabulary);
            }
        } catch (IOException e) {
            Log.i("CC", "CC" + e.getMessage());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this, "translate.sqlite", null, 1);
        database.onCreate(database.getWritableDatabase());
        if (database.checknull()) {
            insertData(database);
        }
        database.close();
        buttonHoc = findViewById(R.id.buttonth);
        String[] manglop = getResources().getStringArray(R.array.list_lop);
        Button buttonnc = findViewById(R.id.buttonnc);
        buttonnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2 = "https://khoapham.vn/download/vietnamoi.mp3";
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url2);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
                dialog.cancel();

            }
        });
        buttontrans = findViewById(R.id.buttontrans);
        buttontrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TanslateActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_lop);
        Button button3 = dialog.findViewById(R.id.buttonlop3);
        Button button4 = dialog.findViewById(R.id.buttonlop4);
        Button button5 = dialog.findViewById(R.id.buttonlop5);
        Button button6 = dialog.findViewById(R.id.buttonlop6);
        Button button7 = dialog.findViewById(R.id.buttonlop7);
        Button button8 = dialog.findViewById(R.id.buttonlop8);
        Button button9 = dialog.findViewById(R.id.buttonlop9);
        Button button10 = dialog.findViewById(R.id.buttonlop10);
        Button button11 = dialog.findViewById(R.id.buttonlop11);
        Button button12 = dialog.findViewById(R.id.buttonlop12);
        Button buttoncancel=dialog.findViewById(R.id.buttoncancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        buttonArrayList = new ArrayList<>();
        buttonArrayList.add(button3);
        buttonArrayList.add(button4);
        buttonArrayList.add(button5);
        buttonArrayList.add(button6);
        buttonArrayList.add(button7);
        buttonArrayList.add(button8);
        buttonArrayList.add(button9);
        buttonArrayList.add(button10);
        buttonArrayList.add(button11);
        buttonArrayList.add(button12);
        int i = 2;
        for (Button button : buttonArrayList) {
            i++;
            final String text = button.getText().toString();
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, PartActivity.class);

                    intent.putExtra("lop", finalI);
                    startActivity(intent);
                }
            });
        }
        dialog.show();
    }


}
