package br.com.seasolutions.empresa.model;


import br.com.seasolutions.empresa.enums.Sexo;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;



@Entity
@Table(name = "trabalhadores")
public class Trabalhador  {

    public Trabalhador() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true , nullable = false)
    private String cpf;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",updatable = true)
    private Setor setor;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(referencedColumnName ="id" , updatable = true)
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    public Trabalhador(String nome, String cpf, Setor setor, Cargo cargo, String sexo) {
        setNome(nome);
        setCpf(cpf);
        setCpf(cpf);
        setSetor(setor);
        setCargo(cargo);
        setSexo(Sexo.valueOf(sexo));
    }

    public Sexo getSexo() { return sexo; }

    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf;  }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


}
