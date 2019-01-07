package com.translate;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.translate.data.Database;
import com.translate.model.Part;
import com.translate.model.Vocabulary;

import java.util.List;
import java.util.Random;

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
        Part part = (Part) intent.getSerializableExtra("part");
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

                if (positon == vocabularyList.size() - 1) {
                    createdialog();
                } else if (buttonda1.getText().equals(vocabularyList.get(positon).getMean())) {
                    positon++;
                    if (positon < vocabularyList.size()) {
                        game(vocabularyList.get(positon));
                    }
                } else {
                    createdialog();
                }
            }
        });
        buttonda2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                countDownTimer.cancel();

                if (positon == vocabularyList.size()/*||!buttonda2.getText().equals(vocabularyList.get(positon).getMean())*/) {
                    createdialog();
                } else if (buttonda2.getText().equals(vocabularyList.get(positon).getMean())) {
                    positon++;
                    if (positon < vocabularyList.size()) {
                        game(vocabularyList.get(positon));
                    }
                } else
                    createdialog();
            }
        });
        buttonda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();

                if (positon == vocabularyList.size()) {
                    createdialog();
                } else if (buttonda3.getText().equals(vocabularyList.get(positon).getMean())) {
                    positon++;
                    if (positon < vocabularyList.size()) {
                        game(vocabularyList.get(positon));
                    }
                } else
                    createdialog();
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
            Intent intenth=new Intent(GameActivity.this,MainActivity.class);
            startActivity(intenth);
        }
        return super.onOptionsItemSelected(item);
    }

    private void createdialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_win);
        dialog.show();
    }

    private void randomanswer(Random random) {
        int answer1 = 0, answer2 = 0;
        boolean check = true;
        while (check) {
            answer1 = random.nextInt(vocabularyList.size());
            answer2 = random.nextInt(vocabularyList.size());
            if (answer1 != positon && answer2 != positon)
                if (answer1 != answer2)
                    check = false;
        }
        Vocabulary answerv1 = vocabularyList.get(answer1);
        Vocabulary answerv2 = vocabularyList.get(answer2);
        Vocabulary answerv3 = vocabularyList.get(positon);
        int r1 = random.nextInt(3);
        if (r1 == 0) {
            buttonda1.setText(answerv1.getMean());
            buttonda2.setText(answerv2.getMean());
            buttonda3.setText(answerv3.getMean());
        } else if (r1 == 1) {
            buttonda1.setText(answerv1.getMean());
            buttonda3.setText(answerv2.getMean());
            buttonda2.setText(answerv3.getMean());
        } else if (r1 == 2) {
            buttonda3.setText(answerv1.getMean());
            buttonda2.setText(answerv2.getMean());
            buttonda1.setText(answerv3.getMean());
        }
    }

    private void game(Vocabulary vocabulary) {
        textViewvocabu.setText(vocabulary.getWord());
        Random random = new Random();
        randomanswer(random);
        seekBar.setProgress(0);
        countDownTimer = new CountDownTimer(5000, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                int i = seekBar.getProgress();
                seekBar.setProgress(i + 1);
            }

            @Override
            public void onFinish() {
//                createdialog();
            }
        }.start();

    }


}
