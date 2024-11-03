package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.services.EtudiantService;

@ExtendWith(MockitoExtension.class)
class EtudiantTests {

    @InjectMocks
    EtudiantService etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        etudiant = Etudiant.builder()
                .nomEtudiant("Ben Salah")
                .prenomEtudiant("Ahmed")
                .cinEtudiant(12345678)
                .dateNaissance(null)
                .reservations(null)
                .build();
    }

    @Test
    void assertParams() {
        assertEquals("Ben Salah", etudiant.getNomEtudiant());
        assertEquals("Ahmed", etudiant.getPrenomEtudiant());
        assertEquals(12345678, etudiant.getCinEtudiant());
        assertNull(etudiant.getReservations());
        assertNull(etudiant.getDateNaissance());
    }

    @Test
    void testCreateEtudiant() {
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        assertNotNull(savedEtudiant);
        assertEquals(etudiant, savedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testGetEtudiantById_Found() {
        when(etudiantRepository.findById(etudiant.getIdEtudiant())).thenReturn(java.util.Optional.of(etudiant));

        Etudiant foundEtudiant = etudiantService.getEtudiantById(etudiant.getIdEtudiant());

        assertEquals(etudiant, foundEtudiant);
        verify(etudiantRepository, times(1)).findById(etudiant.getIdEtudiant());
    }

    @Test
    void testGetEtudiantById_NotFound() {
        when(etudiantRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        Etudiant foundEtudiant = etudiantService.getEtudiantById(2L);

        assertNull(foundEtudiant);
        verify(etudiantRepository, times(1)).findById(2L);
    }

    @Test
    void testGetAllEtudiants() {
        when(etudiantRepository.findAll()).thenReturn(java.util.List.of(etudiant));

        assertEquals(java.util.List.of(etudiant), etudiantService.getAllEtudiants());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEtudiant() {
        etudiant.setNomEtudiant("Ben Salah Up");
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertEquals(etudiant, updatedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testDeleteEtudiant() {
        doNothing().when(etudiantRepository).deleteById(etudiant.getIdEtudiant());

        etudiantService.deleteEtudiant(etudiant.getIdEtudiant());

        verify(etudiantRepository, times(1)).deleteById(etudiant.getIdEtudiant());
    }
}
