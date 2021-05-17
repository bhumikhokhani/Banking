package com.retical.banking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9875641230,'Bhumi',8000.00,'bhumi.02@gmail.com','XXXXXXXXXXXX1254','ABC13896241')");
        db.execSQL("insert into user_table values(9876123548,'Dhara',3500.50,'dhara.03@gmail.com','XXXXXXXXXXXX1234','BCD91774175')");
        db.execSQL("insert into user_table values(8576253573,'Roy',8000.67,'roy.04@gmail.com','XXXXXXXXXXXX2007','CDA95151525')");
        db.execSQL("insert into user_table values(7854692331,'Adithya',3000.00,'adithya.05@gmail.com','XXXXXXXXXXXX5421','DAB84141211')");
        db.execSQL("insert into user_table values(9874533120,'Riya',5000.24,'riya.06@gmail.com','XXXXXXXXXXXX1256','BDC45131102')");
        db.execSQL("insert into user_table values(8977152354,'Rahul',1977.23,'rahul.07@gmail.com','XXXXXXXXXXXX6513','BAD74121274')");
        db.execSQL("insert into user_table values(8775496211,'Mahi',8760.00,'mahi.08@gmail.com','XXXXXXXXXXXX1264','CAB93917941')");
        db.execSQL("insert into user_table values(9985723641,'Lily',1700.00,'lily.09@gmail.com','XXXXXXXXXXXX5892','BAC94119475')");
        db.execSQL("insert into user_table values(8975556123,'Pranav',3000.00,'pranav.10@gmail.com','XXXXXXXXXXXX2718','CBA95028461')");
        db.execSQL("insert into user_table values(8895315744,'Prathik',2500.45,'prathik.01@gmail.com','XXXXXXXXXXXX1173','BCA99957751')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
