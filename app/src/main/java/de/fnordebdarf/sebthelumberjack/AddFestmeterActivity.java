package de.fnordebdarf.sebthelumberjack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by sammann on 10.06.17.
 *
 */

public class AddFestmeterActivity extends AppCompatActivity {

    private static final double pi = Math.PI;
    private static final Double_ _10 = new Double_(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_festmeter);
        TextView d_in_cm = (TextView) findViewById(R.id.d_in_cm);

        AddFestmeterListener updateFestmeter = new AddFestmeterListener(this);
        d_in_cm.setOnFocusChangeListener(updateFestmeter);
        d_in_cm.setOnEditorActionListener(updateFestmeter);

        TextView l_in_m = (TextView) findViewById(R.id.l_in_m);
        l_in_m.setOnFocusChangeListener(updateFestmeter);
        l_in_m.setOnEditorActionListener(updateFestmeter);
    }

    public void updateFestmeter(View view) {
        updateFestmeter();
    }

    private void updateFestmeter() {
        double festmeter = calcUsingHuberscheFormula();
        displayFestmeter(localized(festmeter));
    }

    private double calcUsingHuberscheFormula() {
        Double_ d = Double_.with(valueOf(R.id.d_in_cm));
        double l = asDouble(valueOf(R.id.l_in_m));
        return pi/4 * d.$(2) * l * _10.$(-4);
    }

    private String valueOf(int id) {
        return zeroIfEmptyOr(textOf(id));
    }

    private String zeroIfEmptyOr(String text) {
        if (text.isEmpty()) {
            text = "0";
        }
        return text;
    }

    private String textOf(int id) {
        EditText editText = (EditText) findViewById(id);
        return editText.getText().toString();
    }

    private double asDouble(String string) {
        return Double.valueOf(string);
    }

    private String localized(double aDouble) {
        return String.format(Locale.getDefault(), "%.2f", aDouble );
    }

    private void displayFestmeter(String value) {
        TextView textView = (TextView) findViewById(R.id.v_in_festmeter);
        textView.setText(value);
    }

    private static class AddFestmeterListener implements View.OnFocusChangeListener, TextView.OnEditorActionListener {

        private final AddFestmeterActivity activity;

        AddFestmeterListener(AddFestmeterActivity activity) {
            this.activity = activity;

        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            activity.updateFestmeter();
        }

        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
            if(actionId == 6) {
                activity.updateFestmeter();
            }
            return true;
        }
    }
}
