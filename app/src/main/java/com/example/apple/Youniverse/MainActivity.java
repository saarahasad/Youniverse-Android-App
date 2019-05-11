package com.example.apple.Youniverse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.Youniverse.Model.FriendsResponseModel;
import com.example.apple.Youniverse.Model.Suggestions;
import com.example.apple.Youniverse.Model.Trends;
import com.example.apple.Youniverse.Model.TwitterFriends;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener{

    private MainActivity activity = this;

    TwitterSession twitterSession;
    TwitterAuthToken twitterAuthToken;
    ProgressDialog pd;
    long loggedUserTwitterId;

    Button buttonTwitterLogin;
    ListView mainListView;

    private ArrayAdapter listAdapter ;

    List<TwitterFriends> twitterFriends;
    List<Suggestions> suggestions;

    ArrayList friendsList = new ArrayList();


    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private TwitterAdapter mAdapter;
    private TrendAdapter sAdapter;
        TextView  trendText;
    TextView  intro;
    TextView  followerText;
    String genreString;
    Button button;
    int flag=0;

    String screenname;
    private List<com.example.apple.Youniverse.Twitter> twiList = new ArrayList<>();
    private List<com.example.apple.Youniverse.Twitter> sugList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView2.setVisibility(View.INVISIBLE);

        trendText = (TextView) findViewById(R.id.trend);
        followerText = (TextView) findViewById(R.id.follower);
        intro = (TextView) findViewById(R.id.intro);
        button = (Button) findViewById(R.id.buttonLogin);
        trendText.setVisibility(View.INVISIBLE);
        followerText.setVisibility(View.INVISIBLE);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue.ttf");
        followerText.setTypeface(typeface);
        trendText.setTypeface(typeface);
        intro.setTypeface(typeface);

        button.setTypeface(typeface);
        //Navgation bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        buttonTwitterLogin = (Button) findViewById(R.id.buttonLogin);


        initTwitter();



    }

    void runtwitter(){


    }


    public void initTwitter(){

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.CONSUMER_KEY),getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);

        twitterAuthClient = new TwitterAuthClient();
    }


    TwitterAuthClient twitterAuthClient;
    public void twitterLogin(View view){

        twitterAuthClient.authorize(activity, new Callback() {
            @Override
            public void success(Result result) {
                twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
                twitterAuthToken = twitterSession.getAuthToken();

                TwitterSession twitterSession = (TwitterSession) result.data;

                buttonTwitterLogin.setText("Logged as "+ twitterSession.getUserName());
                screenname=twitterSession.getUserName();
                Log.e("success",twitterSession.getUserName());
                loggedUserTwitterId = twitterSession.getId();

                loadTwitterFriends();

            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        twitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

    private void loadTwitterFriends() {
        long id=1;
        com.example.apple.Youniverse.client.MyTwitterApiClient myTwitterApiClient = new com.example.apple.Youniverse.client.MyTwitterApiClient(twitterSession);
        myTwitterApiClient.getCustomTwitterService().list(id).enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {


                Log.e("onResponse",response.toString());

                List<Suggestions> f = (List<Suggestions>) response.body();

                Trends[] t=f.get(0).getTrends();
               for(int i=0;i<t.length;i++) {
                   Log.e("onResponse", "Name: " + t[i].getName() + "Url: " + t[i].getUrl());
                   com.example.apple.Youniverse.Twitter music = new com.example.apple.Youniverse.Twitter(t[i].getName(),t[i].getUrl());
                   sugList.add(music);
               }

                sAdapter = new TrendAdapter(sugList,activity);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView2.setLayoutManager(mLayoutManager);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
                recyclerView2.setAdapter(sAdapter);
                recyclerView2.setVisibility(View.VISIBLE);
                trendText.setVisibility(View.VISIBLE);
                followerText.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("onFailure",t.toString());
            }

        });

        myTwitterApiClient.getCustomTwitterService().list(screenname).enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(Call call, Response response) {



                Log.e("onResponse",response.toString());
                twitterFriends = fetchResults(response);

                Log.e("onResponse","twitterfriends:"+twitterFriends.size());

                for (int k=0;k<twitterFriends.size();k++){
                    friendsList.add(twitterFriends.get(k).getName());
                    Log.e("Twitter Friends","Id:"+twitterFriends.get(k).getId()+" Name:"+twitterFriends.get(k).getName()+" pickUrl:"+twitterFriends.get(k).getProfilePictureUrl());
                    com.example.apple.Youniverse.Twitter music = new com.example.apple.Youniverse.Twitter(twitterFriends.get(k).getName(),twitterFriends.get(k).getProfilePictureUrl());
                    twiList.add(music);

                }



                mAdapter = new TwitterAdapter(twiList,activity);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                recyclerView.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("onFailure",t.toString());
            }

        });
    }



    private List fetchResults(Response response) {
        FriendsResponseModel responseModel = (FriendsResponseModel) response.body();
        return responseModel.getResults();
    }



