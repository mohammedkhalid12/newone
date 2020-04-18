package com.discussionforum.discussionforum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SqliteDatabase extends SQLiteOpenHelper {
    Context context;
    private static String name = "Discussion";
    private static final  int version = 1;
    static  String profile = "profile";
    static String subject = "subject";
    static String response = "response";

    public SqliteDatabase(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+profile+"(id integer primary key autoincrement," +
                "name varchar(50),list_name varchar(50),location varchar(50),age varchar(50)," +
                "gender varchar(50))");

        sqLiteDatabase.execSQL("create table "+subject+"(id integer primary key autoincrement," +
                "title varchar(50),author varchar(50),bio varchar(255),time varchar(50),post_id integr(10))");

        sqLiteDatabase.execSQL("create table "+response+"(id integer primary key autoincrement," +
                "author varchar(50),time varchar(50),content varchar(255),post_id intege(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       onCreate(sqLiteDatabase);
    }

    public void insert( String name, String list_name, String location, String age, String gender){

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("list_name", list_name);
            contentValues.put("location", location);
            contentValues.put("age", age);
            contentValues.put("gender", gender);
            sqLiteDatabase.insert(profile, null, contentValues);

    }

    public Cursor getData(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+profile+"",null);
        return cursor;
    }

    public int edit(String id,String name, String list_name, String location, String age, String gender){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("list_name", list_name);
        contentValues.put("location", location);
        contentValues.put("age", age);
        contentValues.put("gender",gender);
       return sqLiteDatabase.update(profile,contentValues, "id = ?",new String[]{(id)});
    }

    public void remove(String profile_id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(profile, "id = ?", new String[]{profile_id});
    }

    public void insertSubject(String title,String author,String bio,String time){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("author",author);
        contentValues.put("bio",bio);
        contentValues.put("time",time);
        sqLiteDatabase.insert(subject,null,contentValues);
    }

    public Cursor getSubject(){
      SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
      Cursor cursor = sqLiteDatabase.rawQuery("select * from "+subject+"",null);
      return cursor;
    }

    public Cursor getAuthor(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select name from "+profile+"",null);
        return cursor;
    }

    public void InsertResponse(String author,String time,String content){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author",author);
        contentValues.put("time",time);
        contentValues.put("content",content);
        sqLiteDatabase.insert(response,null,contentValues);
    }

    public Cursor getResponse(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM "+response+"" +
                " INNER JOIN "+subject+" on "+subject+".id = "+response+".id";
        String sql2 = "SELECT * FROM "+response+" where post_id = "+subject+".post_id";
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+response+"",null);
        return cursor;
    }
}
