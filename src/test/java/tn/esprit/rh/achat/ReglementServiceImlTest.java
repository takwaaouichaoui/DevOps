import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReglementServiceImplTest {

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Mock
    private ReglementRepository reglementRepository;

    private Reglement testReglement;
    private Facture testFacture;

    @BeforeEach
    void setUp() {
        // Initialisation des objets nécessaires pour les tests
        testFacture = new Facture(); // Vous devrez peut-être initialiser davantage de champs selon votre entité Facture
        testReglement = new Reglement(1L, 50.0f, 25.0f, false, new Date(), testFacture);
    }

    @Test
    public void testAddReglement() {
        // Configuration du comportement du repository
        when(reglementRepository.save(any(Reglement.class))).thenReturn(testReglement);

        // Appel de la méthode du service
        Reglement addedReglement = reglementService.addReglement(testReglement);

        // Vérification des résultats
        assertEquals(1L, addedReglement.getIdReglement());
        assertEquals(50.0f, addedReglement.getMontantPaye());
        assertEquals(25.0f, addedReglement.getMontantRestant());
        assertEquals(false, addedReglement.getPayee());
        // Vérifiez d'autres attributs selon votre entité

        // Vérification des appels au repository
        verify(reglementRepository, times(1)).save(any(Reglement.class));
    }

    // Ajoutez d'autres tests selon les méthodes de votre service

}
