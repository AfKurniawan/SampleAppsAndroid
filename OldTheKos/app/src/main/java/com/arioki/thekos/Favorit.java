package com.arioki.thekos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favorit extends Fragment {

    public Favorit() {
        // Required empty public constructor
    }

    View favoritView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        favoritView = inflater.inflate(R.layout.favorit, container, false);

        getActivity().setTitle("Favorit");

        return favoritView;
    }



}
