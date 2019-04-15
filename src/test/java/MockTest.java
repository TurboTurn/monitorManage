import com.monitor.App;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : ys
 * @date : 2019/4/15 19:06 星期一
 **/
@SpringBootTest(classes = {App.class})
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class MockTest {
	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void t1() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/demo")
				.param("name","yishuang")
				.accept(MediaType.TEXT_HTML_VALUE))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200,status);
	}
}
