package br.com.seasolutions.empresa.repository;

import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.model.Setor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@DataJpaTest
public class SetorRepositoryTest {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private SetorRepository setorRepository;





    @Test
    public void persistindoSetorNoBanco(){
        Long idCargo = 1L;
        String nome = "Desenvolvimento";
        List<Cargo> cargos = new ArrayList<>();
        cargos.add(cargoRepository.getById(idCargo));

        Setor setor = new Setor(nome , cargos);
        setorRepository.save(setor);

       Assertions.assertThat(setor.getId()).isNotNull();
       Assertions.assertThat(setor.getNome()).isNotNull();
        Assertions.assertThat(setor.getNome()).isNotEmpty();
       Assertions.assertThat(setor.getCargos()).isNotNull();
       Assertions.assertThat(setor.getNome()).isEqualTo(nome);
    }

    @Test
    public void buscarUmSetorDoBanco(){
        Long idSetor = 1L;
        Setor setor = setorRepository.getById(idSetor);

        Assertions.assertThat(setor.getId()).isNotNull();
        Assertions.assertThat(setor.getNome()).isNotNull();
        Assertions.assertThat(setor.getCargos()).isNotNull();
    }

    @Test
    public void AtualizaUmSetorDoBanco(){
        String nome = "CargoTeste 123";
        Long idCargo = 1L;
        Long idCargo1 = 2L;
        Long idCargo2 = 3L;
        Long idSetor = 2L;

        List<Cargo> cargos =  new ArrayList<>();

        cargos.add(cargoRepository.getById(idCargo));
        cargos.add(cargoRepository.getById(idCargo1));
        cargos.add(cargoRepository.getById(idCargo2));

        Setor setor = setorRepository.getById(idSetor);

        setor.setNome(nome);
        setor.setCargos(cargos);




        Assertions.assertThat(setor.getId()).isNotNull();
        Assertions.assertThat(setor.getNome()).isNotNull();
        Assertions.assertThat(setor.getNome()).isNotEmpty();
        Assertions.assertThat(setor.getCargos()).isNotNull();
        Assertions.assertThat(setor.getCargos()).isNotEmpty();
        Assertions.assertThat(setor.getNome()).isEqualTo("CargoTeste 123");
        Assertions.assertThat(setor.getCargos().size()).isEqualTo(3);
    }

    @Test
    public void DeletarUmSetorDoBanco(){
        Long idSetor = 1L ;

        setorRepository.deleteById(idSetor);

        Assertions.assertThat(setorRepository.existsById(idSetor)).isEqualTo(false);
    }


}
