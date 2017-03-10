package org.infobip.resources.account;

import org.apache.tomcat.util.codec.binary.Base64;
import org.infobip.domain.Account;
import org.infobip.domain.UrlDetails;
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
public class GetAccountStatisticTests {

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

        UrlDetails urlDetails = new UrlDetails("https://www.google.co.in", "XYZ", 302, account);
        urlDetailsRepository.save(urlDetails);

        String plainCredentials = account.getAccountId() + ":" + account.getPassword();
        base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    }


    @Test
    public void test_Get_Account_Statistic_Invalid_Id_Failure() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/statistic/InvalidId")
                .header("Authorization", "Basic " + base64Credentials)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid given accountId."));
    }

    @Test
    public void test_Get_Account_Statistic_Success() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/statistic/Test")
                .header("Authorization", "Basic " + base64Credentials)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @After
    public void tearDown() throws Exception {
        urlDetailsRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
