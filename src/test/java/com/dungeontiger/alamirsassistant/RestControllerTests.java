package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testRoll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/roll/1d8").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testRollRepeats() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/roll/1d20/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testCampaigns() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/tableRoll/campaigns").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testTables() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/tableRoll/test/tables").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testTableRoll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/tableRoll/test/test").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
