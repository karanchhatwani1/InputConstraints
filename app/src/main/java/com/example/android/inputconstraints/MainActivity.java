package com.example.android.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.inputconstraints.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_INPUT = 0;
    ActivityMainBinding b;
    String regex = "^";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    //Checking and sending Constraints
    public void sendConstraints(View view) {

        //Checking if uppercase constraint is checked
        if(b.uppercase.isChecked()){
            //updating regex value
            regex = regex + "([A-Z]*)";
        }
        //Checking if lowercase constraint is checked
        if(b.lowercase.isChecked()){
            //updating regex value
            regex = regex + "([a-z]*)";
        }
        //checking if digits constraints is checked
        if(b.digits.isChecked()){
            //updating regex value
            regex = regex + "(\\d+)";
        }
        //checking if mathematical operation is checked
        if(b.mathematicalOperation.isChecked()){
            //updating regex value
            regex = regex + "([+-/*%]*)";
        }
        //checking if other symbol is checked
        if(b.otherSymbol.isChecked()){
            //updating regex value
            regex = regex + "([#$]*)";
        }

        //sending regex value to Input Activity
        Intent intent = new Intent(MainActivity.this,InputActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.REGEX,regex);
        intent.putExtras(bundle);
        startActivityForResult(intent,REQUEST_INPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_INPUT && resultCode == RESULT_OK){
            //getting and setting the result from input activity
            b.inputResult.setText("Input - "+data.getStringExtra(Constants.INPUT_DATA));
            b.inputResult.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }
}