package com.angevin.domain;

/**
 * Created  by  Angevin.
 * Date: 2019-10-31 11:34
 * description:
 *
 * @author Angevin
 */
public class ProOrderResp {
    private String resultCode;
    private String tradeId;
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
