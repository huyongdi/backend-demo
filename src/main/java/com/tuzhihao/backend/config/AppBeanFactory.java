package com.tuzhihao.backend.config;

import com.tuzhihao.backend.web.interceptor.CrossDomainFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by methol on 6/24/16.
 */
@Component
public class AppBeanFactory {

  @Autowired
  private AppConfig appConfig;

  /**
   * 注册跨域支持过滤器
   */
  @Bean
  public FilterRegistrationBean registerCrossDomainFilter() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    CrossDomainFilter crossDomainFilter = new CrossDomainFilter();
    // 设置是否允许跨域访问
    crossDomainFilter.setAllowCrossDomain(appConfig.isCrossDomain());
    registrationBean.setFilter(crossDomainFilter);
    registrationBean.setOrder(1);
    return registrationBean;
  }
}
