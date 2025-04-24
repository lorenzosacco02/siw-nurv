package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Operatore {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String cognome;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private LocalDate dataDiNascita;
	private LocalDateTime dataDiRegistrazione;
	@OneToMany(mappedBy="operatore")
	private List<Video> videoCaricati;


	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataDiNascita, email, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operatore other = (Operatore) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataDiNascita, other.dataDiNascita)
				&& Objects.equals(email, other.email) && Objects.equals(nome, other.nome);
	}
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public LocalDateTime getDataDiRegistrazione() {
		return dataDiRegistrazione;
	}
	public void setDataDiRegistrazione(LocalDateTime dataDiRegistrazione) {
		this.dataDiRegistrazione = dataDiRegistrazione;
	}
	public List<Video> getVideoCaricati() {
		return videoCaricati;
	}
	public void setVideoCaricati(List<Video> videoCaricati) {
		this.videoCaricati = videoCaricati;
	}


}
