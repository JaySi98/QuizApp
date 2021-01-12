package com.example.sb_projekt.Adapter;

import android.content.Context;

import com.example.sb_projekt.Adapter.Category;
import com.example.sb_projekt.R;

import java.util.ArrayList;

public class AppUtility
{
    public static ArrayList<Category> buildArray(Context ctx)
    {
        ArrayList<Category> gridArray = new ArrayList<Category>();

        gridArray.add(new Category( R.drawable.general_knowledge,"General Knowledge", 9));
        gridArray.add(new Category(R.drawable.books,"Books", 10));
        gridArray.add(new Category(R.drawable.film,"Films", 11));
        gridArray.add(new Category(R.drawable.music,"Music", 12));
        gridArray.add(new Category(R.drawable.theatres,"Musicals & Theatres", 13));
        gridArray.add(new Category(R.drawable.television,"Television", 14));
        gridArray.add(new Category(R.drawable.video_game,"Video Games", 15));
        gridArray.add(new Category(R.drawable.board_games,"Board Games", 16));
        gridArray.add(new Category(R.drawable.nature,"Science & Nature", 17));
        gridArray.add(new Category(R.drawable.computer_science,"Computers", 18));
        gridArray.add(new Category(R.drawable.mathematics,"Mathematics", 19));
        gridArray.add(new Category(R.drawable.mythology,"Mythology", 20));
        gridArray.add(new Category(R.drawable.sports,"Sports", 21));
        gridArray.add(new Category(R.drawable.geography,"Geography", 22));
        gridArray.add(new Category(R.drawable.history,"History", 23));
        gridArray.add(new Category(R.drawable.politics,"Politics", 24));
        gridArray.add(new Category(R.drawable.art,"Art", 25));
        gridArray.add(new Category(R.drawable.celebrities,"Celebrities", 26));
        gridArray.add(new Category(R.drawable.animals,"Animals", 27));
        gridArray.add(new Category(R.drawable.vehicles,"Vehicles", 28));
        gridArray.add(new Category(R.drawable.comics,"Comics", 29));
        gridArray.add(new Category(R.drawable.gadgets,"Gadgets", 30));
        gridArray.add(new Category(R.drawable.anime_manga,"Anime & Manga", 31));
        gridArray.add(new Category(R.drawable.cartoon,"Cartoon", 32));

        return gridArray;
    }
}
