package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Tratta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String nome;
	private String descrizione;
	@OneToMany(mappedBy="tratta")
	private List<Video> videoAssociati;
	@ManyToOne
	private Mappa mappa;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<Video> getVideoAssociati() {
		return videoAssociati;
	}
	public void setVideoAssociati(List<Video> video) {
		this.videoAssociati = video;
	}
	public Mappa getMappa() {
		return mappa;
	}
	public void setMappa(Mappa mappa) {
		this.mappa = mappa;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mappa, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tratta other = (Tratta) obj;
		return Objects.equals(mappa, other.mappa) && Objects.equals(nome, other.nome);
	}


}
