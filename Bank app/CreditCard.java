public class CreditCard extends Bank_Product {
    //Attributes
    double Cc_CommisionRate; //promitheia epi tis kinisis gia credit cards
    double MaxTranferAmount; //megisto poso kinisis
    double YearlyMaxAmount; // Etisio megisto poso
    static double totalTransferedAmount;
    double cc_Commission;
    String descr="none";

    //Constuctors
    public CreditCard(int afm, int customer_num, double Cc_CommisionRate, double MaxTranferAmount, double YearlyMaxAmount){
        super(afm,customer_num);
        this.Cc_CommisionRate =Cc_CommisionRate;
        this.MaxTranferAmount =MaxTranferAmount;
        this.YearlyMaxAmount =YearlyMaxAmount;
    }

    public CreditCard() {
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }


    public double getCc_CommissionRate() {
        return Cc_CommisionRate;
    }

    public double getYearlyMaxAmount() {
        return YearlyMaxAmount;
    }

    public void setTotalTransferedAmount(double totalTransferedAmount) {
        CreditCard.totalTransferedAmount = totalTransferedAmount;
    }

    public double getTotalTransferedAmount() {
        return totalTransferedAmount;
    }

    public void setCc_CommisionRate(double cc_CommisionRate) {
        Cc_CommisionRate = cc_CommisionRate;
    }

    public void setYearlyMaxAmount(double yearlyMaxAmount) {
        YearlyMaxAmount = yearlyMaxAmount;
    }

    public void setMaxTranferAmount(double maxTranferAmount) {
        MaxTranferAmount = maxTranferAmount;
    }

    public void setCc_Commission(double cc_Commission) {
        this.cc_Commission = cc_Commission;
    }

    @Override
    public String toString() {
        return "CreditCards{" +
                "Description"+descr+
                "afm=" + afm +
                ", customer_num=" + customer_num +
                ", product_id=" + product_id +
                ", Cc_CommisionRate= " + String.format("%.2f",Cc_CommisionRate*100)+"%"+
                ", MaxTranferAmount=" + String.format("%.2f",MaxTranferAmount) +
                ", YearlyMaxAmount=" + String.format("%.2f",YearlyMaxAmount) +
                ", totalTransferedAmount=" + String.format("%.2f",totalTransferedAmount) +
                ", cc_Commission=" + cc_Commission +
                '}';
    }
}

