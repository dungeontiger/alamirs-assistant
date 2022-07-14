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
class HTMLControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void testRoot() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testDice() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/dice").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testTables() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/tables").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
