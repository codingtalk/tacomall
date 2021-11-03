/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-10 20:53:03
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/shiro/CORSAuthenticationFilter.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.enumeration.BizEnum;

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
        responseJson.setCode(BizEnum.USER_NOT_LOGGED_IN.getCode());
        responseJson.setStatus(false);
        responseJson.setMessage(BizEnum.USER_NOT_LOGGED_IN.getMessage());
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
