package com.angevin.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created  by  Angevin.
 * Date: 2019-11-07 15:44
 * description:
 *
 * @author Angevin
 */
public class Para {
    @JSONField(name="PARA_ID")
    private String  paraId;
    @JSONField(name="PARA_VALUE")
    private String  paraValue;

    public String getParaId() {
        return paraId;
    }

    public void setParaId(String paraId) {
        this.paraId = paraId;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }
}
