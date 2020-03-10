package com.visal.pawws.controllers;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.widget.Button;
import android.widget.TextView;

//class that holds all the alert messages
class AlertDialogComponent {

    static void identifyBreedAlert(Context context, final Button button, TextView title, SpannableString message, boolean cancelable) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);

        //setting a customized alert title
        alertDialogBuilder
                .setCustomTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button.setText("NEXT");     //changin the passed buttons text
                    }
                });

        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();     //showing the alert
    }

    static void identifyDogAlert(Context context, TextView title, String message, boolean cancelable) {
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

    static void basicAlert(Context context, String message, String title) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        TextView timeoutTitle = new TextView(context);
        timeoutTitle.setText(title);
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

    //overloaded method of basicAlert
    static void basicAlert(Context context, SpannableString message, String title) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        TextView timeoutTitle = new TextView(context);
        timeoutTitle.setText(title);
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
