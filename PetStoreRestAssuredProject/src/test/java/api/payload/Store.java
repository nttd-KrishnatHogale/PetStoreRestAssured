package api.payload;
import java.time.LocalDateTime; // import the LocalDateTime class


public class Store {
    int id;
    int petID;
    int quantity;
    LocalDateTime myObj;
    String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getMyObj() {
        return myObj;
    }

    public void setMyObj(LocalDateTime myObj) {
        this.myObj = myObj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    boolean complete;


}
