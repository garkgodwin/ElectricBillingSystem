package com.example.electricbillingsystem.MeterBoard;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricbillingsystem.HomeActivity;
import com.example.electricbillingsystem.R;
import com.example.electricbillingsystem.database.DatabaseHelper;

import java.util.ArrayList;

public class MeterBoardHomeActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    Button btnCreate;
    EditText etSearch;
    //FOR RECYCLERVIEW
    RecyclerView rvMeter;
    ArrayList<String> meterIDs, callNames, icps, totalKwhs, statuss;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getBaseContext(), HomeActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_board_home);

        databaseHelper = new DatabaseHelper(MeterBoardHomeActivity.this);
        setControls();
        setVariables();

        //UPDATE METER CONSUMPTION TOTAL
        databaseHelper.setSumOfConsumptionUnit();

        setAndGetData();


        //button click to go to "MeterBoardFormActivity" as new data creation
        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MeterBoardFormActivity.class);
                i.putExtra("action", 1);
                startActivity(i);
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAndGetData();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setAndGetData(){
        storeDataInArrays(etSearch.getText().toString());
        setViewAdapter();
    }

    //initialize views
    private void setControls(){
        btnCreate = findViewById(R.id.btnCreateMainMeter);
        rvMeter = findViewById(R.id.rvMeter);
        etSearch = findViewById(R.id.etSearchMeterName);
    }

    //initialize variables for adapter
    private void setVariables(){
        meterIDs = new ArrayList<>();
        callNames = new ArrayList<>();
        icps = new ArrayList<>();
        totalKwhs = new ArrayList<>();
        statuss = new ArrayList<>();
    }

    //call this method to store data in array : used to display meter board data
    private void storeDataInArrays(String search){
        Cursor cursor = databaseHelper.getMeterBoardsCursor(search);
        meterIDs.clear();
        callNames.clear();
        icps.clear();
        totalKwhs.clear();
        statuss.clear();
        if(cursor.moveToFirst()){
            do{
                meterIDs.add(cursor.getString(cursor.getColumnIndex("meterBoardID")));
                callNames.add(cursor.getString(cursor.getColumnIndex("callName")));
                icps.add(cursor.getString(cursor.getColumnIndex("installationControlPoint")));
                totalKwhs.add(cursor.getString(cursor.getColumnIndex("totalKWH")));
                statuss.add(cursor.getString(cursor.getColumnIndex("status")));
            }
            while (cursor.moveToNext());
        }
        else{
            Toast.makeText(MeterBoardHomeActivity.this, "You have no meter board created.", Toast.LENGTH_SHORT).show();
        }

    }
    //call this method to get the stored data in arrays and set in the adapter
    private void setViewAdapter(){
        MeterCustomAdapter meterCustomAdapter= new MeterCustomAdapter(MeterBoardHomeActivity.this,
                meterIDs, callNames, icps, totalKwhs, statuss);
        rvMeter.setAdapter(meterCustomAdapter);
        rvMeter.setLayoutManager(new LinearLayoutManager(MeterBoardHomeActivity.this));
    }
}