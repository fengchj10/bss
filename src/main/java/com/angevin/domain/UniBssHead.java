package com.angevin.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created  by  Angevin.
 * Date: 2019-11-07 15:50
 * description:
 *
 * @author Angevin
 */
public class UniBssHead {
    @JSONField(name="APP_ID")
    private String appId;
    @JSONField(name="TIMESTAMP")
    private String timestamp;
    @JSONField(name="TRANS_ID")
    private String transId;
    @JSONField(name="TOKEN")
    private String token;
    private String respDesc;
    private String respCode;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
}
