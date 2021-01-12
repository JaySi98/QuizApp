package com.example.sb_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class AboutActivity extends AbstractActivity
{
    private TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        link = findViewById(R.id.about_textView_link);
        link.setText("https://opentdb.com/");
        Pattern pattern = Pattern.compile("https://opentdb.com/");
        Linkify.addLinks(link, pattern, "https://opentdb.com/");

    }
}