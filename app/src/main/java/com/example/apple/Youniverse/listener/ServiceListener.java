package com.example.apple.Youniverse.listener;

import com.example.apple.Youniverse.Model.FriendsResponseModel;
import com.example.apple.Youniverse.Model.Suggestions;
import com.example.apple.Youniverse.Model.TwitterFriends;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Altaf on 28-Oct-17.
 */

 public interface ServiceListener {
    //For getting friends :  @GET("1.1/friends/list.json")
    @GET("/1.1/trends/place.json")
    Call<List<Suggestions>> list(@Query("id") long id);

   @GET("1.1/followers/list.json")
   Call<FriendsResponseModel> list(@Query("screen_name") String screenname);

    @FormUrlEncoded
    @POST("/1.1/direct_messages/new.json?" +
            "tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Tweet> sendPrivateMessage(@Field("user_id") Long userId,
                                   @Field("screen_name") String screenName,
                                   @Field("text") String text);
}
