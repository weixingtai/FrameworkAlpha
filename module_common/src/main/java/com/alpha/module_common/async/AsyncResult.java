package com.alpha.module_common.async;

public class AsyncResult {

    private int requestCode;//请求id
    private boolean isCheckNetwork;//是否检查网络，true表示检查，false表示不检查
    private int state;//下载状态
    private Object result;//返回结果
    private IOnDataListener listener;//处理监听

    public AsyncResult() {
        super();
    }

    public AsyncResult(int requestCode, boolean isCheckNetwork, IOnDataListener listener) {
        this.requestCode = requestCode;
        this.isCheckNetwork = isCheckNetwork;
        this.listener = listener;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public boolean isCheckNetwork() {
        return isCheckNetwork;
    }

    public void setCheckNetwork(boolean isCheckNetwork) {
        this.isCheckNetwork = isCheckNetwork;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public IOnDataListener getListener() {
        return listener;
    }

    public void setListener(IOnDataListener listener) {
        this.listener = listener;
    }

}
