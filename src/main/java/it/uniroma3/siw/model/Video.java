package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Video {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Operatore operatore;
	@OneToMany(mappedBy = "video")
	private List<Anomalia> anomalie;
	@ManyToOne
	private Tratta tratta;
	@Column(nullable=false)
	private String filePath;
	@Column(nullable=false)
	private LocalDateTime pubblicazione;


	public LocalDateTime getPubblicazione() {
		return pubblicazione;
	}
	public void setPubblicazione(LocalDateTime pubblicazione) {
		this.pubblicazione = pubblicazione;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Operatore getOperatore() {
		return operatore;
	}
	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}
	public List<Anomalia> getAnomalie() {
		return anomalie;
	}
	public void setAnomalie(List<Anomalia> anomalie) {
		this.anomalie = anomalie;
	}
	public Tratta getTratta() {
		return tratta;
	}
	public void setTratta(Tratta tratta) {
		this.tratta = tratta;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public int hashCode() {
		return Objects.hash(operatore, pubblicazione, tratta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return Objects.equals(operatore, other.operatore) && Objects.equals(pubblicazione, other.pubblicazione)
				&& Objects.equals(tratta, other.tratta);
	}


}
