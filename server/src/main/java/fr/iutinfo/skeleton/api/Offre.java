package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.OffreDto;

public class Offre {
	final static Logger logger = LoggerFactory.getLogger(Offre.class);
	private int id = 0;
	private String intitule;
	private String dateDeb;
	private String dateFin;
	private String listeMots;
	private int idEntreprise;
	private String nomEntreprise;
	private String champLibre;


	public Offre(int id, String intitule) {
		this.id = id;
		this.intitule = intitule;
	}

	public Offre(int id, String intitule, String dateDeb, String dateFin, String listeMots, int idEntreprise, String nomEntreprise, String champLibre) {
		this.id = id;
		this.intitule = intitule;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.listeMots = listeMots;
		this.idEntreprise = idEntreprise;
		this.nomEntreprise = nomEntreprise;
		this.champLibre = champLibre;
	}

	public Offre() {
	}

	public int getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(int idEntrepise) {
		this.idEntreprise = idEntrepise;
	}
	

	public String getChampLibre() {
		return champLibre;
	}

	public void setChampLibre(String champLibre) {
		this.champLibre = champLibre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}

	public String getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}


	public void setListeMots(String listeMots) {
		this.listeMots = listeMots;
	}

	public String getListeMots() {
		return listeMots;
	}

	@Override
	public boolean equals(Object arg) {
		if (getClass() != arg.getClass())
			return false;
		Offre offre = (Offre) arg;
		return intitule.equals(offre.intitule) && dateDeb.equals(offre.dateDeb) && dateFin.equals(offre.dateFin)
				&& listeMots.equals(offre.listeMots) && idEntreprise == offre.idEntreprise;
	}

	@Override
	public String toString() {
		String res = id + ": " + intitule + ", " + dateDeb + ", " + dateFin + ", " + listeMots + ", " + idEntreprise;
		return res;
	}

	public void initFromDto(OffreDto dto) {
		this.setIntitule(dto.getIntitule());
		this.setDateDeb(dto.getDateDeb());
		this.setId(dto.getId());
		this.setDateFin(dto.getDateFin());
		this.setListeMots(dto.getListeMots());
		this.setIdEntreprise(dto.getIdEntreprise());
		this.setNomEntreprise(dto.getNomEntreprise());
		this.setChampLibre(dto.getChampLibre());
	}

	public OffreDto convertToDto() {
		OffreDto dto = new OffreDto();
		dto.setIntitule(this.getIntitule());
		dto.setDateDeb(this.getDateDeb());
		dto.setId(this.getId());
		dto.setDateFin(this.getDateFin());
		dto.setListeMots(this.getListeMots());
		dto.setIdEntreprise(this.getIdEntreprise());
		dto.setNomEntreprise(this.getNomEntreprise());
		dto.setChampLibre(this.getChampLibre());
		return dto;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

}
