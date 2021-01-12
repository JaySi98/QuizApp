package com.example.sb_projekt.API;


// URL BUILDER ---------------------------------------------------------------------------------- //
// class for constructing urls
public class UrlBuilder
{
    private static UrlBuilder instance;

    private int id;
    private int amount;
    private String difficulty;

    private final String baseUrl = "https://opentdb.com/api.php?";

    public String getUrl()
    {
        String url = baseUrl;

        url += "amount=" + Integer.toString(this.amount) + '&';

        if(id != 0)
        {
            url += "category=" + Integer.toString(this.id) + '&';
        }

        if(difficulty != null)
        {
            this.difficulty = Character.toLowerCase(difficulty.charAt(0)) + difficulty.substring(1);
            url += "difficulty=" + difficulty;
        }

        return url;
    }

    public void setId(int id){this.id = id;}
    public int getId(){return this.id;}

    public void setAmount(int amount){this.amount = amount;}
    public int getAmount(){return this.amount;}

    public void setDifficulty(String difficulty){this.difficulty = difficulty;}
    public String getDifficulty(){return this.difficulty;}

    public static UrlBuilder getInstance()
    {
        if(instance == null)
            instance = new UrlBuilder();

        return instance;
    }

    private UrlBuilder()
    {
        // podstawowe wartości
        this.id = 0;
        this.difficulty = null;
        this.amount = 10;
    }
}