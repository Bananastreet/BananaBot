package com.discordbot.json.walmartitem;

import com.discordbot.json.walmartitem.data.Common;
import com.discordbot.json.walmartitem.data.Online;
import com.discordbot.json.walmartitem.data.RelatedItemURLs;

public class Data {

    private Common common;
    private Online online;
    private RelatedItemURLs relatedItemURLs;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Online getOnline() {
        return online;
    }

    public void setOnline(Online online) {
        this.online = online;
    }

    public RelatedItemURLs getRelatedItemURLs() {
        return relatedItemURLs;
    }

    public void setRelatedItemURLs(RelatedItemURLs relatedItemURLs) {
        this.relatedItemURLs = relatedItemURLs;
    }

}
