package tn.esprit.tpfoyer17;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.BlocService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
public class BlocTests {

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
    public void testCreateBloc() {
        when(blocRepository.save(bloc)).thenReturn(bloc);
        Bloc createdBloc = blocService.addBloc(bloc);
        assertNotNull(createdBloc);
        assertEquals(bloc, createdBloc);
    }

    @Test
    public void testReadBloc() {
        when(blocRepository.findById(bloc.getIdBloc())).thenReturn(Optional.of(bloc));
        Bloc foundBloc = blocService.getBlocById(bloc.getIdBloc());
        assertNotNull(foundBloc);
        assertEquals(bloc, foundBloc);
    }

    @Test
    public void testUpdateBloc() {
        bloc.setCapaciteBloc(120);
        when(blocRepository.save(bloc)).thenReturn(bloc);
        when(blocRepository.findById(bloc.getIdBloc())).thenReturn(Optional.of(bloc));

        Bloc updatedBloc = blocService.updateBloc(bloc);
        assertNotNull(updatedBloc);
        assertEquals(120, updatedBloc.getCapaciteBloc());
    }
}
