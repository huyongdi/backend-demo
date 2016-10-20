package com.hyd.backend.web.interceptor;

import com.hyd.backend.Application;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter比Spring的Interceptor更早介入请求生命周期，所以可以更早的处理OPTIONS请求.
 * <p>
 * 这个Filter在{@link Application}中引入.
 */
public class CrossDomainFilter extends OncePerRequestFilter {
  private static final Logger LOG = LoggerFactory.getLogger(CrossDomainFilter.class);

  private volatile boolean allowCrossDomain = true;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    FilterChain filterChain) throws ServletException, IOException {

    if (response.getContentType() == null || response.getContentType().isEmpty()) {
      response.setContentType(org.apache.http.entity.ContentType.APPLICATION_JSON.toString());
    }

    // 设置允许跨域访问
//    LOG.debug("Request Origin = {}", request.getHeader("Origin"));

    if (allowCrossDomain) {
      // 重要：clientIp不能为*，否则session无法传递到服务器端.
      response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
      response.addHeader("Access-Control-Allow-Credentials", "true");

      /**
       * 处理 Preflight 情况下的额外返回数据:
       * https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS#Preflighted_requests
       * 需要确认 Preflight 是有效的请求，而不是直接进行的OPTIONS操作.
       */
      if (request.getHeader("Access-Control-Request-Method") != null
        && "OPTIONS".equalsIgnoreCase(request.getMethod())) {
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        response.addHeader("Access-Control-Allow-Headers",
          "X-Requested-With, Origin, Content-Type, Cookie");
      }
      // response.addCookie(new Cookie("test-random-time", System.currentTimeMillis() + ""));
      filterChain.doFilter(request, response);
    }
  }

  public void setAllowCrossDomain(boolean allowCrossDomain) {
    this.allowCrossDomain = allowCrossDomain;
  }
}
