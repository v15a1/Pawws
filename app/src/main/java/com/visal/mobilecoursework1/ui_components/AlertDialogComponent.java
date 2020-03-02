package com.visal.mobilecoursework1.ui_components;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogComponent {


    public static void identifyBreedAlert(Context context, final Button button, TextView title, String message, boolean cancelable) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);

        alertDialogBuilder
                .setCustomTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button.setText("NEXT");
                    }
                });

        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void identifyDogAlert(Context context, TextView title, String message, boolean cancelable) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);

        alertDialogBuilder
                .setCustomTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void emptySelectionAlert(Context context) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        TextView unselectedBreedTitle = new TextView(context);
        unselectedBreedTitle.setText("Select a Breed");
        unselectedBreedTitle.setPadding(30, 30, 20, 30);
        unselectedBreedTitle.setTextSize(20F);
        unselectedBreedTitle.setTypeface(null, Typeface.BOLD);

        alertDialogBuilder
                .setCustomTitle(unselectedBreedTitle)
                .setMessage("A dog breed must be selected")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void timeoutAlert(Context context, String message) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        TextView timeoutTitle = new TextView(context);
        timeoutTitle.setText("Out of Time");
        timeoutTitle.setPadding(30, 30, 20, 30);
        timeoutTitle.setTextSize(20F);
        timeoutTitle.setTypeface(null, Typeface.BOLD);

        alertDialogBuilder
                .setCustomTitle(timeoutTitle)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}
