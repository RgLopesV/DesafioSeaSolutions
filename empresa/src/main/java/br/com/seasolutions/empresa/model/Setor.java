package br.com.seasolutions.empresa.model;



import lombok.ToString;

import javax.persistence.*;


import java.util.List;


@Entity
@Table(name="setores")
public class Setor  {

    public Setor() {
    }

    public Setor(String nome, List<Cargo> cargos) {
        setNome(nome);
        setCargos(cargos);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false)
    private String nome;

    @Column(unique = true)
    @OneToMany(mappedBy = "setor" , cascade = CascadeType.ALL)
    private List<Cargo> cargos;

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

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }


}
