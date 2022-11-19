package com.example.colombopizza;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Uname, Pword;
    Button login;
    DB_operation db;
    ArrayList<DB_cart> cart;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uname = findViewById(R.id.UsernameTxt);
        Pword = findViewById(R.id.passwordTxt);
        My_orders m1 = new My_orders();

        db=new DB_operation(this);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.Lightred));
        getSupportActionBar().hide();
    }

    public void login(View view) {
        if (!Uname.getText().toString().isEmpty() && !Pword.getText().toString().isEmpty()) {
            String name = Uname.getText().toString();
            String password = Pword.getText().toString();

            ProgressDialog progressDialog = new ProgressDialog (MainActivity.this);
            progressDialog.setTitle("Login");
            progressDialog.setMessage("Authentication...");
            progressDialog.show();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    boolean login = db.Customer_login(name,password);
                    if(login){
                        Intent intent = new Intent(MainActivity.this, Customer_account.class);
                        startActivity(intent);
                    }else{
                        errorMsg();
                    }
                    progressDialog.dismiss();
                }
            },1500);
        } else {
            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Passwordforgot(View view) {
        Intent intent = new Intent(MainActivity.this, forgot_password.class);
        startActivity(intent);
    }

    public void signupActivity(View view) {
        Intent intent = new Intent(MainActivity.this, signup_Screen.class);
        startActivity(intent);
    }

    public void skipActivity(View view) {
        Intent intent = new Intent(MainActivity.this, skip_login.class);
        startActivity(intent);
    }

    public void admin_login(View view) {
        Intent intent = new Intent(MainActivity.this, employee_login.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void errorMsg(){
        Toast.makeText(this, "Invalid authantication!", Toast.LENGTH_SHORT).show();
        Uname.setText("");
        Pword.setText("");
        Uname.requestFocus();
    }

}