package Octagon;

public class userData {
    String username;
    double credits;
    String cashierName;

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public double getCredits() {
        return credits;
    }
}
