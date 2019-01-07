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
import android.widget.Toast;

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
    Part part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        Database database = new Database(this, "translate.sqlite", null, 1);
        part = (Part) intent.getSerializableExtra("part");
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
                    createdialog(1);
                } else if (buttonda1.getText().equals(vocabularyList.get(positon).getMean())) {
                    positon++;
                    if (positon < vocabularyList.size()) {
                        game(vocabularyList.get(positon));
                    }
                } else {
                    createdialog(2);
                }
            }
        });
        buttonda2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                countDownTimer.cancel();
                if (positon == vocabularyList.size()-1/*||!buttonda2.getText().equals(vocabularyList.get(positon).getMean())*/) {
                    createdialog(1);
                } else if (buttonda2.getText().equals(vocabularyList.get(positon).getMean())) {
                    positon++;
                    if (positon < vocabularyList.size()) {
                        game(vocabularyList.get(positon));
                    }
                } else
                    createdialog(2);
            }
        });
        buttonda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                if (positon == vocabularyList.size()-1) {
                    createdialog(1);
                } else if (buttonda3.getText().equals(vocabularyList.get(positon).getMean())) {
                    positon++;
                    if (positon < vocabularyList.size()) {
                        game(vocabularyList.get(positon));
                    }
                } else
                    createdialog(2);
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
            Intent intenth = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intenth);
        }
        return super.onOptionsItemSelected(item);
    }

    private void createdialog(int i) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);

        if (i == 1) {
            dialog.setContentView(R.layout.dialog_win);
            Button button = dialog.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GameActivity.this, PartActivity.class);
                    intent.putExtra("lop", part.getIdClass());
                    startActivity(intent);
                    dialog.cancel();
                }
            });
        } else if (i == 2) {
            dialog.setContentView(R.layout.dialog_lose);
            Button button = dialog.findViewById(R.id.buttontt);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GameActivity.this, GameActivity.class);
                    intent.putExtra("part", part);
                    startActivity(intent);
                    dialog.cancel();
                }
            });
        }
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
                createdialog(2);
            }
        }.start();

    }


}
