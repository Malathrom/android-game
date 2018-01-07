package com.example.adrian.game;


import android.content.ContentValues;
import java.util.*;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase";
    private static final String TABLE = "preferences";
    private static final String KEY_ID = "id";
    private static final String KEY_THEME = "themeColor";
    private static final String KEY_X = "xwins";
    private static final String KEY_O = "owins";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_THEME + " INTEGER,"
                + KEY_X + " INTEGER," + KEY_O + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    void addPref(Integer id, Integer color, Integer xwin, Integer owin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_THEME, color);
        values.put(KEY_X, xwin);
        values.put(KEY_O, owin);
        db.insert(TABLE, null, values);
        db.close();
    }


    public int updatePref(Integer id, Integer color, Integer xwin, Integer owin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_THEME, color);
        values.put(KEY_X, xwin);
        values.put(KEY_O, owin);
        return db.update(TABLE, values, KEY_ID + "=?", new String[] { String.valueOf(id) });
    }
    public ArrayList<Integer> getPref(int id) {
        ArrayList<Integer> prefs = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE, new String[] { KEY_ID, KEY_THEME, KEY_X, KEY_O }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        prefs.add(Integer.parseInt(cursor.getString(0)));
        prefs.add(Integer.parseInt(cursor.getString(1)));
        prefs.add(Integer.parseInt(cursor.getString(2)));
        prefs.add(Integer.parseInt(cursor.getString(3)));
        return prefs;
    }

    public ArrayList<Integer> getAllPref() {
        ArrayList<Integer> prefs = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                prefs.add(Integer.parseInt(cursor.getString(0)));
                prefs.add(Integer.parseInt(cursor.getString(1)));
                prefs.add(Integer.parseInt(cursor.getString(2)));
                prefs.add(Integer.parseInt(cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        return prefs;
    }
}
