package com.example.apple.Youniverse;

/**
 * Created by apple on 09/05/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EntertainmentFragment extends Fragment {

    @BindView(R.id.comedy)
    ImageView comedy;

    @BindView(R.id.romance)
    ImageView romance;

    @BindView(R.id.action)
    ImageView action;

    @BindView(R.id.horror)
    ImageView horror;

    @BindView(R.id.adventure)
    ImageView adventure;

    @BindView(R.id.animation)
    ImageView animation;

    @BindView(R.id.sciencefic)
    ImageView sciencefic;

    @BindView(R.id.doc)
    ImageView doc;

    @BindView(R.id.english)
    ImageView english;

    @BindView(R.id.hindi)
    ImageView hindi;

    @BindView(R.id.korean)
    ImageView korean;

    @BindView(R.id.mandarin)
    ImageView mandarin;

    @BindView(R.id.german)
    ImageView german;

    @BindView(R.id.russian)
    ImageView russian;

    @BindView(R.id.japanese)
    ImageView japanese;

    @BindView(R.id.spanish)
    ImageView spanish;

    @BindView(R.id.submit_button) Button submit;



    Fragment fragment;

    String _genreFinal;
    String _lng, _lngFinal="en";

    String _genre="comedy";
    ViewGroup rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_entertainment, container, false);
        ButterKnife.bind(this, rootView);

        comedy = (ImageView) rootView.findViewById(R.id.comedy);
        romance = (ImageView) rootView.findViewById(R.id.romance);
        action = (ImageView) rootView.findViewById(R.id.action);
        horror = (ImageView) rootView.findViewById(R.id.horror);
        adventure = (ImageView) rootView.findViewById(R.id.adventure);
        animation = (ImageView) rootView.findViewById(R.id.animation);
        sciencefic = (ImageView) rootView.findViewById(R.id.sciencefic);
        doc = (ImageView) rootView.findViewById(R.id.doc);

        english = (ImageView) rootView.findViewById(R.id.english);
        hindi = (ImageView) rootView.findViewById(R.id.hindi);
        korean = (ImageView) rootView.findViewById(R.id.korean);
        mandarin = (ImageView) rootView.findViewById(R.id.mandarin);
        german = (ImageView) rootView.findViewById(R.id.german);
        russian = (ImageView) rootView.findViewById(R.id.russian);
        japanese = (ImageView) rootView.findViewById(R.id.japanese);
        spanish = (ImageView) rootView.findViewById(R.id.spanish);

        Picasso.with(getContext()).load("https://comedymoviestodownloadforfree.files.wordpress.com/2014/11/comedy-movies-to-download-for-free-2.png").into(comedy);
        Picasso.with(getContext()).load("https://i.pinimg.com/originals/5d/b1/14/5db114df0e31502c6ea1c4c83cf43113.png").into(romance);
        Picasso.with(getContext()).load("https://assets.corusent.com/wp-content/uploads/2016/12/logo_tv_action_flame-500x250.png").into(action);
        Picasso.with(getContext()).load("https://i.pinimg.com/originals/65/3a/2a/653a2a1a03a5ec463b9ebedb0913239d.jpg").into(horror);
        Picasso.with(getContext()).load("https://i.pinimg.com/originals/2b/cb/40/2bcb40879537087c7efe97aaa3d51b84.jpg").into(adventure);
        Picasso.with(getContext()).load("https://buddymantra.com/wp-content/uploads/2017/03/animated_slide.jpg").into(animation);
        Picasso.with(getContext()).load("https://hips.hearstapps.com/pop.h-cdn.co/assets/17/18/1600x800/landscape-1493847257-boardgames-lead.jpg?resize=768:*").into(sciencefic);
        Picasso.with(getContext()).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVk08f76vwYOZCcQq7EOYiApmLu1dWXPeV1PRW9cTODmn1YudF").into(doc);


        Picasso.with(getContext()).load("https://i2.wp.com/oupeltglobalblog.com/wp-content/uploads/2017/04/shutterstock_574059034.jpg?resize=401%2C267&ssl=1").into(english);
        Picasso.with(getContext()).load("https://dz01iyojmxk8t.cloudfront.net/wp-content/uploads/2017/07/28141419/shahshi1.jpg").into(hindi);
        Picasso.with(getContext()).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtQt0f8OfbukLJN0Epg6ZvQbx-imFGoheX0clld8ZLsHSq9IEH").into(korean);
        Picasso.with(getContext()).load("https://www.abclanguagesf.com/images/Language_Logos/mandarin.png").into(mandarin);
        Picasso.with(getContext()).load("https://www.ribttes.com/wp-content/uploads/2014/11/Russia.png").into(russian);
        Picasso.with(getContext()).load("https://cdn-images-1.medium.com/max/710/1*1Kx85lbw4EP0-A0bXV5n5g.jpeg").into(german);
        Picasso.with(getContext()).load("https://ih0.redbubble.net/image.233836939.0458/mp,550x550,matte,ffffff,t.3u1.jpg").into(japanese);
        Picasso.with(getContext()).load("https://bilingualkidspot.com/wp-content/uploads/2016/11/Spanish-language-learning-resources-for-children-Bilingual-Kidspot-816x612.png").into(spanish);

        submit = (Button) rootView.findViewById(R.id.submit_button);

        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="comedy";

                comedy.setAlpha(40);
                romance.setAlpha(1000);
                action.setAlpha(1000);
                adventure.setAlpha(1000);
                horror.setAlpha(1000);
                animation.setAlpha(1000);
                sciencefic.setAlpha(1000);
                doc.setAlpha(1000);



            }
        });

        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="romance";

                comedy.setAlpha(1000);
                romance.setAlpha(40);
                action.setAlpha(1000);
                adventure.setAlpha(1000);
                horror.setAlpha(1000);
                animation.setAlpha(1000);
                sciencefic.setAlpha(1000);
                doc.setAlpha(1000);

            }
        });

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="action";
                comedy.setAlpha(1000);
                romance.setAlpha(1000);
                action.setAlpha(40);
                adventure.setAlpha(1000);
                horror.setAlpha(1000);
                animation.setAlpha(1000);
                sciencefic.setAlpha(1000);
                doc.setAlpha(1000);

            }
        });
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="adventure";
                comedy.setAlpha(1000);
                romance.setAlpha(1000);
                action.setAlpha(1000);
                adventure.setAlpha(40);
                horror.setAlpha(1000);
                animation.setAlpha(1000);
                sciencefic.setAlpha(1000);
                doc.setAlpha(1000);

            }
        });
        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="animation";
                comedy.setAlpha(1000);
                romance.setAlpha(1000);
                action.setAlpha(1000);
                adventure.setAlpha(1000);
                horror.setAlpha(1000);
                animation.setAlpha(40);
                sciencefic.setAlpha(1000);
                doc.setAlpha(1000);

            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="horror";
                comedy.setAlpha(1000);
                romance.setAlpha(1000);
                action.setAlpha(1000);
                adventure.setAlpha(1000);
                horror.setAlpha(40);
                animation.setAlpha(1000);
                sciencefic.setAlpha(1000);
                doc.setAlpha(1000);

            }
        });
        sciencefic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="science fiction";
                comedy.setAlpha(1000);
                romance.setAlpha(1000);
                action.setAlpha(1000);
                adventure.setAlpha(1000);
                horror.setAlpha(1000);
                animation.setAlpha(1000);
                sciencefic.setAlpha(40);
                doc.setAlpha(1000);
            }
        });
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="documentary";
                comedy.setAlpha(1000);
                romance.setAlpha(1000);
                action.setAlpha(1000);
                adventure.setAlpha(1000);
                horror.setAlpha(1000);
                animation.setAlpha(1000);
                sciencefic.setAlpha(1000);
                doc.setAlpha(40);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="en";
                english.setAlpha(40);
                hindi.setAlpha(1000);
                korean.setAlpha(1000);
                mandarin.setAlpha(1000);
                german.setAlpha(1000);
                russian.setAlpha(1000);
                japanese.setAlpha(1000);
                spanish.setAlpha(1000);
            }
        });
        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="hi";
                english.setAlpha(1000);
                hindi.setAlpha(40);
                korean.setAlpha(1000);
                mandarin.setAlpha(1000);
                german.setAlpha(1000);
                russian.setAlpha(1000);
                japanese.setAlpha(1000);
                spanish.setAlpha(1000);
            }
        });
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="ko";
                english.setAlpha(1000);
                hindi.setAlpha(1000);
                korean.setAlpha(40);
                mandarin.setAlpha(1000);
                german.setAlpha(1000);
                russian.setAlpha(1000);
                japanese.setAlpha(1000);
                spanish.setAlpha(1000);
            }
        });
        mandarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="zh";
                english.setAlpha(1000);
                hindi.setAlpha(1000);
                korean.setAlpha(1000);
                mandarin.setAlpha(40);
                german.setAlpha(1000);
                russian.setAlpha(1000);
                japanese.setAlpha(1000);
                spanish.setAlpha(1000);
            }
        });
        german.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="de";
                english.setAlpha(1000);
                hindi.setAlpha(1000);
                korean.setAlpha(1000);
                mandarin.setAlpha(1000);
                german.setAlpha(40);
                russian.setAlpha(1000);
                japanese.setAlpha(1000);
                spanish.setAlpha(1000);
            }
        });
        russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="ru";
                english.setAlpha(1000);
                hindi.setAlpha(1000);
                korean.setAlpha(1000);
                mandarin.setAlpha(1000);
                german.setAlpha(1000);
                russian.setAlpha(40);
                japanese.setAlpha(1000);
                spanish.setAlpha(1000);
            }
        });
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="ja";
                english.setAlpha(1000);
                hindi.setAlpha(1000);
                korean.setAlpha(1000);
                mandarin.setAlpha(1000);
                german.setAlpha(1000);
                russian.setAlpha(1000);
                japanese.setAlpha(40);
                spanish.setAlpha(1000);
            }
        });
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _lngFinal="es";
                english.setAlpha(1000);
                hindi.setAlpha(1000);
                korean.setAlpha(1000);
                mandarin.setAlpha(1000);
                german.setAlpha(1000);
                russian.setAlpha(1000);
                japanese.setAlpha(1000);
                spanish.setAlpha(40);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(_genre.equalsIgnoreCase("Comedy")){
                    _genreFinal="35";
                }
                else if(_genre.equalsIgnoreCase("Romance")){
                    _genreFinal="10749";
                }
                else if(_genre.equalsIgnoreCase("action")){
                    _genreFinal="28";
                }
                else if(_genre.equalsIgnoreCase("horror")){
                    _genreFinal="27";
                }

                else if(_genre.equalsIgnoreCase("adventure")){
                    _genreFinal="12";
                }

                else if(_genre.equalsIgnoreCase("animation")){
                    _genreFinal="16";
                }

                else if(_genre.equalsIgnoreCase("science fiction")){
                    _genreFinal="878";
                }
                else if(_genre.equalsIgnoreCase("documentary")){
                    _genreFinal="99";
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference ref = database.getReference("form");
                DatabaseReference entRef = ref.child("entertainment");

                Map<String, Object> entUpdates = new HashMap<>();

                entUpdates.put("genre", _genreFinal);
                entUpdates.put("language", _lngFinal);
                Log.e("TAG", "Lang" +_lngFinal);

                entRef.updateChildren(entUpdates);

                Fragment fragment = new ResultEntertainment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        fragment= null;
        //change R.layout.yourlayoutfilename for each of your fragments
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Entertainment");
    }
}
