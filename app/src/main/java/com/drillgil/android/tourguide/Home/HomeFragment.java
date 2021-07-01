package com.drillgil.android.tourguide.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drillgil.android.tourguide.Home.Egypt.EgyptActivity;
import com.drillgil.android.tourguide.Home.UnitedStates.UnitedStatesActivity;
import com.drillgil.android.tourguide.R;


public class HomeFragment extends Fragment {

    CardView unitedStates,egypt,brazil,japan,italy,greece,iceland,ireland,russia,australia;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        unitedStates = rootView.findViewById(R.id.united_states_card);
        egypt = rootView.findViewById(R.id.egypt_card);
        brazil = rootView.findViewById(R.id.brazil_card);
        japan = rootView.findViewById(R.id.japan_card);
        italy = rootView.findViewById(R.id.italy_card);
        greece = rootView.findViewById(R.id.greece_card);
        iceland = rootView.findViewById(R.id.iceland_card);
        ireland = rootView.findViewById(R.id.ireland_card);
        russia = rootView.findViewById(R.id.russia_card);
        australia = rootView.findViewById(R.id.australia_card);

        unitedStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent unitedStatesIntent = new Intent(getActivity(), UnitedStatesActivity.class);
                startActivity(unitedStatesIntent);

            }
        });

        egypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), EgyptActivity.class);
                startActivity(egyptIntent);

            }
        });

        return rootView;
    }
}