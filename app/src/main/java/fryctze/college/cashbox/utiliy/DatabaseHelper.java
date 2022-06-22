package fryctze.college.cashbox.utiliy;

import static fryctze.college.cashbox.data.DatabaseReference.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;

import java.util.ArrayList;

import fryctze.college.cashbox.menu.history.ModelTransaction;
import fryctze.college.cashbox.menu.home.ModelGoal;

public class DatabaseHelper {
    private Context context;

    SQLiteDatabase db;
    DatabaseSqlite databaseSqlite;

    public DatabaseHelper(Context context) {
        this.context = context;
        databaseSqlite = new DatabaseSqlite(context);
    }

    public DatabaseHelper openDB(){
        try {
            db = databaseSqlite.getWritableDatabase();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }

    public void closeDB(){
        try {
            databaseSqlite.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ModelTransaction> transactionOfMonth(String month_year){
        ArrayList<ModelTransaction> listTransaction = new ArrayList<>();
        Cursor cursor = db.query(true, TABLE_TRANSACTION, new String[] {
                TRANSACTION_COL_ID,TRANSACTION_COL_NAME, TRANSACTION_COL_DATE, TRANSACTION_COL_NOMINAL,TRANSACTION_COL_IS_GAIN,  TRANSACTION_COL_DESC},
                TRANSACTION_COL_DATE + " LIKE ?", new String[] {"%"+ month_year +"%" },
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ModelTransaction row = new ModelTransaction(cursor.getInt(0));
                row.setName(cursor.getString(1));
                row.setDate(cursor.getString(2));
                row.setNominal(cursor.getString(3));
                row.setGain(cursor.getInt(4) > 0);
                row.setDesc(cursor.getString(5));

                listTransaction.add(row);
            } while (cursor.moveToNext());
        }
        return listTransaction;
    }

    public ArrayList<ModelGoal> goalOfMonth(String month_year){
        ArrayList<ModelGoal> listTransaction = new ArrayList<>();
        Cursor cursor = db.query(true, TABLE_GOAL, new String[] {
                        TRANSACTION_COL_ID,GOAL_COL_NAME, TRANSACTION_COL_DATE, TRANSACTION_COL_NOMINAL,  TRANSACTION_COL_DESC},
                TRANSACTION_COL_DATE + " LIKE ?", new String[] {"%"+ month_year +"%" },
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ModelGoal row = new ModelGoal(cursor.getInt(0));
                row.setName(cursor.getString(1));
                row.setDate(cursor.getString(2));
                row.setNominal(cursor.getString(3));
                row.setDesc(cursor.getString(4));

                listTransaction.add(row);
            } while (cursor.moveToNext());
        }
        return listTransaction;
    }


    public void insertTransaction(ModelTransaction modelTransaction){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TRANSACTION_COL_NAME, modelTransaction.getName());
            contentValues.put(TRANSACTION_COL_DATE, modelTransaction.getDate());
            contentValues.put(TRANSACTION_COL_NOMINAL, modelTransaction.getNominal());
            contentValues.put(TRANSACTION_COL_IS_GAIN, modelTransaction.isGain());
            contentValues.put(TRANSACTION_COL_DESC, modelTransaction.getDesc());

            db.insert(TABLE_TRANSACTION, null, contentValues);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void insertGoal(ModelGoal modelGoal){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(GOAL_COL_NAME, modelGoal.getName());
            contentValues.put(GOAL_COL_DATE, modelGoal.getDate());
            contentValues.put(GOAL_COL_NOMINAL, modelGoal.getNominal());
            contentValues.put(GOAL_COL_DESC, modelGoal.getDesc());

            db.insert(TABLE_GOAL, null, contentValues);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void deleteTransaction(int id){
        db.delete(TABLE_TRANSACTION, TRANSACTION_COL_ID + " = "+id, null);
    }

    public ArrayList<ModelTransaction> getAllTransaction(){
        ArrayList<ModelTransaction> listTransaction = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from "+TABLE_TRANSACTION, null);
        if (cursor.moveToFirst()) {
            do {
                ModelTransaction row = new ModelTransaction(cursor.getInt(0));
                row.setName(cursor.getString(1));
                row.setDate(cursor.getString(2));
                row.setNominal(cursor.getString(3));
                row.setGain(cursor.getInt(4) > 0);
                row.setDesc(cursor.getString(5));

                listTransaction.add(row);
            } while (cursor.moveToNext());
        }
        return listTransaction;
    }
}
