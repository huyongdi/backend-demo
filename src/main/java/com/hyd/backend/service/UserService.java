package com.hyd.backend.service;

import com.hyd.backend.domain.User;

import java.util.List;

/**
 * Created by methol on 6/24/16.
 */
public interface UserService {

  List<User> findAll();
}
