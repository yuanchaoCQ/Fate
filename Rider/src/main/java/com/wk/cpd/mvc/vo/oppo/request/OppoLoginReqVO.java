package com.wk.cpd.mvc.vo.oppo.request;

/**
 * oppo登录接口请求参数
 * @author Administrator
 *
 */
public class OppoLoginReqVO {
    
    /**用户名**/
    private String name;
    
    /**密码**/
    private String passwd;
    
    /**密码(旧)**/
    private String password;
    
    /**验证码**/
    private String captcha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    
}
