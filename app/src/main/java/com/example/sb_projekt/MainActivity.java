package com.example.sb_projekt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sb_projekt.API.NetworkUtils;
import com.example.sb_projekt.API.Question;
import com.example.sb_projekt.API.UrlBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.sb_projekt.Adapter.AppUtility;
import com.example.sb_projekt.Adapter.CategoryListAdapter;
import com.example.sb_projekt.Adapter.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AbstractActivity implements CategoryListAdapter.OnCategoryListener
{
    //URL BUILDER
    // Singleton for constructing urls
    private UrlBuilder urlBuilder = UrlBuilder.getInstance();

    // WIDGETS
    private Spinner spinner_noq;
    private Spinner spinner_difficulty;
    private RecyclerView recyclerView;
    private ArrayList<Category> categoryList = new ArrayList<Category>();
    private CategoryListAdapter adapter;


    // ON CREATE -------------------------------------------------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // NUMBER OF QUESTIONS SPINNER
        spinner_noq = findViewById(R.id.spinner_noq);
        ArrayAdapter<CharSequence> noqAdapter = ArrayAdapter
                .createFromResource(this, R.array.noq, android.R.layout.simple_spinner_item);
        noqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_noq.setAdapter(noqAdapter);
        //
        spinner_noq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                 String value = parent.getItemAtPosition(position).toString();
                 urlBuilder.setAmount(Integer.parseInt(value));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                urlBuilder.setAmount(10);
            }
        });

        // DIFFICULTY SPINNER
        spinner_difficulty = findViewById(R.id.spinner_difficulties);
        ArrayAdapter<CharSequence> difficultyAdapter = ArrayAdapter
                .createFromResource(this, R.array.difficulty, android.R.layout.simple_spinner_item);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_difficulty.setAdapter(difficultyAdapter);
        //
        spinner_difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String value = parent.getItemAtPosition(position).toString();
                if(value.equals("Any"))
                    urlBuilder.setDifficulty(null);
                else
                    urlBuilder.setDifficulty(value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                urlBuilder.setDifficulty(null);
            }
        });


        // RECYCLER VIEW
        categoryList = AppUtility.buildArray(this);
        recyclerView = findViewById(R.id.recycleView);
        adapter = new CategoryListAdapter(categoryList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    // ON CATEGORY CLICK ------------------------------------------------------------------------ //
    // start quiz with selected category
    @Override
    public void onCategoryClick(int position)
    {
        Category category = categoryList.get(position);
        urlBuilder.setId(category.getId());

        Intent intent = new Intent(this, QuestionActivity.class);
        // send icon of the selected cateogry to the QuestionActivity
        intent.putExtra("icon", category.getImage());
        startActivity(intent);
    }


    // ON CREATE OPTIONS MENU ------------------------------------------------------------------- //
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    // ON OPTIONS ITEM SELECTED ----------------------------------------------------------------- //
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.menu_item_about:
            {
                // change activity to AboutActivity
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}