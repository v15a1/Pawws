package com.visal.mobilecoursework1.ui_components;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;

import com.visal.mobilecoursework1.IdentifyBreedActivity;

public class AlertDialogComponent {
    public static void showSingleButtonAlert(Context context, final Button button, String title, String message, final String buttonLabel, boolean cancelable){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        alertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button.setText(buttonLabel);
                    }
                });

        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
