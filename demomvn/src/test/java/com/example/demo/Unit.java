package com.example.demo;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GreetingController.class)
class CustomRestControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpringFoxConfig service;

    @Test
    public void customControllerAPI() {
        String expected = "custom";

        CustomController controller = new CustomController();
        String result = controller.custom();

        Assert.assertEquals(expected, result);
    }
}


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GreetingController.class)
class GreetingRestControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpringFoxConfig service;

    @Test
    public void greetingController() {
        String name = "test";
        String expected = String.format("Hello, %s", name);
        Greeting greeting = new Greeting(0, name);

        GreetingController controller = new GreetingController();
        Greeting result = controller.greeting(name);

        Assert.assertEquals(expected, result.getContent());
    }
}

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserRepository mock;

    @Autowired
    UserController userController;

    private List<User> mockUsers;

    @Test
    public void getAllUsersTest() {
        mockUsers = new ArrayList<>();
        mockUsers.add(new User("Test", "One"));
        mockUsers.add(new User("Test", "Two"));
        Mockito.when(mock.findAll()).thenReturn(mockUsers);

        List<User> result = userController.getAllUsers();

        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(mockUsers.get(i), result.get(i));
        }
    }

    @Test
    public void getUserTest() {
        String mockId = "mockId";
        User user = new User("Single", "User");
        user.id = mockId;
        Mockito.when(mock.findById(mockId)).thenReturn(Optional.of(user));

        User result = userController.getUser(mockId);

        Assert.assertEquals(result, user);
    }

    @Test
    public void addUserTest() {
        User user = new User("Add User", "Test");
        Mockito.when(mock.insert(user)).thenReturn(user);

        User result = userController.addUser(user);

        Assert.assertEquals(user, result);
    }
}
