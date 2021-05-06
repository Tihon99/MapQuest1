package com.example.mapquest;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NovelActivity extends AppCompatActivity {
    TextView textNovella;
    Button buttonRight;
    Button buttonLeft;
    Resources res;
    int count, count1;
    boolean flag = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);

        getSupportActionBar().hide();


        textNovella     =   findViewById(R.id.text_view_novel);
        buttonLeft      =   findViewById(R.id.button_left);
        buttonRight     =   findViewById(R.id.button_right);

        ArrayList<Integer> partNovel = getIntent().getIntegerArrayListExtra("PART");

        readFile(partNovel.get(0));
        String lines[] = readFile(partNovel.get(0)).split("\n");

        count = 1;
        textNovella.setText(lines[count]);

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!lines[count + 1].equals("")) {
                    count++;
                    textNovella.setText(lines[count]);
                    if (count + 1 == lines.length) {
                        buttonRight.setText("Вернуться на карту");
                    }
                } else {
                    Intent intent1 = new Intent(NovelActivity.this, MainActivity.class);
                    intent1.putExtra("flag", true);
                    startActivity(intent1);
                }

            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    buttonRight.setText("вперёд");
                    count--;
                    textNovella.setText(lines[count]);
                }
            }
        });


        /*if (partNovel.get(0) == 2) {
            readFile(2);
            String lines[] = readFile(2).split("\n");

            count1 = 1;
            textNovella.setText(lines[count1]);

            buttonRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!lines[count1 + 1].equals("")) {
                        count1++;
                        textNovella.setText(lines[count1]);
                        if (count1 == lines.length) {
                            buttonRight.setText("Вернуться на карту");
                        }
                    } else {
                        Intent intent1 = new Intent(NovelActivity.this, MainActivity.class);
                        startActivity(intent1);
                    }

                }
            });

            buttonLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count1 > 1) {
                        buttonRight.setText("вперёд");
                        count1--;
                        textNovella.setText(lines[count1]);
                    }
                }
            });
        }*/

        /*textNovella.setText(lines[1]);

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lines[count + 1].equals("")) {
                    count++;
                    textNovella.setText(lines[count]);
                }
                if (lines[count + 1].equals("")) {
                    buttonRight.setText("Вернуться на карту");
                } *//*else {
                    Intent intent1 = new Intent(NovelActivity.this, MainActivity.class);
                    startActivity(intent1);
                }*//*

            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    buttonRight.setText("вперёд");
                    count--;
                    textNovella.setText(lines[count]);
                }
            }
        });*/
    }

    private String readFile(int tag) {
        String ret = "";

        try {
            InputStream inputStream;
            if (tag == 1) {
                inputStream = this.getResources().openRawResource(R.raw.historybunker);
            } else if (tag == 2) {
                inputStream = this.getResources().openRawResource(R.raw.islandyoung);
            } else if (tag == 3) {
                inputStream = this.getResources().openRawResource(R.raw.walking);
            } else
                inputStream = null;
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String receiveString = "";

                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }
}
