package com.tan.store.shoplogin.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //在方法上生效
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface Login {

}
