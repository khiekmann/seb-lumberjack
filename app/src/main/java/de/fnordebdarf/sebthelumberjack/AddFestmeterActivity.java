package de.fnordebdarf.sebthelumberjack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private static final Double_ _10 = new Double_(10d);
    private static final String roundedToTwoDigits = "%.2f";

    private final AddFestmeterListener updateFestmeter;

    public AddFestmeterActivity() {
        this.updateFestmeter = new AddFestmeterListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_festmeter);
        addUpdateFestmeterListenerTo(R.id.d_in_cm);
        addUpdateFestmeterListenerTo(R.id.l_in_m);
    }

    private void addUpdateFestmeterListenerTo(int id) {
        TextView textView = (TextView) findViewById(id);
        textView.setOnFocusChangeListener(updateFestmeter);
        textView.setOnEditorActionListener(updateFestmeter);
    }

    public void updateFestmeter(View view) {
        updateFestmeter();
    }

    void updateFestmeter() {
        double festmeter = calcUsingHuberscheFormula();
        displayFestmeter(localize(festmeter, Locale.getDefault()));
    }

    private double calcUsingHuberscheFormula() {
        Double_ d = Double_.from(textOf(R.id.d_in_cm));
        double l = Double_.from(textOf(R.id.l_in_m)).$();
        return pi/4 * d.$(2) * l * _10.$(-4);
    }

    private String textOf(int id) {
        EditText editText = (EditText) findViewById(id);
        return editText.getText().toString();
    }

    private String localize(Double value, Locale toThisLocale) {
        String localized = "";
        if ( ! value.isNaN()) {
            localized = String.format(toThisLocale, roundedToTwoDigits, value);
        }
        return localized;
    }

    private void displayFestmeter(String value) {
        TextView textView = (TextView) findViewById(R.id.v_in_festmeter);
        textView.setText(value);
    }
}
