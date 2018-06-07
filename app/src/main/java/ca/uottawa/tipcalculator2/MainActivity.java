package ca.uottawa.tipcalculator2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editBillAmount;
    EditText editPercentage;
    EditText editNum;
    Button enter;
    TextView showCurrency;
    public SharedPreferences setting;

    Bill bill = Bill.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editBillAmount = (EditText) findViewById(R.id.editBillAmount);
        editPercentage = (EditText) findViewById(R.id.editPercentage);
        editNum = (EditText) findViewById(R.id.editNum);
        enter = (Button) findViewById(R.id.enter);
        showCurrency = (TextView) findViewById(R.id.showCurrency);
        setting = getSharedPreferences("settings", MODE_PRIVATE);


        showCurrency.setText(bill.getCurrency());

        String currency = setting.getString("currency", bill.getCurrency());
        double defTip = Double.parseDouble(setting.getString("defaultTip", "0.0" ));

        if(bill.getTipPercentage() != 0.0){
            editPercentage.setText(Double.toString(bill.getTipPercentage()));
        }
        else if(defTip != 0.0){
            editPercentage.setText(Double.toString(defTip));
        }



        if(currency != null){
            bill.setCurrency(currency);
            showCurrency.setText(bill.getCurrency());
            bill.setCurrencySymbol();
        }

        else{
            bill.setCurrency("Dollar ($)");
            bill.setCurrencySymbol();
            showCurrency.setText(bill.getCurrency());
        }


        if(bill.getNumberOfPeople() != 0){
            editNum.setText(Integer.toString(1));
        }


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editBillAmount.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter the Bill Amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (Double.parseDouble(editBillAmount.getText().toString()) >= 1000) {
                    Toast.makeText(getApplicationContext(), "Bill Maximum Value is 999.99", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(editPercentage.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter the Tip Percentage", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(Double.parseDouble(editPercentage.getText().toString()) > 100.0){
                    Toast.makeText(getApplicationContext(), "Tip Percentage Must Be 100 or Less.", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(Double.parseDouble(editPercentage.getText().toString()) < 0){
                    Toast.makeText(getApplicationContext(), "Tip Percentage Must Be 0 or More.", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(Integer.parseInt(editNum.getText().toString()) <= 0){
                    Toast.makeText(getApplicationContext(), "Number of people must be larger than 0.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(Integer.parseInt(editNum.getText().toString()) > 300){
                    Toast.makeText(getApplicationContext(), "Number of people must be less than or equal to 300.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if( editNum.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter the Number of People", Toast.LENGTH_SHORT).show();
                    return;
                }

                else{

                    bill.setBillAmount(Double.parseDouble(editBillAmount.getText().toString().trim()));
                    bill.setTipPercentage(Double.parseDouble(editPercentage.getText().toString().trim()));
                    bill.setNumberOfPeople(Integer.parseInt(editNum.getText().toString().trim()));

                    bill.calculateTipAmount();
                    bill.calculateTotalAmount();
                    bill.calculateTipPerPerson();
                    bill.calculateEachPersonPaysResult();
                    Intent summaryScreen = new Intent(MainActivity.this, SummaryScreen.class);
                    startActivity(summaryScreen);
                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void goToSetting(MenuItem menuItem){
        Intent newActivity = new Intent(MainActivity.this, Setting.class);
        startActivity(newActivity);
    }
    public void goToSuggestATip(MenuItem menuItem){
        Intent newActivity = new Intent(MainActivity.this, SuggestATip.class);
        startActivity(newActivity);
    }

}
