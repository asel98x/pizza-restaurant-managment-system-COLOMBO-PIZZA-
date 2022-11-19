package com.example.colombopizza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frg_customer_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_customer_list extends Fragment {
    ListView listcustomer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_customer_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_customer_list.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_customer_list newInstance(String param1, String param2) {
        frg_customer_list fragment = new frg_customer_list();
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
        View view =  inflater.inflate(R.layout.fragment_frg_customer_list, container, false);

        listcustomer = view.findViewById(R.id.listCustomer);
        DB_operation db = new DB_operation(getActivity());


        ArrayList<Customer> customers = db.viewAllCustomers();
        if(customers!=null) {

            customer_list_adapter customer_list_adapter = new customer_list_adapter(getActivity(), customers);
            listcustomer.setAdapter(customer_list_adapter);
        }else{
            Toast.makeText(getActivity(),"No customers available",Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}