package com.kirichko.testtask.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.kirichko.testtask.Adapters.TariffPlanListAdapter;
import com.kirichko.testtask.DataModels.TariffPlan;
import com.kirichko.testtask.DataModels.UserProfileHolder;
import com.kirichko.testtask.Fragments.LoginFragment;
import com.kirichko.testtask.Fragments.NewTariffFragment;
import com.kirichko.testtask.Fragments.TariffListFragment;
import com.kirichko.testtask.Fragments.WaitingForDataFragment;
import com.kirichko.testtask.R;
import com.kirichko.testtask.Util.GetTPsAsync;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Fragment mCurrentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(UserProfileHolder.mTariffPlanArrayList!= null)
        {
            setTariffListFragment(UserProfileHolder.mTariffPlanArrayList);
        } else {
            setFragment(new LoginFragment());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public  void setFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(mCurrentFragment != null) {

                fragmentTransaction.replace(R.id.fragment_container, fragment)
                                   .commit();
        } else
            {
                fragmentTransaction.add(R.id.fragment_container, fragment)
                                   .commit();
            }

        mCurrentFragment = fragment;
    }

    public void prepareTariffsFragment(String login, String password)
    {
        setFragment(new WaitingForDataFragment());
        GetTPsAsync getTPsAsync = new GetTPsAsync(this, login, password);
        getTPsAsync.execute();
    }

    public void setTariffListFragment(ArrayList<TariffPlan> tariffPlanArrayList)
    {
        TariffListFragment tariffListFragment = new TariffListFragment();
        tariffListFragment.setTariffPlanArrayList(tariffPlanArrayList);
        tariffListFragment.setListAdapter(new TariffPlanListAdapter(this, UserProfileHolder.removeUserTP(tariffPlanArrayList)));
        setFragment(tariffListFragment);
    }

    @Override
    public void onBackPressed() {
        if(mCurrentFragment instanceof TariffListFragment)
        {
            setFragment(new LoginFragment());
        } else
            if(mCurrentFragment instanceof NewTariffFragment)
            {
                TariffListFragment tariffListFragment = new TariffListFragment();
                tariffListFragment.setTariffPlanArrayList(UserProfileHolder.mTariffPlanArrayList);
                tariffListFragment.setListAdapter(new TariffPlanListAdapter(this, UserProfileHolder.removeUserTP(UserProfileHolder.mTariffPlanArrayList)));
                setFragment(tariffListFragment);
            } else
                if(mCurrentFragment instanceof LoginFragment)
                {
                    this.finish();
                } else
                {
                    if(mCurrentFragment instanceof WaitingForDataFragment)
                    {
                        setFragment(new LoginFragment());
                    }
                }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
