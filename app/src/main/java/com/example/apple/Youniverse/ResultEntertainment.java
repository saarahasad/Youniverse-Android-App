package com.example.apple.Youniverse;

/**
 * Created by apple on 09/05/17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.FacebookSdk.getApplicationContext;


public class ResultEntertainment extends Fragment {


    ProgressDialog pd;
    @BindView(R.id.submit_button)
    Button submit;

    RadioGroup cuisine;

    Fragment fragment;

    RadioButton genreRadio;

    private List<Movies> movieList = new ArrayList<>();

    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    String genreString;
    String lngString;
    ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        rootView = (ViewGroup) inflater
                .inflate(R.layout.result_music, container, false);
        ButterKnife.bind(this, rootView);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
// Get a reference to your user
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("form/entertainment");

// Attach a listener to read the data at your profile reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                genreString = dataSnapshot.child("genre").getValue(String.class);
                lngString = dataSnapshot.child("language").getValue(String.class);
                new ResultEntertainment.JsonTask().execute("https://api.themoviedb.org/3/genre/" + genreString + "/movies?api_key=0a5d0e69e03c298bba9aa24da9abab67&language=" + lngString + "&include_adult=false&sort_by=created_at.asc", "4", "4");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Oops,Something went wrong! Try again. ", Toast.LENGTH_SHORT).show();
            }
        });


        fragment = null;
        //change R.layout.yourlayoutfilename for each of your fragments
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Movies");
    }

    class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(rootView.getContext());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
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
                try {
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader1 = new JSONObject(result);

                    // Get the JSONObject "coord"...........................

                    JSONArray results = reader1.getJSONArray("results");


                    for (int i = 1; i < 10; i++) {
                        // Get the weather array first JSONObject

                        JSONObject movie_0 = results.getJSONObject(i);

                        String movie_0_name = movie_0.getString("title");
                        String movie_0_url = movie_0.getString("poster_path");
                        String movie_0_release = movie_0.getString("release_date");
                        String movie_0_vote = movie_0.getString("vote_average");
                        String movie_0_overview = movie_0.getString("overview");

                        Log.e("TAG", movie_0_name);
                        Log.e("TAG", movie_0_url);

                        Movies movie1 = new Movies(movie_0_name, movie_0_overview, movie_0_vote, movie_0_release, movie_0_url);
                        movieList.add(movie1);


                    }



                /*txtJson.setText(txtJson.getText()+ "\tsongs...\n");
                txtJson.setText(txtJson.getText()+ "\t\tindex 0...\n");
                txtJson.setText(txtJson.getText()+ "\t\t\tname..."+ track_0_name + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\turl..."+ track_0_url + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\trank..."+ track_0_rank + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\tartist name..."+ track_0_artistname + "\n");
                txtJson.setText(txtJson.getText()+ "\t\t\timage..."+ image_url + "\n");

                // process other data as this way..............*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
            if (pd.isShowing()) {
                pd.dismiss();
            }
            Context mcontext = getContext();
            mAdapter = new MovieAdapter(movieList, mcontext);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

        }

    }
}
