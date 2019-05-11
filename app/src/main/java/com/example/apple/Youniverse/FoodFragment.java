package com.example.apple.Youniverse;

/**
 * Created by apple on 09/05/17.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener {



    //@BindView(R.id.input_location) EditText location;
    @BindView(R.id.submit_button) Button submit;
    @BindView(R.id.cuisine) RadioGroup cuisine;
    @BindView(R.id.price) RadioGroup price;

    Fragment fragment;


    private RadioButton cuisineRadio;
    private RadioButton priceRadio;
    private RadioButton drinkRadio;

    String _location;
    String TAG="Location Search:";
    private FragmentActivity myContext;

    PlaceAutocompleteFragment places;
    String mStringLatitude,mStringLongitude;
    String _cuisineFinal;
    private Context context;    private GoogleApiClient mGoogleApiClient;
    ViewGroup rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_food, container, false);
        // Construct a GeoDataClient.

        mGoogleApiClient = new GoogleApiClient
                .Builder(rootView.getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();


        places= (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.food_place_autocomplete_fragment);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        places.setFilter(typeFilter);

        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

               // Toast.makeText(getContext(),place.getClass().getName(),Toast.LENGTH_SHORT).show();
                Context mContext=getContext();
                 _location=String.valueOf(place.getName());

                if (place != null) {
                    LatLng latLng = place.getLatLng();
                    mStringLatitude = String.valueOf(latLng.latitude);
                     mStringLongitude = String.valueOf(latLng.longitude);
                }

            }

            @Override
            public void onError(Status status) {

          //      Toast.makeText(getContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });


        ButterKnife.bind(this, rootView);

        fragment= null;

        submit = (Button) rootView.findViewById(R.id.submit_button);
        //location = (EditText) rootView.findViewById(R.id.input_location);

        cuisine = (RadioGroup) rootView.findViewById(R.id.cuisine);
        price=(RadioGroup) rootView.findViewById(R.id.price);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               // String _location=location.getText().toString().trim();

                int selectedId = price.getCheckedRadioButtonId();
                priceRadio = (RadioButton) rootView.findViewById(selectedId);
                String _price=priceRadio.getText().toString();

                selectedId=cuisine.getCheckedRadioButtonId();
                cuisineRadio=(RadioButton) rootView.findViewById(selectedId);
                String _cuisine=cuisineRadio.getText().toString();
                if(_cuisine.equalsIgnoreCase("Indian")){
                    _cuisineFinal="148";
                }
                else if(_cuisine.equalsIgnoreCase("Italian")){
                    _cuisineFinal="55";
                }
                else if(_cuisine.equalsIgnoreCase("Mexican")){
                    _cuisineFinal="73";
                }
                else if(_cuisine.equalsIgnoreCase("Thai")){
                    _cuisineFinal="95";
                }
                else if(_cuisine.equalsIgnoreCase("Chinese")){
                    _cuisineFinal="25";
                }
                else if(_cuisine.equalsIgnoreCase("Arab")){
                    _cuisineFinal="4";
                }




                FirebaseDatabase database = FirebaseDatabase.getInstance();
                // DatabaseReference ref = database.getReference("server/saving-data/fireblog");

                DatabaseReference ref = database.getReference("form");
                DatabaseReference foodRef = ref.child("food");

                Map<String, Object> foodUpdates = new HashMap<>();
                foodUpdates.put("location", _location);
                foodUpdates.put("lat", mStringLatitude);
                foodUpdates.put("lon", mStringLongitude);
                foodUpdates.put("price", _price);
                foodUpdates.put("cuisne", _cuisineFinal);

                foodRef.updateChildren(foodUpdates);


                Fragment fragment = new ResultFood();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


                //change R.layout.yourlayoutfilename for each of your fragments
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        places = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.food_place_autocomplete_fragment);

        if (places != null)
        {
            getActivity().getFragmentManager().beginTransaction().remove(places).commitAllowingStateLoss();
        }

        places = null;
    }


    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // An unresolvable error has occurred and a connection to Google APIs
        // could not be established. Display an error message, or handle
        // the failure silently

        // ...
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Food");
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }



}
