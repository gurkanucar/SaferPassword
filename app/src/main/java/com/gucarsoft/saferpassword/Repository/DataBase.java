package com.gucarsoft.saferpassword.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gucarsoft.saferpassword.Model.Password;

import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {

    private static final String DB_NAME="saferPassword";
    private static final int VERSION=1;
    private static String[] passwordColumns ={"id","title","userName","mail","password","note","category"};

    public DataBase(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE passwords("+passwordColumns[0]+" INTEGER PRIMARY KEY AUTOINCREMENT,"+passwordColumns[1]+" TEXT,"+passwordColumns[2]+" TEXT,"+passwordColumns[3]+" TEXT,"+passwordColumns[4]+" TEXT,"+passwordColumns[5]+" TEXT,"+passwordColumns[6]+" TEXT)");
       db.execSQL("CREATE TABLE categories(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    public List<Password> listAllPasswords(SQLiteDatabase db)throws SQLException {
        List<Password> passwordList=new ArrayList<>();

        Cursor cursor = db.query("passwords",passwordColumns,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            Password password=new Password();
            password.setId(cursor.getInt(cursor.getColumnIndex(passwordColumns[0])));
            password.setTitle(cursor.getString(cursor.getColumnIndex(passwordColumns[1])));
            password.setUserName(cursor.getString(cursor.getColumnIndex(passwordColumns[2])));
            password.setMail(cursor.getString(cursor.getColumnIndex(passwordColumns[3])));
            password.setPassword(cursor.getString(cursor.getColumnIndex(passwordColumns[4])));
            password.setNote(cursor.getString(cursor.getColumnIndex(passwordColumns[5])));
            password.setCategory(cursor.getString(cursor.getColumnIndex(passwordColumns[6])));
            passwordList.add(password);
        }
        return passwordList;
    }

    public void savePassword(SQLiteDatabase db,Password password)throws SQLException {
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",password.getTitle());
        contentValues.put("userName",password.getUserName());
        contentValues.put("mail",password.getMail());
        contentValues.put("password",password.getPassword());
        contentValues.put("note",password.getNote());
        contentValues.put("category",password.getCategory());
        db.insertOrThrow("passwords",null,contentValues);
    }

    public void updatePassword(SQLiteDatabase db,Password password)throws SQLException {
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",password.getTitle());
        contentValues.put("userName",password.getUserName());
        contentValues.put("mail",password.getMail());
        contentValues.put("password",password.getPassword());
        contentValues.put("note",password.getNote());
        contentValues.put("category",password.getCategory());
        db.update("password",contentValues,"a",new String[]{String.valueOf(password.getId())});
    }

    public void deletePassword(SQLiteDatabase db,Password password)throws SQLException {
        db.delete("passwords","id=?",new String[]{String.valueOf(password.getId())});
    }



}
