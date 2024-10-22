package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;
import tn.esprit.tpfoyer17.services.ChambreService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ChambreServiceTests {

    @InjectMocks
    ChambreService chambreService;

    @Mock
    ChambreRepository chambreRepository;

    Chambre chambre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        chambre = Chambre.builder()
                .numeroChambre(101)
                .typeChambre(TypeChambre.SIMPLE)
                .build();
    }

    @Test
    void testAddChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);
        Chambre savedChambre = chambreService.addChambre(chambre);

        assertNotNull(savedChambre);
        assertEquals(chambre, savedChambre);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testGetChambreById() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));
        Chambre foundChambre = chambreService.getChambreById(1L);

        assertNotNull(foundChambre);
        assertEquals(chambre, foundChambre);
        verify(chambreRepository, times(1)).findById(1L);

        // Test when chambre is not found
        when(chambreRepository.findById(2L)).thenReturn(Optional.empty());
        Chambre notFoundChambre = chambreService.getChambreById(2L);

        assertNull(notFoundChambre);
        verify(chambreRepository, times(1)).findById(2L);
    }

    @Test
    void testGetAllChambres() {
        when(chambreRepository.findAll()).thenReturn(java.util.List.of(chambre));
        var chambres = chambreService.getAllChambres();

        assertNotNull(chambres);
        assertEquals(1, chambres.size());
        assertEquals(chambre, chambres.get(0));
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testUpdateChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);
        Chambre updatedChambre = chambreService.updateChambre(chambre);

        assertNotNull(updatedChambre);
        assertEquals(chambre, updatedChambre);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testDeleteChambre() {
        doNothing().when(chambreRepository).deleteById(1L);
        chambreService.deleteChambre(1L);

        verify(chambreRepository, times(1)).deleteById(1L);
    }
}
