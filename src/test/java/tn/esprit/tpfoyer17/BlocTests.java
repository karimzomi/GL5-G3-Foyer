package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;
import tn.esprit.tpfoyer17.services.BlocService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlocTests {

    @InjectMocks
    BlocService blocService;

    @Mock
    BlocRepository blocRepository;

    @Mock
    ChambreRepository chambreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testAddBloc() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(100);
        bloc.setChambres(java.util.Set.of(new Chambre()));
        bloc.setFoyer(new Foyer());

        when(blocRepository.save(bloc)).thenReturn(bloc);
        assertEquals(bloc, blocService.addBloc(bloc));
        assertEquals("Bloc A", bloc.getNomBloc());
        assertEquals(100, bloc.getCapaciteBloc());
        assertNotNull(bloc.getChambres());
        assertNotNull(bloc.getFoyer());
    }

    @Test
    void testGetBlocById() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        when(blocRepository.findById(1L)).thenReturn(java.util.Optional.of(bloc));
        when(blocRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        assertEquals(bloc, blocService.getBlocById(1L));
        assertEquals(null, blocService.getBlocById(2L));
    }

    @Test
    void testGetAllBlocs() {
        Bloc bloc = new Bloc();
        when(blocRepository.findAll()).thenReturn(java.util.List.of(bloc));
        assertEquals(java.util.List.of(bloc), blocService.getAllBlocs());
    }

    @Test
    void testUpdateBloc() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        when(blocRepository.save(bloc)).thenReturn(bloc);
        assertEquals(bloc, blocService.updateBloc(bloc));
    }

    @Test
    void testDeleteBloc() {
        long idBloc = 1L;
        blocService.deleteBloc(idBloc);
        verify(blocRepository, times(1)).deleteById(idBloc);
    }

    @Test
    void testAffecterChambresABloc() {
        Bloc bloc = new Bloc();
        List<Long> numChambres = java.util.List.of(1L, 2L);
        List<Chambre> chambres = java.util.List.of(new Chambre(), new Chambre());

        when(blocRepository.findById(1L)).thenReturn(java.util.Optional.of(bloc));
        when(blocRepository.findById(2L)).thenReturn(Optional.empty());
        when(chambreRepository.findAllById(numChambres)).thenReturn(chambres);

        Bloc result = blocService.affecterChambresABloc(numChambres, 1L);
        Bloc nullResult = blocService.affecterChambresABloc(numChambres, 2L);
        assertNull(nullResult);
        assertEquals(bloc, result);
    }
}
