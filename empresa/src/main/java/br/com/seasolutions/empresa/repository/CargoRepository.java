package br.com.seasolutions.empresa.repository;

import br.com.seasolutions.empresa.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
