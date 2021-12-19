package br.com.seasolutions.empresa.form;

import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.model.Setor;
import br.com.seasolutions.empresa.repository.CargoRepository;
import br.com.seasolutions.empresa.repository.SetorRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class CargoForm {

    public CargoForm(String nome, Long idSetor) {
        setNome(nome);
        setIdSetor(idSetor);
    }

    @NotNull @NotEmpty @NotBlank
    private String nome;

    @NotNull
    private Long idSetor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }


    public Cargo converter(SetorRepository setorRepository) {

        Setor setor = setorRepository.getById(idSetor);

        return  new Cargo( nome , setor);

    }

    public Cargo atualizar(Long id, CargoRepository cargoRepository , SetorRepository setorRepository) {

        Setor setor = setorRepository.getById(getIdSetor());

        Cargo cargo = cargoRepository.getById(id);

        cargo.setNome(getNome());
        cargo.setSetor(setor);


    return cargo;
    }
}
