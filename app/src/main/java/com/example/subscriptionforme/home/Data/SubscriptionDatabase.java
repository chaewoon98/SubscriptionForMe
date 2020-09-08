package com.example.subscriptionforme.home.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.subscriptionforme.recommendation.RecommendationList;
import com.example.subscriptionforme.recommendation.RecommendationUserVO;

import java.util.ArrayList;

public class SubscriptionDatabase extends SQLiteOpenHelper {

    private static SubscriptionDatabase databaseInstance = null;
    public static String tableNameSubscription = "subscriptionDataBase";
    public static String subscriptionForUserInformation = "subscriptionForUserInfo";

    public SubscriptionDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SubscriptionDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new SubscriptionDatabase(context, tableNameSubscription, null, 1);
        }
        return databaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
        createUserSubscriptionTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void createUserSubscriptionTable(SQLiteDatabase database) {

        String sqlString = "CREATE TABLE " + subscriptionForUserInformation + " (title TEXT,name TEXT, description TEXT, " +
                "icon INTEGER, color INTERGER)";

        try {
            database.execSQL(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(SQLiteDatabase database) {

        String sqlString = "CREATE TABLE " + tableNameSubscription + " (title TEXT,name TEXT, price TEXT, " +
                "consumption TEXT, discount TEXT,icon INTEGER, color INTEGER, benefit INTEGER)";

        try {
            database.execSQL(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSubscriptionData(SQLiteDatabase database, String title, String name, String price, String consumption,
                                       String discount, int icon, int color, int benefit) {

        database.beginTransaction();
        try {

            String sqlString = "insert into " + tableNameSubscription + "(title,name,price," +
                    "consumption,discount,icon,color,benefit)"
                    + " values('" + title + "', '" + name + "', '" + price + "', '"
                    + consumption + "', '" + discount + "', " + icon + ", " + color + ", " + benefit + ")";

            database.execSQL(sqlString);
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUserSubscriptionData(SQLiteDatabase database, String title, String name, String description, int icon, int color) {

        database.beginTransaction();
        try {

            String sqlString = "insert into " + subscriptionForUserInformation + "(title,name,description," + "icon,color)"
                    + " values('" + title + "', '" + name + "', '" + description + "', " + icon + ", " + color + ")";

            database.execSQL(sqlString);
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubscription(SQLiteDatabase database) {

        String sqlString = "delete from " + tableNameSubscription;

        database.beginTransaction();
        database.execSQL(sqlString);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void deleteUserSubscription(SQLiteDatabase database) {

        String sqlString = "delete from " + subscriptionForUserInformation;

        database.beginTransaction();
        database.execSQL(sqlString);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public RecommendationList getSubscriptionData(SQLiteDatabase database, int index) {
        String sqlSelect = "SELECT * FROM " + tableNameSubscription;
        Cursor cursor = null;
        int count = 0;

        cursor = database.rawQuery(sqlSelect, null);

        while (cursor.moveToNext()) {


            if (count == index) {

                return new RecommendationList(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));

            }
            count++;
        }
        return null;
    }

    public RecommendationUserVO getUserSubscriptionData(SQLiteDatabase database, int index) {
        String sqlSelect = "SELECT * FROM " + subscriptionForUserInformation;
        Cursor cursor = null;
        int count = 0;

        cursor = database.rawQuery(sqlSelect, null);

        while (cursor.moveToNext()) {

            if (count == index) {
                return new RecommendationUserVO(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4));
            }
            count++;
        }
        return null;
    }

    //총 데이타 수를 get

    public Integer getDataCount(SQLiteDatabase database) {
        String sqlSelect = "SELECT * FROM " + tableNameSubscription;
        Cursor cursor = null;
        cursor = database.rawQuery(sqlSelect, null);

        return cursor.getCount();
    }

    public Integer getUserDataCount(SQLiteDatabase database) {
        String sqlSelect = "SELECT * FROM " + subscriptionForUserInformation;
        Cursor cursor = null;
        cursor = database.rawQuery(sqlSelect, null);

        return cursor.getCount();
    }
}