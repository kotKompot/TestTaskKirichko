package com.kirichko.testtask.DataModels;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Киричко on 06.09.2015.
 */
public class UserProfileHolder {
    public static boolean isLogged = false;
    public static int  mCurrentUserId;
    public static String  mCurrentUserName;
    public static int mCurrentTPid;
    public static ArrayList<TariffPlan> mTariffPlanArrayList;

    public static void setUserProfileHolder(int currentUserId, String currentUserName, int currentTPid, ArrayList<TariffPlan> tariffPlanArrayList) {
        mCurrentUserId = currentUserId;
        mCurrentUserName = currentUserName;
        mCurrentTPid = currentTPid;
        isLogged = true;
        mTariffPlanArrayList = tariffPlanArrayList;
    }
    public static ArrayList<TariffPlan> removeUserTP(ArrayList<TariffPlan> tariffPlans)
    {
        ArrayList<TariffPlan> cutTariffPlant = new ArrayList<>(tariffPlans);
        for (Iterator<TariffPlan> it = cutTariffPlant.iterator(); it.hasNext(); ) {
            TariffPlan tariffPlan = it.next();
            if (tariffPlan.mId == UserProfileHolder.mCurrentTPid) {
                it.remove();
            }
        }
        return  cutTariffPlant;
    }

    public static String getTPNameById(int id)
    {
        for(TariffPlan tariffPlan : mTariffPlanArrayList)
        {
            if(tariffPlan.mId == id)
            {
                return tariffPlan.mTPname;
            }
        }
        return "";
    }
    public static String getTPCostById(int id)
    {
        for(TariffPlan tariffPlan : mTariffPlanArrayList)
        {
            if(tariffPlan.mId == id)
            {
                return String.valueOf(tariffPlan.mTPcost);
            }
        }
        return "";
    }
}
