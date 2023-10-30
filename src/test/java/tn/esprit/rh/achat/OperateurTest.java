package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tn.esprit.rh.achat.entities.Operateur;

public class OperateurTest {

    private Operateur operateur;

    @BeforeEach
    public void setUp() {
        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("John");
        operateur.setPrenom("Doe");
        operateur.setPassword("secret");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, operateur.getIdOperateur());
        assertEquals("John", operateur.getNom());
        assertEquals("Doe", operateur.getPrenom());
        assertEquals("secret", operateur.getPassword());
    }
}
