package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Mappa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imagePath;
	@OneToMany(mappedBy = "mappa", cascade = CascadeType.PERSIST)
	private List<Tratta> tratte;
	@Override
	public int hashCode() {
		return Objects.hash(imagePath, nome);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mappa other = (Mappa) obj;
		return Objects.equals(imagePath, other.imagePath) && Objects.equals(nome, other.nome);
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public List<Tratta> getTratte() {
		return tratte;
	}
	public void setTratte(List<Tratta> tratte) {
		this.tratte = tratte;
	}



}
