package Models.Request;

import org.testng.reporters.AbstractXmlReporter;

public class TrxnRequest {
    private String id;
    private String orderId;
    private int amount;
    private String currency;
    private String status;
    private String processedAt;

    public TrxnRequest(String id, String orderId, int amount, String currency, String status, String processedAt) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.processedAt = processedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }
    // Builder Design Pattern: It avoids messy constructors with many parameters and instead provides a fluent interface for building requests.
    public static class Builder{
        private String id;
        private String orderId;
        private int amount;
        private String currency;
        private String status;
        private String processedAt;

        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder orderId(String orderId){
            this.orderId = orderId;
            return this;
        }
        public Builder amount(int amount){
            this.amount = amount;
            return this;
        }
        public Builder currency(String currency){
            this.currency = currency;
            return this;
        }
        public Builder status(String status){
            this.status = status;
            return this;
        }
        public Builder processedAt(String processedAt){
            this.processedAt = processedAt;
            return this;
        }
        public TrxnRequest build(){
            TrxnRequest trxnRequest = new TrxnRequest(id,orderId,amount,currency,status,processedAt);
            return trxnRequest;
        }
    }
}
