package com.example.listycitylab3;

import android.app.Dialog;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {


    interface EditCityDialogListener {
        void editCity(String cityName, String province);
    }

    private EditCityFragment.EditCityDialogListener listener;
    @ Override
    public void onAttach(@NonNull Context context) {

        // context is an instance of ___ ??

        super.onAttach(context);
        // DID MAINACTIVITY IMPLEMENT ? IF YES THEN
        if (context instanceof AddCityFragment.AddCityDialogListener) {
            // set current context
            // if yes cast into that interface
            listener = (EditCityFragment.EditCityDialogListener) context;   // WHAT DOES THIS MEAN treating context like an interface
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);


        EditText editCityName = view.findViewById(R.id.modify_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.modify_text_province_text);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel", null)      // null means don't do anything when button is added
                .setPositiveButton("Confirm modification", (dialog, which) -> {
                    // get the character sequence and then convert it toString
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    // create new city object using the two strings. Then add it to listener, listener being the INTERFACE
                    listener.editCity(cityName, provinceName);
                })
                .create();
    }
}


