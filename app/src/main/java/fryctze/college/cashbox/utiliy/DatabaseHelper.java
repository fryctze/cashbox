package fryctze.college.cashbox.utiliy;

import static fryctze.college.cashbox.data.DatabaseReference.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fryctze.college.cashbox.menu.history.ModelTransaction;

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

    /*public Cursor getAllPlayers()
    {
        String[] columns={Constants.ROW_ID,Constants.NAME,Constants.POSITION};

        return db.query(Constants.TB_NAME,columns,null,null,null,null,null);

    }*/

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

    public void deleteTransaction(int id){
        db.delete(TABLE_TRANSACTION, TRANSACTION_COL_ID + " = "+id, null);
    }

   /* public long Delete(int id) {
        try
        {

            return db.delete(Contants.TB_NAME,Contants.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }*/

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