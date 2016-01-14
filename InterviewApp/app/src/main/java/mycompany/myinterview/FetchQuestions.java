package mycompany.myinterview;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Wesb on 4/24/15.
 */
public class FetchQuestions {

    private List<String> Questions;
    static String spreadsheetPreKey = "https://spreadsheets.google.com/feeds/list/";
    static String spreadsheetPostKey = "/od6/public/values?alt=json-in-script";
    private String spreadsheetURL = "Moki"; //default value; will change in constructor


    public FetchQuestions(String url) {
        Questions = new ArrayList<String>();
        spreadsheetURL = spreadsheetPreKey + url + spreadsheetPostKey;
    }


    //makeRoutingCall function borrowed from  CPSC 210 final project
    private String makeRoutingCall(String httpRequest) throws IOException, InternetException {
        try {
            URL url = new URL(httpRequest);
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            InputStream in = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String returnString = br.readLine();
            client.disconnect();
            return returnString;
        }
        catch (RuntimeException e) {
            throw new InternetException("FetchBehaviouralQuestions");
        }
    }

    private class fetchQuestions extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<String> doInBackground(Void... params) {

            List<String> Questions = new ArrayList<String>();
            String newQuestion;

            try {
                String responseString = makeRoutingCall(spreadsheetURL);
                int responseLength = responseString.length();
                responseString = responseString.substring(28, (responseLength - 2));
                responseString = responseString + ";";
                JSONObject spreadsheetObj = new JSONObject(responseString);
                JSONObject feed = spreadsheetObj.getJSONObject("feed");
                JSONArray entries = feed.getJSONArray("entry");

                for (int i = 0; i < entries.length(); i++) {

                    JSONObject name = entries.getJSONObject(i).getJSONObject("title");
                    newQuestion = name.getString("$t");
                    Questions.add(newQuestion);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Questions.add("Empty");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Questions.add("Empty");
            } catch (IOException e) {
                e.printStackTrace();
                Questions.add("Empty");
            } catch (InternetException e) {
                e.printStackTrace();
                Questions.add("Empty");
            }

            return Questions;

        }

        @Override
        protected void onPostExecute(List<String> questions) {
        }
    }

    public List<String> getQuestionsDatabase() throws ExecutionException, InterruptedException {
        return new fetchQuestions().execute().get();
    }

    public void addToQuestions(String q){
        Questions.add(q);
    }

    public List<String> getQuestions(){
        return Questions;
    }
}