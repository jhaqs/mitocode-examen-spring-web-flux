package com.chvc.academia.repository;

import com.chvc.academia.model.User;
import reactor.core.publisher.Mono;

public interface IUserRepo extends IGenericRepo<User, String> {

  //@Query("{username: ?}")
  Mono<User> findOneByUsername(String username);
}
