package com.angevin.domain;

import com.angevin.entity.Reconcilia;
import java.sql.Timestamp;

/**
 * Created  by  Angevin.
 * Date: 2019-11-04 17:58
 * description:
 *
 * @author Angevin
 */
public class ExcelReconciliaListVo {


    public ExcelReconciliaListVo() {
    }

    public ExcelReconciliaListVo(Reconcilia reconcilia) {
        this.id = reconcilia.getId();
        this.serialNumber = reconcilia.getSerialNumber();
        this.custId = reconcilia.getCustId();
        this.custName = reconcilia.getCustName();
        this.localNetId = reconcilia.getLocalNetId();
        this.acceptDate = reconcilia.getAcceptDate();
        this.prodId = reconcilia.getProdId();
        this.pricePlanId = reconcilia.getPricePlanId();
        this.actType = reconcilia.getActType();
        this.effDate = reconcilia.getEffDate();
        this.expDate = reconcilia.getExpDate();
        this.institutionCode = reconcilia.getInstitutionCode();
    }

    private Long id;

    /**
     * 业务号码
     */
    private String serialNumber;
    /**
     * 客户标识
     */
    private String custId;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 归属地：区号
     */
    private String localNetId;
    /**
     * 预约受理时间
     */
    private Timestamp acceptDate;
    /**
     * 产品编码
     */
    private String prodId;
    /**
     * 资费编码
     */
    private String pricePlanId;
    /**
     * 业务操作（开通/退订）A增加  R删除  X不变
     */
    private String actType;
    /**
     * 生效时间
     */
    private Timestamp effDate;
    /**
     * 失效时间
     */
    private Timestamp expDate;
    /**
     *接收机构编码
     */
    private String institutionCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public Timestamp getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Timestamp acceptDate) {
        this.acceptDate = acceptDate;
    }

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

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public Timestamp getEffDate() {
        return effDate;
    }

    public void setEffDate(Timestamp effDate) {
        this.effDate = effDate;
    }

    public Timestamp getExpDate() {
        return expDate;
    }

    public void setExpDate(Timestamp expDate) {
        this.expDate = expDate;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
}
