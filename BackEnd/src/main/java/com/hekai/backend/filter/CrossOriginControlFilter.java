package com.hekai.backend.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: hekai
 * @Date: 2022/6/3
 */
@Component
public class CrossOriginControlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
        System.out.println(request.getHeader("Origin"));
        System.out.println("正在调用过滤器+"+request.getSession().getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        isCross=true;
//
//        HttpServletResponse response1= (HttpServletResponse) response;
//        HttpServletRequest request1=(HttpServletRequest)request;
//        response1.setContentType("text/html;charset=UTF-8");
//        response1.setHeader("Access-Control-Allow-Origin", request1.getHeader("Origin"));
//        System.out.println(request1.getHeader("Origin"));
//        response1.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response1.setHeader("Access-Control-Max-Age", "3600");
//        response1.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//        response1.setHeader("Access-Control-Allow-Credentials", "true");
//        response1.setHeader("XDomainRequestAllowed","1");
//        System.out.println("正在调用过滤器");
//        chain.doFilter(request,response);
//    }



//    @Override
//    public void init(FilterConfig filterConfig) {
//        String isCrossStr = filterConfig.getInitParameter("IsCross");
//        isCross = true;
//        System.out.println(isCrossStr);
//
//    }


//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "0");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("XDomainRequestAllowed","1");
//        filterChain.doFilter(servletRequest,servletResponse);
//        System.out.println("过滤器"+httpServletRequest.getSession().getId());
//    }

}