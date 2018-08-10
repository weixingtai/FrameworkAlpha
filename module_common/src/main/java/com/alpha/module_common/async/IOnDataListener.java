package com.alpha.module_common.async;

public interface IOnDataListener {

    /**
     * 异步耗时方法
     *
     * @param requestCode 请求码
     * @return 耗时操作进程
     * @throws HttpException 异常
     */
    Object doInBackground(int requestCode) throws HttpException;

    /**
     * 打断方法
     *
     * @param requestCode 请求码
     * @param result 返回结果 true表示打断，false表示继续执行onSuccess方法
     */
    boolean onIntercept(int requestCode, Object result);

    /**
     * 成功方法（可直接更新UI）
     *
     * @param requestCode 请求码
     * @param result 返回结果
     */
    void onSuccess(int requestCode, Object result);

    /**
     * 失败方法（可直接更新UI）
     *
     * @param requestCode 请求码
     * @param state 返回状态
     * @param result 返回结果
     */
    void onFailure(int requestCode, int state, Object result);
}
