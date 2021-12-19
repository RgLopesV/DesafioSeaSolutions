package br.com.seasolutions.empresa.model;

import br.com.seasolutions.empresa.repository.SetorRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.*;

@Entity
@Table(name = "cargos")
public class Cargo {


    public Cargo() {
    }
    public Cargo(String nome, Setor setor) {
        setNome(nome);
        setSetor(setor);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(unique = true)
    private String nome;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "setor_id", nullable = true )
    private Setor setor;


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
