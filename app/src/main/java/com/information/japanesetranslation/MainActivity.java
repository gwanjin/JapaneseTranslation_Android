package com.information.japanesetranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context mContext;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        final EditText sentence = findViewById(R.id.sentence);
        mTextView = findViewById(R.id.result);
        final InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestHttpURLConnection conn = new RequestHttpURLConnection(mTextView);
                conn.execute(sentence.getText().toString());
                imm.hideSoftInputFromWindow(sentence.getWindowToken(), 0);
            }
        });

    }
}
