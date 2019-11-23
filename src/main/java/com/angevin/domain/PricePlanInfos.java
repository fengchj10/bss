package com.angevin.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created  by  Angevin.
 * Date: 2019-11-07 15:45
 * description:
 *
 * @author Angevin
 */
public class PricePlanInfos {
    @JSONField(name="PROD_ID")
    private String  prodId;
    @JSONField(name="PRICE_PLAN_ID")
    private String  pricePlanId;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(String pricePlanId) {
        this.pricePlanId = pricePlanId;
    }
}
