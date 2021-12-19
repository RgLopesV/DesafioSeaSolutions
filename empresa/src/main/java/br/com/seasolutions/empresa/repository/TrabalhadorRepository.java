package br.com.seasolutions.empresa.repository;

import br.com.seasolutions.empresa.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador , Long> {

}
