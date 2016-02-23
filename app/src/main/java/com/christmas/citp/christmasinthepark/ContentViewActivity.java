package com.christmas.citp.christmasinthepark;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.christmas.citp.christmasinthepark.JSONParser;


public class ContentViewActivity extends ActionBarActivity {

    // Text View Declaration
    TextView content;
    // Url for fetching JSON
    String url;

    //JSON Node Names
    private static final String TAG_POSTS = "posts";
    private static final String TAG_CONTENT = "content";
    JSONArray posts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_view);

        //URL to get JSON Array from Intent
        url = getIntent().getStringExtra("Url");
        new JSONParse().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            content = (TextView)findViewById(R.id.posts);
            pDialog = new ProgressDialog(ContentViewActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                posts = json.getJSONArray(TAG_POSTS);
                JSONObject contentObj = posts.getJSONObject(0);
                // Storing  JSON item in a Variable
                String post = contentObj.getString(TAG_CONTENT);
                //Set JSON Data in TextView
                content.setMovementMethod(new ScrollingMovementMethod());
                content.setText(Html.fromHtml(post, null, new HTMLTagHandler()));

                // Linkify
                content.setClickable(true);
                Linkify.addLinks(content, Linkify.ALL);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
