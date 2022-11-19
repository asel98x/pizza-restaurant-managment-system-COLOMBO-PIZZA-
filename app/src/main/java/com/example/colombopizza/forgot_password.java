package com.example.colombopizza;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class forgot_password extends AppCompatActivity {
    EditText Mno,Uname, Pword;
    DB_operation db;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Mno = (EditText)findViewById(R.id.MNumberTxt);
        Uname = (EditText)findViewById(R.id.unameTxt);
        Pword =(EditText) findViewById(R.id.passwordTxt);
        db = new DB_operation(this);

        getWindow().setStatusBarColor(ContextCompat.getColor(forgot_password.this,R.color.Lightred));
        getSupportActionBar().hide();
    }

    public void forgotPassword(View view){

        if (!Mno.getText().toString().isEmpty()) {

            Customer customer = new Customer();
            int mobile = Integer.parseInt(Mno.getText().toString());
            customer.setMobile_no(mobile);
            customer = db.forgotPasword(customer);
            try {

                if (customer != null) {
                    Uname.setText(customer.getUname());
                    Pword.setText(customer.getPass());
                } else {
                    Toast.makeText(this, "User Records Not Found", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.getStackTrace();
                Toast.makeText(this, "can not Find User", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
        }

    }


    public void gotoLogin(View view){
        Intent intent = new Intent(forgot_password.this, MainActivity.class);
        startActivity(intent);
    }
}