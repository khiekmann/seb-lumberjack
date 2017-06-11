package de.fnordebdarf.sebthelumberjack;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

/**
 * Created by sammann on 11.06.17.
 *
 */

class AddFestmeterListener implements View.OnFocusChangeListener, TextView.OnEditorActionListener {

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
