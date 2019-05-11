package com.example.apple.Youniverse;

/**
 * Created by apple on 09/05/17.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class TwitterFragment extends Fragment {

    TextView txtJson;
    ProgressDialog pd;


    Fragment fragment;

    private TwitterLoginButton twitterLoginButton;

    private TextView userDetailsLabel;



    //twitter auth client required for custom login
    private TwitterAuthClient client;

    Callback<TwitterSession> callback;

    TwitterSession twitterSession;
    TwitterAuthToken twitterAuthToken;
    long loggedUserTwitterId;
    public Button buttonTwitterLogin;
    ListView mainListView;

    private ArrayAdapter listAdapter ;

    List<com.example.apple.Youniverse.Twitter> twitterFriends;



    ArrayList friendsList = new ArrayList();


    private static final String TAG = MainActivity.class.getSimpleName();

    ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        rootView = (ViewGroup) inflater
                .inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, rootView);

        fragment = null;


        //initialize twitter auth client

        //change R.layout.yourlayoutfilename for each of your fragments
        return rootView;
    }







    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Twitter");
    }

}


