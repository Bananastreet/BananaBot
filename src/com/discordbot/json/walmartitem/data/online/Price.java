package com.discordbot.json.walmartitem.data.online;

public class Price {
    private float priceInCents;
    private String currencyUnit;
    private boolean showInCart;

    public float getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(float priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public boolean isShowInCart() {
        return showInCart;
    }

    public void setShowInCart(boolean showInCart) {
        this.showInCart = showInCart;
    }

}
