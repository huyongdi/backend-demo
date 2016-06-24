package com.tuzhihao.backend;

/**
 * Created by methol on 6/24/16.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {

  private static Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
    LOG.debug("服务启动成功...");
  }

}