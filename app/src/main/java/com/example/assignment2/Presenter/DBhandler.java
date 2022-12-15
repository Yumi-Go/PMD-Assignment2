package com.example.assignment2.Presenter;

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
    private static final String COL4_PASSWORD = "password";

    public DBhandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2_NAME + " TEXT, "
                + COL3_EMAIL + " TEXT, "
                + COL4_PASSWORD + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public void insertUser(String name, String email, String password){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(COL2_NAME, name);
        cValues.put(COL3_EMAIL, email);
        cValues.put(COL4_PASSWORD, password);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME,null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, email FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndexOrThrow(COL2_NAME)));
            user.put("email", cursor.getString(cursor.getColumnIndexOrThrow(COL3_EMAIL)));
            user.put("password", cursor.getString(cursor.getColumnIndexOrThrow(COL4_PASSWORD)));

            userList.add(user);
        }
        return userList;
    }


    public String GetNameByEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT name, email FROM "+ TABLE_NAME;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL2_NAME},
                COL3_EMAIL + "=?", new String[]{email},null, null, null, null);
        String name = "";
        if (cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndexOrThrow(COL2_NAME));
        }
        cursor.close();
        return name;
    }


    public ArrayList<HashMap<String, String>> GetUserById(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, email FROM "+ TABLE_NAME;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL2_NAME, COL3_EMAIL},
                COL1_ID + "=?", new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndexOrThrow(COL2_NAME)));
            user.put("email",cursor.getString(cursor.getColumnIndexOrThrow(COL3_EMAIL)));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }

    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1_ID +" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }

    public int UpdateUser(String email, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(COL3_EMAIL, email);
        int count = db.update(TABLE_NAME, cVals, COL1_ID +" = ?",new String[]
                {String.valueOf(id)});
        return count;
    }


    public boolean checkUserExist(String email, String password){
        String[] columns = {"email"};
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = "email=? and password=?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        return count > 0;
    }











}
