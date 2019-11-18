package com.example.lab7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class ExampleDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(getActivity());
        builder.setMessage("Powiadomienie z interakcja")
                .setPositiveButton("Wow", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                .setNegativeButton("Tyle opcji", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Tost drugi
                            }
                        });
        Dialog dialog = builder.create();
        return dialog;
    }
}
