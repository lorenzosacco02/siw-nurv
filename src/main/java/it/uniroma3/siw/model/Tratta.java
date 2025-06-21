package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tratta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Inserisci un nome per la tratta")
	@Column(nullable=false)
	private String nome;

	@NotBlank(message = "Inserisci una descrizione per la tratta")
	private String descrizione;

	@OneToMany(mappedBy="tratta", cascade=CascadeType.REMOVE)
	private List<Video> videoAssociati;

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

	@Override
	public int hashCode() {
		return Objects.hash(descrizione, nome);
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
		return Objects.equals(descrizione, other.descrizione) && Objects.equals(nome, other.nome);
	}


}
