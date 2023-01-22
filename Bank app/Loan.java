public class Loan extends Bank_Product {
    //Attributes
    protected double loan_amount;
    protected double interest_rate;
    static double totalLoanValue=0;
    static double totalLoanInterest=0;
    double loanCommision=0;
    String descr="none";//description
    //Constuctors
    public Loan(int afm, int customer_num, double loan_amount, double interest_rate){
    super(afm, customer_num);
    this.loan_amount=loan_amount;
    this.interest_rate=interest_rate;
    }

    public Loan() {
        super();
    }

    public void setLoan_amount(double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double calculateInterest(){
        return loan_amount * interest_rate;
    }

    public double getLoan_amount() {
        return loan_amount;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public static void setTotalLoanValue() {
        Loan.totalLoanValue = 0;
    }

    public static void setTotalLoanInterest() {
        Loan.totalLoanInterest = 0;
    }

    //upologismos sunolou daneiwn pwliti-gia na upologisoume meta tin promitheia
    public void calculateLoanValue(double loan){
        this.totalLoanValue +=loan;
    }

    //upologismos sunolou twn promithiwn apo daneia(trapezas)
    public void totalLoanInterest(double interest){
        this.totalLoanInterest += interest;
    }

    public static double getTotalLoanInterest() {
        return totalLoanInterest;
    }

    public static double getTotalLoanValue() {
        return totalLoanValue;
    }


    @Override
    public String toString() {
        return "Loans{" +
                "Descr"+ descr+
                "afm=" + afm +
                ", customer_num=" + customer_num +
                ", product_id=" + product_id +
                ", loan_amount=" + String.format("%.2f",loan_amount) +
                ", interest_rate=" + String.format("%.2f",interest_rate*100) +"%"+
                '}';
    }

}
