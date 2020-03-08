package com.visal.mobilecoursework1.ui_components;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.visal.mobilecoursework1.R;
import com.visal.mobilecoursework1.activities.IdentifyDogActivity;
import com.visal.mobilecoursework1.activities.MainActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class AlertDialogComponent {

    public static void identifyBreedAlert(Context context, final Button button, TextView title, SpannableString message, boolean cancelable) {
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

    public static void basicAlert(Context context, String message, String title) {
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

    public static void homeAlert(final Context context, String message, String title) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        TextView timeoutTitle = new TextView(context);
        timeoutTitle.setText(title);
        timeoutTitle.setPadding(30, 30, 20, 30);
        timeoutTitle.setTextSize(20F);
        timeoutTitle.setTypeface(null, Typeface.BOLD);

        final IdentifyDogActivity identifyDogActivity = new IdentifyDogActivity();

        alertDialogBuilder
                .setCustomTitle(timeoutTitle)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
