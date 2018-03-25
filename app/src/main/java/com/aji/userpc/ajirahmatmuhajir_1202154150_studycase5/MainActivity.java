package com.aji.userpc.ajirahmatmuhajir_1202154150_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DataHelper db;
    RecyclerView mRecyclerView;
    DataAdapter mAdapter;
    ArrayList<Data> mList;

    //melakukan insiasi variable yang telah di deklarasikan
    //inisiasi sharedpreferences
    //membuat mAdapter baru
    //menampilkan layoutnya
    //set mAdapter untuk recycler view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recview);
        mList = new ArrayList<>();
        db = new DataHelper(this);
        db.getData(mList);


        SharedPreferences SharedPreferences = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = SharedPreferences.getInt("BackGroundCollor", R.color.white);

        mAdapter = new DataAdapter(this, mList, color);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        deleteData();
    }


    // method untuk menghapus data
    public void deleteData(){
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Data current = mAdapter.getData(position);

                //apabila item di swipe ke arah kanan
                if(direction==ItemTouchHelper.RIGHT){
                    if(db.deletData(current.getTodoName())){

                        //delete data
                        mAdapter.deleteData(position);
                    }
                }
            }
        };

        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(mRecyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method ketika memilih setting
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingColor.class);
                startActivity(intent);
                return true;
        }
        //mengembalikan nilai item
        return super.onOptionsItemSelected(item);
    }

    //mengklik fab ke addactivity
    public void addTodo(View view) {
        Intent intent = new Intent(this, AddTodo.class);
        startActivity(intent);
    }
}
