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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ChambreTests {

    @InjectMocks
    ChambreService chambreService;

    @Mock
    ChambreRepository chambreRepository;

    Chambre chambre;
    List<Chambre> chambreList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        chambre = Chambre.builder()
                .numeroChambre(101)
                .typeChambre(TypeChambre.SIMPLE)
                .build();

        chambreList = List.of(chambre);
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

        when(chambreRepository.findById(2L)).thenReturn(Optional.empty());
        Chambre notFoundChambre = chambreService.getChambreById(2L);

        assertNull(notFoundChambre);
        verify(chambreRepository, times(1)).findById(2L);
    }

    @Test
    void testGetAllChambres() {
        when(chambreRepository.findAll()).thenReturn(chambreList);
        List<Chambre> chambres = chambreService.getAllChambres();

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

    @Test
    void testGetChambresParNomUniversite() {
        String nomUniversite = "ESPRIT";
        when(chambreRepository.findByBlocFoyerUniversiteNomUniversite(nomUniversite)).thenReturn(chambreList);
        List<Chambre> result = chambreService.getChambresParNomUniversite(nomUniversite);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambre, result.get(0));
        verify(chambreRepository, times(1)).findByBlocFoyerUniversiteNomUniversite(nomUniversite);
    }

    @Test
    void testGetChambresParBlocEtTypeKeyWord() {
        long idBloc = 1L;
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        when(chambreRepository.findByBlocIdBlocAndTypeChambre(idBloc, typeChambre)).thenReturn(chambreList);
        List<Chambre> result = chambreService.getChambresParBlocEtTypeKeyWord(idBloc, typeChambre);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambre, result.get(0));
        verify(chambreRepository, times(1)).findByBlocIdBlocAndTypeChambre(idBloc, typeChambre);
    }

    @Test
    void testGetChambresParBlocEtTypeJPQL() {
        long idBloc = 1L;
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        when(chambreRepository.findByBlocIdBlocAndTypeChambreJPQL(idBloc, typeChambre)).thenReturn(chambreList);
        List<Chambre> result = chambreService.getChambresParBlocEtTypeJPQL(idBloc, typeChambre);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambre, result.get(0));
        verify(chambreRepository, times(1)).findByBlocIdBlocAndTypeChambreJPQL(idBloc, typeChambre);
    }

    @Test
    void testGetChambresNonReserveParNomUniversiteEtTypeChambre() {
        String nomUniversite = "ESPRIT";
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        when(chambreRepository.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, typeChambre)).thenReturn(chambreList);
        List<Chambre> result = chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, typeChambre);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambre, result.get(0));
        verify(chambreRepository, times(1)).getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, typeChambre);
    }

    @Test
    void testGetChambreNonReserverScheduled() {
        when(chambreRepository.getChambresNonReserve()).thenReturn(chambreList);
        
        chambreService.getChambreNonReserver();
        
        verify(chambreRepository, times(1)).getChambresNonReserve();
    }
}





