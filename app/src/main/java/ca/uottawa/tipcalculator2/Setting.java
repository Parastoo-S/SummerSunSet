package ca.uottawa.tipcalculator2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView currentCurrency;
    Spinner chooseCurrency;
    Button save;
    EditText defaultTip;
    Bill bill = Bill.getInstance();
    Button cancel;
    private SharedPreferences setting;
    private SharedPreferences.Editor settingEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentCurrency = (TextView) findViewById(R.id.currentCurrency);
        chooseCurrency = (Spinner)findViewById(R.id.chooseCurrency);
        save = (Button) findViewById(R.id.save);
        defaultTip = (EditText) findViewById(R.id.defaultTip);
        cancel = (Button) findViewById(R.id.cancel);

        setting = PreferenceManager.getDefaultSharedPreferences(this);
        settingEditor = setting.edit();

        currentCurrency.setText(setting.getString("currency", bill.getCurrency()));

        if(currentCurrency.getText() != null){
            bill.setCurrency(setting.getString("currency", bill.getCurrency()));
        }
        defaultTip.setText(setting.getString("defaultTip", Double.toString(bill.getDefaultTipPercentage())));
        chooseCurrency.setSelection(bill.getCurrencyPosition());
//        defaultTip.setText(Double.toString(bill.getDefaultTipPercentage()));
//        chooseCurrency.setSelection(bill.getCurrencyPosition());


        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                bill.setCurrency(String.valueOf(chooseCurrency.getSelectedItem()));
                bill.setDefaultTipPercentage(Double.parseDouble(defaultTip.getText().toString()));

                settingEditor.putString("currency", String.valueOf(chooseCurrency.getSelectedItem()));
                settingEditor.commit();
                settingEditor.putString("defaultTip", defaultTip.getText().toString());
                settingEditor.commit();

                Intent backToMainPage = new Intent(Setting.this, MainActivity.class);
                startActivity(backToMainPage);
            }

        });


        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent backToMainPage = new Intent(Setting.this, MainActivity.class);
                startActivity(backToMainPage);
            }

        });



    }

}
