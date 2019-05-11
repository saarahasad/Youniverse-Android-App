package com.example.apple.Youniverse.client;

import com.example.apple.Youniverse.listener.ServiceListener;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Altaf on 28-Oct-17.
 */

public class MyTwitterApiClient extends TwitterApiClient {

    public MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public ServiceListener getCustomTwitterService() {
        return getService(ServiceListener.class);
    }



}