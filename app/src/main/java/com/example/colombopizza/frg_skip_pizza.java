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
 * Use the {@link frg_skip_pizza#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_skip_pizza extends Fragment {
    ArrayList<DB_pizza> pizza_items;
    DB_operation db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_skip_pizza() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_skip_pizza.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_skip_pizza newInstance(String param1, String param2) {
        frg_skip_pizza fragment = new frg_skip_pizza();
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
        View view = inflater.inflate(R.layout.fragment_frg_skip_pizza, container, false);
        ListView proList = view.findViewById(R.id.listProducts);

        DB_operation db = new DB_operation(getActivity());
        pizza_items = db.viewAllpizza_items();

        ArrayList<DB_pizza> pizza_items = db.viewAllpizza_items();
        if(pizza_items!=null) {

            skip_pizza_list_adapter skip_pizza_list_adapter = new skip_pizza_list_adapter(getActivity(), pizza_items);
            proList.setAdapter(skip_pizza_list_adapter);
        }else{
            Toast.makeText(getActivity(),"No itams available",Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}