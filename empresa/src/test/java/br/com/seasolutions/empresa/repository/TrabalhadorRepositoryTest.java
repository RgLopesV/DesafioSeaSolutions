package br.com.seasolutions.empresa.repository;

import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.model.Setor;
import br.com.seasolutions.empresa.model.Trabalhador;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class TrabalhadorRepositoryTest {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;



    @Test
    public void persistindoTrabalhadorNoBanco(){
        Long idSetor = 1L;
        Long idCargo = 1L;

        String nome = "Edward Elric";
        String cpf = "324.270.370-79";
        String sexo = "FEMININO";

        Trabalhador trabalhador = new Trabalhador( nome, cpf ,  setorRepository.getById(idSetor) , cargoRepository.getById(idCargo) , sexo);


       Assertions.assertThat(trabalhador.getNome()).isNotNull();
       Assertions.assertThat(trabalhador.getSetor()).isNotNull();
        Assertions.assertThat(trabalhador.getNome()).isNotEmpty();
        Assertions.assertThat(trabalhador.getCpf()).isNotNull();
        Assertions.assertThat(trabalhador.getCpf()).isNotEmpty();
       Assertions.assertThat(trabalhador.getCargo().getNome()).isEqualTo("Diretor de Recursos Humanos");
       Assertions.assertThat(trabalhador.getSetor().getId()).isEqualTo(idSetor);
       Assertions.assertThat(trabalhador.getSetor().getNome()).isEqualTo("Recursos Humanos");
    }

    @Test
    public void buscarUmTrabalhadorDoBanco(){
        Long idTrabalhador = 1L;

        Trabalhador trabalhador = trabalhadorRepository.getById(idTrabalhador);

        Assertions.assertThat(trabalhador.getId()).isNotNull();
        Assertions.assertThat(trabalhador.getNome()).isNotNull();
        Assertions.assertThat(trabalhador.getSetor()).isNotNull();
        Assertions.assertThat(trabalhador.getCpf()).isNotNull();
        Assertions.assertThat(trabalhador.getCpf()).isNotEmpty();
        Assertions.assertThat(trabalhador.getNome()).isNotEmpty();
        Assertions.assertThat(trabalhador.getCargo().getNome()).isEqualTo("Diretor de Recursos Humanos");
        Assertions.assertThat(trabalhador.getSetor().getNome()).isEqualTo("Recursos Humanos");
    }

    @Test
    public void AtualizaUmTrabalhadorDoBanco(){
        Long idCargo = 1L ;
        Long idSetor = 1L;
        Long idTrabalhador = 1L;

        Trabalhador trabalhador = trabalhadorRepository.getById(idTrabalhador);
        trabalhador.setSetor(setorRepository.getById(idSetor));
        trabalhador.setCargo(cargoRepository.getById(idCargo));
        trabalhador.setNome("Trabalahador 123");

        Assertions.assertThat(trabalhador.getId()).isNotNull();
        Assertions.assertThat(trabalhador.getNome()).isNotNull();
        Assertions.assertThat(trabalhador.getNome()).isNotEmpty();
        Assertions.assertThat(trabalhador.getNome()).isNotEmpty();
        Assertions.assertThat(trabalhador.getSetor()).isNotNull();
        Assertions.assertThat(trabalhador.getNome()).isEqualTo("Trabalahador 123");
        Assertions.assertThat(trabalhador.getSetor()).isEqualTo(setorRepository.getById(idSetor));
    }

    @Test
    public void DeletarUmTrabalhadorDoBanco(){
        Long idTrabalhador = 1L ;

        trabalhadorRepository.deleteById(idTrabalhador);

        Assertions.assertThat(trabalhadorRepository.existsById(idTrabalhador)).isEqualTo(false);
    }


}
