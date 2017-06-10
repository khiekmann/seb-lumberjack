package de.fnordebdarf.sebthelumberjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the to_add_festmeter button */
    public void toAddFestmeter(View view) {
        Intent intent = new Intent(this, AddFestmeterActivity.class);
        startActivity(intent);
    }
}
