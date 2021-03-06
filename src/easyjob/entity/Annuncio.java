package easyjob.entity;

import java.util.List;

public class Annuncio {
	
	//Parametri
	private int idAnnuncio;
	private String titolo;
	private String descrizione;
	private String requisiti;
	private List<String> tags;
	private String tipoContratto;
	private String data;
	private int azienda;
	private String nomeAzienda;
	private String citt�;
	
	
	//Costruttore
	public Annuncio(int idAnnuncio, String titolo, String descrizione, String requisiti, List<String> tags,
			String tipoContratto, String data, int azienda,String citt�) {
		this.idAnnuncio = idAnnuncio;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.requisiti = requisiti;
		this.tags = tags;
		this.tipoContratto = tipoContratto;
		this.data = data;
		this.azienda = azienda;
		this.citt� = citt�;
		this.nomeAzienda = nomeAzienda;
	}
	
	public Annuncio() {
		
	}

	//Getters and setters
	
	
	public String getCitt�(){
		return citt�;
	}
	
	public void setCitt�(String nuovaCitt�){
		this.citt� = nuovaCitt�;
	}
	
	public int getIdAnnuncio() {
		return idAnnuncio;
	}

	public void setIdAnnuncio(int idAnnuncio) {
		this.idAnnuncio = idAnnuncio;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getRequisiti() {
		return requisiti;
	}

	public void setRequisiti(String requisiti) {
		this.requisiti = requisiti;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public void addTag (String tag){
		tags.add(tag);
	}

	public String getTipoContratto() {
		return tipoContratto;
	}

	public void setTipoContratto(String tipoContratto) {
		this.tipoContratto = tipoContratto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getAzienda() {
		return azienda;
	}

	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}
	
}
