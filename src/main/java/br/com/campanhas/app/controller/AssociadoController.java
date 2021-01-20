package br.com.campanhas.app.controller;

import br.com.campanhas.app.dto.request.AssociadoRequest;
import br.com.campanhas.app.dto.request.CampanhaRequest;
import br.com.campanhas.app.dto.response.AssociadoResponse;
import br.com.campanhas.app.dto.response.CampanhaResponse;
import br.com.campanhas.app.services.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/associado")
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService associadoService;

    @PostMapping
    public ResponseEntity<AssociadoResponse> inserir(@RequestBody AssociadoRequest associadoRequest) {
        AssociadoResponse associadoResponse = associadoService.inserir(associadoRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(associadoResponse.getId()).toUri();

        return ResponseEntity.created(location).body(associadoResponse);
    }

    @GetMapping
    public ResponseEntity<List<AssociadoResponse>> listar() {
        List<AssociadoResponse> associadoResponses = associadoService.listar();
        return ResponseEntity.ok(associadoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoResponse> obter(@PathVariable Long id) {
        AssociadoResponse associadoResponse = associadoService.obter(id);

        return ResponseEntity.ok(associadoResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AssociadoResponse> atualizar(@RequestBody AssociadoRequest associadoRequest, @PathVariable Long id) {
        AssociadoResponse associadoResponse = associadoService.atualizar(id, associadoRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(associadoResponse.getId()).toUri();

        return ResponseEntity.created(location).body(associadoResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        associadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
