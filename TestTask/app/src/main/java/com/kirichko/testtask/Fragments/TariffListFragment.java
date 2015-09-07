package com.kirichko.testtask.Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kirichko.testtask.DataModels.TariffPlan;
import com.kirichko.testtask.DataModels.UserProfileHolder;
import com.kirichko.testtask.R;

import java.util.ArrayList;

/**
 * Created by Киричко on 06.09.2015.
 */
public class TariffListFragment extends ListFragment {

    private TextView mCurrentTPsName;
    private TextView mCurrentTPsCost;
    private ArrayList<TariffPlan> mTariffPlanArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tariff_list, container, false);
        mCurrentTPsName = (TextView) rootView.findViewById(R.id.headerTariffName);
        mCurrentTPsCost = (TextView) rootView.findViewById(R.id.headerTariffCost);

        for(TariffPlan tariffPlan : mTariffPlanArrayList)
        {
            if(tariffPlan.mId == UserProfileHolder.mCurrentTPid)
            {
                mCurrentTPsName.setText(tariffPlan.mTPname);
                mCurrentTPsCost.setText(String.valueOf(tariffPlan.mTPcost));
            }
        }

        return rootView;
    }

    public void setTariffPlanArrayList(ArrayList<TariffPlan> tariffPlanArrayList)
    {
      mTariffPlanArrayList = new ArrayList<>(tariffPlanArrayList);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        setListAdapter(null);
    }
}
