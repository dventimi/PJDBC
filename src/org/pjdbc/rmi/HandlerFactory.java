package org.pjdbc.rmi;

import java.lang.reflect.*;
import java.rmi.*;

@SuppressWarnings("unchecked")
public class HandlerFactory {
    public static<T> T getProxy(Class<T> iface, final Object delegate) {
        return (T)Proxy.newProxyInstance(delegate.getClass().getClassLoader(), new Class[]{iface}, new InvocationHandler() {
		public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
		    return method.invoke(delegate, args);}});}}
