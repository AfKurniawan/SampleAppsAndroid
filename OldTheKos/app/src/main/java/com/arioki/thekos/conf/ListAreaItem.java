package com.arioki.thekos.conf;

import java.io.Serializable;

/**
 * Created by arioki on 07/12/2016.
 */

public class ListAreaItem implements Serializable {
    private String areaNama;

    public String getAreaId() {
        return areaId;
    }

    public String getAreaNama() {
        return areaNama;
    }

    public String getAreaLogo() {
        return areaLogo;
    }

    private String areaId;

    private String areaLogo;

    public ListAreaItem(String areaId, String areaNama, String areaLogo) {
        this.areaId = areaId;
        this.areaNama = areaNama;
        this.areaLogo = areaLogo;
    }

}
