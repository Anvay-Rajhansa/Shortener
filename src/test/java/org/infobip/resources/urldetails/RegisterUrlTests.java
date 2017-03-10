package org.infobip.resources.urldetails;

import org.apache.tomcat.util.codec.binary.Base64;
import org.infobip.domain.Account;
import org.infobip.domain.repository.AccountRepository;
import org.infobip.domain.repository.UrlDetailsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterUrlTests {

    private MockMvc mvc;
    private String base64Credentials;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UrlDetailsRepository urlDetailsRepository;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).addFilter(springSecurityFilterChain).build();
        Account account = new Account("Test", "Test");
        accountRepository.save(account);
        String plainCredentials = account.getAccountId() + ":" + account.getPassword();
        base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    }

    @Test
    public void test_Register_Url_Invalid_Fields_Failure() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + base64Credentials)
                .content("{\"url\":\"Invalid\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_Register_Url_Success() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + base64Credentials)
                .content("{\"url\":\"https://www.google.co.in\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortUrl").exists());
    }

    @After
    public void tearDown() throws Exception {
        urlDetailsRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
