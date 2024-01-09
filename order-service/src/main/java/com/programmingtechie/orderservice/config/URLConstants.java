package com.programmingtechie.orderservice.config;

public enum URLConstants {
    INVENTORYENDPOINTS("http://INVENTORY-SERVICE/api/inventorys");
    private final String endPointURL;
    URLConstants (String endPointURL) {
        this.endPointURL = endPointURL;
    }

    public String getEndPointURL() {
        return endPointURL;
    }

}
