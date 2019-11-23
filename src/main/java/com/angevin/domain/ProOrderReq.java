package com.angevin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created  by  Angevin.
 * Date: 2019-11-07 15:23
 * description:
 *
 * @author Angevin
 */
public class ProOrderReq {
    @JSONField(name="SERIAL_NUMBER")
    private String  serialNumber;
    @JSONField(name="ACT_TYPE")
    private String  actType;
    @JSONField(name="CUST_ID")
    private String  custId;
    @JSONField(name="LOCAL_NET_ID")
    private String  localNetId;
    @JSONField(name="EXP_DATE",format = "yyyy-MM-dd hh:mm:ss SSS")
    private Timestamp exp_date;
    @JSONField(name="PARA")
    private List<Para> para;
    @JSONField(name="PRICE_PLAN_INFOS")
    private List<PricePlanInfos>  pricePlanInfos;
    @JSONField(name="EFF_DATE",format = "yyyy-MM-dd hh:mm:ss SSS")
    private Timestamp  effDate;
    @JSONField(name="ACCEPT_DATE",format = "yyyy-MM-dd hh:mm:ss SSS")
    private Timestamp  acceptDate;
    @JSONField(name="INSTITUTION_CODE")
    private String  institutionCode;
    @JSONField(name="CUST_NAME")
    private String  custName;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public Timestamp getExp_date() {
        return exp_date;
    }

    public void setExp_date(Timestamp exp_date) {
        this.exp_date = exp_date;
    }

    public List<Para> getPara() {
        return para;
    }

    public void setPara(List<Para> para) {
        this.para = para;
    }

    public List<PricePlanInfos> getPricePlanInfos() {
        return pricePlanInfos;
    }

    public void setPricePlanInfos(List<PricePlanInfos> pricePlanInfos) {
        this.pricePlanInfos = pricePlanInfos;
    }

    public Timestamp getEffDate() {
        return effDate;
    }

    public void setEffDate(Timestamp effDate) {
        this.effDate = effDate;
    }

    public Timestamp getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Timestamp acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
