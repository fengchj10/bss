package com.angevin.domain;

/**
 * Created  by  Angevin.
 * Date: 2019-11-07 15:54
 * description:
 *
 * @author Angevin
 */
public class BssResult {
    private  UniBssHead uniBssHead;
    private  UniBssAttached uniBssAttached;
    private  UniBssBody uniBssBody;

    public UniBssHead getUniBssHead() {
        return uniBssHead;
    }

    public void setUniBssHead(UniBssHead uniBssHead) {
        this.uniBssHead = uniBssHead;
    }

    public UniBssAttached getUniBssAttached() {
        return uniBssAttached;
    }

    public void setUniBssAttached(UniBssAttached uniBssAttached) {
        this.uniBssAttached = uniBssAttached;
    }

    public UniBssBody getUniBssBody() {
        return uniBssBody;
    }

    public void setUniBssBody(UniBssBody uniBssBody) {
        this.uniBssBody = uniBssBody;
    }
}
