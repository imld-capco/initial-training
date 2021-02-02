//package com.example.demo;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { DemoApplication.class })
//@WebAppConfiguration(value = "")
//public class Integration {
//    private final String url = "http://localhost:8090";
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mock;
//
//    @Before
//    public void setup() throws Exception {
//        this.mock = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void greetingEndpoint() throws Exception {
//        this.mock.perform(MockMvcRequestBuilders.get("/greeting"));
//    }
//
////    @Test
////    public void greetingEndpoint() throws IOException {
////        final String endpoint = "/greeting";
////        HttpURLConnection con = (HttpURLConnection) getURLConnection(endpoint).openConnection();
////        con.setRequestMethod("GET");
////
////        String response = getURLResponse(con);
////    }
////
////    @Test
////    public void customEndpoint() throws IOException {
////        final String endpoint = "/custom";
////        HttpURLConnection con = (HttpURLConnection) getURLConnection(endpoint).openConnection();
////        con.setRequestMethod("POST");
////
////        String response = getURLResponse(con);
////    }
////
////    @Test
////    public void swaggerEndpoint() throws IOException {
////        final String endpoint = "/v2/api-docs";
////        HttpURLConnection con = (HttpURLConnection) getURLConnection(endpoint).openConnection();
////        con.setRequestMethod("GET");
////
////        String response = getURLResponse(con);
////    }
//
//    private URL getURLConnection(String endpoint) throws MalformedURLException {
//        return new URL(url + endpoint);
//    }
//
//    private String getURLResponse(HttpURLConnection con) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer input = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            input.append(inputLine);
//        }
//
//        in.close();
//        con.disconnect();
//
//        return input.toString();
//    }
//}
