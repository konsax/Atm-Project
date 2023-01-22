//Team 61
//Members: D2180215, D2180203, P3210175

import java.io.*;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class mainApp {
    //PROMITHIES
    public static void main(String[] args) {
        //ylopoiisi listwn arraylist
        ArrayList<Transfer> transfers = new ArrayList<Transfer>();
        ArrayList<Salesman> salesmen = new ArrayList<Salesman>();
        ArrayList<Bank_Product> bank_products = new ArrayList<Bank_Product>();
        ArrayList<Sale> sales = new ArrayList<Sale>();
        //
        ReadFileBankProduct("BANKITEM_LIST.txt", bank_products);
        ReadFileSalesman("SALESMAN_LIST.txt",salesmen);
        ReadFileSales("SALES_LIST.txt",sales);
        ReadTransferFile("TRN_LIST.txt",transfers);

        /*showLoans(bank_products);
        showCreditCards(bank_products);
        showSalesmen(salesmen);
        showSales(sales);
        showTransfers(transfers);*/

        Menu(sales, salesmen, bank_products, transfers);

        //ta writers einai epilogi sto menu(san saving option)
    }

    public static void Menu(ArrayList<Sale> sales, ArrayList<Salesman> salesmen, ArrayList<Bank_Product> bank_products, ArrayList<Transfer> transfers) {
        for (; ; ) {
            //
            int key;
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("\nPlease type one of the following numbers to select an action:");
                System.out.println("1 Add Salesman");
                System.out.println("2 Add Bank Product");
                System.out.println("3 Add Sale");
                System.out.println("4 Add Transfer");
                System.out.println("5 Show Loans");
                System.out.println("6 Salesman Commission");
                System.out.println("7 Salesman Transfers");
                System.out.println("8 Calculate All Salesmen Commissions");
                System.out.println("9 Show All Salesmen Commissions");
                System.out.println("10 Save Transfer and Sale entries");
                System.out.println("0 Exit\n");
                key = sc.nextInt();


            } while (key < 0 || key > 10);
            //
            //

            if (key == 1) {
                insert_salesman(salesmen);
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 2) {
                int Type;
                do {
                    Scanner type = new Scanner(System.in);
                    System.out.println("Type '1' if bank product is a loan\nType '2' if bank product is a credit card");
                    Type = type.nextInt();
                } while (Type < 0 || Type > 2);
                if (Type == 0) Menu(sales, salesmen, bank_products, transfers);
                insert_bankproducts(Type, bank_products);
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 3) {
                int Salepick;
                do {
                    showSalesmen(salesmen);
                    Scanner pick1 = new Scanner(System.in);
                    System.out.println("Pick salesman");
                    Salepick = pick1.nextInt();
                } while (Salepick > salesmen.size() - 1 || Salepick < -1);
                if (Salepick == -1) Menu(sales, salesmen, bank_products, transfers);
                insert_sale(Salepick, sales, bank_products);
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 4) {
                int Cardpick;
                do {
                    showCreditCards(bank_products);
                    Scanner pick3 = new Scanner(System.in);
                    System.out.println("Pick the credit card");
                    Cardpick = pick3.nextInt();
                } while ((Cardpick > bank_products.size() - 1 || Cardpick < -1) || bank_products.get(Cardpick) instanceof Loan);
                if (Cardpick == -1) Menu(sales, salesmen, bank_products, transfers);
                insert_transfer(Cardpick, transfers, bank_products);
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 5) {
                showLoans(bank_products);
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 6) {
                int k = 0;
                showSalesmen(salesmen);
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Give Salesman's ID to calculate his commissions");
                do {
                    key = sc1.nextInt();
                    if (k > salesmen.size() - 1 || k < -1) System.out.println("You gave invalid id.Try again!!");
                } while (k > salesmen.size() - 1 || k < -1); //epalitheusi timis
                if (k == -1) Menu(sales, salesmen, bank_products, transfers);

                calculateSalesmansCommission(key, bank_products, sales, transfers);

                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 7) {
                showSalesmansTransfers(transfers, sales, salesmen);
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 8) {
                for (int j = 0; j <= salesmen.size() - 1; j++) {
                    calculateSalesmansCommission(j, bank_products, sales, transfers);
                }
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if (key == 9) {
                show_allSalesmansCommission(salesmen, bank_products, sales, transfers);
                System.out.println("\nAction Completed. Returning to Menu.\n");
            } else if(key==10){
                SalesWriter("SALES_LIST.txt",sales);
                TransfersWriter("TRN_LIST.txt",transfers);
            }
            else break;
        }
    }

    public static void insert_sale(int Salepick, ArrayList<Sale> sales, ArrayList<Bank_Product> bank_products) {
        String Type="";
        int Prodpick;
        do {
            //print products
            Scanner pick2 = new Scanner(System.in);
            System.out.println("Pick the bank product");
            Prodpick = pick2.nextInt();
        } while (Prodpick <= bank_products.size() - 1 && Prodpick < 0);
        Scanner prdctsale = new Scanner(System.in);
        System.out.println("Enter Reason");
        String Reason = prdctsale.nextLine();
        if(bank_products.get(Prodpick)instanceof Loan){
            Type= "Loan";
        }else if(bank_products.get(Prodpick)instanceof CreditCard){
            Type= "Card";
        }
        sales.add(new Sale(Salepick, Prodpick, Reason,Type));
    }

    public static void insert_transfer(int Cardpick, ArrayList<Transfer> tranfers, ArrayList<Bank_Product> bank_products) {

        Scanner trnsfr = new Scanner(System.in);
        Scanner tr=new Scanner(System.in);
        System.out.println("Enter tranfer value ");
        double Transfer_Value = trnsfr.nextDouble();
        System.out.println("Enter reason ");
        String Reason = tr.nextLine();
        validateTransfer(new Transfer(Cardpick, Transfer_Value, Reason), (CreditCard) bank_products.get(Cardpick), tranfers);
    }

    public static void insert_salesman(ArrayList<Salesman> salesmen) {
        Scanner salesman_credentials = new Scanner(System.in);
        System.out.println("Enter Surname,First Name,AFM");
        String Surname = salesman_credentials.nextLine();
        String FirstName = salesman_credentials.nextLine();
        int AFM = salesman_credentials.nextInt();
        salesmen.add(new Salesman(FirstName, Surname, AFM));
    }

    public static void insert_bankproducts(int Type, ArrayList<Bank_Product> bank_products) {

        if (Type == 1) {
            Scanner loan = new Scanner(System.in);
            System.out.println("Enter Customer Number,AFM,Loan Amount,Interest Rate");
            int CustomerNumber = loan.nextInt();
            int AFM = loan.nextInt();
            double LoanAmount = loan.nextDouble();
            double InterestRate = loan.nextDouble() / 100;
            bank_products.add(new Loan(CustomerNumber, AFM, LoanAmount, InterestRate));
        } else if (Type == 2) {
            Scanner crdtcard = new Scanner(System.in);
            System.out.println("Enter Customer Number,AFM,Commission Rate,Max Transfer Amount,Max Yearly Amount");
            int CustomerNumber = crdtcard.nextInt();
            int AFM = crdtcard.nextInt();
            double Com_Rate = crdtcard.nextDouble() / 100;
            double Max_transfer = crdtcard.nextDouble();
            double Max_year = crdtcard.nextDouble();
            bank_products.add(new CreditCard(AFM, CustomerNumber, Com_Rate, Max_transfer, Max_year));
        }
    }

    //
    public static void showTransfers(ArrayList<Transfer> transfers) {
        for (int i = 0; i <= transfers.size() - 1; i++) {
            System.out.println(transfers.get(i).toString());
        }
    }


    //
    public static void showLoans(ArrayList<Bank_Product> bank_products) {
        for (int i = 0; i <= bank_products.size() - 1; i++) {
            if (bank_products.get(i) instanceof Loan)
                System.out.println(bank_products.get(i).toString());

        }
    }

    //
    public static void showCreditCards(ArrayList<Bank_Product> bank_products) {
        for (int i = 0; i <= bank_products.size() - 1; i++) {
            if (bank_products.get(i) instanceof CreditCard)
                System.out.println(bank_products.get(i).toString());
        }
    }
    public static void showSales(ArrayList<Sale> sales) {
        for (int i = 0; i <= sales.size() - 1; i++) {
                System.out.println(sales.get(i).toString());
        }
    }

    //
    public static void showSalesmen(ArrayList<Salesman> salesmen) {
        for (int i = 0; i <= salesmen.size() - 1; i++) {
            System.out.println(salesmen.get(i).toString());
        }
    }
    //

    //
    public static void validateTransfer(Transfer transfer, CreditCard creditCards, ArrayList<Transfer> transfers) {
        if (transfer.tranferValue <= creditCards.MaxTranferAmount && transfer.tranferValue
                + creditCards.getTotalTransferedAmount() <= creditCards.YearlyMaxAmount) {
            creditCards.setTotalTransferedAmount(transfer.tranferValue);
            System.out.println("Transaction occured successfully");
            transfers.add(transfer);
        } else {
            System.out.println("Transaction failed");
        }
    }

    public static void showSalesmansTransfers(ArrayList<Transfer> transfers, ArrayList<Sale> sales, ArrayList<Salesman> salesmen) {
        int id;
        Scanner sc = new Scanner(System.in);
        showSalesmen(salesmen);
        System.out.println("Enter salesman's ID number to show his transfers");
        id = sc.nextInt();
        for (int i = 0; i <= sales.size() - 1; i++) {
            for (int j = 0; j <= transfers.size() - 1; j++) {
                if (id == sales.get(i).salesmanId && sales.get(i).productId == transfers.get(j).cardId) {
                    System.out.println(transfers.get(j).toString());
                }
            }
        }
    }


    public static void calculateSalesmansCommission(int key, ArrayList<Bank_Product> bank_products, ArrayList<Sale> sales, ArrayList<Transfer> transfers) {
        //attributes
        double loan_value;
        double product_commission;
        double SalesmanloanCommision;
        double card_transfers = 0;
        double credit_card_commissions;
        double total_creditcard_commissions = 0;
        double total_salesman_commision;
        //midenizei tis metavlites
        Loan.setTotalLoanInterest();
        Loan.setTotalLoanValue();
        CreditCard.totalTransferedAmount = 0;


        //commission calculation for loans + emfanisi
        for (int i = 0; i <= sales.size() - 1; i++) {
            if (key == sales.get(i).salesmanId) {
                if (bank_products.get(sales.get(i).productId) instanceof Loan) {

                    product_commission = ((Loan) bank_products.get(sales.get(i).productId)).calculateInterest();
                    loan_value = ((Loan) bank_products.get(sales.get(i).productId)).getLoan_amount();

                    System.out.printf("Loan Value: %.2f,Loan Interest: %.2f", loan_value, product_commission);
                    System.out.println();

                    //upologizei to sunolo twn daneiwn
                    ((Loan) bank_products.get(sales.get(i).productId)).calculateLoanValue(loan_value);
                    //upologizei to sunolo twn tokwn
                    ((Loan) bank_products.get(sales.get(i).productId)).totalLoanInterest(product_commission);
                } else {
                    //upologismos sunolou aksias twn sunallagwn tis mias kartas apo tis sunallages
                    for (int j = 0; j <= transfers.size() - 1; j++) {
                        if (sales.get(i).productId == transfers.get(j).cardId) {
                            card_transfers += transfers.get(j).tranferValue;
                        }
                    }
                    //kanei set stin credit card to sunoliko transfered amount tis kartas(gia na fenetai se toString se periptosi pou xreiastei na emfanisoume tin karta)
                    ((CreditCard) bank_products.get(sales.get(i).productId)).setTotalTransferedAmount(card_transfers);
                    //

                    credit_card_commissions = card_transfers * ((CreditCard) bank_products.get(sales.get(i).productId)).getCc_CommissionRate();//upologismos promitheias mias kartas
                    total_creditcard_commissions += card_transfers * ((CreditCard) bank_products.get(sales.get(i).productId)).getCc_CommissionRate();//upologismos sunolou promithiwn kartwn

                    //emfanisi tou sunolou twn sunallagwn kai tis promitheias tou pwliti(gia mia karta)
                    System.out.printf("Card's %d transfered amount is: %.2f %n", sales.get(i).productId, card_transfers);
                    System.out.printf("Card's %d commission amount is: %.2f %n", sales.get(i).productId, credit_card_commissions);
                }
            }
        }
        //Ypologismos meridiou tou pwliti aptin promitheia
        if (Loan.getTotalLoanValue() <= 500000) {
            SalesmanloanCommision = 0.01 * Loan.getTotalLoanValue();
        } else if (Loan.getTotalLoanValue() <= 2000000) {
            SalesmanloanCommision = 0.02 * Loan.getTotalLoanValue();
        } else {
            SalesmanloanCommision = 0.025 * Loan.getTotalLoanValue();
        }

        if (SalesmanloanCommision > Loan.getTotalLoanValue())
            SalesmanloanCommision = Loan.getTotalLoanValue();


        System.out.println();
        System.out.printf("Salesman's Total loan amount is:%.2f %n", Loan.getTotalLoanValue());
        System.out.printf("Salesman's Loan commission is:%.2f %n", SalesmanloanCommision);
        System.out.printf("Salesman's Credit Card commission is:%.2f %n", total_creditcard_commissions);
        total_salesman_commision = SalesmanloanCommision + total_creditcard_commissions;
        System.out.println();
        System.out.printf("Salesman's %d Total commission is:%.2f %n", sales.get(key).productId, total_salesman_commision);
        System.out.println("------------------------------------------");
    }

    public static void show_allSalesmansCommission(ArrayList<Salesman> salesmen, ArrayList<Bank_Product> bank_products, ArrayList<Sale> sales, ArrayList<Transfer> transfers) {
        double loan_value;
        double product_commission;
        double SalesmanloanCommision;
        double card_transfers = 0;
        double total_creditcard_commissions = 0;
        double total_salesman_commision;

        double Sum_commissions = 0;
        //midenizei tis metavlites
        Loan.setTotalLoanInterest();
        Loan.setTotalLoanValue();
        CreditCard.totalTransferedAmount = 0;

        for (int key = 0; key <= salesmen.size() - 1; key++) {
            for (int i = 0; i <= sales.size() - 1; i++) {
                if (key == sales.get(i).salesmanId) {
                    if (bank_products.get(sales.get(i).productId) instanceof Loan) {

                        product_commission = ((Loan) bank_products.get(sales.get(i).productId)).calculateInterest();
                        loan_value = ((Loan) bank_products.get(sales.get(i).productId)).getLoan_amount();
                        //upologizei to sunolo twn daneiwn
                        ((Loan) bank_products.get(sales.get(i).productId)).calculateLoanValue(loan_value);
                        //upologizei to sunolo twn tokwn
                        ((Loan) bank_products.get(sales.get(i).productId)).totalLoanInterest(product_commission);
                    } else {
                        //upologismos sunolou aksias twn sunallagwn tis mias kartas apo tis sunallages
                        for (int j = 0; j <= transfers.size() - 1; j++) {
                            if (sales.get(i).productId == transfers.get(j).cardId) {
                                card_transfers += transfers.get(j).tranferValue;
                            }
                        }
                        total_creditcard_commissions += card_transfers * ((CreditCard) bank_products.get(sales.get(i).productId)).getCc_CommissionRate();//upologismos sunolou promithiwn kartwn
                    }
                }
            }
            //Ypologismos meridiou tou pwliti aptin promitheia
            if (Loan.getTotalLoanValue() <= 500000) {
                SalesmanloanCommision = 0.01 * Loan.getTotalLoanValue();
            } else if (Loan.getTotalLoanValue() <= 2000000) {
                SalesmanloanCommision = 0.02 * Loan.getTotalLoanValue();
            } else {
                SalesmanloanCommision = 0.025 * Loan.getTotalLoanValue();
            }

            if (SalesmanloanCommision > Loan.getTotalLoanValue())
                SalesmanloanCommision = Loan.getTotalLoanValue();

            total_salesman_commision = SalesmanloanCommision + total_creditcard_commissions;
            System.out.printf("%s ,Total commission is:%.2f %n", salesmen.get(key).toString(), total_salesman_commision);
            Sum_commissions += total_salesman_commision;
        }
        System.out.println(Sum_commissions);
        System.out.println("------------------------------------------");

    }


    //Tropopoiiseis gia to B meros tis ergasias:
    // Allages se orismenes topikes metavlites kathws kai prosthiki orismenwn extra metavlitwn.
    // Dimiourgia extra setter stis alles klaseis.
    // Dimiourgia default constructor.

    public static void ReadFileBankProduct(String aFileName, ArrayList<Bank_Product> bank_products) {
        try {
            BufferedReader buff = new BufferedReader(new FileReader(aFileName));

            StringTokenizer lineTokens;
            String token;
            String line;
            String[] str;//pinakas gia tin methodo split

            String type=null;//local variable:typou trapezikou proiontos
            String descr =null;//local variable: description
            int code=-1;//local variable: kodikou proiontos
            double amount=0;//local variable: loan amount,transfer value
            int ssn=-1;//local variable:afm
            int cn=-1;//local variable: customer number
            float ir=0;//local variable:interest rate,commission rate
            double maxtr=0;//local variable:max transfer amount
            double yeartr=0;//local variable:yearly max transfer amount

            Bank_Product bankProduct = null;
            line = buff.readLine();
            while (line != null) {
                boolean l=false;
                boolean l2=false;
                boolean l3=false;
                boolean l4=false;
                //System.out.println(line);
                if (line.trim().toUpperCase().equals("BANKITEM")) {
                    //System.out.println(line);
                    line = buff.readLine(); //sto line mpainei {
                    if (line.trim().equals("{")) {
                            str =buff.readLine().trim().split(" ");
                            if (str[0].trim().toUpperCase().equals("TYPE")) {
                                token = str[1];
                                if (token.trim().toUpperCase().equals("LOAN")) {
                                    l=true;
                                    str = buff.readLine().trim().split(" ");
                                    while (!str[0].equals("}")) {
                                        //--> str[0]="CODE"
                                        if (str[0].toUpperCase().equals("CODE")) {
                                            //System.out.println(str[0] + " " + str[1]);
                                            code=Integer.parseInt(str[1]);
                                            l3=true;
                                        }

                                        //--> str[0]="DESCR"
                                        if (str[0].toUpperCase().equals("DESCR")) {
                                            //System.out.println(str[0] + " " + str[1] + str[2]);
                                            descr=(str[1] + " " + str[2]);
                                            l4=true;
                                        }
                                        if (str[0].toUpperCase().equals("LOAN_AMOUNT")) {
                                            //--> str[0]="LOAN AMOUNT"
                                            //System.out.println(str[0] + " " + str[1]);
                                            amount=Double.parseDouble(str[1]);
                                        }
                                        if (str[0].toUpperCase().equals("AFM")) {
                                            //--> str[0]="AFM"
                                           // System.out.println(str[0] + " " + str[1]);
                                            ssn=Integer.parseInt(str[1]);
                                        }
                                        if (str[0].toUpperCase().equals("C#")) {
                                            //--> str[0]="COSTUMER NUMBER"
                                            //System.out.println(str[0] + " " + str[1]);
                                            cn=Integer.parseInt(str[1]);
                                        }
                                        if (str[0].toUpperCase().equals("INTEREST_RATE")) {
                                            //--> str[0]="INTEREST RATE"
                                            //System.out.println(str[0] + " " + str[1]);
                                            ir=Float.parseFloat(str[1]);
                                        }

                                        str = buff.readLine().trim().split(" ");
                                    }
                                } else if (token.trim().toUpperCase().equals("CREDIT")) {
                                    l2=true;
                                    str = buff.readLine().trim().split(" ");
                                    while (!str[0].equals("}")) {
                                        //--> str[0]="CODE"

                                            if (str[0].toUpperCase().equals("CODE")) {
                                                //System.out.println(str[0] + " " + str[1]);
                                                code=Integer.parseInt(str[1]);
                                                l3 = true;
                                            }

                                            if (str[0].toUpperCase().equals("DESCR")) {
                                                //System.out.println(str[0] + " " + str[1] + str[2]);
                                                descr=str[1] + " " + str[2];
                                                l4 = true;
                                            }

                                            if (str[0].toUpperCase().equals("AFM")) {
                                                //--> str[0]="AFM"
                                                //System.out.println(str[0] + " " + str[1]);
                                                ssn=Integer.parseInt(str[1]);
                                            }

                                            if (str[0].toUpperCase().equals("C#")) {
                                                //--> str[0]="COSTUMER NUMBER"
                                                //System.out.println(str[0] + " " + str[1]);
                                                cn=Integer.parseInt(str[1]);
                                            }
                                            if (str[0].toUpperCase().equals("COMMISSION_RATE")) {
                                                //--> str[0]="COMMISSION_RATE"
                                                //System.out.println(str[0] + " " + str[1]);
                                                ir=Float.parseFloat(str[1]);
                                            }

                                            if (str[0].toUpperCase().equals("MAX_TRANSFER_AMOUNT")) {
                                                //--> str[0]="MAX_TRANSFER_AMOUNT"
                                                //System.out.println(str[0] + " " + str[1]);
                                                maxtr=Double.parseDouble(str[1]);
                                            }

                                            if (str[0].toUpperCase().equals("YEARLY_MAX_TRANSFER_AMOUNT")) {
                                                //--> str[0]="YEARLY_MAX_TRANSFER_AMOUNT"
                                                //System.out.println(str[0] + " " +str[1]);
                                                yeartr=Double.parseDouble(str[1]);
                                            }

                                        str = buff.readLine().trim().split(" ");
                                    }
                                }
                            }
                            if(l && l3 && l4){
                            Loan ln=new Loan();
                            ln.setDescr(descr);//setter gia perigrafi
                            ln.setAfm(ssn);//setter gia afm
                            ln.setProduct_id(code);
                            ln.setCustomer_num(cn);
                            ln.setInterest_rate(ir);
                            ln.setLoan_amount(amount);
                            bank_products.add(ln);

                            }else if(l2 && l3 && l4){
                            CreditCard cr=new CreditCard();
                            cr.setDescr(descr);
                            cr.setProduct_id(code);
                            cr.setAfm(ssn);
                            cr.setCustomer_num(cn);
                            cr.setCc_CommisionRate(ir);
                            cr.setMaxTranferAmount(maxtr);
                            cr.setYearlyMaxAmount(yeartr);
                            bank_products.add(cr);
                        }else{System.out.println("Error: Critical entry missing ");}

                    }
                }
                //System.out.println(token);
                line = buff.readLine();

            }//while
            buff.close();
        } catch (IOException e) {
            System.out.println("File ERROR");
        }//try-catch
        catch (NumberFormatException l){
            System.out.println("Error: Wrong input. Please check the data entries!!");
        }catch (ArrayIndexOutOfBoundsException a){
            System.out.println("Error: Missing input (Delete attribute's tag or enter correct input to fix this.)");
        }
    }

    public static void ReadFileSalesman(String SALESMAN_LIST, ArrayList<Salesman> salesmen) {
            try {
                BufferedReader buff = new BufferedReader(new FileReader(SALESMAN_LIST));
                Salesman salesman = null;
                String line;
                String[] str;
                int s1 = 0;
                String s2 = "";
                String s3 = "";
                int s4 = 0;
                line= buff.readLine();
                while(line!=null) {
                        if (line.trim().toUpperCase().equals("SALESMAN")) {
                            //System.out.println(line);
                            line = buff.readLine(); //sto line mpainei {
                            if (line.trim().equals("{")) {
                                str = buff.readLine().trim().split(" ");
                                boolean l1 = false;
                                boolean l2 = false;
                                boolean l3 = false;
                                while (!str[0].equals("}")) {
                                    //--> str[0]="CODE"
                                    if (str[0].toUpperCase().equals("CODE")) {
                                       // System.out.println(str[0] + " " + str[1]);
                                        s1 = Integer.parseInt(str[1]);
                                        l1 = true;
                                    } else if (str[0].toUpperCase().equals("SURNAME")) {
                                       // System.out.println(str[0] + " " + str[1]);
                                        s2 = str[1];
                                        l2 = true;
                                    } else if (str[0].toUpperCase().equals("FIRSTNAME")) {
                                       // System.out.println(str[0] + " " + str[1]);
                                        s3 = str[1];
                                        l3 = true;
                                    } else if (str[0].toUpperCase().equals("AFM")) {
                                       // System.out.println(str[0] + " " + str[1]);
                                        s4 = Integer.parseInt(str[1]);
                                    }
                                    str = buff.readLine().trim().split(" ");
                                }

                                if (l1 && l2 && l3) {
                                    salesman = new Salesman();
                                    salesman.setSalesman_id(s1);
                                    salesman.setLastName(s2);
                                    salesman.setFirstName(s3);
                                    salesman.setAfm(s4);
                                    salesmen.add(salesman);
                                } else System.out.println("Error: Critical entry missing ");
                            }
                        }
                    line = buff.readLine();
                }
                buff.close();
                } catch(IOException e){
                    System.out.println("ERROR");
                }catch (NumberFormatException l){
                System.out.println("Error: Wrong input. Please check the data entries!!");
                }catch (ArrayIndexOutOfBoundsException a){
                System.out.println("Error: Missing input (Delete attribute's tag or enter correct input to fix this.)");
            }
        }

    public static void ReadFileSales(String SALES_LIST, ArrayList<Sale> sales) {
        try {
            BufferedReader buff = new BufferedReader(new FileReader(SALES_LIST));
            String line;
            String[] str;
            String s1 = "";
            int s2 = 0;
            int s3 = 0;
            line = buff.readLine();
            while (line != null) {
                if (line.trim().toUpperCase().equals("SALES")) {
                    //System.out.println(line);
                    line = buff.readLine(); //sto line mpainei {
                    if (line.trim().equals("{")) {
                        str = buff.readLine().trim().split(" ");
                        boolean l1 = false;
                        boolean l2 = false;
                        boolean l3 = false;
                        String s4="";
                        while (!str[0].equals("}")) {
                            //--> str[0]="CODE"
                            if (str[0].toUpperCase().equals("BANKITEM_TYPE")) {
                                //System.out.println(str[0] + " " + str[1]);
                                s1 = str[1];
                                l1 = true;
                            } else if (str[0].toUpperCase().equals("SALESMAN_CODE")) {
                                //System.out.println(str[0] + " " + str[1]);
                                s2 = Integer.parseInt(str[1]);
                                l2 = true;
                            } else if (str[0].toUpperCase().equals("BANKITEM_CODE")) {
                                //System.out.println(str[0] + " " + str[1]);
                                s3 = Integer.parseInt(str[1]);
                                l3 = true;
                            }else if (str[0].toUpperCase().equals("JUSTIFICATION")) {
                                    for (int i = 1; i <= str.length - 1;i++ ) {
                                        s4 =(s4+" " + str[i]).trim();
                                    }
                                }

                                str = buff.readLine().trim().split(" ");
                            }

                            if (l1 && l2 && l3) {
                                Sale s = new Sale();
                                s.setType(s1);
                                s.setAitiologia(s4);
                                s.setSalesmanId(s2);
                                s.setProductId(s3);
                                sales.add(s);
                            } else {
                                System.out.println("Error: Critical entry missing ");
                            }
                        }
                    }
                    line = buff.readLine();
                }
                buff.close();

            } catch(IOException e){
                System.out.println("ERROR");
            } catch(NumberFormatException l){
                System.out.println("Error: Wrong input. Please check the data entries!!");
            } catch(ArrayIndexOutOfBoundsException a){
                System.out.println("Error: Missing input (Delete attribute's tag or enter correct input to fix this.)");
            }
    }

    public static void ReadTransferFile(String TRN_LIST, ArrayList<Transfer> transfers) {
        try {
            BufferedReader buff = new BufferedReader(new FileReader(TRN_LIST));
            String line;
            String[] str;
            int t1 = 0;
            double t2 = 0;
            line = buff.readLine();
            while (line != null) {
            if (line.trim().toUpperCase().equals("TRN")) {
                line = buff.readLine();
                if (line.trim().equals("{")) {
                    boolean l1 = false;
                    boolean l2 = false;
                    str = buff.readLine().trim().split(" ");
                    String t3="";
                    while (!str[0].equals("}")) {
                        if (str[0].toUpperCase().equals("BANKITEM_CODE")) {
                            //System.out.println(str[0] + " " + str[1]);
                            t1 = Integer.parseInt(str[1]);
                            l1 = true;
                        } else if (str[0].toUpperCase().equals("VAL")) {
                            //System.out.println(str[0] + " " + str[1]);
                            t2 = Double.parseDouble(str[1]);
                            l2 = true;
                        } else if (str[0].toUpperCase().equals("JUSTIFICATION")) {
                            //System.out.println(str[0] + " " + str[1]);
                            for (int i = 1; i <= str.length - 1; i++) {
                                t3=(t3+" "+str[i]).trim();

                            }
                        }
                            str = buff.readLine().trim().split(" ");

                        }
                        if (l1 && l2) {
                            Transfer tr = new Transfer();
                            tr.setCardId(t1);
                            tr.setTranferValue(t2);
                            tr.setAitiologia(t3.toString());
                            transfers.add(tr);
                        } else {
                            System.out.println("Error: Critical entry missing ");
                        }
                    }

                }
                line = buff.readLine();
            }
                buff.close();
            } catch(IOException e){
                System.out.println("ERROR");
            } catch(NumberFormatException l){
            System.out.println("Error: Wrong input. Please check the data entries!!");
            } catch(ArrayIndexOutOfBoundsException a){
            System.out.println("Error: Missing input (Delete attribute's tag or enter correct input to fix this.)");
            }
        }

    public static void TransfersWriter(String TRN_LIST,ArrayList<Transfer> transfers){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TRN_LIST));
            writer.write("TRN_LIST\n{");
            for(int i=0;i<=transfers.size()-1 ;i++){
                writer.write("\nTRN\n{");
                writer.write("\nBANKITEM_CODE "+transfers.get(i).cardId);
                writer.write("\nVAL "+transfers.get(i).tranferValue);
                writer.write("\nJUSTIFICATION "+transfers.get(i).aitiologia);
                writer.write("\n}");
            }
            writer.write("\n}");
            writer.close();
            System.out.println("Transfer entries saved successfully.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void SalesWriter(String TRN_LIST,ArrayList<Sale> sales){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TRN_LIST));
            writer.write("SALES_LIST\n{");
            for(int i=0;i<=sales.size()-1 ;i++){
                writer.write("\nSALES\n{");
                writer.write("\nBANKITEM_CODE "+sales.get(i).productId);
                writer.write("\nSALESMAN_CODE "+sales.get(i).salesmanId);
                writer.write("\nJUSTIFICATION "+sales.get(i).aitiologia);
                writer.write("\nBANKITEM_TYPE "+sales.get(i).Type);
                writer.write("\n}");
            }
            writer.write("\n}");
            writer.close();
            System.out.println("Sale entries saved successfully.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}




