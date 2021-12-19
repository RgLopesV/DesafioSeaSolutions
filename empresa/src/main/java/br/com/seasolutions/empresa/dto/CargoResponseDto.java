package br.com.seasolutions.empresa.dto;

import br.com.seasolutions.empresa.model.Cargo;

import java.util.List;
import java.util.stream.Collectors;

public class CargoResponseDto {


    public CargoResponseDto(Cargo cargo) {
        this.id = cargo.getId();
        this.cargoNome = cargo.getNome();
        this.setorNome = cargo.getSetor().getNome();
    }

    private Long id;
    private String cargoNome;
    private String setorNome;

    public Long getId() {
        return id;
    }

    public String getCargoNome() {
        return cargoNome;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public static CargoResponseDto converter(Cargo cargo) {
        return new CargoResponseDto(cargo);
    }

    public static List<CargoResponseDto> converterLista(List<Cargo> cargos) {
        return cargos.stream().map(CargoResponseDto::new).collect(Collectors.toList());
    }

}
