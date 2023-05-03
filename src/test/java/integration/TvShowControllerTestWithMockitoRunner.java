package integration;

import com.everis.d4i.tutorial.controllers.impl.TvShowControllerImpl;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.impl.TvShowServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TvShowControllerTestWithMockitoRunner {

    private MockMvc mockMvc;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private TvShowControllerImpl tvShowControllerImpl;

    @Mock
    private TvShowServiceImpl tvShowServiceImpl;


    @BeforeClass
    public static void runBeforeAllTestOfThisClass() {
        System.out.println("Run before all test of this class!");
    }

    @Before
    public void setUpMockMvc() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tvShowControllerImpl).build();
    }

    @Before
    public void runBeforeIndividualTestOfThisClass() throws Exception {
        System.out.println("Run before individual test of this class!");
    }

    @AfterClass
    public static void runAfterAllTestOfThisClass() {
        System.out.println("Run after all test of this class!");
    }

    @After
    public void runAfterIndividualTestOfThisClass() {
        System.out.println("Run after individual test of this class!");
    }

    @Test
    public void getTvShowById() throws Exception {

        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");
        TvShowRest tvRest = modelMapper.map(tvShow1, TvShowRest.class);

        // Crear objeto esperado
        NetflixResponse<TvShowRest> expectedResponse = new NetflixResponse<>(
                CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, tvRest);


        when(tvShowServiceImpl.getTvShowById(1L)).thenReturn(tvRest);

        MvcResult result = mockMvc.perform(
                        get(RestConstants.RESOURCE_TV_SHOW + RestConstants.RESOURCE_ID, 1)
                                .accept(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(content().json("{\"status\":\"Success\",\"code\":\"200 OK\"}", false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.OK))))
                .andExpect(jsonPath("$.message", is(CommonConstants.OK)))
                .andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS)))
                .andReturn();

        verify(tvShowServiceImpl, times(1)).getTvShowById(1L);

    }

    @Test
    public void insertCategoryInTvShow() throws Exception {

        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");
        TvShowRest tvRest = modelMapper.map(tvShow1, TvShowRest.class);

        Category ca = new Category(9L, "Premios Oscars");
        List<CategoryRest> listCategories = new ArrayList<>();
        listCategories.add(modelMapper.map(ca, CategoryRest.class));
        tvRest.setCategories(listCategories);



        when(tvShowServiceImpl.insertNewCategory(1L, 9L)).thenReturn(tvRest);

        MvcResult result = mockMvc.perform(
                        post(RestConstants.RESOURCE_TV_SHOW +"/admin/insertcategoryintvshows"+ "/{id_show}" + "/{id_category}", 1, 9)
                                .accept(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(content().json("{\"status\":\"Success\",\"code\":\"200 OK\"}",false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.OK))))
                .andExpect(jsonPath("$.message", is(CommonConstants.OK)))
                .andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS)))
                .andReturn();

        verify(tvShowServiceImpl, times(1)).insertNewCategory(1L, 9L);

    }

    @Test
    public void updateNameInTvShow() throws Exception {

        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");
        TvShowRest tvRest = modelMapper.map(tvShow1, TvShowRest.class);

        TvShowRest tvRestAux = new TvShowRest(1L, "La pepa", null, null,null,(byte) 0, null, null);
        tvRest.setName("La pepa");
        when(tvShowServiceImpl.updateTvShow(tvRestAux)).thenReturn(tvRest);

        MvcResult result = mockMvc.perform(
                        patch(RestConstants.RESOURCE_TV_SHOW +"/admin/updatetvshow")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(new ObjectMapper().writeValueAsString(tvRestAux))
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.name", is("La pepa")))
                .andExpect(content().json("{\"status\":\"Success\",\"code\":\"200 OK\"}",false))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.OK))))
                .andExpect(jsonPath("$.message", is(CommonConstants.OK)))
                .andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS)))
                .andReturn();

        verify(tvShowServiceImpl, times(1)).updateTvShow(tvRestAux);

    }
    @Test
    public void testDeleteTvShow() throws Exception {
        // Crear un objeto TvShowRest simulado para enviar en la petición
        TvShowRest tvShowRest = new TvShowRest();
        tvShowRest.setId(1L);
        tvShowRest.setName("Test TV Show");

        // Configurar el comportamiento del servicio simulado para devolver el objeto TvShowRest simulado
        when(tvShowServiceImpl.deleteTvShow(tvShowRest)).thenReturn(tvShowRest);

        // Realizar la petición DELETE al controlador simulando la eliminación de un programa de TV
        mockMvc.perform(delete(RestConstants.RESOURCE_TV_SHOW+ "/admin/deletetvshow").accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tvShowRest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS)))
                .andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.OK))))
                .andExpect(jsonPath("$.message", is(CommonConstants.OK)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Test TV Show")));

        // Verificar que se llamó al método deleteTvShow del servicio simulado con el objeto TvShowRest simulado
        verify(tvShowServiceImpl, times(1)).deleteTvShow(tvShowRest);
    }

    // Método auxiliar para convertir un objeto a JSON
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}