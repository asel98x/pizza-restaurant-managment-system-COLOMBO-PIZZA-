package com.example.colombopizza;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewEmployee#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewEmployee extends Fragment {
    EditText Fname, Lname, Mnumber, addrss, uname, pword;
    Button save, find,update,delete,clear;
    DB_operation db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewEmployee() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewEmployee.
     */
    // TODO: Rename and change types and number of parameters
    public static NewEmployee newInstance(String param1, String param2) {
        NewEmployee fragment = new NewEmployee();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_employee, container, false);
        Fname = view.findViewById(R.id.firstNameTxt);
        Lname = view.findViewById(R.id.LastNameTxt);
        Mnumber = view.findViewById(R.id.mobileTxt);
        addrss = view.findViewById(R.id.addressTxt);
        uname = view.findViewById(R.id.usernameTxt);
        pword = view.findViewById(R.id.passwordTxt);
        save =view.findViewById(R.id.saveBtn);
        find =view.findViewById(R.id.findBtn);
        update =view.findViewById(R.id.updateBtn);
        delete =view.findViewById(R.id.deleteBtn);
        clear =view.findViewById(R.id.clearBtn);
        db = new DB_operation(getActivity());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Mnumber.getText().toString().isEmpty() && !Fname.getText().toString().isEmpty() && !Lname.getText().toString().isEmpty() && !addrss.getText().toString().isEmpty() && !uname.getText().toString().isEmpty() && !pword.getText().toString().isEmpty() ) {
                    int mobile_no = Integer.parseInt(Mnumber.getText().toString());
                    String first_name = Fname.getText().toString();
                    String last_name = Lname.getText().toString();
                    String address = addrss.getText().toString();
                    String username = uname.getText().toString();
                    String password = pword.getText().toString();
                    db = new DB_operation(getActivity());

                    DB_employee emp = new DB_employee();
                    emp.setFname(first_name);
                    emp.setLname(last_name);
                    emp.setMobile_no(mobile_no);
                    emp.setAddress(address);
                    emp.setUname(username);
                    emp.setPass(password);
                    try {
                        if (mobile_no <= 700000000 || mobile_no >= 789999999) {
                            Toast.makeText(getActivity(), "Invalid mobile number!", Toast.LENGTH_SHORT).show();
                        }else{
                            db.create_employee(emp);
                            Toast.makeText(getActivity(), "Successfully Employee details Inserted", Toast.LENGTH_SHORT).show();
                            clear();
                        }
                    }catch (Exception exception){
                        Toast.makeText(getActivity(), "Invalid Authentication!", Toast.LENGTH_SHORT).show();
                        exception.getStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity(), "Fields can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Mnumber.getText().toString().isEmpty() ) {

                    DB_employee emp = new DB_employee();
                    int number = Integer.parseInt(Mnumber.getText().toString());
                    emp.setMobile_no(number);
                    emp = db.findemp(emp);
                    try {
                        if (emp != null) {
                            Fname.setText(emp.getFname());
                            Lname.setText(emp.getLname());
                            addrss.setText(emp.getAddress());
                            uname.setText(emp.getUname());
                            pword.setText(emp.getPass());
                        } else {
                            Toast.makeText(getActivity(), "Invalid mobile number", Toast.LENGTH_SHORT).show();
                            clear();
                        }
                    } catch (Exception e) {
                        e.getStackTrace();
                        Toast.makeText(getActivity(), "can not Find User", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Mobile number can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Mnumber.getText().toString().isEmpty() && !Fname.getText().toString().isEmpty()
                        && !Lname.getText().toString().isEmpty() && !addrss.getText().toString().isEmpty() &&
                        !uname.getText().toString().isEmpty() && !pword.getText().toString().isEmpty() ) {

                    DB_employee emp = new DB_employee();
                    emp.setMobile_no(Integer.parseInt(Mnumber.getText().toString()));
                    emp.setFname(Fname.getText().toString());
                    emp.setLname(Lname.getText().toString());
                    emp.setAddress(addrss.getText().toString());
                    emp.setUname(uname.getText().toString());
                    emp.setPass(pword.getText().toString());

                    db.update_employee_details(emp);
                    Toast.makeText(getActivity(), "Employee Record Updated", Toast.LENGTH_SHORT).show();
                    clear();
                }else{
                    Toast.makeText(getActivity(), "Fields can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Mnumber.getText().toString().isEmpty() ) {

                    DB_employee employee = new DB_employee();
                    employee.setMobile_no(Integer.parseInt(Mnumber.getText().toString()));

                    if (db.delte_employee(employee) > 0) {
                        Toast.makeText(getActivity(), "Employee Record Deleted", Toast.LENGTH_SHORT).show();
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
        Fname.setText("");
        Lname.setText("");
        addrss.setText("");
        uname.setText("");
        pword.setText("");
        Mnumber.requestFocus();
    }


}