package it.uniroma3.siw.repository;

import java.util.List;

import it.uniroma3.siw.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.model.Video;
import org.springframework.data.repository.query.Param;

public interface VideoRepository extends CrudRepository<Video, Long> {
	List<Video> findByTratta(Tratta tratta);
	List<Video> findByUser(User user);

	@Query(value = "SELECT * FROM video WHERE tratta_id = :id AND LOWER(REPLACE(nome, ' ', '')) = :nome", nativeQuery = true)
	Video findByNomeInTratta(@Param("nome") String nome, @Param("id") Long trattaId);

}
