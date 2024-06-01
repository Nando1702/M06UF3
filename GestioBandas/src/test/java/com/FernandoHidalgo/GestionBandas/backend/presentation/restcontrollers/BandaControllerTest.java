package com.FernandoHidalgo.GestionBandas.backend.presentation.restcontrollers;

import com.fernandoHidalgo.gestionBandas.backend.business.model.Bandas;
import com.fernandoHidalgo.gestionBandas.backend.business.services.BandaServices;
import com.fernandoHidalgo.gestionBandas.backend.presentation.restcontrollers.BandaController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BandaController.class)
public class BandaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BandaServices bandaServices;

    private Bandas banda1;
    private Bandas banda2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        banda1 = new Bandas("Metallica", "Rock", LocalDate.of(1981, 10, 28), "USA", true);
        banda2 = new Bandas("Nirvana", "Grunge", LocalDate.of(1987, 1, 1), "USA", false);
    }

    @Test
    void testGetAllBandas() throws Exception {
        List<Bandas> bandas = Arrays.asList(banda1, banda2);
        when(bandaServices.getAll()).thenReturn(bandas);

        mockMvc.perform(get("/bandas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetBandaById() throws Exception {
        Long id = 1L;
        when(bandaServices.read(id)).thenReturn(Optional.of(banda1));

        mockMvc.perform(get("/bandas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value(banda1.getNom()));
    }

    @Test
    void testCreateBanda() throws Exception {
        when(bandaServices.create(any(Bandas.class))).thenReturn(1L);

        mockMvc.perform(post("/bandas")
                .content(objectMapper.writeValueAsString(banda1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/bandas/1"));
    }

    @Test
    void testDeleteBanda() throws Exception {
        Long id = 1L;
        doNothing().when(bandaServices).delete(id);

        mockMvc.perform(delete("/bandas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(bandaServices, times(1)).delete(id);
    }

    @Test
    void testUpdateBanda() throws Exception {
        Long id = 1L;
        banda1.setId(id);
        doNothing().when(bandaServices).update(banda1);

        mockMvc.perform(put("/bandas/{id}", id)
                .content(objectMapper.writeValueAsString(banda1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(bandaServices, times(1)).update(banda1);
    }

    @Test
    void testExceptionHandler() throws Exception {
        String errorMessage = "Error message";
        when(bandaServices.getAll()).thenThrow(new IllegalArgumentException(errorMessage));

        mockMvc.perform(get("/bandas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value(errorMessage));
    }
}
