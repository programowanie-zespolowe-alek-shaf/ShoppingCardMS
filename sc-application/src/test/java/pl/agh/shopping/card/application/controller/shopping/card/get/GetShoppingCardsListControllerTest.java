package pl.agh.shopping.card.application.controller.shopping.card.get;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
public class GetShoppingCardsListControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private URLProvider urlProvider;

    @Test
    public void noLimitAndOffsetTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards"))
                .andExpect(status().is(400))
                .andExpect(status().reason("Required int parameter 'limit' is not present"));
    }

    @Test
    public void noOffsetTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards")
                .param("limit", "0"))
                .andExpect(status().is(400))
                .andExpect(status().reason("Required int parameter 'offset' is not present"));
    }

    @Test
    public void onlyLimitAndOffsetTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards")
                .param("offset", "0")
                .param("limit", "10"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("list[0].id").value("1"))
                .andExpect(jsonPath("list[0].username").value("user1"))
                .andExpect(jsonPath("list[0].createDate").value("2020-05-04"))
                .andExpect(jsonPath("list[1].id").value("2"))
                .andExpect(jsonPath("list[1].username").value("user2"))
                .andExpect(jsonPath("list[1].createDate").value("2020-05-04"))
                .andExpect(jsonPath("count").value("2"));
    }

    @Test
    public void usernameSuccessTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards")
                .param("offset", "0")
                .param("limit", "10")
                .param("username", "user1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("list[0].id").value("1"))
                .andExpect(jsonPath("list[0].username").value("user1"))
                .andExpect(jsonPath("list[0].create_date").value("2020-05-04"))
                .andExpect(jsonPath("count").value("1"));
    }

    @Test
    public void usernameFailedTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards")
                .param("offset", "0")
                .param("limit", "10")
                .param("username", "user3"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("list").isEmpty())
                .andExpect(jsonPath("count").value("0"));
    }

    @Test
    public void offset1Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards")
                .param("offset", "1")
                .param("limit", "10"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("list[0].id").value("2"))
                .andExpect(jsonPath("list[0].username").value("user2"))
                .andExpect(jsonPath("list[0].createDate").value("2020-05-04"))
                .andExpect(jsonPath("count").value("2"));
    }

    @Test
    public void limit0Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCards")
                .param("offset", "0")
                .param("limit", "0"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("list").isEmpty())
                .andExpect(jsonPath("count").value("2"));
    }
}
