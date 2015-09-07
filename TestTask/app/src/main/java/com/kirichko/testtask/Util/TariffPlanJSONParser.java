package com.kirichko.testtask.Util;

import com.kirichko.testtask.DataModels.TariffPlan;
import com.kirichko.testtask.DataModels.UserProfileHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Киричко on 06.09.2015.
 */
public class TariffPlanJSONParser {

        public static ArrayList<TariffPlan> parseJSON(String string) throws JSONException, MalformedURLException {
            ArrayList<TariffPlan> TariffPlanArrayList = new ArrayList<>();

            JSONObject jSONObject = new JSONObject(string);
            JSONArray salesJSONArray = jSONObject.getJSONArray("TPs");
            for(int i=0; i<salesJSONArray.length(); ++i) {
                JSONObject s = salesJSONArray.getJSONObject(i);
                TariffPlanArrayList.add( new TariffPlan(s.getInt("TPid"),
                                                  s.getString("TPname"),
                                                  s.getInt("TPcost"),
                                                  s.getInt("TPposition"),
                                                  s.getString("TPcolor")));
            }
            return  TariffPlanArrayList;
        }

        public static void parseJSONforUserProfile(String string, ArrayList<TariffPlan> mTariffPlanArrayList) throws JSONException {
            JSONObject jSONObject = new JSONObject(string);
            UserProfileHolder.setUserProfileHolder(jSONObject.getInt("UserID"), jSONObject.getString("UserName"), jSONObject.getInt("CurrentTPid"), mTariffPlanArrayList);
        }
}
