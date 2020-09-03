package com.gucarsoft.saferpassword.Service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gucarsoft.saferpassword.Model.Password;
import com.gucarsoft.saferpassword.Repository.DataBase;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class PasswordService {
    static Context context;
    static SQLiteDatabase db;
    static String key;

    public PasswordService(Context _context,String _key) {
        DataBase dataBase = new DataBase(_context);
        SQLiteDatabase _db = dataBase.getWritableDatabase();
        context = _context;
        db = _db;
        key="asdfghjklqwertyu";
    }

    public void savePassword(Password _password) {

        try {
            Password password = _password;
//            System.out.println(String.valueOf(Encryption.encryptMsg(password.getTitle(), secret)));
//            password.setTitle(String.valueOf(Encryption.encryptMsg(password.getTitle(), secret)));
//            password.setUserName(String.valueOf(Encryption.encryptMsg(password.getUserName(), secret)));
//            password.setMail(String.valueOf(Encryption.encryptMsg(password.getMail(), secret)));
//            password.setPassword(String.valueOf(Encryption.encryptMsg(password.getPassword(), secret)));
//            password.setNote(String.valueOf(Encryption.encryptMsg(password.getNote(), secret)));
//            password.setCategory(String.valueOf(Encryption.encryptMsg(password.getTitle(), secret)));

            DataBase dataBase = new DataBase(context);
            dataBase.savePassword(db, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
//        for (Password pswd:passwordList
//             ) {
//
//        }
        return passwordList;
    }
}
