package br.com.seasolutions.empresa.controller;

import br.com.seasolutions.empresa.dto.CargoResponseDto;
import br.com.seasolutions.empresa.form.CargoForm;
import br.com.seasolutions.empresa.model.Cargo;
import br.com.seasolutions.empresa.repository.CargoRepository;
import br.com.seasolutions.empresa.repository.SetorRepository;
import br.com.seasolutions.empresa.validation.ValidaCargoJaCadastradoEmOutroSetor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private SetorRepository setorRepository;

    @GetMapping
    public List<CargoResponseDto>  buscarCargos(){

        return  CargoResponseDto.converterLista(cargoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCargo(@PathVariable Long id){

           return ResponseEntity.ok(CargoResponseDto.converter(cargoRepository.getById(id)));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CargoResponseDto> cadastrarCargo(@RequestBody @Valid CargoForm form, UriComponentsBuilder uriBuilder){

            Cargo cargo = form.converter(setorRepository);
            cargoRepository.save(cargo);

            URI uri = uriBuilder.path("/cargos/{id}").buildAndExpand(cargo.getId()).toUri();
            return ResponseEntity.created(uri).body(new CargoResponseDto(cargo));
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<CargoResponseDto> editarCargo(@PathVariable Long id, @RequestBody @Valid CargoForm form){
        Optional<Cargo> optional  =  cargoRepository.findById(id);

        if(optional.isPresent()) {
            ValidaCargoJaCadastradoEmOutroSetor.verifica(cargoRepository.getById(id), setorRepository.getById(form.getIdSetor()));
            Cargo cargo = form.atualizar(id, cargoRepository, setorRepository);
            return ResponseEntity.ok(new CargoResponseDto(cargo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarCargo(@PathVariable Long id){


        cargoRepository.deleteById(id);
    }
}
