package com.example.sb_projekt.API;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// NETWORK UTILS -------------------------------------------------------------------------------- //
// class responsible for downloading data from API
public class NetworkUtils
{
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public static String getQuestions()
    {
        UrlBuilder urlBuilder = UrlBuilder.getInstance();
        String queryString = urlBuilder.getUrl();

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = null;

        try
        {
            Uri builtURI = Uri.parse(queryString).buildUpon().build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
                builder.append("\n");

                if (builder.length() == 0)
                    return null;
            }
            JSONString = builder.toString();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return " ";
        }
        finally
        {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Log.d(LOG_TAG, JSONString);
        return JSONString;
    }
}