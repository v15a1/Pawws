package com.visal.mobilecoursework1.ui_components;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;


public class AlertDialogComponent {


    public static void showSingleButtonAlert(Context context, final Button button, String title, String message, final String buttonLabel, boolean cancelable){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);

        TextView correctAlertTitle = new TextView(context);
        TextView wrongAlertTitle = new TextView(context);

        correctAlertTitle.setTextColor(Color.parseColor("#4dffa0"));
        correctAlertTitle.setText("CORRECT!");
        correctAlertTitle.setPadding(30, 30, 20, 30);
        correctAlertTitle.setTextSize(20F);
        correctAlertTitle.setTypeface(null, Typeface.BOLD);

        wrongAlertTitle.setTextColor(Color.parseColor("#4dffa0"));
        wrongAlertTitle.setText("WRONG!");
        wrongAlertTitle.setPadding(30, 30, 20, 30);
        wrongAlertTitle.setTextSize(20F);
        wrongAlertTitle.setTypeface(null, Typeface.BOLD);

        alertDialogBuilder
                .setCustomTitle(correctAlertTitle)
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
