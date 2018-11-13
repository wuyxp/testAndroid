package com.firstlinecode.wuyang.firstlinecode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends Activity implements View.OnClickListener {
    final private String tag = "key";

    private EditText et;
    private Button ac;
    private Button c;
    private Button n0;
    private Button n1;
    private Button n2;
    private Button n3;
    private Button n4;
    private Button n5;
    private Button n6;
    private Button n7;
    private Button n8;
    private Button n9;
    private Button addition;
    private Button subtraction;
    private Button multiply;
    private Button division;
    private Button negate;
    private Button per;
    private Button compute;
    private Button point;

    private String result = ""; // 当前结果
    private String current = ""; //保存的临时结果
    private char type = '+'; //保存的临时计算符号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        /**
         * 获取所有页面元素
         */

        et = (EditText) findViewById(R.id.textView);
        /**
         * 清除按钮
         */
        ac = (Button) findViewById(R.id.ac);
        c = (Button) findViewById(R.id.c);

        /**
         * 数字键盘
         */
        n0 = (Button) findViewById(R.id.n0);
        n1 = (Button) findViewById(R.id.n1);
        n2 = (Button) findViewById(R.id.n2);
        n3 = (Button) findViewById(R.id.n3);
        n4 = (Button) findViewById(R.id.n4);
        n5 = (Button) findViewById(R.id.n5);
        n6 = (Button) findViewById(R.id.n6);
        n7 = (Button) findViewById(R.id.n7);
        n8 = (Button) findViewById(R.id.n8);
        n9 = (Button) findViewById(R.id.n9);
        /**
         * 加减乘除
         */
        addition = (Button) findViewById(R.id.addition);
        subtraction = (Button) findViewById(R.id.subtraction);
        multiply = (Button) findViewById(R.id.multiply);
        division = (Button) findViewById(R.id.division);
        /**
         * 取反，百分号，小数点
         */
        negate = (Button) findViewById(R.id.negate);
        per = (Button) findViewById(R.id.per);
        point = (Button) findViewById(R.id.point);
        /**
         * 结算结果
         */
        compute = (Button) findViewById(R.id.compute);

        n0.setOnClickListener(this);
        n1.setOnClickListener(this);
        n2.setOnClickListener(this);
        n3.setOnClickListener(this);
        n4.setOnClickListener(this);
        n5.setOnClickListener(this);
        n6.setOnClickListener(this);
        n7.setOnClickListener(this);
        n8.setOnClickListener(this);
        n9.setOnClickListener(this);

        c.setOnClickListener(this);
        ac.setOnClickListener(this);

        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiply.setOnClickListener(this);
        division.setOnClickListener(this);

        compute.setOnClickListener(this);

        negate.setOnClickListener(this);
        per.setOnClickListener(this);
        point.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int num  = 0;
        boolean isClearAll = false;
        char type = '+';
        /**
         * 数字键盘
         */
        if((view == n0 && (num = 0) == 0) ||
                    (view == n1 && (num = 1) == 1) ||
                    (view == n2 && (num = 2) == 2) ||
                    (view == n3 && (num = 3) == 3) ||
                    (view == n4 && (num = 4) == 4) ||
                    (view == n5 && (num = 5) == 5) ||
                    (view == n6 && (num = 6) == 6) ||
                    (view == n7 && (num = 7) == 7) ||
                    (view == n8 && (num = 8) == 8) ||
                    (view == n9 && (num = 9) == 9))
        {
            Log.i(tag, "n----"+num);
            this.onClickForNumber(num);
        }
        /**
         * 清除按钮
         */
        else if(view == c || (view == ac && (isClearAll = true))){
            Log.i(tag, "clear----"+(isClearAll? "all": "0"));
            this.onClickForClear(isClearAll);
        }
        /**
         * 加减乘除
         */
        else if(
                (view == addition && (type = '+') == '+') ||
                        (view == subtraction && (type = '-') == '-') ||
                        (view == multiply && (type = '*') == '*') ||
                        (view == division) && (type = '/') == '/'){
            Log.i(tag, "type----"+type);
            this.onClickForOperation(type);
        }
        /**
         * 计算结果
         */
        else if(view == compute){
            Log.i(tag, "计算结果了----");
            this.onClickForCompute();
        }
        /**
         * 正负号
         */
        else if(view == negate){
            Log.i(tag, "增加正负号----");
            this.onClickNegate();
        }
        /**
         * 百分号
         */
        else if(view == per){
            Log.i(tag, "百分号----");
            this.onClickPer();
        }
        /**
         * 小数点
         */
        else if(view == point){
            Log.i(tag, "小数点----");
            this.onClickPoint();
        }
    }

    /**
     * 输入数字
     * @param num
     */
    private void onClickForNumber(int num){
        if(result == "0"){
            if(num > 0){
                result = ""+num;
            }
        }else{
            result = result+num;
        }
        et.setText(result);
    }

    /**
     * 清除数字
     * @param isClearAll
     */
    private void onClickForClear(boolean isClearAll){
        int length = result.length();
        if(isClearAll){
            result = "0";
        }else{
            if(length > 1){
                result = result.substring(0, length-1);
            }else{
                result = "0";
            }
        }
        et.setText(result);
    }

    /**
     * 加减乘除操作
     * @param t
     */
    private void onClickForOperation(char t){
        if(current != ""){
            this.onClickForCompute();
        }
        current = result;
        result = "0";
        type = t;
    }

    /**
     * 正负号操作
     */
    private void onClickNegate(){
        if(result == "0") return;
        if(result.charAt(0) == '-'){
            result = result.substring(1);
        }else{
            result = "-" + result;
        }
        et.setText(result);
    }

    /**
     * 百分号操作
     */
    private void onClickPer(){
        if(result == "0") return;
        result = "" + (Double.valueOf(result) / 100);
        et.setText(result);
    }

    /**
     * 小数点
     */
    private void onClickPoint(){
        if(result.matches("\\.")){
            return;
        }else{
            result = result + ".";
        }
        et.setText(result);
    }

    /**
     * 结算结果
     */
    private void onClickForCompute(){
        if(current == "") return;
        String s = "0";
        switch (type){
            case '+':
                s = "" + (Double.valueOf(result) + Double.valueOf(current));
                break;
            case '-':
                s = "" + (Double.valueOf(current) - Double.valueOf(result));
                break;
            case '*':
                s = "" + (Double.valueOf(current) * Double.valueOf(result));
                break;
            case '/':
                s = "" + (Double.valueOf(current) / Double.valueOf(result));
                break;
        }
        current = "";
        Log.i(tag, "计算结果了----"+s);
        String[] arrs= s.split("\\.");
        if(arrs[1].matches("[1-9]+")){
            result = s;
        }else{
            result = arrs[0];
        }
        et.setText(result);
    }
}
