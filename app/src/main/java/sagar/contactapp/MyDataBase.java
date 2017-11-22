package sagar.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper{

static String DBName="contact.db";
       String TableName="contact";
       String ColumnName="name";
       String ColumnNumber="number";
       String ColumnId="id";

       String Create_Table_Query="create table contact(id integer primary key,name text,number text)";

    public MyDataBase(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long Insert(Contact contact)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ColumnName,contact.getName());
        contentValues.put(ColumnNumber,contact.getNumbere());
        long insertedId=database.insert(TableName,null,contentValues);
        return insertedId;

    }

    public ArrayList<Contact> getAllContact()
    {
        ArrayList<Contact> arrayList=new ArrayList<>();

        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(TableName,null,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            String name=cursor.getString(cursor.getColumnIndex(ColumnName));
            String number=cursor.getString(cursor.getColumnIndex(ColumnNumber));
            long id=cursor.getLong(cursor.getColumnIndex(ColumnId));

            Contact contact=new Contact();
            contact.setName(name);
            contact.setNumbere(number);
            contact.setId(id);

            arrayList.add(contact);
            cursor.moveToNext();

        }
        cursor.close();
        return arrayList;


    }

    public int delete(long id)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        int row=sqLiteDatabase.delete(TableName,"id "+id,null);
        return row;
    }

//    public int update()
//    {
//
//    }
}

