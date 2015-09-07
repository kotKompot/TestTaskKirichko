package com.kirichko.testtask.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kirichko.testtask.Activities.MainActivity;
import com.kirichko.testtask.DataModels.TariffPlan;
import com.kirichko.testtask.DataModels.UserProfileHolder;
import com.kirichko.testtask.Fragments.NewTariffFragment;
import com.kirichko.testtask.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Киричко on 06.09.2015.
 */
public class TariffPlanListAdapter extends ArrayAdapter<TariffPlan> {
    private final Activity context;
    private final ArrayList<TariffPlan> mTariffPlans;

    public TariffPlanListAdapter(Activity context, ArrayList<TariffPlan> tariffPlans) {
        super(context, R.layout.tariff_plan_row, tariffPlans);
        this.context = context;
        mTariffPlans = tariffPlans;

    }

    static class ViewHolder {
        public TextView name;
        public TextView cost;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.tariff_plan_row, null, true);
            holder = new ViewHolder();
            holder.name = (TextView) rowView.findViewById(R.id.name);
            holder.cost = (TextView) rowView.findViewById(R.id.cost);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        sortTariffPlanArray();

        holder.name.setText(mTariffPlans.get(position).mTPname);
        holder.name.setTextColor(Color.parseColor(mTariffPlans.get(position).mTPcolor));
        holder.cost.setText(String.valueOf(mTariffPlans.get(position).mTPcost));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTariffFragment newTariffFragment = new NewTariffFragment();
                newTariffFragment.setTariffForOffer(context, mTariffPlans.get(position).mId);
                ((MainActivity) context).setFragment(newTariffFragment);
            }
        });

        return rowView;
    }

    private void sortTariffPlanArray() {
        Collections.sort(mTariffPlans, new Comparator<TariffPlan>() {
            @Override
            public int compare(TariffPlan lhs, TariffPlan rhs) {
                return lhs.mId - rhs.mId;
            }

            @Override
            public boolean equals(Object object) {
                return false;
            }
        });
    }
}
