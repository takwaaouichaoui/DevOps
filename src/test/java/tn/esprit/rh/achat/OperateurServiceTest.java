package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OperateurServiceTest {

    @InjectMocks
    private OperateurServiceImpl operateurService = new OperateurServiceImpl();

    @Mock
    private OperateurRepository operateurRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllOperateurs() {
        List<Operateur> operateurs = new ArrayList<>();
        operateurs.add(new Operateur(1L, "John", "Doe", "password1"));
        operateurs.add(new Operateur(2L, "Alice", "Smith", "password2"));

        when(operateurRepository.findAll()).thenReturn(operateurs);

        List<Operateur> result = operateurService.retrieveAllOperateurs();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddOperateur() {
        Operateur newOperateur = new Operateur(3L, "Jane", "Johnson", "password3");

        when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(newOperateur);

        Operateur addedOperateur = operateurService.addOperateur(newOperateur);

        assertEquals("Jane", addedOperateur.getNom());
    }

    @Test
    public void testDeleteOperateur() {
        Long operateurId = 1L;
        operateurService.deleteOperateur(operateurId);

        Mockito.verify(operateurRepository, Mockito.times(1)).deleteById(operateurId);
    }

    @Test
    public void testUpdateOperateur() {
        Operateur updatedOperateur = new Operateur(1L, "Updated", "Name", "newpassword");

        when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(updatedOperateur);

        Operateur result = operateurService.updateOperateur(updatedOperateur);

        assertEquals("Updated", result.getNom());
        assertEquals("newpassword", result.getPassword());
    }

    @Test
    public void testRetrieveOperateur() {
        Long operateurId = 1L;
        Operateur operateur = new Operateur(operateurId, "John", "Doe", "password");

        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(operateur));

        Operateur result = operateurService.retrieveOperateur(operateurId);

        assertEquals("John", result.getNom());
    }
}
