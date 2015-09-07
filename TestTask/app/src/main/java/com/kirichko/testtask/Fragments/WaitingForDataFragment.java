package com.kirichko.testtask.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirichko.testtask.R;

/**
 * Created by Киричко on 06.09.2015.
 */
public class WaitingForDataFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_waiting_for_data, container, false);
        return rootView;
    }
}
