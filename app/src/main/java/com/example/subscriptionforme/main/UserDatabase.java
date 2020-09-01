package com.example.subscriptionforme.main;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.subscriptionforme.home.UserSubscriptionData;

public class UserDatabase extends SQLiteOpenHelper {

    private static UserDatabase databaseInstance = null;
    public static String tableName = "userDataBase";

    public UserDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static UserDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new UserDatabase(context, tableName, null, 1);
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

        String sqlString = "CREATE TABLE " + tableName + " (registerNumber TEXT,subscriptionNumberID TEXT,subscriptionName TEXT, subscriptionPaymentSystem TEXT, " +
                "subscriptionPrice TEXT, subscriptionPayDate TEXT, subscriptionAlarmSetting TEXT, subscriptionIsAlarmOn TEXT, subscriptionImageID INTEGER)";

        try {
            database.execSQL(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSubcriptionData(SQLiteDatabase database,String registerNumber, String subscriptionNumberID, String subscriptionName, String subscriptionPaymentSystem, String subscriptionPrice,
                                      String subscriptionPayDate, String subscriptionAlarmSetting,String subscriptionIsAlarmOn,int subscriptionImageID) {

        database.beginTransaction();
        try {
            String sqlString = "insert into " + tableName + "(registerNumber, subscriptionNumberID,subscriptionName,subscriptionPaymentSystem," +
                    "subscriptionPrice,subscriptionPayDate,subscriptionAlarmSetting, subscriptionIsAlarmOn, subscriptionImageID)"
                    + " values('" + registerNumber + "', '" + subscriptionNumberID + "', '" + subscriptionName + "', '" + subscriptionPaymentSystem + "', '"
                    + subscriptionPrice + "', '" + subscriptionPayDate + "', '" + subscriptionAlarmSetting + "', '" + subscriptionIsAlarmOn + "', " + subscriptionImageID + ")";
            database.execSQL(sqlString);
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdrress(SQLiteDatabase database, String registerNumber) {

        String sqlString = "delete from " + tableName + " WHERE registerNumber = '" + registerNumber+ "'";

        database.beginTransaction();
        database.execSQL(sqlString);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public UserSubscriptionData getUserData(SQLiteDatabase database, int index) {
        String sqlSelect = "SELECT * FROM " + tableName;
        Cursor cursor = null;
        int count = 0;

        cursor = database.rawQuery(sqlSelect, null);

        while (cursor.moveToNext()) {

            if (count == index)
                return new UserSubscriptionData(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8));
            count++;
        }
        return null;
    }

    //총 데이타 수를 get
    public Integer getDataCount(SQLiteDatabase database) {
        String sqlSelect = "SELECT * FROM " + tableName;
        Cursor cursor = null;
        cursor = database.rawQuery(sqlSelect, null);

        return cursor.getCount();
    }
}
