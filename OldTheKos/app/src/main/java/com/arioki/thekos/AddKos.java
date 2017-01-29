package com.arioki.thekos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import static com.arioki.thekos.R.id.add_new_kos;

/**
 * Created by Yoga on 7/29/2015.
 */
public class AddKos extends Fragment {

    private FloatingActionButton add_kos;
    View addkosView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Tambah Kos");
        addkosView = inflater.inflate(R.layout.add_kos, container, false);


        add_kos = (FloatingActionButton) addkosView.findViewById(add_new_kos);

        add_kos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(getActivity(), AddKosForm.class);
                startActivity(inten);
            }
        });
        return addkosView;
    }
}

