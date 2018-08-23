package com.discordbot.json.walmartitem.data;

import com.discordbot.json.walmartitem.data.common.CustomerRating;
import com.discordbot.json.walmartitem.data.common.ProductId;

public class Common {
    private ProductId productId;
    private String name;
    private String department;
    private String thumbnailImageUrl;
    private String productImageUrl;
    private CustomerRating customerRating;
    private String productUrl;
    //variantSwatches
    private String offerType;
    private boolean twoDayShippingEligible;
    private boolean storePickupAvailable;

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public CustomerRating getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(CustomerRating customerRating) {
        this.customerRating = customerRating;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public boolean isTwoDayShippingEligible() {
        return twoDayShippingEligible;
    }

    public void setTwoDayShippingEligible(boolean twoDayShippingEligible) {
        this.twoDayShippingEligible = twoDayShippingEligible;
    }

    public boolean isStorePickupAvailable() {
        return storePickupAvailable;
    }

    public void setStorePickupAvailable(boolean storePickupAvailable) {
        this.storePickupAvailable = storePickupAvailable;
    }

}
