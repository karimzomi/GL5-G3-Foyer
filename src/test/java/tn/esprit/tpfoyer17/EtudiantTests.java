package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.services.IEtudiantService;

@SpringBootTest
@Slf4j
public class EtudiantTests {

    @Autowired
    IEtudiantService etudiantService;

    @Test
    public void testEtudiant() {
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Ben Salah")
                .prenomEtudiant("Ahmed")
                .cinEtudiant(12345678)
                .build();
        etudiantService.addEtudiant(etudiant);

        assertNotNull(etudiant.getIdEtudiant());

        Etudiant etudiant1 = etudiantService.getEtudiantById(etudiant.getIdEtudiant());

        assertNotNull(etudiant1);

        assertEquals(etudiant.getIdEtudiant(), etudiant1.getIdEtudiant());
        assertEquals(etudiant.getCinEtudiant(), etudiant1.getCinEtudiant());
        assertEquals(etudiant.getNomEtudiant(), etudiant1.getNomEtudiant());
        assertEquals(etudiant.getPrenomEtudiant(), etudiant1.getPrenomEtudiant());

        etudiantService.deleteEtudiant(etudiant.getIdEtudiant());

    }
}
