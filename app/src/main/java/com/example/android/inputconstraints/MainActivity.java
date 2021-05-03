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

    public void sendConstraints(View view) {

        if(b.uppercase.isChecked()){
            regex = regex + "([A-Z]*)";
        }
        if(b.lowercase.isChecked()){
            regex = regex + "([a-z]*)";
        }
        if(b.digits.isChecked()){
            regex = regex + "(\\d+)";
        }
        if(b.mathematicalOperation.isChecked()){
            regex = regex + "([+-/*%]*)";
        }
        if(b.otherSymbol.isChecked()){
            regex = regex + "([#$]*)";
        }

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
            b.inputResult.setText("Input - "+data.getStringExtra(Constants.INPUT_DATA));
            b.inputResult.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }
}