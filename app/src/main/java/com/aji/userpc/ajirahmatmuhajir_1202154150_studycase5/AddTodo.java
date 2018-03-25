package com.aji.userpc.ajirahmatmuhajir_1202154150_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodo extends AppCompatActivity {
    EditText todoName, todoDescription, todoPriority;
    DataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //melakukan insiasi terhadap variable yang telah di deklarasikan
        todoName = (EditText) findViewById(R.id.addTodo);
        todoDescription = (EditText) findViewById(R.id.addDesciptin);
        todoPriority = (EditText) findViewById(R.id.addPriority);
        db = new DataHelper(this);
    }

    //method back
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddTodo.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    //method ketika tombol addData klik
    public void addData(View view) {
        //dngan kondisi disisi semua
        if (db.inputdata(new Data(todoName.getText().toString(), todoDescription.getText().toString(), todoPriority.getText().toString()))){
            Toast.makeText(this, "Input Success", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddTodo.this, MainActivity.class));
            this.finish();
        //apabila tidak ada yang tidak di isi
        }else {
            Toast.makeText(this, "input Failed", Toast.LENGTH_LONG).show();
            todoName.setText(null);
            todoDescription.setText(null);
            todoPriority.setText(null);
        }
    }

}
