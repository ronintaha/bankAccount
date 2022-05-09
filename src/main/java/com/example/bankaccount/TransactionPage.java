package com.example.bankaccount;

public class TransactionPage {
    private String srsAccNum;
    private String desAccnum;
    private String Amount;
    private String type;

    public String getSrsAccNum() {
        return srsAccNum;
    }

    public void setSrsAccNum(String srsAccNum) {
        this.srsAccNum = srsAccNum;
    }

    public String getDesAccnum() {
        return desAccnum;
    }

    public void setDesAccnum(String desAccnum) {
        this.desAccnum = desAccnum;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
