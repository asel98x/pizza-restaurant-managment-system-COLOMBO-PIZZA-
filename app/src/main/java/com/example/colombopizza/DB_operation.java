package com.example.colombopizza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_operation extends SQLiteOpenHelper {
    public DB_operation(@Nullable Context context) {
        super(context, "pizza", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table Customer_tbl (mobile_no integer primary key, uName varchar(20),  pWord varchar(25))";
        db.execSQL(sql);

       sql = "create table Employee_tbl (Mobile_no integer primary key, first_name varchar (25), last_name varchar (25), address varchar (55) , username varchar (25), password varchar (25))";
        db.execSQL(sql);

        sql = "create table pizza_tbl (ID integer primary key, name varchar (25), discription varchar (50), price double, IMG blog)";
        db.execSQL(sql);

        sql = "create table Beverage_tbl (ID integer primary key, name varchar (25), discription varchar (50), price double, IMG blog)";
        db.execSQL(sql);

        sql = "create table Apprtizers_tbl (ID integer primary key, name varchar (25), discription varchar (50), price double, IMG blog)";
        db.execSQL(sql);

        sql = "create table orderList_tbl (ID integer primary key AUTOINCREMENT, address varchar (50),name varchar (50), mobile integer, itemName varchar (50), price double, quantity integer, fee integer, total double)";
        db.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String  sql = "drop table if exists Customer_tbl";
        db.execSQL(sql);

        sql = "drop table if exists Employee_tbl";
        db.execSQL(sql);

        sql = "drop table if exists pizza_tbl";
        db.execSQL(sql);

        sql = "drop table if exists Beverage_tbl";
        db.execSQL(sql);

        sql = "drop table if exists Apprtizers_tbl";
        db.execSQL(sql);

        sql = "drop table if exists orderList_tbl";
        db.execSQL(sql);

    }

    public long addToCart(DB_cart cart) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("address",cart.getAddress());
        contentValues.put("name",cart.getName());
        contentValues.put("mobile",cart.getMobile());
        contentValues.put("itemName",cart.getItem_name());
        contentValues.put("price",cart.getPrice());
        contentValues.put("quantity",cart.getQuantity());
        contentValues.put("fee",cart.getFee());
        contentValues.put("total",cart.getTotal());

        return db.insert("orderList_tbl",null,contentValues);

    }

    public ArrayList<DB_cart>viewAllorders(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from orderList_tbl";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<DB_cart> cart = new ArrayList<DB_cart>();
        int count = cursor.getCount();

        if(count !=0){
            while(cursor.moveToNext()) {
                DB_cart cart2 = new DB_cart();
                cart2.setPid(cursor.getInt(0));
                cart2.setAddress(cursor.getString(1));
                cart2.setName(cursor.getString(2));
                cart2.setMobile(cursor.getInt(3));
                cart2.setItem_name(cursor.getString(4));
                cart2.setPrice(cursor.getDouble(5));
                cart2.setQuantity(cursor.getInt(6));
                cart2.setFee(cursor.getInt(7));
                cart2.setTotal(cursor.getDouble(8));
                cart.add(cart2);
            }
        }else{
            cart = null;
        }
        return cart;
    }

    public void create_customer(Customer c) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "insert into Customer_tbl values (" + c.getMobile_no() + ", '" + c.getUname() + "', '" + c.getPass() + "')";
        database.execSQL(sql);

    }

    public void create_employee(DB_employee e) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "insert into Employee_tbl values (" + e.getMobile_no() + ",'" + e.getFname() + "', '" + e.getLname() + "', '" + e.getAddress() + "', '" + e.getUname() + "', '" + e.getPass() + "')";
        database.execSQL(sql);

    }

    public Customer forgotPasword(Customer c){
        String sql = "select * from Customer_tbl where mobile_no = "+c.getMobile_no();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);
        int count = cursor.getCount();
        Customer customer = new Customer();
        if(count !=0){
            if(cursor.moveToFirst()) {
                customer.setMobile_no(cursor.getInt(cursor.getColumnIndex("mobile_no")));
                customer.setUname(cursor.getString(cursor.getColumnIndex("uName")));
                customer.setPass(cursor.getString(cursor.getColumnIndex("pWord")));
            }else{
                customer = null;
            }
        }
        return customer;
    }

    /*public DB_cart viewMyOrders(DB_cart cart) {
        String sql = "select * from orderList_tbl where name = "+cart.getName();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        DB_cart cart1 = new DB_cart();
        if(count !=0){
            if(cursor.moveToFirst()) {

                cart1.setName(cursor.getString(cursor.getColumnIndex("Mobile_no")));
                cart1.setItem_name(cursor.getString(5));
                cart1.setPrice(cursor.getDouble(6));
                cart1.setQuantity(cursor.getInt(7));
                cart1.setFee(cursor.getDouble(8));
                cart1.setTotal(cursor.getDouble(9));
            }
        } else {
            cart1 = null;
        }
        return cart1;
    }*/

    /*public ArrayList<DB_cart> viewMyOrders2(DB_cart cart) {
        ArrayList<DB_cart> orderArrayList = new ArrayList<>();
        String sql = "select * from orderList_tbl where name = "+cart.getName();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DB_cart cart1 = new DB_cart();
                cart1.setName(cursor.getString(cursor.getColumnIndex("name")));
                cart1.setItem_name(cursor.getString(5));
                cart1.setPrice(cursor.getDouble(6));
                cart1.setQuantity(cursor.getInt(7));
                cart1.setFee(cursor.getDouble(8));
                cart1.setTotal(cursor.getDouble(9));
                orderArrayList.add(cart1);
            }
        } else {
            orderArrayList = null;
        }
        return orderArrayList;
    }*/

    public ArrayList<DB_employee> viewAllEmployees() {
        ArrayList<DB_employee> employeeArrayList = new ArrayList<>();
        String sql = "select * from Employee_tbl";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DB_employee e = new DB_employee();
                e.setMobile_no(cursor.getInt(cursor.getColumnIndex("Mobile_no")));
                e.setFname(cursor.getString(1));
                e.setLname(cursor.getString(2));
                e.setAddress(cursor.getString(3));
                e.setUname(cursor.getString(4));
                e.setPass(cursor.getString(5));
                employeeArrayList.add(e);
            }
        } else {
            employeeArrayList = null;
        }
        return employeeArrayList;
    }

    public ArrayList<Customer> viewAllCustomers() {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        String sql = "select * from Customer_tbl";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Customer customer = new Customer();
                customer.setMobile_no(cursor.getInt(cursor.getColumnIndex("mobile_no")));
                customer.setUname(cursor.getString(1));
                customer.setPass(cursor.getString(2));
                customerArrayList.add(customer);
            }
        } else {
            customerArrayList = null;
        }
        return customerArrayList;
    }

    public DB_employee findemp(DB_employee db_employee) {
        String sql = "select * from Employee_tbl where Mobile_no = " + db_employee.getMobile_no();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        DB_employee employee = new DB_employee();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            employee.setMobile_no(cursor.getInt(cursor.getColumnIndex("Mobile_no")));
            employee.setFname(cursor.getString(cursor.getColumnIndex("first_name")));
            employee.setLname(cursor.getString(cursor.getColumnIndex("last_name")));
            employee.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            employee.setUname(cursor.getString(cursor.getColumnIndex("username")));
            employee.setPass(cursor.getString(cursor.getColumnIndex("password")));
        } else {
            employee = null;
        }
        return employee;
    }

    public Customer findcustomer(Customer customer) {
        String sql = "select * from Customer_tbl where mobile_no = " + customer.getMobile_no();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Customer customer2 = new Customer();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            customer2.setMobile_no(cursor.getInt(cursor.getColumnIndex("mobile_no")));
            customer2.setUname(cursor.getString(cursor.getColumnIndex("uName")));
            customer2.setPass(cursor.getString(cursor.getColumnIndex("pWord")));
        } else {
            customer2 = null;
        }
        return customer2;
    }

    public boolean Employee_login(String mobile_no, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true,"Employee_tbl",new String[]{"password"},"Mobile_no = ?",new String[]{String.valueOf(mobile_no)},null,null,null,"1");
        String passowrd = null;
        boolean result = false;
        while (cursor.moveToNext()) {
            passowrd = cursor.getString(0);
        }
        cursor.close();
        if(passowrd != null && passowrd.equals(pass)){
            result = true;
        }
        return result;
    }

    public boolean Customer_login(String uname, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true,"Customer_tbl",new String[]{"pWord"},"uName = ?",new String[]{String.valueOf(uname)},null,null,null,"1");
        String passowrd = null;
        boolean login = false;
        while (cursor.moveToNext()) {
            passowrd = cursor.getString(0);
        }
        cursor.close();
        if(passowrd != null && passowrd.equals(pass)){
            login = true;
        }
        return login;
    }



    public void update_employee_details(DB_employee emp) {
        SQLiteDatabase db = getReadableDatabase();


        String sql = "update Employee_tbl set first_name = '" + emp.getFname() + "',last_name='" + emp.getLname() + "',address='" + emp.getAddress() + "',username='" + emp.getUname() + "',password='" + emp.getPass() + "'"+
                "where Mobile_no = " + emp.getMobile_no();

        db.execSQL(sql);
    }

    public void update_customer_details(Customer customer) {
        SQLiteDatabase db = getReadableDatabase();


        String sql = "update Customer_tbl set uName = '" + customer.getUname() + "',pWord='" + customer.getPass()  + "'"+
                "where mobile_no = " + customer.getMobile_no();

        db.execSQL(sql);
    }

    public int delte_employee(DB_employee emp){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("Employee_tbl","Mobile_no="+emp.getMobile_no(),null);
    }

    public int delte_customer(Customer customer){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("Customer_tbl","mobile_no="+customer.getMobile_no(),null);
    }

    public int confirmed_order(DB_cart cart){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("orderList_tbl","ID="+cart.getPid(),null);
    }

    public long insertPizza_img(DB_pizza pizza){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",pizza.getF_id());
        contentValues.put("IMG",pizza.getImg());
        contentValues.put("name",pizza.getF_name());
        contentValues.put("price",pizza.getF_price());
        contentValues.put("discription",pizza.getF_description());


        return db.insert("pizza_tbl",null,contentValues);
    }

    public ArrayList<DB_pizza>viewAllpizza_items(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from pizza_tbl";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<DB_pizza> pizza = new ArrayList<DB_pizza>();
        int count = cursor.getCount();

        if(count !=0){
            while(cursor.moveToNext()) {
                DB_pizza p = new DB_pizza();
                p.setF_name(cursor.getString(1));
                p.setF_price(cursor.getDouble(3));
                p.setImg(cursor.getBlob(4));
                pizza.add(p);
            }
        }else{
            pizza = null;
        }
        return pizza;
    }

    public long insert_Beverage_img(DB_beverage Beverage){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",Beverage.getF_id());
        contentValues.put("IMG",Beverage.getImg());
        contentValues.put("name",Beverage.getF_name());
        contentValues.put("price",Beverage.getF_price());
        contentValues.put("discription",Beverage.getF_description());


        return db.insert("Beverage_tbl",null,contentValues);
    }

    public ArrayList<DB_beverage>viewAllbeverage_items(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from Beverage_tbl";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<DB_beverage> beverage = new ArrayList<DB_beverage>();
        int count = cursor.getCount();

        if(count !=0){
            while(cursor.moveToNext()) {
                DB_beverage bvrg = new DB_beverage();
                bvrg.setF_name(cursor.getString(1));
                bvrg.setF_price(cursor.getDouble(3));
                bvrg.setImg(cursor.getBlob(4));
                beverage.add(bvrg);
            }
        }else{
            beverage = null;
        }
        return beverage;
    }

    public long insert_apprtizers(DB_apprtizer apprtizer){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",apprtizer.getF_id());
        contentValues.put("IMG",apprtizer.getImg());
        contentValues.put("name",apprtizer.getF_name());
        contentValues.put("price",apprtizer.getF_price());
        contentValues.put("discription",apprtizer.getF_description());


        return db.insert("Apprtizers_tbl",null,contentValues);
    }

    public ArrayList<DB_apprtizer>viewAllapprtizer_items(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from Apprtizers_tbl";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<DB_apprtizer> apprtizer = new ArrayList<DB_apprtizer>();
        int count = cursor.getCount();

        if(count !=0){
            while(cursor.moveToNext()) {
                DB_apprtizer aptz = new DB_apprtizer();
                aptz.setF_name(cursor.getString(1));
                aptz.setF_price(cursor.getDouble(3));
                aptz.setImg(cursor.getBlob(4));
                apprtizer.add(aptz);
            }
        }else{
            apprtizer = null;
        }
        return apprtizer;
    }

    public DB_pizza findPizza_item(DB_pizza db_pizza){
        String sql = "select * from pizza_tbl where ID = "+db_pizza.getF_id();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        DB_pizza pizza = new DB_pizza();
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            //pizza.setF_id(cursor.getInt(cursor.getColumnIndex("ID")));
            pizza.setF_name(cursor.getString(cursor.getColumnIndex("name")));
            pizza.setF_description(cursor.getString(cursor.getColumnIndex("discription")));
            pizza.setF_price(cursor.getDouble(cursor.getColumnIndex("price")));
            pizza.setImg(cursor.getBlob(cursor.getColumnIndex("IMG")));
        }
        else{
            pizza = null;
        }
        return pizza;
    }



    /*public void update_pizza_item(DB_pizza db_pizza){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "update pizza_tbl set name = '"+db_pizza.getF_name()+"', discription = '"+db_pizza.getF_description()+"', price ="+db_pizza.getF_price()+", IMG ='"+db_pizza.getImg()+"'" +
                "where ID = "+db_pizza.getF_id();
        db.execSQL(sql);

    }*/

    public void update_pizza_item(DB_pizza db_pizza){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues ();

        contentValues.put("name", db_pizza.getF_name());
        contentValues.put("price", db_pizza.getF_price());
        contentValues.put("IMG", db_pizza.getImg());

        db.update("pizza_tbl",contentValues,"ID="+db_pizza.getF_id(),null);
    }

    public int delte_pizza_item(DB_pizza db_pizza){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("pizza_tbl","ID="+db_pizza.getF_id(),null);
    }

    public DB_beverage findBeverage_item(DB_beverage db_beverage){
        String sql = "select * from Beverage_tbl where ID = "+db_beverage.getF_id();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        DB_beverage beverage = new DB_beverage();
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            beverage.setF_id(cursor.getInt(cursor.getColumnIndex("ID")));
            beverage.setF_name(cursor.getString(cursor.getColumnIndex("name")));
            beverage.setF_description(cursor.getString(cursor.getColumnIndex("discription")));
            beverage.setF_price(cursor.getDouble(cursor.getColumnIndex("price")));
            beverage.setImg(cursor.getBlob(cursor.getColumnIndex("IMG")));
        }
        else{
            beverage = null;
        }
        return beverage;
    }

    public void update_beverage_item(DB_beverage db_beverage){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "update Beverage_tbl set name = '"+db_beverage.getF_name()+"', discription = '"+db_beverage.getF_description()+"', price ="+db_beverage.getF_price()+", IMG ='"+db_beverage.getImg()+"'" +
                "where ID = "+db_beverage.getF_id();
        db.execSQL(sql);
    }

    public int delte_beverage_item(DB_beverage db_beverage){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("Beverage_tbl","ID="+db_beverage.getF_id(),null);
    }

    public DB_apprtizer findAppetizers_item(DB_apprtizer db_apprtizer){
        String sql = "select * from Apprtizers_tbl where ID = "+db_apprtizer.getF_id();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        DB_apprtizer apprtizer = new DB_apprtizer();
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            apprtizer.setF_id(cursor.getInt(cursor.getColumnIndex("ID")));
            apprtizer.setF_name(cursor.getString(cursor.getColumnIndex("name")));
            apprtizer.setF_description(cursor.getString(cursor.getColumnIndex("discription")));
            apprtizer.setF_price(cursor.getDouble(cursor.getColumnIndex("price")));
            apprtizer.setImg(cursor.getBlob(cursor.getColumnIndex("IMG")));
        }
        else{
            apprtizer = null;
        }
        return apprtizer;
    }

    public void update_apprtizer_item(DB_apprtizer db_apprtizer){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "update Apprtizers_tbl set name = '"+db_apprtizer.getF_name()+"', discription = '"+db_apprtizer.getF_description()+"', price ="+db_apprtizer.getF_price()+", IMG ='"+db_apprtizer.getImg()+"'" +
                "where ID = "+db_apprtizer.getF_id();
        db.execSQL(sql);
    }

    public int delte_apprtizer_item(DB_apprtizer db_apprtizer){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("Apprtizers_tbl","ID="+db_apprtizer.getF_id(),null);
    }
}
