package ca.uottawa.tipcalculator2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SuggestATip extends AppCompatActivity {

    RatingBar ratingBar;
    Button calculate;
    TextView calculatedTip;
    Rating rating = Rating.getInstance();
    Bill bill = Bill.getInstance();
    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_atip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        calculate = (Button) findViewById(R.id.calculate);
        calculatedTip = (TextView) findViewById(R.id.calculatedTip);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ratingBar.getRating() == 0){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Rating", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    rating.setRate(ratingBar.getRating());
                    Toast.makeText(getApplicationContext(), "You rated " + Double.toString(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
                    rating.calculateTipPercentage();
                    calculatedTip.setText(Double.toString(rating.getSuggestedTipPercentage()));
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(calculatedTip.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "No Tip Calculated To Save.", Toast.LENGTH_SHORT).show();
                }

                else{
                    bill.setTipPercentage(rating.getSuggestedTipPercentage());

                    Toast.makeText(getApplicationContext(), "Suggested Tip Saved.", Toast.LENGTH_SHORT).show();
                    Intent backToMainPage = new Intent(SuggestATip.this, MainActivity.class);
                    startActivity(backToMainPage);

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Canceled.", Toast.LENGTH_SHORT).show();
                Intent backToMainPage = new Intent(SuggestATip.this, MainActivity.class);
                startActivity(backToMainPage);

            }
        });
    }

}
