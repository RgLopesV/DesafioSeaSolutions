package br.com.seasolutions.empresa.dto;

import br.com.seasolutions.empresa.enums.Sexo;
import br.com.seasolutions.empresa.model.Trabalhador;

import java.util.List;
import java.util.stream.Collectors;

public class TrabalhadorResponseDto {



    public TrabalhadorResponseDto(Trabalhador trabalhador) {
        this.chapa = trabalhador.getId();
        this.nome = trabalhador.getNome();
        this.sexo = trabalhador.getSexo();
        this.cpf = trabalhador.getCpf();
        this.setor = trabalhador.getSetor().getNome();
        this.cargo = trabalhador.getCargo().getNome();
    }

    private Long chapa;
    private String nome;
    private String cpf;


    private Sexo sexo;
    private  String cargo;
    private  String  setor;

    public static TrabalhadorResponseDto converter(Trabalhador trabalhador) {
        return  new TrabalhadorResponseDto(trabalhador);
    }

    public String getCargo() { return cargo;  }

    public String getCpf() {  return cpf;  }

    public String getSetor() {  return setor;  }

    public Long getChapa() { return chapa; }

    public String getNome() { return nome; }

    public Sexo getSexo() { return sexo;  }

    public static List<TrabalhadorResponseDto> converterLista(List<Trabalhador> trabalhadores){

        return trabalhadores.stream().map(TrabalhadorResponseDto::new).collect(Collectors.toList());
    }






}
