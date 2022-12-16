package com.example.assignment2.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class itemDBhandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "itemsdb";
    private static final String TABLE_NAME = "items";
    private static final String COL1_ID = "id";
    private static final String COL2_CATEGORY = "category";
    private static final String COL3_NAME = "name";
    private static final String COL4_QUANTITY = "quantity";
    private static final String COL5_PRICE = "price";

    public itemDBhandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2_CATEGORY + " TEXT, "
                + COL3_NAME + " TEXT, "
                + COL4_QUANTITY + " INTEGER, "
                + COL5_PRICE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public void insertItem(String category, String name, int quantity, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
//        cValues.put(COL1_ID, id);
        cValues.put(COL2_CATEGORY, category);
        cValues.put(COL3_NAME, name);
        cValues.put(COL4_QUANTITY, quantity);
        cValues.put(COL5_PRICE, price);

        long newRowId = db.insert(TABLE_NAME,null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> GetItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> itemList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> item = new HashMap<>();
            item.put(COL1_ID, cursor.getString(cursor.getColumnIndexOrThrow(COL1_ID)));
            item.put(COL2_CATEGORY, cursor.getString(cursor.getColumnIndexOrThrow(COL2_CATEGORY)));
            item.put(COL3_NAME, cursor.getString(cursor.getColumnIndexOrThrow(COL3_NAME)));
            item.put(COL4_QUANTITY, cursor.getString(cursor.getColumnIndexOrThrow(COL4_QUANTITY)));
            item.put(COL5_PRICE, cursor.getString(cursor.getColumnIndexOrThrow(COL5_PRICE)));
            itemList.add(item);
        }
        return itemList;
    }

    public int GetTotalPrice() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT quantity, price FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int totalPrice = 0;
        int quantity = 0;
        int price = 0;

        while (cursor.moveToNext()){
            quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COL4_QUANTITY));
            price = cursor.getInt(cursor.getColumnIndexOrThrow(COL5_PRICE));
            int row_result = quantity * price;
            totalPrice = totalPrice + row_result;
        }
        cursor.close();
        return totalPrice;
    }

    public void ClearCartItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

//    public int GetTotalCostByItem(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT quantity, price FROM "+ TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
//        int totalCost = 0;
//        int quantity = 0;
//        int price = 0;
//
//        if (cursor.moveToNext()){
//            quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COL4_QUANTITY));
//            price = cursor.getInt(cursor.getColumnIndexOrThrow(COL5_PRICE));
//        }
//        totalCost = quantity * price;
//
//        cursor.close();
//        return totalCost;
//    }
//
//
//    public void DeleteItem(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, COL1_ID +" = ?",new String[]{String.valueOf(id)});
//        db.close();
//    }


}
