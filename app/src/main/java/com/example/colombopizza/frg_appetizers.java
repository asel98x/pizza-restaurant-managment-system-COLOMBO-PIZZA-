package com.example.colombopizza;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frg_appetizers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_appetizers extends Fragment {
    EditText txtID, txtName, txtdescription, TxtPrice;
    Button save, find,update,delete,clear,insert,viewList;
    ImageView image;
    byte[] imageByte;
    DB_operation db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_appetizers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_appetizers.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_appetizers newInstance(String param1, String param2) {
        frg_appetizers fragment = new frg_appetizers();
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
        View view = inflater.inflate(R.layout.fragment_frg_appetizers, container, false);
        txtID = view.findViewById(R.id.idTxt);
        txtName = view.findViewById(R.id.foodNameTxt);
        txtdescription = view.findViewById(R.id.descriptionTxt);
        TxtPrice = view.findViewById(R.id.priceTxt);
        insert =view.findViewById(R.id.insertBtn);
        save =view.findViewById(R.id.saveBtn);
        find =view.findViewById(R.id.findBtn);
        update =view.findViewById(R.id.updateBtn);
        delete =view.findViewById(R.id.deleteBtn);
        clear =view.findViewById(R.id.clearBtn);
        image =view.findViewById(R.id.imgFood);
        db = new DB_operation(getActivity());

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX",0);
                intent.putExtra("aspectY",0);
                intent.putExtra("outputX",160);
                intent.putExtra("outputY",150);
                intent.putExtra("return_data",true);
                startActivityForResult(Intent.createChooser(intent,"SELECT PRODUCT IMAGE"),11);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtID.setText("");
                txtName.setText("");
                txtdescription.setText("");
                TxtPrice.setText("");
                image.setImageBitmap(null);
                txtID.requestFocus();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtID.getText().toString().isEmpty() && !txtName.getText().toString().isEmpty() && !txtdescription.getText().toString().isEmpty() && !TxtPrice.getText().toString().isEmpty()){


                    int id = Integer.parseInt(txtID.getText().toString());
                    String name = txtName.getText().toString();
                    String description = txtdescription.getText().toString();
                    Double price = Double.parseDouble(TxtPrice.getText().toString());


                    DB_apprtizer apprtizer = new DB_apprtizer();

                    apprtizer.setF_id(id);
                    apprtizer.setF_name(name);
                    apprtizer.setF_description(description);
                    apprtizer.setF_price(price);
                    apprtizer.setImg(imageByte);


                    try {
                        db = new DB_operation(getActivity());
                        if (db.insert_apprtizers(apprtizer) > 0) {
                            Toast.makeText(getActivity(), "New apprtizer item Interested!", Toast.LENGTH_SHORT).show();
                            clear_appertizer();
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Invalid details!", Toast.LENGTH_SHORT).show();
                    }


                }else {

                    Toast.makeText(getActivity(), "Empty fields can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtID.getText().toString().isEmpty()){
                    int id = Integer.parseInt(txtID.getText().toString());

                    DB_apprtizer apprtizer = new DB_apprtizer();
                    apprtizer.setF_id(id);
                    apprtizer = db.findAppetizers_item(apprtizer);

                    try {

                        if (apprtizer!= null) {
                            txtName.setText(apprtizer.getF_name());
                            txtdescription.setText(apprtizer.getF_description());
                            TxtPrice.setText("" + apprtizer.getF_price());
                            Bitmap bitmap = BitmapFactory.decodeByteArray(apprtizer.getImg(),0,apprtizer.getImg().length);
                            image.setImageBitmap(bitmap);

                        } else {
                            Toast.makeText(getActivity(), "Can not Find the apprtizer item", Toast.LENGTH_SHORT).show();

                        }
                    }catch (Exception e){
                        e.getStackTrace();
                        Toast.makeText(getActivity(), "Invalid details entered", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "ID can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtID.getText().toString().isEmpty()){
                    int ID = Integer.parseInt(txtID.getText().toString());

                    DB_apprtizer apprtizer = new DB_apprtizer();

                    apprtizer.setF_id(ID);
                    apprtizer.setF_name(txtName.getText().toString());
                    apprtizer.setF_description(txtdescription.getText().toString());
                    apprtizer.setF_price(Double.parseDouble(TxtPrice.getText().toString()));
                    apprtizer.setImg(imageByte);



                    db.update_apprtizer_item(apprtizer);
                    Toast.makeText(getActivity(),"Pizza item Updated", Toast.LENGTH_SHORT).show();
                    clear_appertizer();

                }else{
                    Toast.makeText(getActivity(),"Pizza item not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtID.getText().toString().isEmpty()){
                    int ID = Integer.parseInt(txtID.getText().toString());

                    DB_apprtizer apprtizer = new DB_apprtizer();
                    apprtizer.setF_id(ID);

                    if(db.delte_apprtizer_item(apprtizer)>0){
                        Toast.makeText(getActivity(),"Apprtizer item Deleted", Toast.LENGTH_SHORT).show();
                        clear_appertizer();
                    }
                }else{
                    Toast.makeText(getActivity(),"Can't find the Apprtizer item!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==11){
            if(data!=null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0,arrayOutputStream);
                    imageByte = arrayOutputStream.toByteArray();
                    image.setImageBitmap(bitmap);
                }catch (IOException e){
                    Log.e("Error","MSG"+e.getMessage());
                }
            }
        }
    }

    public void clear_appertizer(){
        txtID.setText("");
        txtName.setText("");
        txtdescription.setText("");
        TxtPrice.setText("");
        image.setImageBitmap(null);
        txtID.requestFocus();
    }
}