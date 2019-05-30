package com.example.nice.taobao_shop.callback;

public interface EmptyCallBack<T> {
    void getList(T t);
    void setError(String str);
}
