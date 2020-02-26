package com.visal.mobilecoursework1.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TextView;

import com.visal.mobilecoursework1.R;

public class AlertMessages {
    private TextView CORRECT_ANSWER_ALERT_TITLE;
    private TextView WRONG_ANSWER_ALERT_TITLE;

    public AlertMessages(Context context) {
        this.CORRECT_ANSWER_ALERT_TITLE = new TextView(context);
        this.WRONG_ANSWER_ALERT_TITLE = new TextView(context);

        CORRECT_ANSWER_ALERT_TITLE.setTextColor(Color.parseColor("#1BE59D"));
        CORRECT_ANSWER_ALERT_TITLE.setText(R.string.correct_answer_message);
        CORRECT_ANSWER_ALERT_TITLE.setPadding(40, 30, 20, 30);
        CORRECT_ANSWER_ALERT_TITLE.setTextSize(20F);
        CORRECT_ANSWER_ALERT_TITLE.setTypeface(null, Typeface.BOLD);

        WRONG_ANSWER_ALERT_TITLE.setTextColor(Color.parseColor("#FF0000"));
        WRONG_ANSWER_ALERT_TITLE.setText(R.string.incorrect_answer_message);
        WRONG_ANSWER_ALERT_TITLE.setPadding(40, 30, 20, 30);
        WRONG_ANSWER_ALERT_TITLE.setTextSize(20F);
        WRONG_ANSWER_ALERT_TITLE.setTypeface(null, Typeface.BOLD);
    }

    public TextView getCORRECT_ANSWER_ALERT_TITLE() {
        return CORRECT_ANSWER_ALERT_TITLE;
    }

    public TextView getWRONG_ANSWER_ALERT_TITLE() {
        return WRONG_ANSWER_ALERT_TITLE;
    }
}
