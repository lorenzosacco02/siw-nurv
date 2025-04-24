package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Anomalia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int gravita;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoDiAnomalia tipoAnomalia;
	@ManyToOne
	private Video video;


	@Override
	public int hashCode() {
		return Objects.hash(gravita, tipoAnomalia, video);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anomalia other = (Anomalia) obj;
		return gravita == other.gravita && tipoAnomalia == other.tipoAnomalia && Objects.equals(video, other.video);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getGravita() {
		return gravita;
	}
	public void setGravita(int gravita) {
		this.gravita = gravita;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public TipoDiAnomalia getTipoAnomalia() {
		return tipoAnomalia;
	}
	public void setTipoAnomalia(TipoDiAnomalia tipoAnomalia) {
		this.tipoAnomalia = tipoAnomalia;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}


}
