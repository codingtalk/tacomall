
package com.tacomall.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import com.alibaba.fastjson.JSON;
import com.tacomall.common.enumeration.ExceptionEnum;
import com.tacomall.common.json.ResponseJson;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CORSAuthenticationFilter extends FormAuthenticationFilter {
    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                System.out.println("OPTIONS请求");
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        ResponseJson<String> responseJson = new ResponseJson<>();
        responseJson.setCode(ExceptionEnum.UNAUTHORIZED_ERROR.getCode());
        responseJson.setStatus(false);
        responseJson.setMessage(ExceptionEnum.UNAUTHORIZED_ERROR.getMessage());
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=utf-8");
        PrintWriter writer = res.getWriter();
        writer.write(JSON.toJSONString(responseJson));
        writer.close();
        return false;
    }
}
