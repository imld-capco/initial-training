package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepo;

    @Test
    public void greetingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greetings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World"))
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn();
    }

    @Test
    public void customEndpointTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/custom"))
                .andExpect(status().isOk())
                .andExpect(content().string("custom"))
                .andReturn();
    }

    @Test
    public void swaggerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addUserTest() throws Exception {
        User user = new User("Integration", "Test");
        ObjectMapper mapper = new ObjectMapper();
        user.id = LocalDateTime.now().toString();
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().json(json))
                .andReturn();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        List<User> users = userRepo.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(users);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(json))
                .andReturn();
    }

    @Test
    public void getUserTestExisting() throws Exception {
        User user = new User("Integration", "Test");
        user.id = LocalDateTime.now().toString();
        userRepo.insert(user);

        Optional<User> optionalUser = userRepo.findById(user.id);
        Assert.assertTrue((optionalUser.isPresent()));
        User result = optionalUser.get();
        Assert.assertEquals(result.id, user.id);
        Assert.assertEquals(result.firstName, user.firstName);
        Assert.assertEquals(result.lastName, user.lastName);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/" + user.id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(json))
                .andReturn();
    }

    @Test
    public void getUserTestNonExisting() throws Exception {
        User user = new User("Integration", "Test");
        user.id = LocalDateTime.now().toString();

        Optional<User> optionalUser = userRepo.findById(user.id);
        Assert.assertTrue(!optionalUser.isPresent());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/" + user.id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
