package com.mark.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;

@Aspect
@Component
public class ModelZeroAspect {

    @Pointcut("@annotation(com.mark.Aop.ModelsZeroMethod)")
    public void pt()
    {
    }

    @AfterReturning(value = "pt()",returning = "obj")
    public Object ExcuteModelZero(JoinPoint joinPoint, Object obj)
    {
        Object o = obj;
        //增强返回值
        InvokeModelZero(o);
        return o;
    }

    public Object InvokeModelZero(Object obj)
    {
        //1.判断obj是否为集合
        if(obj instanceof Collection)
        {
            //2.若是则遍历它的每一项
            for(Object o : ((Collection) obj).toArray())
            {
                InvokeModelZero(o);
            }
        }

        //对象不为null则
        if (obj != null)
        {
            Class<?> objClass = obj.getClass();
            //3.得到对象后查看对象是否有ModelsZero注解
            ModelZero annotation = objClass.getAnnotation(ModelZero.class);
            //4.若没有加该注解则说明Model不需要增强
            if (annotation == null) return obj;
            //5.若加了ModelZero注解则需要增强该Model下的变量

            //6.获取类中变量集合
            //Field[] fields = objClass.getFields();//获取当前类或父类或父接口的 public 修饰的字段
            Field[] fields = objClass.getDeclaredFields();//获取当前类的所有字段，包括 protected/默认/private 修饰的字段；不包括父类public 修饰的字段
            for (Field fd : fields)
            {
                //7.设置私有成员变量暂时可以访问和修改
                fd.setAccessible(true);
                //8.判断变量类型是否为BigDecimal同时变量为空
                try {
                    if (fd.getType().equals(BigDecimal.class) && fd.get(obj) == null)
                    {
                        fd.set(obj,BigDecimal.ZERO);
                    }
                } catch (Exception e) {
                    System.out.println("转化出现异常，异常信息为："+e.getMessage());
                }
            }
        }
        return obj;

    }
}
