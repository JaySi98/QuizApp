package com.example.sb_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sb_projekt.API.NetworkUtils;
import com.example.sb_projekt.API.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuestionActivity extends AbstractActivity
{
    // QUIZ VARIABLES
    private  List<Question> questionList;
    private int currentQuestion;
    private int score;
    private int numbOfQuestions;
    private int positionOfCorrectAnswer;

    // WIDGETS
    private ImageView imageView;
    private TextView textViewQuestion;
    private TextView textViewNumber;
    private TextView textViewDifficulty;
    //
    private List<Button> buttonList;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    //
    private Button buttonNext;
    private Button buttonFinish;


    // ON CREATE -------------------------------------------------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // SETTING THE ICON
        Intent intent = getIntent();
        int icon = intent.getIntExtra("icon",0);

        // SETTING VARIABLE VALUES
        currentQuestion = 0;
        score = 0;
        numbOfQuestions = 0;
        positionOfCorrectAnswer = 0;

        // SETTING WIDGETS
        imageView = findViewById(R.id.question_imageView_icon);
        imageView.setImageResource(icon);
        //
        textViewQuestion = findViewById(R.id.question_textView_question);
        textViewNumber = findViewById(R.id.question_textView_number);
        textViewDifficulty = findViewById(R.id.question_textView_difficulty);
        //
        buttonFinish = findViewById(R.id.question_button_finish);
        buttonNext = findViewById(R.id.question_button_next);
        button1 = findViewById(R.id.question_button_1);
        button2 = findViewById(R.id.question_button_2);
        button3 = findViewById(R.id.question_button_3);
        button4 = findViewById(R.id.question_button_4);
        //
        buttonList = new ArrayList<Button>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

        // DOWNLOADING QUESTIONS
        questionList = new ArrayList<Question>();
        new FetchQuestions().execute();

        // BUTTONS LISTENERS
        // checking the selected answer
        button1.setOnClickListener(view -> checkAnswer(0));
        button2.setOnClickListener(view -> checkAnswer(1));
        button3.setOnClickListener(view -> checkAnswer(2));
        button4.setOnClickListener(view -> checkAnswer(3));
        // loading next question
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // if this was the last question
                if(currentQuestion == questionList.size()-1)
                {
                    // go to Results activity
                    showResults();
                }
                else
                {
                    // load next question
                    currentQuestion++;
                    loadQuestion();
                }
            }
        });

        // skip whole quiz to the final result
        buttonFinish.setOnClickListener(view -> showResults());
    }


    // SHOW RESULTS ----------------------------------------------------------------------------- //
    // transfers to ResultActivity
    private void showResults()
    {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("score", this.score);
        intent.putExtra("max_questions", this.numbOfQuestions);
        startActivity(intent);
    }


    // CHECK ANSWER ----------------------------------------------------------------------------- //
    // check answer selected by the user if it's correct add 1 to the score
    private void checkAnswer(int number)
    {
        // disable 4 answer buttons so the user cant change the answer
        for(int i = 0; i<buttonList.size(); i++)
        {
            buttonList.get(i).setEnabled(false);
        }

        // check the answer
        Question question = questionList.get(currentQuestion);
        String selectedAnswer = buttonList.get(number).getText().toString();

        if(selectedAnswer.equals(question.getCorrect_answer()))
            score++;

        // change the color of selected and correct button
        setAnswerColors(positionOfCorrectAnswer, number);
    }


    // SHOW ERROR ----------------------------------------------------------------------------- //
    // transfers to ResultActivity with score
    private void showError()
    {
        for(int i = 0; i <buttonList.size(); i++)
            buttonList.get(i).setVisibility(View.GONE);
        buttonNext.setVisibility(View.GONE);
        buttonFinish.setVisibility(View.GONE);

        textViewNumber.setVisibility(View.GONE);
        textViewDifficulty.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        textViewQuestion.setText(getString(R.string.error));
    }


    // SET ANSWER COLORS ------------------------------------------------------------------------ //
    // change button colors
    private void setAnswerColors(int correct, int selected)
    {
        Button button = buttonList.get(correct);
        button.setBackgroundColor(getResources().getColor(R.color.correct));

        if(correct != selected)
        {
            Button button2 = buttonList.get(selected);
            button2.setBackgroundColor(getResources().getColor(R.color.wrong));
        }
    }


    //LOAD QUESTION ----------------------------------------------------------------------------- //
    // change all the widgets to initial state and set new question
    private void loadQuestion()
    {
        // enable answer buttons and change their color
        for(int i = 0; i<buttonList.size(); i++)
        {
            buttonList.get(i).setEnabled(true);
            buttonList.get(i).setBackgroundColor(getResources().getColor(R.color.teal));
        }

        // get another question
        Question question = questionList.get(currentQuestion);

        // question with 4 answers
        if(question.getType().equals("multiple"))
        {
            // correct answer on random button
            int random = new Random().nextInt(4);
            positionOfCorrectAnswer = random;

            // set answers
            int incorrect = 0;
            for(int i =0; i < 4; i++)
            {
                Button button = buttonList.get(i);
                if(random == i)
                    button.setText(question.getCorrect_answer());
                else
                {
                    button.setText(question.getIncorrect_answers().get(incorrect));
                    incorrect++;
                }
            }
        }
        // true-false question
        else if(question.getType().equals("boolean"))
        {
            // set answers
            if(question.getCorrect_answer().equals("True"))
                positionOfCorrectAnswer = 0;
            else
                positionOfCorrectAnswer = 1;

            button1.setText("True");
            button2.setText("False");

            // disable other buttons
            button3.setEnabled(false);
            button3.setText("");
            button3.setBackgroundColor(getResources().getColor(R.color.white));
            //
            button4.setEnabled(false);
            button4.setText("");
            button4.setBackgroundColor(getResources().getColor(R.color.white));
        }

        // set textViews
        textViewQuestion.setText(question.getQuestion());
        textViewDifficulty.setText(question.getDifficulty());
        textViewNumber.setText(String.format("%s/%s",
                Integer.toString(currentQuestion+1), Integer.toString(numbOfQuestions)));
    }


    // FETCH QUESTIONS -------------------------------------------------------------------------- //
    // asynchronous class for downloading questions
    private class FetchQuestions extends AsyncTask<Void,Void,String>
    {
        public FetchQuestions() { }

        // DO IN BACKGROUND --------------------------------------------------------------------- //
        // using NetworkUtils donwloads json file
        @Override
        protected String doInBackground(Void... arg0) {
            return NetworkUtils.getQuestions();
        }

        // ON POST EXECUTE ---------------------------------------------------------------------- //
        // after downloading file convert it to List<Question>
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            try
            {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray itemsArray = jsonObject.getJSONArray("results");

                int i = 0;
                String category = null;
                String type = null;
                String difficulty = null;
                String questionString = null;
                String correctAnswer = null;

                while (i < itemsArray.length())
                {
                    // Get the current item information.
                    JSONObject question = itemsArray.getJSONObject(i);
                    JSONArray incorrectAnswers = question.getJSONArray("incorrect_answers");
                    ArrayList<String> incorrect = new ArrayList<String>();

                    try
                    {
                        category = question.getString("category");
                        type = question.getString("type");
                        difficulty = question.getString("difficulty");
                        questionString =  Html.fromHtml(question.getString("question"))
                                .toString();
                        correctAnswer = Html.fromHtml(question.getString("correct_answer"))
                                .toString();

                        for(int j = 0; j<itemsArray.length(); j++)
                        {
                            incorrect.add( Html.fromHtml(incorrectAnswers.getString(j)).toString());
                        }

                    }
                    catch (Exception e)
                    {
                        //showError();
                    }

                    Question newQuestion = new Question(category, type,difficulty,questionString, correctAnswer, incorrect);
                    questionList.add(newQuestion);

                    i++;
                }

                numbOfQuestions = questionList.size();
                loadQuestion();
            }
            catch (JSONException e)
            {
                showError();
            }
        }
    }
}