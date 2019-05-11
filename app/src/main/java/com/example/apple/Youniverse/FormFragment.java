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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FormFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener{

/*    @BindView(R.id.input_name) EditText name;
    @BindView(R.id.input_email) EditText email;
    @BindView(R.id.input_age) EditText age;
    @BindView(R.id.input_nationality) EditText nationality;
    @BindView(R.id.gender) RadioGroup gender;*/
@BindView(R.id.submit_button) Button submit;
    @BindView(R.id.qualification) RadioGroup qualification;
    @BindView(R.id.interest) RadioGroup interest;
    @BindView(R.id.future) RadioGroup future;


    Fragment fragment;
    View inflatedView = null;
    private RadioButton genderRadio;
    private RadioButton qualificationRadio;
    private RadioButton interestRadio;
    private RadioButton futureRadio;


    String _location;
    String TAG="Location Search:";
    private FragmentActivity myContext;

    PlaceAutocompleteFragment places;

    private Context context;    private GoogleApiClient mGoogleApiClient;


    ViewGroup rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_form, container, false);



        mGoogleApiClient = new GoogleApiClient
                .Builder(rootView.getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();


        places= (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

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

            }

            @Override
            public void onError(Status status) {

                //      Toast.makeText(getContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        ButterKnife.bind(this, rootView);
        fragment= null;



        submit = (Button) rootView.findViewById(R.id.submit_button);
      /*  name = (EditText) rootView.findViewById(R.id.input_name);
        email = (EditText) rootView.findViewById(R.id.input_email);
        age = (EditText) rootView.findViewById(R.id.input_age);
        nationality = (EditText) rootView.findViewById(R.id.input_nationality);
        gender = (RadioGroup) rootView.findViewById(R.id.gender);*/
        qualification=(RadioGroup) rootView.findViewById(R.id.qualification);
        interest=(RadioGroup) rootView.findViewById(R.id.interest);
        future=(RadioGroup) rootView.findViewById(R.id.future);


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


               /* String _name=name.getText().toString().trim();
                String _email=email.getText().toString().trim();
                String _age=age.getText().toString().trim();
                String _nationality=nationality.getText().toString().trim();

                int selectedId = gender.getCheckedRadioButtonId();
                genderRadio = (RadioButton) rootView.findViewById(selectedId);
                String _gender=genderRadio.getText().toString();*/

                int selectedId=qualification.getCheckedRadioButtonId();
                qualificationRadio=(RadioButton) rootView.findViewById(selectedId);
                String _qualification=qualificationRadio.getText().toString();

                selectedId=interest.getCheckedRadioButtonId();
                interestRadio=(RadioButton) rootView.findViewById(selectedId);
                String _interest=interestRadio.getText().toString();

                selectedId=future.getCheckedRadioButtonId();
                futureRadio=(RadioButton) rootView.findViewById(selectedId);
                String _future=futureRadio.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
               // DatabaseReference ref = database.getReference("server/saving-data/fireblog");

                DatabaseReference ref = database.getReference("form");
                DatabaseReference eduRef = ref.child("education");

                Map<String, Object> eduUpdates = new HashMap<>();
                eduUpdates.put("location", _location);

                eduUpdates.put("qualification", _qualification);
                eduUpdates.put("interest", _interest);
                eduUpdates.put("future", _future);

                eduRef.updateChildren(eduUpdates);




            }
        });

        //change R.layout.yourlayoutfilename for each of your fragments
        return rootView;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();

        places = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

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
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Education and career");
    }
}
