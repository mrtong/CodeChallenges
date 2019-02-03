package com.foo.amro.pojo;

import java.util.StringJoiner;

public class FutureTransactions implements java.io.Serializable {
    public boolean isEmptyTransaction() {
        return isEmptyTransaction;
    }

    public void setEmptyTransaction(boolean emptyTransaction) {
        isEmptyTransaction = emptyTransaction;
    }

    public FutureTransactions(
            final String clientType,
            final String clientNumber,
            final String accountNumber,
            final String subAccountNumber,
            final String exchangeCode,
            final String productGroupCode,
            final String symbol,
            final String expireDate,
            final String quantityLong,
            final String quantityShort) {
        this.clientType = clientType;
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.subAccountNumber = subAccountNumber;
        this.exchangeCode = exchangeCode;
        this.productGroupCode = productGroupCode;
        this.symbol = symbol;
        this.exchangeCode = exchangeCode;
        this.expireDate = expireDate;
        this.quantityLong = quantityLong;
        this.quantityShort = quantityShort;

    }

    public FutureTransactions() {
    }

    public String getClientType() {
        return clientType;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getQuantityLong() {
        return quantityLong;
    }

    public String getQuantityShort() {
        return quantityShort;
    }

    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String exchangeCode;
    private String productGroupCode;
    private String symbol;
    private String expireDate;
    private String quantityLong;
    private String quantityShort;
    private boolean isEmptyTransaction;

    @Override
    public String toString(){
        StringJoiner sj = new StringJoiner(",");
        sj.add(clientType);
        sj.add(clientNumber);
        sj.add(accountNumber);
        sj.add(subAccountNumber);
        sj.add(exchangeCode);
        sj.add(productGroupCode);
        sj.add(symbol);
        sj.add(expireDate);
        sj.add(quantityLong);
        sj.add(quantityShort);

        return sj.toString();
    }

    public static final class Builder {
        private String clientType;
        private String clientNumber;
        private String accountNumber;
        private String subAccountNumber;
        private String exchangeCode;
        private String productGroupCode;
        private String symbol;
        private String expireDate;
        private String quantityLong;
        private String quantityShort;

        public Builder withClientType(final String clientType){
            this.clientType=clientType;
            return this;
        }

        public Builder withClientNumber(final String clientNumber){
            this.clientNumber = clientNumber;
            return this;
        }
        public Builder withAccountNumber(final String accountNumber){
            this.accountNumber=accountNumber;
            return this;
        }
        public Builder withSubAccountNumber(final String subAccountNumber){
            this.subAccountNumber=subAccountNumber;
            return this;
        }
        public Builder withExchangeCode(final String exchangeCode){
            this.exchangeCode=exchangeCode;
            return this;
        }
        public Builder withProductGroupCode(final String productGroupCode){
            this.productGroupCode=productGroupCode;
            return this;
        }
        public Builder withSymbol(final String symbol){
            this.symbol=symbol;
            return this;
        }
        public Builder withExpireDate(final String expireDate){
            this.expireDate=expireDate;
            return this;
        }
        public Builder withQuantityLong(final String quantityLong){
            this.quantityLong=quantityLong;
            return this;
        }
        public Builder withQuantityShort(final String quantityShort){
            this.quantityShort=quantityShort;
            return this;
        }

        public FutureTransactions build() {
            return new FutureTransactions(
                    this.clientType,
                    this.clientNumber,
                    this.accountNumber,
                    this.subAccountNumber,
                    this.exchangeCode,
                    this.productGroupCode,
                    this.symbol,
                    this.expireDate,
                    this.quantityLong,
                    this.quantityShort
            );
        }
    }
}
