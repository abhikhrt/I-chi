package com.ontro.abhi.diary1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhi on 08-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "diary.db";

    public static final String Table1Name = "Users";
    public static final String col10 = "username";
    public static final String col11 = "password";

    public static final String Table2Name = "DiaryContent";
    public static final String col20 = "date";
    public static final String col21 = "content";


    public static final String Table3Name = "DiaryNote";
    public static final String col30 = "date";
    public static final String col31 = "note1";
    public static final String col32 = "note2";
    public static final String col33 = "note3";
    public static final String col34 = "note4";
    public static final String col35 = "note5";

    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) throws SQLiteException{
        sqLiteDatabase.execSQL("CREATE  TABLE \"main\".\"Users\" (\"username\" TEXT PRIMARY KEY  NOT NULL  UNIQUE , \"password\" TEXT NOT NULL )");
        sqLiteDatabase.execSQL("CREATE  TABLE \"main\".\"DiaryContent\" (\"date\" TEXT PRIMARY KEY  NOT NULL  UNIQUE , \"content\" TEXT)");
        sqLiteDatabase.execSQL("CREATE  TABLE \"main\".\"DiaryNote\" (\"date\" TEXT PRIMARY KEY  NOT NULL  UNIQUE , \"note1\" TEXT , \"note2\" TEXT , \"note3\" TEXT , \"note4\" TEXT , \"note5\" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) throws SQLiteException{
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table1Name);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table2Name);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table3Name);
        onCreate(sqLiteDatabase);

    }

    public Cursor Get_user() throws SQLiteException{

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from Users",null);
        return res;
    }

    public boolean Reg_user(String usrnm, String pswrd) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col10,usrnm);
        contentValues.put(col11,pswrd);
        long result = db.insert(Table1Name,null,contentValues);
        if (result==-1)
            return false;
        else return true;
    }

    public boolean insert_dry(String datess, String s) throws SQLiteException{
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        ContentValues contentValues = new ContentValues();
        //contentValues.put(col20, datess);
        contentValues.put(col21, s);
        Cursor res = db.rawQuery("SELECT * FROM DiaryContent WHERE date=\"" + datess + "\"", null);
        if (res.getCount() != 0)
        {
            //db.execSQL("UPDATE DiaryContent SET content =" + s + " WHERE date =\"" + datess + "\"", null);
            result=db.update(Table2Name, contentValues, "date=?", new String[] {datess});
        }
        else {

            contentValues.put(col20, datess);
            result = db.insert(Table2Name, null, contentValues);
        }
        if (result==-1)
            return false;
        else return true;
    }

    public Cursor getDryData(String datess) throws SQLiteException{
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM DiaryContent WHERE date=\""+datess+"\"",null);
        return res;
    }

    public Cursor getnotes(String datess) throws SQLiteException{
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM DiaryNote WHERE date=\""+datess+"\"",null);
        return res;
    }

    public boolean insert_note(String datess, String s, String s1, String s2, String s3, String s4) throws SQLiteException{
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        ContentValues contentValues = new ContentValues();
        //contentValues.put(col20, datess);
        contentValues.put(col31, s);
        contentValues.put(col32, s1);
        contentValues.put(col33, s2);
        contentValues.put(col34, s3);
        contentValues.put(col35, s4);
        Cursor res = db.rawQuery("SELECT * FROM DiaryNote WHERE date=\"" + datess + "\"", null);
        if (res.getCount() != 0)
        {
            //db.execSQL("UPDATE DiaryContent SET content =" + s + " WHERE date =\"" + datess + "\"", null);
            result=db.update(Table3Name, contentValues, "date=?", new String[] {datess});
        }
        else {

            contentValues.put(col20, datess);
            result = db.insert(Table3Name, null, contentValues);
        }
        if (result==-1)
            return false;
        else return true;
    }
}
