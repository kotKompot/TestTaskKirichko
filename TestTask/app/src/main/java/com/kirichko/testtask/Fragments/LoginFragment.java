package com.kirichko.testtask.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.kirichko.testtask.Listeners.OnEnterButtonClickListener;
import com.kirichko.testtask.R;

/**
 * Created by Киричко on 06.09.2015.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        EditText loginTextView = (EditText) rootView.findViewById(R.id.login);
        EditText passwordTextView = (EditText) rootView.findViewById(R.id.password);
        Button enterButton = (Button) rootView.findViewById(R.id.button);

        enterButton.setOnClickListener(new OnEnterButtonClickListener(getActivity(),loginTextView, passwordTextView));


        return rootView;
    }
}
