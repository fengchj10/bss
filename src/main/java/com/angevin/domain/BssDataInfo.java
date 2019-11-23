package com.angevin.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created  by  Angevin.
 * Date: 2019-10-30 18:47
 * description:
 *
 * @author Angevin
 */
public class BssDataInfo {


    @JSONField(name="UNI_BSS_BODY")
    private UniBssBody uniBssBody;
    @JSONField(name="UNI_BSS_HEAD")
    private UniBssHead uniBssHead;

    public UniBssBody getUniBssBody() {
        return uniBssBody;
    }

    public void setUniBssBody(UniBssBody uniBssBody) {
        this.uniBssBody = uniBssBody;
    }

    public UniBssHead getUniBssHead() {
        return uniBssHead;
    }

    public void setUniBssHead(UniBssHead uniBssHead) {
        this.uniBssHead = uniBssHead;
    }
}

