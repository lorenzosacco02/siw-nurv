package it.uniroma3.siw.repository;

import java.util.List;

import it.uniroma3.siw.model.User;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.model.Video;

public interface VideoRepository extends CrudRepository<Video, Long> {
	List<Video> findByTratta(Tratta tratta);
	List<Video> findByUser(User user);
}
