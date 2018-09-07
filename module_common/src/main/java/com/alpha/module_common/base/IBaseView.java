package com.alpha.module_common.base;

/**
 * 基础View接口
 *
 * setPresenter(T presenter)：将presenter实例传入view中
 */
public interface IBaseView<T> {

    void setPresenter(T presenter);

}
