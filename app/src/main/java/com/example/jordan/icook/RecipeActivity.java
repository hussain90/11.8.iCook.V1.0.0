package com.example.jordan.icook;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {
    DatabaseRecipe myDb;
    EditText editName, editIngredient1, editQuantity1,
            editIngredient2, editQuantity2,
            editIngredient3, editQuantity3,
            editIngredient4, editQuantity4,
            editIngredient5, editQuantity5,
            editInstruction;
    Button btnAdd, btnView, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        myDb = new DatabaseRecipe(this);

        editName = (EditText)findViewById(R.id.editText_Name);
        editIngredient1 = (EditText)findViewById(R.id.editText_Ingredient1);
        editQuantity1 = (EditText)findViewById(R.id.editText_Quantity1);
        editIngredient2 = (EditText)findViewById(R.id.editText_Ingredient2);
        editQuantity2 = (EditText)findViewById(R.id.editText_Quantity2);
        editIngredient3 = (EditText)findViewById(R.id.editText_Ingredient3);
        editQuantity3 = (EditText)findViewById(R.id.editText_Quantity3);
        editIngredient4 = (EditText)findViewById(R.id.editText_Ingredient4);
        editQuantity4 = (EditText)findViewById(R.id.editText_Quantity4);
        editIngredient5 = (EditText)findViewById(R.id.editText_Ingredient5);
        editQuantity5 = (EditText)findViewById(R.id.editText_Quantity5);
        editInstruction = (EditText)findViewById(R.id.editText_Instruction);
        btnAdd = (Button)findViewById(R.id.button_Add);
        btnView = (Button)findViewById(R.id.button_View);
        btnDelete = (Button)findViewById(R.id.button_Delete);
        AddData();
        viewAll();
        DeleteData();
    }

    public void AddData(){
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editIngredient1.getText().toString(),
                                Integer.parseInt(editQuantity1.getText().toString()),
                                editIngredient2.getText().toString(),
                                Integer.parseInt(editQuantity2.getText().toString()),
                                editIngredient3.getText().toString(),
                                Integer.parseInt(editQuantity3.getText().toString()),
                                editIngredient4.getText().toString(),
                                Integer.parseInt(editQuantity4.getText().toString()),
                                editIngredient5.getText().toString(),
                                Integer.parseInt(editQuantity5.getText().toString()),
                                editInstruction.getText().toString());
                        if (isInserted = true) {
                            Toast.makeText(RecipeActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            editName.setText("");
                            editIngredient1.setText("");
                            editQuantity1.setText("");
                            editIngredient2.setText("");
                            editQuantity2.setText("");
                            editIngredient3.setText("");
                            editQuantity3.setText("");
                            editIngredient4.setText("");
                            editQuantity4.setText("");
                            editIngredient5.setText("");
                            editQuantity5.setText("");
                            editInstruction.setText("");
                        }else {
                            Toast.makeText(RecipeActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editName.getText().toString());
                        if(deletedRows > 0){
                            Toast.makeText(RecipeActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                            editName.setText("");
                        }else{
                            Toast.makeText(RecipeActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }

                }
        );
    }

    public void viewAll(){
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent RecipeList = new Intent(RecipeActivity.this, ListRecipe.class);
                        startActivity(RecipeList);
                    }
                        /*Cursor res = myDb.getAllData();
                            if(res.getCount() == 0){
                                //show message
                                showMessage("Error", "Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("Name :"+ res.getString(0)+"\n");
                                buffer.append("Ingredient :"+ res.getString(1)+"\n");
                                buffer.append("Quantity :"+ res.getString(2)+"\n");
                                buffer.append("Instruction :"+res.getString(3)+"\n\n");
                            }
                            //show all data
                            showMessage("Data", buffer.toString());
                    }*/
                }
        );
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

