package pl.agh.shopping.card.application.controller.shopping.card.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.agh.shopping.card.application.rest.url.EurekaURLProvider;
import pl.agh.shopping.card.application.rest.url.URLProvider;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser
public class GetShoppingCardControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private URLProvider urlProvider;

    @Test
    public void successTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("username").value("user1"))
                .andExpect(jsonPath("createDate").value("2020-05-04"));
    }

    @Test
    public void notFoundTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards/10"))
                .andExpect(status().is(404));
    }
}
