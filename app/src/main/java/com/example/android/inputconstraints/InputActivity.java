package com.example.android.inputconstraints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.android.inputconstraints.databinding.ActivityInputBinding;
import com.example.android.inputconstraints.databinding.ActivityMainBinding;

public class InputActivity extends AppCompatActivity {

    ActivityInputBinding b;

    String reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        gettingRegexValue();
        settingOnTextChange();
    }

    private void settingOnTextChange() {
        b.data.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.data.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void gettingRegexValue() {
        Bundle bundle = getIntent().getExtras();
        reg = bundle.getString(Constants.REGEX);
    }

    public void send(View view) {
        String input = b.data.getEditText().getText().toString().trim();

        if(input.isEmpty() || !input.matches(reg)){
            b.data.setError("Enter valid data");
            return;
        }
        b.data.setError(null);

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(Constants.INPUT_DATA,input);
        setResult(RESULT_OK,intent);
        finish();
    }
}