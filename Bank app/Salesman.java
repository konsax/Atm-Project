public class Salesman {
    //Attributes
    private String firstName;
    private String lastName;
    private int salesman_id;
    private int afm;
    private static int id=-1;


    //Constructor
    Salesman(String firstName,String lastName,int afm){
        id++;
        this.salesman_id=id;
        this.firstName =firstName;
        this.lastName =lastName;
        this.afm =afm;
    }


    public Salesman() {
    }

    public void setAfm(int afm) {
        this.afm = afm;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalesman_id(int salesman_id) {
        this.salesman_id = salesman_id;
    }

    @Override
    public String toString() {
        return "key= "+ salesman_id + " ,First Name=" + firstName +" ,Last Name=" +lastName ;
    }
}
