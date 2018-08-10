package com.alpha.module_common.model.base;

import android.text.TextUtils;

public class BaseResponse<T> extends BaseModel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5616901114632786764L;

    private String resultCode;

    private String errorMsg;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSucces() {
        if (!TextUtils.isEmpty(resultCode) && "1".equals(resultCode)) {
            return true;
        }
        return false;
    }
}
