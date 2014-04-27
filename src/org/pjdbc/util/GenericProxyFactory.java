package org.pjdbc.util;

import java.lang.reflect.*;

@SuppressWarnings("unchecked")
public class GenericProxyFactory {
    public static<T> T getProxy(Class<T> iface, final Object delegate) {
        return (T)Proxy.newProxyInstance(delegate.getClass().getClassLoader(), new Class[]{iface}, new InvocationHandler() {
		public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
		    return method.invoke(delegate, args);}});}}
