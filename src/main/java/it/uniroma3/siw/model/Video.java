package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Video {

	public static final int MAX_SIZE = 50 * 1024 * 1024;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "video", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Anomalia> anomalie;
	
	@ManyToOne
	private Tratta tratta;

	@Transient
	private MultipartFile multipartFile;

	@Column(columnDefinition = "bytea")
	private byte[] file;

	@Past(message = "Inserisci una data nel passato")
	@NotNull(message = "Inserisci una data")
	private LocalDate data;

	@NotBlank(message = "Inserisci un Nome")
	private String nome;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, data, tratta);
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
		return Objects.equals(user, other.user) && Objects.equals(data, other.data)
				&& Objects.equals(tratta, other.tratta);
	}


}
