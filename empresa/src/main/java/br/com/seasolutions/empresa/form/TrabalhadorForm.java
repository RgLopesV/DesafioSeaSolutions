package br.com.seasolutions.empresa.form;



import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.seasolutions.empresa.enums.Sexo;
import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.model.Setor;
import br.com.seasolutions.empresa.model.Trabalhador;
import br.com.seasolutions.empresa.repository.CargoRepository;
import br.com.seasolutions.empresa.repository.SetorRepository;
import br.com.seasolutions.empresa.repository.TrabalhadorRepository;



import javax.validation.constraints.*;
import java.util.List;

public class TrabalhadorForm {

    @NotNull  @NotEmpty @NotBlank
    private String nome;

    @NotNull @NotEmpty @NotBlank
    private String cpf;

    @NotNull
    private Long idSetor;

    @NotNull
    private Long idCargo;

    @NotNull @NotEmpty
    private String sexo;

    public TrabalhadorForm(String nome, String cpf, Long idSetor, Long idCargo, String sexo) {
        setNome(nome);
        if(valida(cpf)) {
            setCpf(limpacpf(cpf));
        }
        setIdSetor(idSetor);
        setIdCargo(idCargo);
        setSexo(sexo);
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSecao) {
        this.idSetor = idSecao;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Trabalhador converter(SetorRepository setorRepository, CargoRepository cargoRepository) {

        Cargo cargo = cargoRepository.getById(idCargo);
        Setor setor = setorRepository.getById(idSetor);

        return new Trabalhador(nome , cpf , setor , cargo , sexo);
    }

    public Trabalhador Atualizar(Long id, TrabalhadorRepository trabalhadorRepository, SetorRepository setorRepository, CargoRepository cargoRepository) {
        Trabalhador trabalhador =trabalhadorRepository.getById(id);

        Setor setor = setorRepository.getById(getIdSetor());
        Cargo cargo = cargoRepository.getById(getIdCargo());

        cargo.setSetor(setor);


            trabalhador.setNome(getNome());
            trabalhador.setCpf(getCpf());
            trabalhador.setCargo(cargo);
            trabalhador.setSetor(setor);
            trabalhador.setSexo(Sexo.valueOf(getSexo()));



        return  trabalhador;
    }

    public boolean valida(String cpf){
              CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
        return erros.size() <= 0;
    }
    public String limpacpf(String cpf){
        cpf = cpf.replaceAll("\\.","");
        cpf = cpf.replaceAll("-","");

        return cpf;
    }

}
