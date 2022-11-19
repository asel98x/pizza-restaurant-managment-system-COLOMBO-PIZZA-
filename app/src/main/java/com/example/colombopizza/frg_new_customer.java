package com.example.colombopizza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frg_new_customer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_new_customer extends Fragment {
    EditText Mnumber, username,pword;
    Button find,update,delete,clear;
    DB_operation db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_new_customer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_new_customer.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_new_customer newInstance(String param1, String param2) {
        frg_new_customer fragment = new frg_new_customer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_new_customer, container, false);

        Mnumber = view.findViewById(R.id.mobileTxt);
        username = view.findViewById(R.id.unameTxt);
        pword = view.findViewById(R.id.passwordTxt);
        find =view.findViewById(R.id.findBtn);
        update =view.findViewById(R.id.updateBtn);
        delete =view.findViewById(R.id.deleteBtn);
        clear =view.findViewById(R.id.clearBtn);
        db = new DB_operation(getActivity());

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Mnumber.getText().toString().isEmpty() ) {

                    Customer customer = new Customer();
                    int number = Integer.parseInt(Mnumber.getText().toString());
                    customer.setMobile_no(number);
                    customer = db.findcustomer(customer);
                    try {

                        if (customer != null) {
                            username.setText(customer.getUname());
                            pword.setText(customer.getPass());
                        } else {
                            Toast.makeText(getActivity(), "Invalid mobile number", Toast.LENGTH_SHORT).show();
                            clear();
                        }
                    } catch (Exception e) {
                        e.getStackTrace();
                        Toast.makeText(getActivity(), "can not Find the Customer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Mobile number can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Mnumber.getText().toString().isEmpty() && !username.getText().toString().isEmpty()) {

                    try {
                        Customer customer = new Customer();
                        customer.setMobile_no(Integer.parseInt(Mnumber.getText().toString()));
                        customer.setUname(username.getText().toString());
                        customer.setPass(pword.getText().toString());

                        db.update_customer_details(customer);
                        Toast.makeText(getActivity(), "Customer Record Updated", Toast.LENGTH_SHORT).show();
                        clear();
                    }catch(Exception e){
                        e.getStackTrace();
                        Toast.makeText(getActivity(), "can not Find the customer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Fields can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Mnumber.getText().toString().isEmpty() ) {

                    Customer customer = new Customer();
                    customer.setMobile_no(Integer.parseInt(Mnumber.getText().toString()));

                    if (db.delte_customer(customer) > 0) {
                        Toast.makeText(getActivity(), "Customer Record Deleted", Toast.LENGTH_SHORT).show();
                        clear();
                    }else{
                        Toast.makeText(getActivity(), "Invalid mobile number", Toast.LENGTH_SHORT).show();
                        clear();
                    }
                }else{
                    Toast.makeText(getActivity(), "Mobile number can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        return view;
    }

    public void clear(){
        Mnumber.setText("");
        username.setText("");
        pword.setText("");
        Mnumber.requestFocus();
    }
}