package com.kelvin.oocl.springsecurityjwt.repository;//package com.kelvin.oocl.springsecurityjwt.repository;

import com.kelvin.oocl.springsecurityjwt.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByUsername(String username);
}
