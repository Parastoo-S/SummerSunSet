package ca.uottawa.tipcalculator2;

public class Bill {

    private static Bill instance;
    private static double billAmount;
    private static double tipPercentage;
    private static double tipAmount;
    private static int numOfPeople = 1;
    private static String currency = "Dollar ($)";
    private static double totalAmount;
    private static double tipPerPerson;
    private static double eachPersonPaysResult;
    private static String currencySymbol;
    private static int currencyPosition;
    private double defaultTipPercentage;
    private Bill(){}

    public static synchronized Bill getInstance(){
        if (instance == null){
            instance = new Bill();
        }
        return instance;
    }

    public void setBillAmount(double newAmount){
        Bill.billAmount = newAmount;
    }

    public double getBillAmount(){
        return Bill.billAmount;
    }

    public void setTipPercentage(double newTipPercentage){
        Bill.tipPercentage = newTipPercentage;
    }
//
    public double getTipPercentage(){
        return Bill.tipPercentage;
    }
//
//    public static void setTipAmount(double newTipAmount) {
//        Bill.tipAmount = newTipAmount;
//    }

    public double getTipAmount(){
        return Bill.tipAmount;
    }

    public double getTotalAmount(){
        return Bill.totalAmount;
    }

    public double getTipPerPerson(){
        return tipPerPerson;
    }

    public double getEachPersonPaysResult(){
        return eachPersonPaysResult;
    }
    public void setNumberOfPeople(int newNumOfPeople){
        Bill.numOfPeople = newNumOfPeople;
    }
    public int getNumberOfPeople(){
        return Bill.numOfPeople;
    }

    public void setCurrency(String newCurrency){
        Bill.currency = newCurrency;
    }

    public String getCurrency(){
        return Bill.currency;
    }

    public static void calculateTipAmount(){
        tipAmount = (Math.round(((Bill.tipPercentage / 100.0 ) * Bill.billAmount) * 100.0)/100.0);
    }

    public void calculateTotalAmount(){

        totalAmount = (Bill.billAmount + Bill.tipAmount);
    }

    public void calculateTipPerPerson(){
        tipPerPerson = (Math.round(((tipAmount / numOfPeople)*100.0))/100.0);
    }

    public void calculateEachPersonPaysResult(){
        eachPersonPaysResult = (Math.round(((Bill.totalAmount/ numOfPeople)*100.0))/100.0);
    }

    public void setDefaultTipPercentage(double newDefaultTip){
        defaultTipPercentage = newDefaultTip;
    }

    public double getDefaultTipPercentage() {
        return defaultTipPercentage;
    }

    public void setCurrencySymbol(){
        if(currency.equals("Dollar ($)")){
            currencySymbol = "$";
        }

        else if(currency.equals("Euro (€)")){
            currencySymbol = "€";
        }

        else{
            currencySymbol = "£";
        }
    }

    public String getCurrencySymbol(){
        return currencySymbol;
    }

    public int getCurrencyPosition(){
        if(Bill.currency == "Dollar ($)"){
            currencyPosition = 0;
        }

        else if(Bill.currency.equals("Euro (€)")){
            currencyPosition = 1;
        }

        else if(Bill.currency.equals("Pound (£)")){
            currencyPosition = 2;
        }

        else{
            currencyPosition = 0;
        }

        return currencyPosition;
    }

}
