package com.wk.cpd.mvc.advice;

import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wk.cpd.mvc.expection.BusinessException;
import com.wk.cpd.mvc.expection.Errors;
import com.wk.cpd.mvc.utils.JsonUtils;
import com.wk.cpd.mvc.utils.local.LocalInfo;
import com.wk.cpd.mvc.utils.local.ThreadLocalManager;
import com.wk.cpd.mvc.utils.log.WKLogManager;

@Component
@Aspect
public class ExceptionAdvisor {

    @AfterThrowing(value = "execution(public * com.wk.feed.mvc.controller.*.*(..)) throws Exception", throwing = "ex")
    public void afterThrowingAdvice(final JoinPoint joinPoint, final Exception ex) {

        // 权限校验不通过
        if (ex.getClass().equals(BusinessException.class)) {
            String message = ex.getMessage();
            if (!StringUtils.isBlank(message)) {
                int code = Integer.parseInt(message);
                this.responseHandle(code);
            }
        } else { // 其他系统异常默认为没有内容
            WKLogManager.getLOG().addSysError(ex); // 记录系统异常
            this.responseHandle(Errors.NO_CONTENT);
        }
    }

    /**
     * 响应处理类，根据传入的{@code code}处理响应，返回响应的数据
     * 
     * @param code 传入的错误码
     */
    private void responseHandle(int code) {
        // 错误信息写入到日志中
        String errorMessage = Errors.getErrorMessageViaErrorCode(code);
        WKLogManager.getLOG().addError(errorMessage);

        // 获取请求属性
        ServletRequestAttributes respAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletResponse response = respAttributes.getResponse();

        LocalInfo localInfo = ThreadLocalManager.getLocal();
        
        try {
            // 构造返回消息
//            ResponseVO sdkResponse = new ResponseVO();
//            sdkResponse.setCode(code);
//            sdkResponse.setReqid(localInfo.getRequestId());
//            sdkResponse.setUid(localInfo.getUid());
//            sdkResponse.setData(new ArrayList<>());

            // 设置返回状态
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");

            // 设置相应返回内容
            OutputStream ou = response.getOutputStream();
            ou.write(JsonUtils.writeObject2Json(null).getBytes());
            ou.flush();
            ou.close();
        } catch (Exception e) {
            WKLogManager.getLOG().addSysError(e); // 记录系统异常
        }
    }
}
