package com.tuzhihao.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by methol on 6/24/16.
 */
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {
  private String name;

  private boolean api;

  private boolean crossDomain;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isApi() {
    return api;
  }

  public void setApi(boolean api) {
    this.api = api;
  }

  public boolean isCrossDomain() {
    return crossDomain;
  }

  public void setCrossDomain(boolean crossDomain) {
    this.crossDomain = crossDomain;
  }
}
