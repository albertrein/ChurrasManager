package br.git.churras.repository;

import br.git.churras.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Participantes extends JpaRepository<Participante, Long> {

}
