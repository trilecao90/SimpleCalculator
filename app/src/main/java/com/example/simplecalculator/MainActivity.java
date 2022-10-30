package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_result;
    MaterialButton bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9;
    MaterialButton bt_add, bt_sub, bt_multiply, bt_divide;
    MaterialButton bt_c, bt_open_parenthesis, bt_close_parenthesis, bt_minus, bt_dot, bt_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.textview_result);

        assignButtonId(bt_0, R.id.button_0);
        assignButtonId(bt_1, R.id.button_1);
        assignButtonId(bt_2, R.id.button_2);
        assignButtonId(bt_3, R.id.button_3);
        assignButtonId(bt_4, R.id.button_4);
        assignButtonId(bt_5, R.id.button_5);
        assignButtonId(bt_6, R.id.button_6);
        assignButtonId(bt_7, R.id.button_7);
        assignButtonId(bt_8, R.id.button_8);
        assignButtonId(bt_9, R.id.button_9);
        assignButtonId(bt_add, R.id.button_add);
        assignButtonId(bt_sub, R.id.button_sub);
        assignButtonId(bt_multiply, R.id.button_multiply);
        assignButtonId(bt_divide, R.id.button_divide);
        assignButtonId(bt_c, R.id.button_c);
        assignButtonId(bt_open_parenthesis, R.id.button_open_parenthesis);
        assignButtonId(bt_close_parenthesis, R.id.button_close_parenthesis);
        assignButtonId(bt_minus, R.id.button_minus);
        assignButtonId(bt_dot, R.id.button_dot);
        assignButtonId(bt_equal, R.id.button_equal);
    }

    private void assignButtonId(MaterialButton bt, int id) {
        bt = findViewById(id);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton bt = (MaterialButton) view;
        String btText = bt.getText().toString();

        if(btText.equals("C")) {
            tv_result.setText("");
            return;
        }

        if(btText.equals("=")) {
            String finalResult = getResult(tv_result.getText().toString());
            tv_result.setText(finalResult);
            return;
        }

        String data = tv_result.getText().toString();
        data += btText;
        tv_result.setText(data);
    }

    private String getResult(String data) {
        // for debug purpose
        System.out.println("String to be evaluated: " + data);

        try {
            Context ct = Context.enter();
            ct.setOptimizationLevel(-1);
            Scriptable sc = ct.initStandardObjects();
            String finalResult = ct.evaluateString(sc, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }
        catch (Exception e) {
            return "E_NOK";
        }
    }
}