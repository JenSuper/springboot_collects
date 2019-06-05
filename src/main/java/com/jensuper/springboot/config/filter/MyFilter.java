package com.jensuper.springboot.config.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: MyFilter
 * @Description: 自定义过滤器
 * @author:jichao
 * @date: 2019/5/30
 * @Copyright: 2019/5/30 www.rongdasoft.com
 * Inc. All rights reserved.
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(".......initFilter........");
        String filterName = filterConfig.getFilterName();
        System.out.println(filterName);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(".......doFilter.......");
        HttpServletRequest hsReq = (HttpServletRequest) servletRequest;
        StringBuffer requestURL = hsReq.getRequestURL();
        System.out.println("【过滤器】url： " + requestURL);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
