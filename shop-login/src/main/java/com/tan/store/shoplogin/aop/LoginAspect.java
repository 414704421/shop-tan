package com.tan.store.shoplogin.aop;

import com.tan.store.model.BaseResponse;
import com.tan.store.model.login.UserInfo;
import com.tan.store.shoplogin.manager.LoginManager;
import com.tan.store.utils.RedisUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Slf4j
public class LoginAspect {

    @Resource
    RedisUtil redisUtil;

    @Before(value = "@annotation(com.tan.store.shoplogin.aop.Login)")
    public void before(JoinPoint joinPoint) throws Throwable {
        log.info("———— before method,check login status ————");
        //获取请求
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //请求中得到请求头的token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new Exception("请先登录！");
        }

        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg instanceof UserInfo userInfo){
                    String tk = redisUtil.get(LoginManager.getLoginKey(userInfo.getUserName()));
                    if (StringUtils.isBlank(tk) || !token.equals(tk)){
                        throw new Exception("请先登录！");
                    }
                }
            }
        }
    }
}
