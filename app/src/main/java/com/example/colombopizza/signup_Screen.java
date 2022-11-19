package com.example.colombopizza;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup_Screen extends AppCompatActivity {
    EditText txtMno, txtUname, txtPword;
    DB_operation db;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__screen);

        txtMno = (EditText)findViewById(R.id.MobileNoBtn);
        txtUname =(EditText) findViewById(R.id.userNameBtn);
        txtPword = (EditText)findViewById(R.id.passwordBtn);

        db = new DB_operation(this);


        getWindow().setStatusBarColor(ContextCompat.getColor(signup_Screen.this,R.color.Lightred));
        getSupportActionBar().hide();
    }

    public void loginScreen(View view) {
        Intent intent = new Intent(signup_Screen.this, MainActivity.class);
        startActivity(intent);
    }

    public void insert_customer (View view){
        if(!txtMno.getText().toString().isEmpty() && !txtUname.getText().toString().isEmpty() && !txtPword.getText().toString().isEmpty()) {
            int mobile_no = Integer.parseInt(txtMno.getText().toString());
            String uname = txtUname.getText().toString();
            String pass = txtPword.getText().toString();
            try {
                ProgressDialog progressDialog = new ProgressDialog (signup_Screen.this);
                progressDialog.setTitle("Login");
                progressDialog.setMessage("Authentication...");
                progressDialog.show();
                Customer c = new Customer();
                c.setMobile_no(mobile_no);
                c.setUname(uname);
                c.setPass(pass);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mobile_no <= 700000000 || mobile_no >= 789999999) {
                            mobile_verify();
                            clear_customerDetails();
                        }else{
                            db.create_customer(c);
                            successfullMsg();
                            Intent intent = new Intent(signup_Screen.this, MainActivity.class);
                            startActivity(intent);
                        }
                        progressDialog.dismiss();
                    }
                },1500);
            }catch (Exception e){
                Toast.makeText(this, "Invalid Authentication!", Toast.LENGTH_SHORT).show();
                e.getStackTrace();
            }
        }else{
            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void mobile_verify(){
        Toast.makeText(this, "Invalid mobile number!", Toast.LENGTH_SHORT).show();
    }

    public void successfullMsg() {
        Toast.makeText(this, "Successfully Customer details Inserted", Toast.LENGTH_SHORT).show();
    }

    public void clear_customerDetails(){
        txtMno.setText("");
        txtUname.setText("");
        txtPword.setText("");
        txtMno.requestFocus();
    }
}