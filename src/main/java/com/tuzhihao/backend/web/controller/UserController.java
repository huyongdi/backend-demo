package com.tuzhihao.backend.web.controller;

import com.tuzhihao.backend.domain.User;
import com.tuzhihao.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by methol on 6/24/16.
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json")
@Api(value = "用户", produces = "application/json")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ApiOperation(value = "获取用户列表", notes = "获取所有用户列表")
  public ResponseEntity<List<User>> list() throws Exception {
    List<User> users = userService.findAll();
    return ResponseEntity.ok().body(users);
  }

}
