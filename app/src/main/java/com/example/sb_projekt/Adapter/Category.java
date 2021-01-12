package com.example.sb_projekt.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

import com.example.sb_projekt.QuestionActivity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category
{
    private int image;
    private String title;
    private int id;
    private UUID uuid;

    public Category(int image, String title, int id)
    {
        uuid = UUID.randomUUID();

        this.image = image;
        this.title = title;
        this.id = id;
    }

}
