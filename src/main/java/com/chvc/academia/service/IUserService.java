package com.chvc.academia.service;

import com.chvc.academia.model.User;
import reactor.core.publisher.Mono;

public interface IUserService extends ICRUD<User, String> {

  Mono<User> saveHash(User user);

  Mono<com.chvc.academia.security.User> searchByUser(String username);
}
