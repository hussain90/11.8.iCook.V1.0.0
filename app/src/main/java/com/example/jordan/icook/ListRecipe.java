package com.example.jordan.icook;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by edske on 11/17/2017.
 */

public class ListRecipe extends AppCompatActivity {
    private static final String TAG = "ListRecipe";
    DatabaseRecipe myDb;
    private ListView Name;
    //private ListView Ingredient;
    //private ListView Quantity;
    //private ListView Instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe);
        Name = (ListView) findViewById(R.id.ListView);
        //Ingredient = (ListView) findViewById(R.id.ListView2);
        myDb = new DatabaseRecipe(this);
        populateListView();
    }

    private void populateListView() {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<String> listData2 = new ArrayList<>();
        while (res.moveToNext()){
            listData.add(res.getString(0));
            listData2.add(res.getString(1));

        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        ListAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData2);
        Name.setAdapter(adapter);
        //Ingredient.setAdapter(adapter2);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
