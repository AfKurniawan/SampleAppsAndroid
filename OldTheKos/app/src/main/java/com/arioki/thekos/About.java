package com.arioki.thekos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {

    public About() {
        // Required empty public constructor
    }

    View aboutView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        aboutView = inflater.inflate(R.layout.about, container, false);
       // getActivity().setTitle("About");
        return aboutView;
    }



}
