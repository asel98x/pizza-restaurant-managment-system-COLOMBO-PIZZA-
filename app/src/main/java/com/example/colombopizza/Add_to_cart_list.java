package com.example.colombopizza;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class Add_to_cart_list extends AppCompatActivity implements LocationListener {
    TextView Item_name, Item_price,fee,total,quantity;
    EditText locationTxt,name, mobile;
    int count = 1;
    Button locationBtn;
    DB_operation db;
    LocationManager locationManager;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_list);

        getWindow().setStatusBarColor(ContextCompat.getColor(Add_to_cart_list.this, R.color.Lightred));
        getSupportActionBar().hide();

        Item_name = findViewById(R.id.Pname);
        Item_price = findViewById(R.id.Pprice);
        fee = findViewById(R.id.Pfee);
        total = findViewById(R.id.Ptotal);
        quantity = findViewById(R.id.qty);
        locationTxt = findViewById(R.id.AdrsTxt);
        name = findViewById(R.id.CustomerName);
        mobile = findViewById(R.id.CustomerMobile);

        db = new DB_operation(this);

        Bundle bn = getIntent().getExtras();
        String name = bn.getString("Pname");
        Double Pprice = bn.getDouble("Pprice");
        Item_name.setText(String.valueOf(name));
        Item_price.setText(Double.toString(Pprice));
        fee.setText("55.0");
        total_calculate2();

        //run time permision
        if (ContextCompat.checkSelfPermission(Add_to_cart_list.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Add_to_cart_list.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);

        }
    }


    public void delete_cart(View view){
        Item_name.setText("");
        Item_price.setText("");
        fee.setText("");
        total.setText("");
        quantity.setText("0");
        Toast.makeText(this,"Food item remove from the cart.",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Add_to_cart_list.this,Customer_account.class);
        startActivity(intent);
    }

    public void total_calculate(){
        double Fprice, Ffee, ttl,x;
        int qty;

        Fprice = Double.parseDouble(String.valueOf(Item_price.getText()));
        qty = Integer.parseInt(String.valueOf(quantity.getText()));

        x = Fprice*qty;

        Ffee = Double.parseDouble(String.valueOf(fee.getText()));
        ttl = x + Ffee;
        String ttl1 = String.valueOf(ttl);
        total.setText(ttl1);
    }

    public void total_calculate2(){
        double Fprice, Ffee, ttl;

        Fprice = Double.parseDouble(String.valueOf(Item_price.getText()));

        Ffee = Double.parseDouble(String.valueOf(fee.getText()));
        ttl = Fprice + Ffee;
        String ttl1 = String.valueOf(ttl);
        total.setText(ttl1);
    }

    public void order_food(View view) {
        if (!locationTxt.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !mobile.getText().toString().isEmpty()) {

            String Iaddress = locationTxt.getText().toString();
            String Iname = name.getText().toString();
            int Imobile = Integer.parseInt(mobile.getText().toString());
            String Iitem_name = Item_name.getText().toString();
            Double Iprice = Double.parseDouble(Item_price.getText().toString());
            int Iquantity = Integer.parseInt(quantity.getText().toString());
            Double Ifee = Double.parseDouble(fee.getText().toString());
            Double Itotal = Double.parseDouble(total.getText().toString());

            DB_cart cart = new DB_cart();
            cart.setAddress(Iaddress);
            cart.setName(Iname);
            cart.setMobile(Imobile);
            cart.setItem_name(Iitem_name);
            cart.setPrice(Iprice);
            cart.setQuantity(Iquantity);
            cart.setFee(Ifee);
            cart.setTotal(Itotal);

            try {
                    db.addToCart(cart);
                    Toast.makeText(this, "Successfully Ordered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add_to_cart_list.this, My_orders.class);
                    startActivity(intent);



            }catch (Exception e){
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                e.getStackTrace();
            }
        }else{
            Toast.makeText(this,"Fields can't be empty!",Toast.LENGTH_SHORT).show();
        }
    }

    public void increase (View view){
        count ++;
        quantity.setText(""+count);
        total_calculate();
    }

    public void descrese (View view){
        if (count <=1) count = 1;
        else count --;
        quantity.setText(""+count);
        total_calculate();
    }

    @SuppressLint("MissingPermission")
    public void getLocation(View view){
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, Add_to_cart_list.this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(Add_to_cart_list.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            locationTxt.setText(address);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }


}