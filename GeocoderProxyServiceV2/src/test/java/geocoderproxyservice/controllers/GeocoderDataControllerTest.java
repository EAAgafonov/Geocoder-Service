//package geocoderproxyservice.controllers;
//
//import com.geocoderproxyservice.controllers.GeocoderDataController;
//import com.geocoderproxyservice.data.GeocoderData;
//import com.geocoderproxyservice.service.GeocoderDataService;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(GeocoderDataController.class)
//class GeocoderDataControllerTest {
//    @MockBean
//    private GeocoderDataService geocoderDataService;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void mockMvcNotNull()  {
//        assertNotNull(mockMvc);
//    }
//
//
//
//    @Test
//    void shouldGetAllData() throws Exception {
//        when(geocoderDataService.getData())
//                .thenReturn(List.of(new GeocoderData(2L,33,55,"Москва")));
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                .get("/data"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.dataDBs[0].address", Matchers.is("Москва")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.dataDBs[0].id", Matchers.is(2)));
//
//    }
//
//    @Test
//    void shouldPostDataFinal() throws Exception {
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/data/final")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("[\n" +
//                                "    {\n" +
//                                "        \"latitude\": 55,\n" +
//                                "        \"longitude\": 37\n" +
//                                "    }\n" +
//                                "]"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.header().exists("Content-Type"))
//                .andExpect(MockMvcResultMatchers.header().string("Content-Type", Matchers.containsString("application/json")));
//
//    }
//
//    @Test
//    void sendCoordinates() throws Exception {
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                .get("/data/c/55+33"))
//                .andExpect(MockMvcResultMatchers.status().is(200));
//        verify(geocoderDataService).sendCoordinates("55", "33");
//    }
//
//    @Test
//    void sendAddress() throws Exception {
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get("/data/a/Город+Улица+Дом"))
//                .andExpect(MockMvcResultMatchers.status().is(200));
//        verify(geocoderDataService).sendAddress("Город", "Улица", "Дом");
//    }
//}