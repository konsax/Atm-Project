import java.util.Scanner;

public class Transfer  {
    int cardId;
    double tranferValue;
    String aitiologia;


    public Transfer(int cardId, double tranferValue, String aitiologia) {
        this.cardId = cardId;
        this.tranferValue = tranferValue;
        this.aitiologia = aitiologia;
    }

    public Transfer() {
    }

    public void setAitiologia(String aitiologia) {
        this.aitiologia = aitiologia;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setTranferValue(double tranferValue) {
        this.tranferValue = tranferValue;
    }

    public double getTranferValue() {
        return tranferValue;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "cardId=" + cardId +
                ",tranferValue=" + String.format("%.2f",tranferValue) +
                ",aitiologia='" + aitiologia + '\'' +
                '}';
    }
}


