package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.FoyerService;

@ExtendWith(MockitoExtension.class)
class FoyerTests {

    @InjectMocks
    private FoyerService foyerService;

    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    private Foyer foyer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        foyer = new Foyer();
        foyer.setCapaciteFoyer(1L);
        foyer.setNomFoyer("Foyer");
    }

    @Test
    void testAddFoyer() {
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer savedFoyer = foyerService.addFoyer(foyer);

        assertNotNull(savedFoyer);
        assertEquals(foyer, savedFoyer);
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testGetAllFoyers() {
        when(foyerRepository.findAll()).thenReturn(List.of(foyer));

        List<Foyer> foyers = foyerService.getAllFoyers();

        assertEquals(1, foyers.size());
        assertEquals(foyer, foyers.get(0));
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testGetFoyerById_Found() {
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        Foyer foundFoyer = foyerService.getFoyerById(1L);

        assertEquals(foyer, foundFoyer);
        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFoyerById_NotFound() {
        when(foyerRepository.findById(2L)).thenReturn(Optional.empty());

        Foyer foundFoyer = foyerService.getFoyerById(2L);

        assertNull(foundFoyer);
        verify(foyerRepository, times(1)).findById(2L);
    }

    @Test
    void testDeleteFoyer() {
        doNothing().when(foyerRepository).deleteById(1L);

        foyerService.deleteFoyer(1L);

        verify(foyerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateFoyer() {
        foyer.setNomFoyer("Updated Foyer");
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer updatedFoyer = foyerService.updateFoyer(foyer);

        assertEquals(foyer, updatedFoyer);
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testAjouterFoyerEtAffecterAUniversite() {
        Universite universite = new Universite();
        universite.setNomUniversite("Universite");

        when(foyerRepository.save(foyer)).thenReturn(foyer);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        Foyer addedFoyer = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, 1L);

        assertEquals(foyer, addedFoyer);
        assertEquals(foyer, universite.getFoyer());
        verify(foyerRepository, times(1)).save(foyer);
        verify(universiteRepository, times(1)).findById(1L);
        verify(universiteRepository, times(1)).save(universite);
    }
}