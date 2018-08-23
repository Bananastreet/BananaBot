package com.discordbot.json.walmartitem.data.common;

public class ProductId {

    private String wwwItemId;
    private String wupc;
    private String upca;
    private String productId;
    private String ean13;

    public String getWwwItemId() {
        return wwwItemId;
    }

    public void setWwwItemId(String wwwItemId) {
        this.wwwItemId = wwwItemId;
    }

    public String getWupc() {
        return wupc;
    }

    public void setWupc(String wupc) {
        this.wupc = wupc;
    }

    public String getUpca() {
        return upca;
    }

    public void setUpca(String upca) {
        this.upca = upca;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }
}
