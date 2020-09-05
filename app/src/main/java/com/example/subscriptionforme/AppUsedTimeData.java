package com.example.subscriptionforme;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AppUsedTimeData {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
    public final String TAG = AppUsedTimeData.class.getSimpleName();

    public List<UsageStats> getUsageStatsList(Context context){
        UsageStatsManager usm = getUsageStatsManager(context);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_WEEKLY,calendar.getTimeInMillis(), System.currentTimeMillis());
        return usageStatsList;
    }

    public long getAppUsedTime(Context context,String appName){

        List<UsageStats> usageStatsList = getUsageStatsList(context);

        for (UsageStats u : usageStatsList){
            String appTestName = u.getPackageName().replace(" ","").toLowerCase();
            if(appName.equals(appTestName.substring(appTestName.lastIndexOf(".")+1))){
                return u.getTotalTimeInForeground();
            }
        }
        return 0;
    }

    /*public void printUsageStats(List<UsageStats> usageStatsList){
        for (UsageStats u : usageStatsList){
            Log.d(TAG, "Pkg: " + u.getPackageName() +  "\t" + "ForegroundTime: "
                    + u.getTotalTimeInForeground()) ;
        }
    }

    public void printCurrentUsageStatus(Context context){
        printUsageStats(getUsageStatsList(context));
    }*/

    @SuppressWarnings("ResourceType")
    private UsageStatsManager getUsageStatsManager(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        return usm;
    }
}
