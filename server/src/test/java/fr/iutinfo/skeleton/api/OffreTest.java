package fr.iutinfo.skeleton.api;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OffreTest {
    @Test
    public void should_set_salt_at_build () {
        Offre offre= new Offre();
        assertNotNull(offre.getId());
        assertFalse(offre.getIntitule().equals(""));
        assertNotNull(offre.getDateDeb());
        assertNotNull(offre.getDateFin());
    }
}
