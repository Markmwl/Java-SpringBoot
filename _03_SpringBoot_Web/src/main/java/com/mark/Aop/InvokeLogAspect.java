package com.mark.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InvokeLogAspect {
    //定义切点
    @Pointcut("@annotation(com.mark.Aop.InvokeLog)")
    public void pt(){}

    @Around("pt()")
    public Object printInvokeLog(ProceedingJoinPoint joinPoint)
    {
        Object proceed =null;
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        System.out.println(methodName+"即将被调用！");
        try {
            proceed = joinPoint.proceed();
            System.out.println(methodName+"调用完成！");
        } catch (Throwable throwable) {
            System.out.println(methodName+"调用出现异常，异常信息为："+throwable.getMessage());
        }
        return proceed;
    }
}
