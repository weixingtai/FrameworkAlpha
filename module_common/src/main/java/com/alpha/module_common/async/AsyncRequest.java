package com.alpha.module_common.async;

public class AsyncRequest {

    private int requestCode;//请求id
    private boolean isCheckNetwork;//是否检查网络，true表示检查，false表示不检查
    private IOnDataListener listener;//处理监听

    public AsyncRequest() {
        super();
    }

    public AsyncRequest(int requestCode, boolean isCheckNetwork, IOnDataListener listener) {
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

    public IOnDataListener getListener() {
        return listener;
    }

    public void setListener(IOnDataListener listener) {
        this.listener = listener;
    }

}