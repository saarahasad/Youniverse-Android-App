package com.example.apple.Youniverse;

/**
 * Created by apple on 09/05/17.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MusicFragment extends Fragment {

    //@BindView(R.id.input_language) EditText language;

    @BindView(R.id.rock)
    ImageView rock;

    @BindView(R.id.pop)
    ImageView pop;

    @BindView(R.id.classical)
    ImageView classical;

    @BindView(R.id.rnb)
    ImageView rnb;

    @BindView(R.id.jazz)
    ImageView jazz;

    @BindView(R.id.blues)
    ImageView blues;

    @BindView(R.id.country)
    ImageView country;

    @BindView(R.id.folk)
    ImageView folk;

    String _genre;
    Fragment fragment;
    ViewGroup rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, rootView);





        rock = (ImageView) rootView.findViewById(R.id.rock);
        pop = (ImageView) rootView.findViewById(R.id.pop);
        classical = (ImageView) rootView.findViewById(R.id.classical);
        rnb = (ImageView) rootView.findViewById(R.id.rnb);
        jazz = (ImageView) rootView.findViewById(R.id.jazz);
        blues = (ImageView) rootView.findViewById(R.id.blues);
        country = (ImageView) rootView.findViewById(R.id.country);
        folk = (ImageView) rootView.findViewById(R.id.folk);

        Picasso.with(getContext()).load("http://dreamreader.net/wp-content/uploads/2015/01/Rock-Music-titlea.jpg").into(rock);
        Picasso.with(getContext()).load("https://gigifm.com/wp-content/uploads/2017/01/pop-music-500x446.jpg").into(pop);
        Picasso.with(getContext()).load("https://is5-ssl.mzstatic.com/image/thumb/Music/v4/46/f6/a2/46f6a2d8-d8cb-201f-2e61-c3fe2d652b2e/cover.jpg/1200x630bb.jpg").into(classical);
        Picasso.with(getContext()).load("http://channels.roku.com/images/54d3aeb6948b4e0990a802a8dd7842e2-hd.jpg").into(rnb);
        Picasso.with(getContext()).load("https://image.freepik.com/free-vector/jazz-music-elements_23-2147492185.jpg").into(jazz);
        Picasso.with(getContext()).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQB9Zqd1pIIMuPbNTv250JmbDuds3aRIx40TFOh4wIj-xO9z_8").into(blues);
        Picasso.with(getContext()).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoUtmljuTaFFXTHr3DBfAuVvXJeyLoczY8W_z9yOIiucDNwDOrMw").into(country);
        Picasso.with(getContext()).load("https://images.8tracks.com/cover/i/002/682/752/tumblr_ldsw8gjimt1qdq19emodif-1948.jpg?rect=0,0,500,500&q=98&fm=jpg&fit=max").into(folk);
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 _genre="rock";
                submit();
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="pop";
                submit();
            }
        });

        classical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="classical";
                submit();
            }
        });
        rnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="rnb";
                submit();
            }
        });
        jazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="jazz";
                submit();
            }
        });
        blues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="blues";
                submit();
            }
        });
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="country";
                submit();
            }
        });
        folk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _genre="folk";
                submit();
            }
        });
        fragment= null;

        //change R.layout.yourlayoutfilename for each of your fragments
        return rootView;
    }
    public Drawable resizeImage(int imageResource) {// R.drawable.icon
        // Get device dimensions
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();

        BitmapDrawable bd = (BitmapDrawable) this.getResources().getDrawable(
                imageResource);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageResource);
        Drawable drawable = new BitmapDrawable(this.getResources(),
               getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));

        return drawable;
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }


    public void submit(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("form");
        DatabaseReference musicRef = ref.child("music");

        Map<String, Object> musicUpdates = new HashMap<>();
        //  musicUpdates.put("language", _language);
        musicUpdates.put("genre", _genre);

        musicRef.updateChildren(musicUpdates);

       fragment= new ResultMusic();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Music");
    }


}
