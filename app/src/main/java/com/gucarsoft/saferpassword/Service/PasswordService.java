package com.gucarsoft.saferpassword.Service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gucarsoft.saferpassword.Model.Password;
import com.gucarsoft.saferpassword.Repository.DataBase;

import java.util.List;

public class PasswordService {
    static Context context;
    static SQLiteDatabase db;


    public PasswordService(Context _context) {
        DataBase dataBase = new DataBase(_context);
        SQLiteDatabase _db = dataBase.getWritableDatabase();
        context = _context;
        db = _db;
    }

    public void savePassword(Password password) {
        DataBase dataBase = new DataBase(context);
        dataBase.savePassword(db, password);
    }

    public void update(Password password) {
        DataBase dataBase = new DataBase(context);
        dataBase.updatePassword(db, password);
    }

    public void delete(Password password) {
        DataBase dataBase = new DataBase(context);
        dataBase.deletePassword(db, password);
    }

    public List<Password> list() {
        DataBase dataBase = new DataBase(context);
        List<Password> passwordList = dataBase.listAllPasswords(db);
        return passwordList;
    }
}
