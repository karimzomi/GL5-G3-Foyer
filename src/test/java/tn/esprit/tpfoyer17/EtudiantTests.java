package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.services.EtudiantService;

import java.util.Date;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class EtudiantTests {

    @InjectMocks
    EtudiantService etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testEtudiant() {
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Ben Salah")
                .prenomEtudiant("Ahmed")
                .cinEtudiant(12345678)
                .dateNaissance(new Date())
                .reservations(Set.of(Reservation.builder().build()))
                .build();

        assertNotNull(etudiant);
        assertEquals("Ben Salah", etudiant.getNomEtudiant());
        assertEquals("Ahmed", etudiant.getPrenomEtudiant());
        assertEquals(12345678, etudiant.getCinEtudiant());
        assertNotNull(etudiant.getDateNaissance());
        assertNotNull(etudiant.getReservations());

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        assertEquals(etudiant, etudiantService.addEtudiant(etudiant));

        when(etudiantRepository.findById(etudiant.getIdEtudiant())).thenReturn(java.util.Optional.of(etudiant));
        when(etudiantRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        assertEquals(etudiant, etudiantService.getEtudiantById(etudiant.getIdEtudiant()));
        assertEquals(null, etudiantService.getEtudiantById(2L));

        when(etudiantRepository.findAll()).thenReturn(java.util.List.of(etudiant));
        assertEquals(java.util.List.of(etudiant), etudiantService.getAllEtudiants());

        etudiant.setNomEtudiant("Ben Salah Up");
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        assertEquals(etudiant, etudiantService.updateEtudiant(etudiant));

        etudiantService.deleteEtudiant(etudiant.getIdEtudiant());
    };
}
