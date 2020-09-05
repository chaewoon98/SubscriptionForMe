package com.example.subscriptionforme.home.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.subscriptionforme.recommendation.RecommendationList;

public class SubscriptionDatabase extends SQLiteOpenHelper {

    private static SubscriptionDatabase databaseInstance = null;
    public static String tableNameSubscription = "subscriptionDataBase";


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

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void createTable(SQLiteDatabase database) {

        String sqlString = "CREATE TABLE " + tableNameSubscription + " (registerNumber INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title TEXT,name TEXT, price TEXT, " +
                "consumption TEXT, discount TEXT,icon INTEGER, color INTEGER, benefit INTEGER)";

        try {
            database.execSQL(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSubscriptionData(SQLiteDatabase database, String title, String name, String price, String consumption,
                                  String discount,int icon, int color,int benefit) {

        database.beginTransaction();
        try {

            String sqlString = "insert into " + tableNameSubscription + "( title,name,price," +
                    "consumption,discount,icon,color, benefit)"
                    + " values('" + title + "', '" + name + "', '" + price + "', '"
                    + consumption + "', '" + discount + "', '" + icon + "', '" + color + "', " + benefit + ")";

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


    public RecommendationList getSubscriptionData(SQLiteDatabase database, int index) {
        String sqlSelect = "SELECT * FROM " + tableNameSubscription;
        Cursor cursor = null;
        int count = 0;

        cursor = database.rawQuery(sqlSelect, null);

        while (cursor.moveToNext()) {

            if (count == index)
                return new RecommendationList(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getInt(5),cursor.getInt(6),cursor.getInt(7));
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
}