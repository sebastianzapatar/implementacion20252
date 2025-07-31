package com.sindocker.sindocker;
import com.sindocker.sindocker.dto.MedianoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // usa application-test.properties
class MedianoControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testAgregarMedianoCorrecto() {
        MedianoDTO dto = new MedianoDTO("Juana", 130, "juana@email.com");

        ResponseEntity<Void> response = restTemplate.postForEntity(
                "/api/medianos/", dto, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testAgregarMedianoInvalido() {
        MedianoDTO dto = new MedianoDTO("", 90, "invalido");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/medianos/", dto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Ingresar nombre", "Coloca un email");
    }

    @Test
    void testObtenerMedianos() {
        // Primero agregamos uno
        MedianoDTO dto = new MedianoDTO("Lucas", 120, "lucas@email.com");
        restTemplate.postForEntity("/api/medianos/", dto, Void.class);

        // Luego lo pedimos
        ResponseEntity<MedianoDTO[]> response = restTemplate.getForEntity(
                "/api/medianos/", MedianoDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].nombre()).isEqualTo("Lucas");
    }
}
