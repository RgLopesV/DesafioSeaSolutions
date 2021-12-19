package br.com.seasolutions.empresa.controller;

import br.com.seasolutions.empresa.dto.SetorResponseDto;
import br.com.seasolutions.empresa.form.SetorForm;
import br.com.seasolutions.empresa.model.Setor;
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

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private CargoRepository cargoRepository;


   @GetMapping
    public List<SetorResponseDto> buscarSetores(){ return SetorResponseDto.converterLista(setorRepository.findAll()); }

    @GetMapping("/{id}")
    public SetorResponseDto buscarSetor(@PathVariable Long id){
        return  SetorResponseDto.converter(setorRepository.getById(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SetorResponseDto> cadastrarSetor(@RequestBody @Valid SetorForm form , UriComponentsBuilder uriBuilder){
        Setor setor = form.converter(cargoRepository);

        setor.getCargos().forEach(cargo -> ValidaCargoJaCadastradoEmOutroSetor.verifica(cargo , setor));

        setorRepository.save(setor);
        URI uri = uriBuilder.path("/setores/{id}").buildAndExpand(setor.getId()).toUri();
        return ResponseEntity.created(uri).body(new SetorResponseDto(setor));
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<SetorResponseDto> editarSetor(@PathVariable Long id, @RequestBody @Valid SetorForm form){
       Setor setor = form.atualizar(id , setorRepository, cargoRepository);
       return ResponseEntity.ok(new SetorResponseDto(setor));
    }

    @DeleteMapping("/{id}" )
    @Transactional
    public void deletarSetor(@PathVariable Long id){
           setorRepository.deleteById(id);
       }
}
