package br.com.seasolutions.empresa.dto;

import static br.com.seasolutions.empresa.config.MensagemConstantesConfig.*;

public class ErrorsResponseDto {


    private String mensagem;
    private String erro;


    public ErrorsResponseDto(String erro, String mensagem) {
        this.mensagem = mensagem;
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getErro() {
        return erro;
    }

    public static ErrorsResponseDto Converte(String erro , String mensagem){


        if(mensagem.contains(CLASSE_CARGO_EN) || mensagem.contains(ENTIDADE_CARGO_EN)){
            mensagem = mensagem.replaceAll(mensagem, CARGO_PT);
        }else if(mensagem.contains(CLASSE_SETOR_EN) || mensagem.contains(ENTIDADE_SETOR_EN)){
            mensagem = mensagem.replaceAll(mensagem, SETOR_PT);
        }else if(mensagem.contains(CLASSE_TRABALHADOR_EN) ){
            mensagem = mensagem.replaceAll(mensagem, TRABALHADOR_PT);
        }

        return new ErrorsResponseDto(erro , mensagem);
    }
}
