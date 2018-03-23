package fr.iutinfo.skeleton.api;

import org.junit.Assert;
import org.junit.Test;

import fr.iutinfo.skeleton.common.dto.OffreDto;


public class OffreTest {
	@Test
	public void should_find_offre_by_intitule() {
		HelperOffre.initDb();
		HelperOffre.createOffre1();
		OffreDao dao = BDDFactory.getDbi().open(OffreDao.class);
		Offre offre = dao.findByIntitule("Offre_1");
		Assert.assertEquals("Offre_1", offre.getIntitule());
	}
	
	 @Test
	 public void should_init_entity_from_dto() {
		 Offre offre = new Offre();
		 OffreDto dto = new OffreDto();
		 dto.setIntitule("thomas");
		 offre.initFromDto(dto);
		 Assert.assertEquals("thomas", offre.getIntitule());
	 }

}
