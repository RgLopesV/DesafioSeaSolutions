package br.com.seasolutions.empresa.repository;

import br.com.seasolutions.empresa.model.Cargo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class CargoRepositoryTest {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private SetorRepository setorRepository;



    @Test
    public void persistindoCargoNoBanco(){
        Long idSetor = 1L;

        Cargo cargo = new Cargo("Operador Nivel I" , setorRepository.getById(idSetor));
        cargoRepository.save(cargo);

       Assertions.assertThat(cargo.getId()).isNotNull();
       Assertions.assertThat(cargo.getNome()).isNotNull();
       Assertions.assertThat(cargo.getSetor()).isNotNull();
        Assertions.assertThat(cargo.getNome()).isNotEmpty();
       Assertions.assertThat(cargo.getNome()).isEqualTo("Operador Nivel I");
       Assertions.assertThat(cargo.getSetor().getId()).isEqualTo(idSetor);
       Assertions.assertThat(cargo.getSetor().getNome()).isEqualTo("Recursos Humanos");
    }

    @Test
    public void buscarUmCargoDoBanco(){
        Long idCargo = 1L ;
        Cargo cargo = cargoRepository.getById(idCargo);

        Assertions.assertThat(cargo.getId()).isNotNull();
        Assertions.assertThat(cargo.getNome()).isNotNull();
        Assertions.assertThat(cargo.getNome()).isNotEmpty();
        Assertions.assertThat(cargo.getSetor()).isNotNull();
    }

    @Test
    public void AtualizaUmCargoDoBanco(){
        Long idCargo = 1L ;
        Long idSetor = 2L;

        Cargo cargo = cargoRepository.getById(idCargo);
        cargo.setSetor(setorRepository.getById(idSetor));
        cargo.setNome("CargoTeste 123");

        Assertions.assertThat(cargo.getId()).isNotNull();
        Assertions.assertThat(cargo.getNome()).isNotNull();
        Assertions.assertThat(cargo.getNome()).isNotEmpty();
        Assertions.assertThat(cargo.getSetor()).isNotNull();
        Assertions.assertThat(cargo.getNome()).isEqualTo("CargoTeste 123");
        Assertions.assertThat(cargo.getSetor()).isEqualTo(setorRepository.getById(idSetor));
    }

    @Test
    public void DeletarUmCargoDoBanco(){
        Long idCargo = 1L ;

        cargoRepository.deleteById(idCargo);

        Assertions.assertThat(cargoRepository.existsById(idCargo)).isEqualTo(false);
    }


}
