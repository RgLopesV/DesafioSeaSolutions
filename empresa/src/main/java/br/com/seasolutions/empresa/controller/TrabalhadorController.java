package br.com.seasolutions.empresa.controller;

import br.com.seasolutions.empresa.dto.TrabalhadorResponseDto;
import br.com.seasolutions.empresa.form.TrabalhadorForm;
import br.com.seasolutions.empresa.model.Trabalhador;
import br.com.seasolutions.empresa.repository.CargoRepository;
import br.com.seasolutions.empresa.repository.SetorRepository;
import br.com.seasolutions.empresa.repository.TrabalhadorRepository;
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
@RequestMapping("/trabalhadores")
public class TrabalhadorController {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private CargoRepository cargoRepository;


    @GetMapping
    public List<TrabalhadorResponseDto> buscartrabalhadores() {

        return TrabalhadorResponseDto.converterLista(trabalhadorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabalhadorResponseDto> buscarTrabalhador(@PathVariable Long id) {
        Optional<Trabalhador> optional = trabalhadorRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(TrabalhadorResponseDto.converter(trabalhadorRepository.getById(id)));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TrabalhadorResponseDto> cadastrarTrabalhador(@RequestBody @Valid TrabalhadorForm form, UriComponentsBuilder uriBuilder) {
        Trabalhador trabalhador = form.converter(setorRepository, cargoRepository);

        trabalhadorRepository.save(trabalhador);

        URI uri = uriBuilder.path("/trabalhadores/{id}").buildAndExpand(trabalhador.getId()).toUri();
        return ResponseEntity.created(uri).body(new TrabalhadorResponseDto(trabalhador));
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<TrabalhadorResponseDto> editarTrabalhador(@PathVariable Long id, @RequestBody @Valid TrabalhadorForm form) {
        Optional<Trabalhador> optional = trabalhadorRepository.findById(id);

        if (optional.isPresent()) {
            ValidaCargoJaCadastradoEmOutroSetor.verifica(cargoRepository.getById(form.getIdCargo()), setorRepository.getById(form.getIdSetor()));
            Trabalhador trabalhador = form.Atualizar(id, trabalhadorRepository, setorRepository, cargoRepository);
            return ResponseEntity.ok(new TrabalhadorResponseDto(trabalhador));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarTrabalhador(@PathVariable Long id) {
        trabalhadorRepository.deleteById(id);

    }
}
