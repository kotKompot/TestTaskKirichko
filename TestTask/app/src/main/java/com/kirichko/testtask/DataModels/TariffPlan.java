package com.kirichko.testtask.DataModels;

/**
 * Created by Киричко on 06.09.2015.
 */
public class TariffPlan {
    public int mId;
    public String mTPname;
    public double mTPcost;
    public int mTPposition;
    public String mTPcolor;

    public TariffPlan(int mId, String mTPname, double mTPcost, int mTPposition, String mTPcolor) {
        this.mId = mId;
        this.mTPname = mTPname;
        this.mTPcost = mTPcost;
        this.mTPposition = mTPposition;
        this.mTPcolor = mTPcolor;
    }

}
