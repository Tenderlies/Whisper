package com.tosh.whisper.utils.sort;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface Sort {
    static void sortByAlgorithm(int[] arr, Class<? extends Sort> clazz) {
        Sort sort = (Sort) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{Sort.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method,
                                         Object[] args) throws Throwable {
                        if (args[0] == null || ((int[]) args[0]).length == 0) {
                            throw new Exception("Integer array for argument is null or empty!");
                        }
                        System.out.println(method.getName());
                        return method.invoke(clazz.getConstructor()
                                .newInstance(), args);
                    }
                });

        sort.sort(arr);
    }

    void sort(int[] arr);

}
