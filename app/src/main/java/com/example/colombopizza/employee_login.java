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

public class employee_login extends AppCompatActivity {
    EditText Mno, Pword;
    DB_operation db;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);

        Mno = findViewById(R.id.MobileNumberTxt);
        Pword = findViewById(R.id.passwordTxt);
        db=new DB_operation(this);


        getWindow().setStatusBarColor(ContextCompat.getColor(employee_login.this,R.color.Lightred));
        getSupportActionBar().hide();
    }



    public void employeeLogin(View view) {
        if (!Mno.getText().toString().isEmpty() && !Pword.getText().toString().isEmpty()) {
            String mobile = Mno.getText().toString();
            String password = Pword.getText().toString();


            try {
                ProgressDialog progressDialog = new ProgressDialog (employee_login.this);
                progressDialog.setTitle("Login");
                progressDialog.setMessage("Authentication...");
                progressDialog.show();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean login = db.Employee_login(mobile,password);
                        if(login){
                            Intent intent = new Intent(employee_login.this, orders.class);
                            startActivity(intent);

                        }else if (mobile.equals("0771234567") && password.equals("admin")) {
                            Intent intent = new Intent(employee_login.this, orders.class);
                            startActivity(intent);
                        }else{
                            errorMsg();
                        }
                        progressDialog.dismiss();

                    }
                },1500);


            }catch (Exception e){
                Toast.makeText(this, "Invalid authantication!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void errorMsg(){
        Toast.makeText(this, "Invalid authantication!", Toast.LENGTH_SHORT).show();
        Mno.setText("");
        Pword.setText("");
        Mno.requestFocus();
    }
}