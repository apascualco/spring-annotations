package com.apascualco.annotations;

import com.apascualco.annotations.config.CacheConfiguration;
import com.apascualco.annotations.domain.User;
import com.apascualco.annotations.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CacheConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class BehaviourCacheTest {

    @Inject private UserService userService;

    @Before
    public void setUp() {
        final List<User> users = new ArrayList<>();
        User paco = new User();
        paco.setEmail("paco@paco.com");
        users.add(paco);
        User fer = new User();
        fer.setEmail("fer@fer.com");
        users.add(fer);
        userService.setUsers(users);
    }

    @Test
    public void only_join_one_time() {
        userService.getAllUsers();
        userService.getAllUsers();
    }

    @Test
    public void join_two_time() {
        userService.getAllUsers();
        userService.activeAllUsers();
    }

    @Test
    public void modifying_cache() {
        final List<User> users = userService.getAllUsers();
        assertEquals("Users list should've 2 results", 2, users.size());
        users.remove(0);
        final List<User> userCache = userService.getAllUsers();
        assertEquals("Users list should've 1 results", 1, userCache.size());
    }
}