//    public void sendDirectMsg(View view){
//        sendMsg(341925762,"saddm_ruet","Hello, this is test msg from demo app.");
//    }

    public void sendMsg(long userId,String replyName,String msg){
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        com.example.apple.Youniverse.client.MyTwitterApiClient myTwitterApiClient = new com.example.apple.Youniverse.client.MyTwitterApiClient(session);
        Call call = myTwitterApiClient.getCustomTwitterService().sendPrivateMessage(userId,replyName,msg);
        call.enqueue(new Callback() {
            @Override
            public void success(Result result) {
                Toast.makeText(activity,"Message sent", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(activity, exception.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displaySelectedScreen(item.getItemId());

        return true;
    }



    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        Activity a=null;
        //initializing the fragment object which is selected
        switch (itemId) {
           /*case R.id.nav_twitter:
                fragment =new TwitterFragment();

                break;
            case R.id.nav_education:
                fragment = new FormFragment();
                break;*/
            case R.id.nav_twitter:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_food:
                fragment = new FoodFragment();
                break;
            case R.id.nav_music:
                fragment = new MusicFragment();
                break;

            case R.id.nav_entertainment:
                fragment = new EntertainmentFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                String result = buffer.toString();
             /*   try{
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader1= new JSONObject(result);

                    // Get the JSONObject "coord"...........................
                    JSONObject tracks = reader1.getJSONObject("tracks");

                    JSONArray track = tracks.getJSONArray("track");

                    JSONObject track_0 = track.getJSONObject(0);

                    String track_0_name = track_0.getString("name");
                    String track_0_url = track_0.getString("url");

                    JSONArray image = track_0.getJSONArray("image");
                    JSONObject image_large = image.getJSONObject(2);
                    String image_url = image_large.getString("#text");

                    JSONObject track_art_0 = track_0.getJSONObject("artist");
                    String track_0_artistname = track_art_0.getString("name");

                    JSONObject track_attr_0 = track_0.getJSONObject("@attr");
                    String track_0_rank = track_attr_0.getString("rank");


                    Music music = new Music(track_0_name, track_0_url,track_0_rank,track_0_artistname,image_url);
                    musicList.add(music);

                    for(int i=1;i<19;i++) {
                        // Get the weather array first JSONObject
                        track_0 = track.getJSONObject(i);

                        track_0_name = track_0.getString("name");
                        track_0_url = track_0.getString("url");

                        image = track_0.getJSONArray("image");
                        image_large = image.getJSONObject(2);
                        image_url = image_large.getString("#text");

                        track_art_0 = track_0.getJSONObject("artist");
                        track_0_artistname = track_art_0.getString("name");

                        track_attr_0 = track_0.getJSONObject("@attr");
                        track_0_rank = track_attr_0.getString("rank");

                        Log.e("Here", " HERE"+ track_0_name+" "+ track_0_url+" "+ image_url+" "+track_0_artistname+" "+track_0_rank);

                        Music music1 = new Music(track_0_name, track_0_url,track_0_rank,track_0_artistname,image_url);
                        musicList.add(music1);


                    }



                /*txtJson.setText(txtJson.getText()+ "\tsongs...\n");
                txtJson.setText(txtJson.getText()+ "\t\tindex 0...\n");
                txtJson.setText(txtJson.getText()+ "\t\t\tname..."+ track_0_name + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\turl..."+ track_0_url + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\trank..."+ track_0_rank + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\tartist name..."+ track_0_artistname + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\timage..."+ image_url + "\n");

                // process other data as this way..............

                }catch(JSONException e){
                    e.printStackTrace();
                }*/

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            // txtJson.setText(result);
        }

    }
}