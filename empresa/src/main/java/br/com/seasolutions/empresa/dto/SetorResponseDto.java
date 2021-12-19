package br.com.seasolutions.empresa.dto;

import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.model.Setor;

import java.util.List;
import java.util.stream.Collectors;

public class SetorResponseDto {

    public SetorResponseDto(Setor setor) {
        this.id = setor.getId();
        this.nome = setor.getNome();
        this.cargos = setor.getCargos();
    }

    private Long id;
    private String nome;
    private List<Cargo> cargos;


    public Long getId() { return id; }

    public String getNome() { return nome; }

    public List<Cargo> getCargos() { return cargos; }

    public static SetorResponseDto converter(Setor setor) {
        return new SetorResponseDto(setor);
    }

    public static List<SetorResponseDto> converterLista(List<Setor> setores) {
        return setores.stream().map(SetorResponseDto::new).collect(Collectors.toList());
    }

}
