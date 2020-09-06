package com.example.subscriptionforme.home.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.subscriptionforme.recommendation.RecommendationList;

public class AccountDatabase extends SQLiteOpenHelper {

    private static AccountDatabase databaseInstance = null;
    public static String tableNameAccount = "accountDataBase";

    public AccountDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static AccountDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new AccountDatabase(context, tableNameAccount, null, 1);
        }
        return databaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        createTable(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void createTable(SQLiteDatabase database) {

        String sqlString = "CREATE TABLE " + tableNameAccount + " (resAccountTrDate TEXT,resAccountTrTime TEXT, resAccountOut TEXT, " +
                "resAccountIn TEXT, resAccountDesc1 TEXT,resAccountDesc2 TEXT, resAccountDesc3 TEXT, resAfterTranBalance TEXT)";

        try {
            database.execSQL(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAccountData(SQLiteDatabase database, String resAccountTrDate, String resAccountTrTime, String resAccountOut, String resAccountIn,
                                      String resAccountDesc1,String resAccountDesc2, String resAccountDesc3,String resAfterTranBalance) {

        database.beginTransaction();
        try {
            String sqlString = "insert into " + tableNameAccount + "( resAccountTrDate,resAccountTrTime,resAccountOut," +
                    "resAccountIn,resAccountDesc1,resAccountDesc2,resAccountDesc3, resAfterTranBalance)"
                    + " values('" + resAccountTrDate + "', '" + resAccountTrTime + "', '" + resAccountOut + "', '"
                    + resAccountIn + "', '" + resAccountDesc1 + "', '" + resAccountDesc2 + "', '" + resAccountDesc3 + "', " + resAfterTranBalance + ")";
            database.execSQL(sqlString);
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteAccount(SQLiteDatabase database) {

        String sqlString = "delete from " + tableNameAccount;

        database.beginTransaction();
        database.execSQL(sqlString);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void updateSubscruption(SQLiteDatabase database,String registerNumberString, String subscriptionPrice, String subscriptionBeginningPayDate,String subscriptionPayDate,
                                   String subscriptionAlarmSetting,String subscriptionIsAlarmOn,String subscriptionDescription,String subscriptionDeleteURL){

        int registerNumber = Integer.parseInt(registerNumberString);

        String sqlString = "UPDATE " + tableNameAccount + " SET subscriptionPrice = '" + subscriptionPrice + "', subscriptionBeginningPayDate = '" + subscriptionBeginningPayDate + "', subscriptionPayDate = '"
                + subscriptionPayDate + "', subscriptionAlarmSetting = '" + subscriptionAlarmSetting + "', subscriptionIsAlarmOn = '" + subscriptionIsAlarmOn + "', subscriptionDescription = '" + subscriptionDescription +
                "', subscriptionDeleteURL = '" + subscriptionDeleteURL + "' WHERE registerNumber = '" + registerNumber+ "'";

        database.beginTransaction();
        database.execSQL(sqlString);
        database.setTransactionSuccessful();
        database.endTransaction();
    }



    //총 데이타 수를 get
    public Integer getDataCount(SQLiteDatabase database) {
        String sqlSelect = "SELECT * FROM " + tableNameAccount;
        Cursor cursor = null;
        cursor = database.rawQuery(sqlSelect, null);

        return cursor.getCount();
    }


}