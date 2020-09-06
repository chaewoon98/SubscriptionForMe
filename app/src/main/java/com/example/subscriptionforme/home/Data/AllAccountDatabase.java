package com.example.subscriptionforme.home.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.subscriptionforme.recommendation.RecommendationList;
import com.example.subscriptionforme.setting.card.AccountVO;

public class AllAccountDatabase extends SQLiteOpenHelper {

    private static AllAccountDatabase databaseInstance = null;
    public static String tableNameAllAccount = "allaccountDataBase";

    public AllAccountDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static AllAccountDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new AllAccountDatabase(context, tableNameAllAccount, null, 1);
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

        String sqlString = "CREATE TABLE " + tableNameAllAccount + " (resAccountTrDate TEXT,resAccountTrTime TEXT, resAccountOut TEXT, " +
                "resAccountIn TEXT, resAccountDesc1 TEXT,resAccountDesc2 TEXT, resAccountDesc3 TEXT, resAfterTranBalance TEXT)";

        try {
            database.execSQL(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAllAccountData(SQLiteDatabase database, String resAccountTrDate, String resAccountTrTime, String resAccountOut, String resAccountIn,
                                  String resAccountDesc1,String resAccountDesc2, String resAccountDesc3,String resAfterTranBalance) {

        database.beginTransaction();
        try {
            String sqlString = "insert into " + tableNameAllAccount + "( resAccountTrDate,resAccountTrTime,resAccountOut," +
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

    public void deleteAllAccount(SQLiteDatabase database) {

        String sqlString = "delete from " + tableNameAllAccount;

        database.beginTransaction();
        database.execSQL(sqlString);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public AccountVO getAccountdata(SQLiteDatabase database, int index) {
        String sqlSelect = "SELECT * FROM " + tableNameAllAccount;
        Cursor cursor = null;
        int count = 0;

        cursor = database.rawQuery(sqlSelect, null);

        while (cursor.moveToNext()) {

            if (count == index)
                return new AccountVO(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6),cursor.getString(7));
            count++;
        }
        return null;
    }



    //총 데이타 수를 get
    public Integer getDataCount(SQLiteDatabase database) {
        String sqlSelect = "SELECT * FROM " + tableNameAllAccount;
        Cursor cursor = null;
        cursor = database.rawQuery(sqlSelect, null);

        return cursor.getCount();
    }


}