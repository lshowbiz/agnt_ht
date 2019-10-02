package com.joymain.jecs.sys.service;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class WecsPowerAdvice implements MethodBeforeAdvice {
	protected Log log = LogFactory.getLog(WecsPowerAdvice.class);

	public void before(Method method, Object[] args, Object target) throws Throwable {
		log.info(method.getDeclaringClass().getName());
	}

}
