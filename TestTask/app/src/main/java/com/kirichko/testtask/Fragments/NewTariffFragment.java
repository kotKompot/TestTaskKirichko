package com.kirichko.testtask.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kirichko.testtask.Activities.MainActivity;
import com.kirichko.testtask.Adapters.TariffPlanListAdapter;
import com.kirichko.testtask.DataModels.TariffPlan;
import com.kirichko.testtask.DataModels.UserProfileHolder;
import com.kirichko.testtask.R;

import java.util.ArrayList;

/**
 * Created by Киричко on 07.09.2015.
 */
public class NewTariffFragment extends Fragment {
    private View rootView;
    private int mTariffPlanId;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new_tariff, container, false);

            TextView currentTariffName = (TextView) rootView.findViewById(R.id.currentTariffName);
            TextView currentTariffCost = (TextView) rootView.findViewById(R.id.currentTariffCost);
            TextView newTariffName = (TextView) rootView.findViewById(R.id.newTariffName);
            TextView newTariffCost = (TextView) rootView.findViewById(R.id.newTariffCost);
            Button changeTariff = (Button) rootView.findViewById(R.id.changeTariff);
            changeTariff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserProfileHolder.mCurrentTPid = mTariffPlanId;

                    TariffListFragment tariffListFragment = new TariffListFragment();
                    tariffListFragment.setTariffPlanArrayList(UserProfileHolder.mTariffPlanArrayList);
                    ArrayList<TariffPlan> tariffPlans = new ArrayList<>(UserProfileHolder.mTariffPlanArrayList);
                    tariffListFragment.setListAdapter(new TariffPlanListAdapter((Activity)mContext, UserProfileHolder.removeUserTP(tariffPlans)));
                    ((MainActivity)mContext).setFragment(tariffListFragment);
                }
            });

            currentTariffName.setText(UserProfileHolder.getTPNameById(UserProfileHolder.mCurrentTPid));
            currentTariffCost.setText(UserProfileHolder.getTPCostById(UserProfileHolder.mCurrentTPid));

            newTariffName.setText(UserProfileHolder.getTPNameById(mTariffPlanId));
            newTariffCost.setText(UserProfileHolder.getTPCostById(mTariffPlanId));


        return rootView;
    }

    public void setTariffForOffer(Context context, int tariffPlanId)
    {
        mTariffPlanId = tariffPlanId;
        mContext = context;
    }
}
