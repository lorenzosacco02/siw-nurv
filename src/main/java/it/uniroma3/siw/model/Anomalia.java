package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class Anomalia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean risolta = false;

	@Min(value = 1, message = "Inserisci un valore maggiore o uguale a 0")
	@Max(value = 5, message = "Inserisci un valore minore o uguale a 5")
	private int gravita;

	@NotBlank(message = "Inserisci una descrizione")
	@Length(max = 100)
	private String descrizione;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoDiAnomalia tipoAnomalia;

	@ManyToOne
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getRisolta() {
		return risolta;
	}

	public void setRisolta(boolean risolta) {
		this.risolta = risolta;
	}
}
