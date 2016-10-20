package com.hyd.backend;

/**
 * Created by methol on 6/24/16.
 */

import com.fasterxml.classmate.TypeResolver;
import com.hyd.backend.config.AppConfig;
import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@SpringBootApplication
@EnableSwagger2
public class Application {

  @Autowired
  private AppConfig appConfig;

  @Autowired
  private TypeResolver typeResolver;

  private static Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
    LOG.debug("服务启动成功...");
  }

  @Bean
  public Docket newsApi() {

    if (!appConfig.isApi()) {
      // 不生成api  适用于线上环境
      return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.none())
        .build();
    }

    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(new ApiInfoBuilder()
        .title(appConfig.getName() + " swagger-ui")
        .description(appConfig.getName() + " swagger-ui")
        .termsOfServiceUrl("http://www.tuzhihao.com")
        .version("2.0")
        .build())
      .select()
      // 标示只有被 @Api 标注的才能生成API.
      .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
      .paths(PathSelectors.any()).build()
      .pathMapping("/")
      .directModelSubstitute(LocalDate.class, String.class)
      // 遇到 LocalDate时，输出成String
      .genericModelSubstitutes(ResponseEntity.class)
      .alternateTypeRules(
        newRule(
          typeResolver.resolve(DeferredResult.class,
            typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
          typeResolver.resolve(WildcardType.class)))
      .useDefaultResponseMessages(false);
  }

}