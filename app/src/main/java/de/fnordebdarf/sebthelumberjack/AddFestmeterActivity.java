package de.fnordebdarf.sebthelumberjack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

/**
 * Created by sammann on 10.06.17.
 *
 */

public class AddFestmeterActivity extends AppCompatActivity {

    private static final double pi = Math.PI;
    private static final Double_ _10 = new Double_(10d);
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

    private void updateFestmeter() {
        double festmeter = calcUsingHuberscheFormula();
        displayFestmeter(localized(festmeter));
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

    private String localized(Double value) {
        String localized = "";
        if ( ! value.isNaN()) {
            localized = String.format(Locale.getDefault(), "%.2f", value);
        }
        return localized;
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
            if(actionId == IME_ACTION_DONE) {
                activity.updateFestmeter();
            }
            return true;
        }
    }
}
