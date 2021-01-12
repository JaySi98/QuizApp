package com.example.sb_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AbstractActivity
{
    private int score;
    private int maxQuestions;

    private TextView textView_score;
    private ImageView imageView_score;
    private Button button_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get values from previous Activity
        Intent intent = getIntent();
        this.score = intent.getIntExtra("score",0);
        this.maxQuestions = intent.getIntExtra("max_questions",0);

        textView_score = findViewById(R.id.result_textView_score2);
        imageView_score = findViewById(R.id.result_imageView);

        button_menu = findViewById(R.id.button);
        button_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        // SET THE FINAL SCORE AND THE IMAGE
        setResult();
    }

    private void setResult()
    {
        this.textView_score.setText(Integer.toString(this.score) + '/'
                + Integer.toString(this.maxQuestions));

        float percent =  (float)this.score / this.maxQuestions;

        if(percent <= 0.25)
        {
            imageView_score.setImageResource(R.drawable.cry);
            textView_score.setTextColor(getResources().getColor(R.color.wrong));
        }
        else if(percent > 0.25 && percent <= 0.5)
        {
            imageView_score.setImageResource(R.drawable.thinking);
            textView_score.setTextColor(getResources().getColor(R.color.wrong));
        }
        else if(percent > 0.5 && percent <= 0.75)
        {
            imageView_score.setImageResource(R.drawable.thumb_up);
            textView_score.setTextColor(getResources().getColor(R.color.gold));
        }
        else if(percent > 0.75)
        {
            imageView_score.setImageResource(R.drawable.surprised);
            textView_score.setTextColor(getResources().getColor(R.color.correct));
        }
    }
}