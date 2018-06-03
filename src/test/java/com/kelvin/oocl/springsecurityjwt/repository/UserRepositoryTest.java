package com.kelvin.oocl.springsecurityjwt.repository;

import com.kelvin.oocl.springsecurityjwt.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application-test.yml"})
@SpringBootTest
public class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    public void findByUsername() throws Exception {
        List<User> users = userRepository.findByUsername("kelvin");

        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void findById() throws Exception {
        User user = userRepository.findById(1L).get();

        Assert.assertFalse(user == null);
    }

    @Test
    public void encode() throws Exception {//$2a$10$hj6S9qG7MiH7OhPxqaEXWuE.s8oN8BTAWHOVKe5SATDwNr7Z8OCH.
        String str = encoder.encode("test1234");
        System.out.println(str);
    }

    @Test
    public void match() throws Exception {
        boolean matches = encoder.matches("test1234", "$2a$10$hj6S9qG7MiH7OhPxqaEXWuE.s8oN8BTAWHOVKe5SATDwNr7Z8OCH.");
        Assert.assertTrue(matches);
    }
}