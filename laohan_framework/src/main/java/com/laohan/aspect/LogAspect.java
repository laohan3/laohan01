package com.laohan.aspect;

import com.alibaba.fastjson.JSON;
import com.laohan.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Slf4j
@Component
//把当前类标识为一个切面供容器读取
@Aspect

public class LogAspect {
    //指定切点所用的注解:SystemLog,执行的增强方法pt().
    @Pointcut("@annotation(com.laohan.annotation.SystemLog)")
    public void pt(){
    }
    @Pointcut("execution(* *..*.* (..))")
    public void pt2(){
    }

    @Before("pt2()")
    public void before(){
    }
    @After("pt2()")
    public void after(){
    }

    //直接这样写死也可以(切点和通知写在一起)
    @Before("execution(* *..*.* (..))")
    public void before2(){
        log.info("执行打印日志");
    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed=null;
        try {
            //目标方法执行前打印的日志
            handlerBefore(joinPoint);
            //调用增强方法,最后要return它的返回值
            proceed = joinPoint.proceed();
            //目标方法执行后打印的日志
            handlerAfter(proceed);
            //System.lineSeparator()系统换行符.
        }
        //无论是否抛出异常,finally语句块中的代码都会执行
        finally{
            log.info("========END========"+System.lineSeparator());
        }

        //增强方法的返回值
        return proceed;

    }

    //参数jointPoint相当于被增强的方法
    private void handlerBefore(ProceedingJoinPoint joinPoint) {
        //spring中提供的获取request对象的方法
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法的注解
        SystemLog systemLog=getSystemLog(joinPoint);

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}",systemLog.businessName());
        // 打印请求方式
        log.info("HTTP Method    : {}",request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature)joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));

    }
    //通过反射获取被增强方法的注解
    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature =(MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = signature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }


    private void handlerAfter(Object ret) {
        // 打印出参
        log.info("Response       : {}",JSON.toJSONString(ret));

    }




}
