package fryctze.college.cashbox.utiliy;

import static fryctze.college.cashbox.data.DatabaseReference.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import fryctze.college.cashbox.menu.history.ModelTransaction;

class DatabaseSqlite extends SQLiteOpenHelper {
    public DatabaseSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("create table " +
                    TABLE_TRANSACTION + "(" +
                    TRANSACTION_COL_ID + " integer primary key autoincrement," +
                    TRANSACTION_COL_NAME + " text not null," +
                    TRANSACTION_COL_DATE + " text not null," +
                    TRANSACTION_COL_NOMINAL + " text not null," +
                    TRANSACTION_COL_IS_GAIN + " integer default 0," +
                    TRANSACTION_COL_DESC + " text)");
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            sqLiteDatabase.execSQL("create table " +
                    TABLE_TRANSACTION + "(" +
                    TRANSACTION_COL_ID + " integer primary key autoincrement," +
                    TRANSACTION_COL_NAME + " text not null," +
                    TRANSACTION_COL_DATE + " text not null," +
                    TRANSACTION_COL_NOMINAL + " text not null," +
                    TRANSACTION_COL_IS_GAIN + " integer default 0," +
                    TRANSACTION_COL_DESC + " text)");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_TRANSACTION);
        onCreate(sqLiteDatabase);
    }
}
