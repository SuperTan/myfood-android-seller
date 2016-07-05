package com.kxiang.okhttp.gson;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 成功返回接口
 */
public abstract class GenericityGson<T, T1> {


    public Type mType;

    //-----------------------------------在泛型擦除类型前保存传入的类型---------------------------------
    public GenericityGson() {
        mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
    //-----------------------------------在泛型擦除类型前保存传入的类型---------------------------------

    /**
     * 联网成功后获取的值
     *
     * @param t
     */
    public abstract void onSucceed(T t, T1 t1);

    /**
     * 联网失败时候执行的操作
     */
    public abstract void onFail();

}
