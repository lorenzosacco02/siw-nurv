package it.uniroma3.siw.model;



import jakarta.persistence.*;

@Entity
public class Video {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
     private Long id;
	@ManyToOne
	private Operatore operatore;
}
