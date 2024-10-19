package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.BlocService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlocTests {

    @InjectMocks
    BlocService blocService;

    @Mock
    private BlocRepository blocRepository;

    private Bloc bloc;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        bloc = Bloc.builder()
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();
    }

    @Test
    void testCreateBloc() {
        when(blocRepository.save(bloc)).thenReturn(bloc);
        Bloc createdBloc = blocService.addBloc(bloc);
        assertNotNull(createdBloc);
        assertEquals(bloc, createdBloc);
    }

    @Test
    void testReadBloc() {
        when(blocRepository.findById(bloc.getIdBloc())).thenReturn(Optional.of(bloc));
        Bloc foundBloc = blocService.getBlocById(bloc.getIdBloc());
        assertNotNull(foundBloc);
        assertEquals(bloc, foundBloc);
    }

    @Test
    void testUpdateBloc() {
        bloc.setCapaciteBloc(120);
        when(blocRepository.save(bloc)).thenReturn(bloc);
        Bloc updatedBloc = blocService.updateBloc(bloc);
        assertNotNull(updatedBloc);
        assertEquals(120, updatedBloc.getCapaciteBloc());
    }
}
