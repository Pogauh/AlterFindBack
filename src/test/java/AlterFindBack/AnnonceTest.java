package AlterFindBack;

import AlterFindBack.controller.AnnonceController;
import AlterFindBack.entities.Annonce;
import AlterFindBack.repositories.AnnonceRepository;
import AlterFindBack.service.AnnonceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;




import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnnonceController.class)
@ExtendWith(MockitoExtension.class)
public class AnnonceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnnonceRepository annonceRepository;

    @MockBean
    private AnnonceService annonceService;

    @BeforeEach
    public void setup() {
        annonceService = new AnnonceService();
        annonceService.annonceRepository = annonceRepository;
    }

    @Test
    void shouldGetAnnonceById() throws Exception {
        Long annonceId = 1L;
        Annonce annonce = new Annonce();
        annonce.setId(annonceId);
        annonce.setTitre("Développeur Backend");
        annonce.setDescription("Un métier passionnant");

        when(annonceRepository.findById(annonceId)).thenReturn(Optional.of(annonce));

        mockMvc.perform(get("/annonces/{id}", annonceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titre", is("Développeur Backend")))
                .andExpect(jsonPath("$.description", is("Un métier passionnant")));

        verify(annonceRepository, times(1)).findById(annonceId);
    }

    // Test pour récupérer toutes les annonces
    @Test
    void shouldGetAllAnnonces() throws Exception {
        Annonce annonce1 = new Annonce();
        annonce1.setId(1L);
        annonce1.setTitre("Développeur Backend");
        annonce1.setDescription("Un métier passionnant");
        annonce1.setLocalisation("Lille");
        annonce1.setSalaire("3000");

        Annonce annonce2 = new Annonce();
        annonce2.setId(2L);
        annonce2.setTitre("Développeur Frontend");
        annonce2.setDescription("Création d'interfaces utilisateurs");
        annonce2.setLocalisation("Paris");
        annonce2.setSalaire("4200");
        
        List<Annonce> mockAnnonces = List.of(annonce1, annonce2);
        when(annonceRepository.findAll()).thenReturn(mockAnnonces);

        mockMvc.perform(get("/api/annonces")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].titre", is("Développeur Backend")))
                .andExpect(jsonPath("$[0].description", is("Un métier passionnant")))
                .andExpect(jsonPath("$[0].salaire", is("3000")))
                .andExpect(jsonPath("$[0].localisation", is("Lille")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].titre", is("Développeur Frontend")))
                .andExpect(jsonPath("$[1].description", is("Création d'interfaces utilisateurs")))
                .andExpect(jsonPath("$[1].salaire", is("4200")))
                .andExpect(jsonPath("$[1].localisation", is("Paris")));
    }

    // Test pour créer une annonce
    @Test
    void shouldCreateAnnonce() throws Exception {
        String annonceJson = """
        {
            "titre": "Développeur Backend",
            "description": "Un métier passionnant"
        }
        """;

        mockMvc.perform(post("/annonces")
                        .contentType("application/json")
                        .content(annonceJson))
                .andExpect(status().isCreated());
    }

    // Test pour supprimer une annonce
    @Test
    void shouldDeleteAnnonce() throws Exception {
        Long annonceId = 1L;

        mockMvc.perform(delete("/annonces/{id}", annonceId))
                .andExpect(status().isNoContent());
    }

    // Test pour mettre à jour une annonce
    @Test
    void shouldUpdateAnnonce() throws Exception {
        Long annonceId = 1L;
        String updatedAnnonceJson = """
        {
            "titre": "Développeur Fullstack",
            "description": "Passionné par le développement front et backend"
        }
        """;

        mockMvc.perform(patch("/annonces/{id}", annonceId)
                        .contentType("application/json")
                        .content(updatedAnnonceJson))
                .andExpect(status().isOk());
    }
}
