package com.drillgil.android.tourguide.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drillgil.android.tourguide.Home.Australia.AustraliaActivity;
import com.drillgil.android.tourguide.Home.Brazil.BrazilActivity;
import com.drillgil.android.tourguide.Home.Egypt.EgyptActivity;
import com.drillgil.android.tourguide.Home.Greece.GreeceActivity;
import com.drillgil.android.tourguide.Home.Iceland.IcelandActivity;
import com.drillgil.android.tourguide.Home.Ireland.IrelandActivity;
import com.drillgil.android.tourguide.Home.Italy.ItalyActivity;
import com.drillgil.android.tourguide.Home.Japan.JapanActivity;
import com.drillgil.android.tourguide.Home.Russia.RussiaActivity;
import com.drillgil.android.tourguide.Home.Russia.RussiaFragment1;
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

        //----------------------------------------------------------------------------------

        brazil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), BrazilActivity.class);
                startActivity(egyptIntent);

            }
        });

        japan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), JapanActivity.class);
                startActivity(egyptIntent);

            }
        });

        //----------------------------------------------------------------------------------

        italy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent unitedStatesIntent = new Intent(getActivity(), ItalyActivity.class);
                startActivity(unitedStatesIntent);

            }
        });

        greece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), GreeceActivity.class);
                startActivity(egyptIntent);

            }
        });

        //----------------------------------------------------------------------------------

        iceland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), IcelandActivity.class);
                startActivity(egyptIntent);

            }
        });

        ireland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), IrelandActivity.class);
                startActivity(egyptIntent);

            }
        });

        //----------------------------------------------------------------------------------

        russia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), RussiaActivity.class);
                startActivity(egyptIntent);

            }
        });

        australia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent egyptIntent = new Intent(getActivity(), AustraliaActivity.class);
                startActivity(egyptIntent);

            }
        });

        return rootView;
    }

}