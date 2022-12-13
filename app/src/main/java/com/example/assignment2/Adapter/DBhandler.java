package com.example.assignment2.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBhandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_NAME = "users";
    private static final String COL1_ID = "id";
    private static final String COL2_NAME = "name";
    private static final String COL3_EMAIL = "email";

    public DBhandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL2_NAME + " TEXT,"
                + COL3_EMAIL + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    void insertUserDetails(String name, String email){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(COL2_NAME, name);
        cValues.put(COL3_EMAIL, email);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME,null, cValues);
        db.close();
    }

    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, email FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndexOrThrow(COL2_NAME)));
            user.put("email", cursor.getString(cursor.getColumnIndexOrThrow(COL3_EMAIL)));
            userList.add(user);
        }
        return userList;
    }

    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, email FROM "+ TABLE_NAME;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL2_NAME, COL3_EMAIL},
                COL1_ID + "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndexOrThrow(COL2_NAME)));
            user.put("email",cursor.getString(cursor.getColumnIndexOrThrow(COL3_EMAIL)));
            userList.add(user);
        }
        return userList;
    }

    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1_ID +" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }

    // Update User Details
    public int UpdateUserDetails(String email, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(COL3_EMAIL, email);
        int count = db.update(TABLE_NAME, cVals, COL1_ID +" = ?",new String[]
                {String.valueOf(id)});
        return count;
    }














}