package ca.uottawa.tipcalculator2;

public class Rating {
    private static Rating instance;
    private static double rate;
    private static double suggestedTipPercentage;

    private Rating(){}

    public static synchronized Rating getInstance(){
        if (instance == null){
            instance = new Rating();
        }
        return instance;
    }
    public void setRate(double newRate){
        Rating.rate = newRate;
    }


    public void calculateTipPercentage(){
        suggestedTipPercentage = 10 + (rate * 2);
    }

    public double getSuggestedTipPercentage(){
        return Rating.suggestedTipPercentage;
    }

    public void resetSuggestedTipPercentage(){
        suggestedTipPercentage = 0.0;
    }



}
