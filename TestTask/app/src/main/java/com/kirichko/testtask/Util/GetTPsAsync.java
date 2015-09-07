package com.kirichko.testtask.Util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.kirichko.testtask.Activities.MainActivity;
import com.kirichko.testtask.DataModels.TariffPlan;
import com.kirichko.testtask.DataModels.UserProfileHolder;
import com.kirichko.testtask.Fragments.LoginFragment;
import com.kirichko.testtask.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Киричко on 06.09.2015.
 */
public class GetTPsAsync extends AsyncTask<Void, Void,String> {

        private static final String MAIN_ADRESS = "http://vs-premiera.ru/TariffPlanManager/";
        private static final String TAG = "Http Connection";
        private MainActivity mContext;
        private String mLogin, mPassword;

        public GetTPsAsync(MainActivity context, String login, String password)
        {
            mContext = context;
            mLogin = login;
            mPassword = password;
        }

        @Override
        protected String doInBackground(Void... params) {

            String response="";

                InputStream inputStream;
                HttpURLConnection urlConnection;

            Integer result = 0;
            try {
                URL url = new URL(MAIN_ADRESS + mLogin);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();

            /* 200 represents HTTP OK */
                if (statusCode ==  200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    response = convertInputStreamToString(inputStream);
                    result = 1; // Successful
                    return response;

                }else{
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            if(!result.equals(""))
            {
                try {
                    ArrayList<TariffPlan> mTariffPlanArrayList;
                    mTariffPlanArrayList = TariffPlanJSONParser.parseJSON(result);
                    TariffPlanJSONParser.parseJSONforUserProfile(result,mTariffPlanArrayList);
                    mContext.setTariffListFragment(mTariffPlanArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else
                {
                    mContext.setFragment(new LoginFragment());
                    Toast toast = Toast.makeText(mContext,
                            R.string.WrongLogin, Toast.LENGTH_SHORT);
                    toast.show();
                }

        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException {

            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line;
            String result = "";
            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            if(null!=inputStream){
                inputStream.close();
            }

            return result;
        }
    }


