package com.example.admin.contact_contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> dsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();

        showContact();
    }

    public void showContact(){

        //tao uri cho
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);

        dsDanhBa.clear();
        while (cursor.moveToNext())
        {

            String tenCotContact = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int vtTenCotName = cursor.getColumnIndex(tenCotContact);
            int vtTenCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(vtTenCotName);
            String phone = cursor.getString(vtTenCotPhone);
            Contact contact = new Contact(name,phone);
            dsDanhBa.add(contact);

        }

        dsAdapter.notifyDataSetChanged();
    }

    private void addEvent(){

    }

    private void addControls(){

        lvDanhBa = findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        dsAdapter = new ArrayAdapter<Contact>(Main2Activity.this,
                android.R.layout.simple_list_item_1,
                dsDanhBa);

        lvDanhBa.setAdapter(dsAdapter);
    }





}
