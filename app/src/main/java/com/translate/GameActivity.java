package com.translate;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.translate.data.Database;
import com.translate.model.Part;
import com.translate.model.Vocabulary;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    List<Vocabulary> vocabularyList;
    int positon = 0;
    Button buttonda1;
    Button buttonda2;
    Button buttonda3;
    SeekBar seekBar;
    TextView textViewvocabu;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        Database database = new Database(this, "translate.sqlite", null, 1);
        final Part part = (Part) intent.getSerializableExtra("part");
        vocabularyList = database.getListVocabu(part.getId());
        buttonda1 = findViewById(R.id.buttonda1);
        buttonda2 = findViewById(R.id.buttonda2);
        buttonda3 = findViewById(R.id.buttonda3);
        seekBar = findViewById(R.id.seekBar);
        textViewvocabu = findViewById(R.id.textViewvocabu);
        game(vocabularyList.get(positon));

        buttonda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                positon++;
                if (positon == vocabularyList.size()) {
                    createdialog();
                } else {
                    game(vocabularyList.get(positon));
                }
            }
        });
        buttonda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    private void createdialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_part);
        dialog.show();


    }

    int count = 0;

    private void game(Vocabulary vocabulary) {

        textViewvocabu.setText(vocabulary.getWord());
        buttonda1.setText(vocabulary.getMean());
        seekBar.setProgress(0);
        countDownTimer = new CountDownTimer(5000, 50) {

            @Override
            public void onTick(long millisUntilFinished) {
                int i = seekBar.getProgress();
                seekBar.setProgress(i + 1);

            }

            @Override
            public void onFinish() {
                createdialog();
            }
        }.start();

    }


}
