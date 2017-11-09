package com.example.priya.contextmenuwithimplicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    ListView customlistView;

    // datasource
    String[] name = {"Kiran", "Vinay", "Subhash", "Ranjith", "Harsha", "Chikki", "Vedh", "Vahin", "Jagadesh","Kranti"};
    String[] number = {"9999999999", "8888888888", "7777777777", "6666666666", "5555555555", "4444444444", "3333333333", "2222222222", "1111111111", "1234567890"};
    List<Single> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        customlistView = (ListView) findViewById(R.id.Listview);
        for (int i = 0; i < name.length; i++) {
            Single single = new Single();
            single.setName(name[i]);
            single.setNumber(number[i]);
            list.add(single);
        }

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, list);
        customlistView.setAdapter(customAdapter);
        registerForContextMenu(customlistView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select Action");
        Log.e("Main", "context menu inflated");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int IndexSelected = info.position;
        int id = item.getItemId();
        if (id == R.id.menu_call) {
            Toast.makeText(this, "Call selected", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "call invoked");
            Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(IndexSelected).getNumber()));
            startActivity(callintent);
            return true;
        }
        else if (id == R.id.menu_sms) {
            Toast.makeText(this, "SMS selected", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "SMS invoked");
            Intent smsintent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + list.get(IndexSelected).getNumber()));
            startActivity(smsintent);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}



