package com.sindocker.sindocker;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sindocker.sindocker.controllers.MedianoController;
import com.sindocker.sindocker.dto.MedianoDTO;
import com.sindocker.sindocker.models.Mediano;
import com.sindocker.sindocker.services.IServiceMediano;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedianoController.class)
class MedianoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IServiceMediano medianoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetMedianos() throws Exception {
        MedianoDTO dto = new MedianoDTO("Ana", 120, "ana@email.com");
        Mockito.when(medianoService.getMedianos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/medianos/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ana"))
                .andExpect(jsonPath("$[0].altura").value(120))
                .andExpect(jsonPath("$[0].email").value("ana@email.com"));
    }

    @Test
    void testAddMediano() throws Exception {
        MedianoDTO dto = new MedianoDTO("Pedro", 130, "pedro@email.com");

        mockMvc.perform(post("/api/medianos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        Mockito.verify(medianoService).addMediano(Mockito.eq(dto));
    }

    @Test
    void testFiltrado() throws Exception {
        Mediano mediano = new Mediano("1", "Alberto", 135, "a@email.com", Set.of());
        Mockito.when(medianoService.getByName("Alberto")).thenReturn(Optional.of(mediano));

        mockMvc.perform(get("/api/medianos/filtrado/Alberto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alberto"))
                .andExpect(jsonPath("$.height").value(135));
    }


}
