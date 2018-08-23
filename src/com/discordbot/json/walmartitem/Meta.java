package com.discordbot.json.walmartitem;

public class Meta {

    private String pluginVersion;
    private boolean isExtruded;
    private String requestId;

    public String getPluginVersion() {
        return pluginVersion;
    }

    public void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public boolean isExtruded() {
        return isExtruded;
    }

    public void setExtruded(boolean extruded) {
        isExtruded = extruded;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
