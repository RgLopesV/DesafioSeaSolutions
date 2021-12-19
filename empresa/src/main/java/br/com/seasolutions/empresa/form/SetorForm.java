package br.com.seasolutions.empresa.form;

import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.model.Setor;
import br.com.seasolutions.empresa.repository.CargoRepository;
import br.com.seasolutions.empresa.repository.SetorRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SetorForm {


    public SetorForm(String nome, List<Long> idCargos) {
        setNome(nome);
        setIdCargos(idCargos);
    }


    @NotNull @NotEmpty @NotBlank
    private String nome;

    private List<Long> idCargos;


    public String getNome() {
        return nome;
    }

    public List<Long> getIdCargos() {
        return idCargos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCargos(List<Long> idCargos) {
        this.idCargos = idCargos;
    }


    public Setor converter(CargoRepository cargoRepository) {

        List<Cargo> cargos = cargoRepository.findAllById(idCargos);

        return new Setor(nome , cargos );
    }

    public Setor atualizar(Long id, SetorRepository setorRepository, CargoRepository cargoRepository) {
        Setor setor = setorRepository.getById(id);
                List<Cargo> cargos = cargoRepository.findAllById(getIdCargos());

        if(cargos.isEmpty()){
            for (Cargo cargo : setor.getCargos()) {
                cargoRepository.deleteById(cargo.getId());
            }
            setor.setCargos(cargos);
        }else{
            cargos.forEach(c -> c.setSetor(setor));
        }

        setor.setNome(this.getNome());
        return  setor;
    }
}
