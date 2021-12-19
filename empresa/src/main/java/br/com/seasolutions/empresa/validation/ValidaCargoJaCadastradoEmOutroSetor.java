package br.com.seasolutions.empresa.validation;

import br.com.seasolutions.empresa.exception.CargoJaCadastradoException;
import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.model.Setor;

import static br.com.seasolutions.empresa.config.MensagemConstantesConfig.CARGO_CADASTRADO;
import static br.com.seasolutions.empresa.config.MensagemConstantesConfig.CARGO_JA_ESTA_VINCULADO_A_SETOR;

public class ValidaCargoJaCadastradoEmOutroSetor {


    public static String verifica(Cargo cargo , Setor setor ){

        if(!setor.getCargos().contains(cargo)){
            throw new CargoJaCadastradoException(CARGO_JA_ESTA_VINCULADO_A_SETOR);
        }
        return CARGO_CADASTRADO;
    }

}
