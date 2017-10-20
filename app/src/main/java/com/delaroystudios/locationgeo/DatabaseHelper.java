package com.delaroystudios.locationgeo;

/**
 * Created by SARDIUS on 11-10-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.delaroystudios.locationgeo.model.User;


import java.util.ArrayList;
import java.util.List;
import static android.R.attr.value;

/**
 * Created by SARDIUS on 10-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "user_id";
    private static final String COLUMN_NAME = "user_name";
    private static final String COLUMN_EMAIL = "user_email";
    private static String COLUMN_PASSWORD = "user_password";
    public SQLiteDatabase db;

    //CREATE TABLE
    private static final String TABLE_CREATE = "CREATE TABLE "+ TABLE_NAME +" ( " + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_NAME +" TEXT "+ COLUMN_EMAIL +
            " TEXT " + COLUMN_PASSWORD + " TEXT " + ");";


    //DROP TABLE
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Log.e("Database Operations","Database created / opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (TABLE_CREATE);
        Log.e("Database Operations","Table created...");
        this.db=db;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
        Log.e("Database Operations","Table created...");
    }


    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        Log.e("Database Operations","Row Inserted.....");
        db.close();
    }


    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ID,
                COLUMN_EMAIL,
                COLUMN_NAME,
                COLUMN_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }


    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }


    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }


    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                null,//selection,                  //columns for the WHERE clause
                null,//selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                null,//selection,                  //columns for the WHERE clause
                null,//selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}

