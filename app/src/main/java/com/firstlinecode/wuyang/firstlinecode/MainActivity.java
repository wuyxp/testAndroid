package com.firstlinecode.wuyang.firstlinecode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private Button btn1;
    private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.btn1:
                intent = new Intent(this, CalculatorActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(this, CalculatorActivity.class);
                intent.putExtra("data","这是首页过去的");
                startActivityForResult(intent, 101);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 101 && resultCode == 101){
            tv.setText(data.getStringExtra("data"));
        }
    }

}
