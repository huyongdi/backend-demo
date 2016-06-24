package com.tuzhihao.backend.web.controller;

import com.tuzhihao.backend.domain.User;
import com.tuzhihao.backend.service.UserService;

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
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseEntity<List<User>> list() throws Exception {
    List<User> users = userService.findAll();
    return ResponseEntity.ok().body(users);
  }

}
