package com.angevin.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created  by  Angevin.
 * Date: 2019-10-31 11:36
 * description:
 *
 * @author Angevin
 */
public class UniBssBody {
    @JSONField(name="PRO_ORDER_REQ")
    private ProOrderReq proOrderReq;

    //返回使用的对象
    private ProOrderResp proOrderResp;


    public ProOrderReq getProOrderReq() {
        return proOrderReq;
    }

    public void setProOrderReq(ProOrderReq proOrderReq) {
        this.proOrderReq = proOrderReq;
    }

    public ProOrderResp getProOrderResp() {
        return proOrderResp;
    }

    public void setProOrderResp(ProOrderResp proOrderResp) {
        this.proOrderResp = proOrderResp;
    }
}
