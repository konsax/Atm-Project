public class Sale {
//Attributes
int salesmanId;
int productId;
String aitiologia;
String Type;
static int count=-1;
public Sale(int salesmanId, int productId, String aitiologia,String Type){
    this.productId=productId;
    this.aitiologia=aitiologia;
    this.salesmanId=salesmanId;
    this.Type=Type;
    count++;
}

    public Sale() {
    }

    public void setType(String type) {
        Type = type;
    }

    public void setAitiologia(String aitiologia) {
        this.aitiologia = aitiologia;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getType() {
        return Type;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "salesmanId=" + salesmanId +
                ", productId=" + productId +
                ", aitiologia='" + aitiologia + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
