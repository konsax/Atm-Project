public abstract class Bank_Product {
    //Attributes
    protected static int kodikos_proiontos = -1;
    protected int afm; //afm pelati
    protected int customer_num; //arithmos pelati
    protected int product_id; //kodikos proiontos

    //constructor
    public Bank_Product(int afm, int customer_code) {
        kodikos_proiontos++;
        this.afm = afm;
        this.customer_num = customer_code;
        this.product_id = kodikos_proiontos;
    }

    //default constructor gia tin reader
    public Bank_Product() {
    }

    public void setAfm(int afm) {
        this.afm = afm;
    }

    public void setCustomer_num(int customer_num) {
        this.customer_num = customer_num;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
