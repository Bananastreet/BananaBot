package com.discordbot.json.walmartitem.data;

import com.discordbot.json.walmartitem.data.online.Inventory;
import com.discordbot.json.walmartitem.data.online.Price;

public class Online {
    private Price price;
    private Inventory inventory;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


}
