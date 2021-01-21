package br.com.campanhas.app.controller;

import br.com.campanhas.app.dto.request.AssociadoRequest;
import br.com.campanhas.app.dto.request.ClubeRequest;
import br.com.campanhas.app.dto.response.AssociadoResponse;
import br.com.campanhas.app.dto.response.ClubeResponse;
import br.com.campanhas.app.entities.Clube;
import br.com.campanhas.app.services.AssociadoService;
import br.com.campanhas.app.services.ClubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clube")
@RequiredArgsConstructor
public class ClubController {

    private final ClubeService clubeService;


        @PostMapping
        public ResponseEntity<ClubeResponse> inserir(@RequestBody ClubeRequest clubeRequest) {
            ClubeResponse clubeResponse = clubeService.inserir(clubeRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clubeResponse.getId()).toUri();

            return ResponseEntity.created(location).body(clubeResponse);
        }

    @GetMapping
    public ResponseEntity<List<ClubeResponse>> listar() {
        List<ClubeResponse> clubeResponses = clubeService.listar();
        return ResponseEntity.ok(clubeResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubeResponse> obter(@PathVariable Long id) {
        ClubeResponse clubeResponses = clubeService.obter(id);
        return ResponseEntity.ok(clubeResponses);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClubeResponse> atualizar(@RequestBody ClubeRequest clubeRequest, @PathVariable Long id) {
        ClubeResponse clubeResponses = clubeService.atualizar(id, clubeRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(clubeResponses.getId()).toUri();

        return ResponseEntity.created(location).body(clubeResponses);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clubeService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
