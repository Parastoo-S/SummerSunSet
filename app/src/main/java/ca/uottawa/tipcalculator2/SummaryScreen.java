package ca.uottawa.tipcalculator2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryScreen extends AppCompatActivity {

    TextView billAmountResult;
    TextView tipAmountResult;
    TextView totalAmountResult;
    TextView tipPerPersonResult;
    TextView eachPersonPaysResult;
    Button done;
    Bill bill = Bill.getInstance();
    Rating rating = Rating.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        billAmountResult = (TextView) findViewById(R.id.billAmountResult);
        tipAmountResult = (TextView) findViewById(R.id.tipAmountResult);
        totalAmountResult = (TextView) findViewById(R.id.totalAmountResult);
        tipPerPersonResult = (TextView) findViewById(R.id.tipPerPersonResult);
        eachPersonPaysResult = (TextView) findViewById(R.id.eachPersonPaysResult);
        done = (Button) findViewById(R.id.done);


        billAmountResult.setText(bill.getCurrencySymbol() + " " +Double.toString(bill.getBillAmount()));
        tipAmountResult.setText(bill.getCurrencySymbol() + " " +Double.toString(bill.getTipAmount()));
        totalAmountResult.setText(bill.getCurrencySymbol() + " " +Double.toString(bill.getTotalAmount()));
        tipPerPersonResult.setText(bill.getCurrencySymbol() + " " +Double.toString(bill.getTipPerPerson()));
        eachPersonPaysResult.setText(bill.getCurrencySymbol() + " " +Double.toString(bill.getEachPersonPaysResult()));

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating.setRate(0.0);
                rating.resetSuggestedTipPercentage();
                bill.setTipPercentage(0.0);
                Intent backToMainPage = new Intent(SummaryScreen.this, MainActivity.class);
                startActivity(backToMainPage);
            }
        });



    }





}
