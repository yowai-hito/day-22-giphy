package giphy.pikachu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import giphy.pikachu.Models.QueryObject;
import giphy.pikachu.Services.SearchService;

@SpringBootTest
class PikachuApplicationTests {

	@Test
	void contextLoads() {
		QueryObject test = new QueryObject();
		SearchService testService = new SearchService();

	}

}
