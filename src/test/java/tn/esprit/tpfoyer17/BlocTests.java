package tn.esprit.tpfoyer17;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.services.IBlocService;
import tn.esprit.tpfoyer17.services.IEtudiantService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class BlocTests {

    @Autowired
    IBlocService blocService;

    @Test
    public void testBloc() {
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();
        blocService.addBloc(bloc);
        Bloc bloc1 = blocService.getBlocById(bloc.getIdBloc());
        assertNotNull(bloc1);
        assertEquals(bloc.getIdBloc(), bloc1.getIdBloc());
        assertEquals(bloc.getNomBloc(), bloc1.getNomBloc());
        assertEquals(bloc.getCapaciteBloc(), bloc1.getCapaciteBloc());
        blocService.deleteBloc(bloc.getIdBloc());
    }
}
