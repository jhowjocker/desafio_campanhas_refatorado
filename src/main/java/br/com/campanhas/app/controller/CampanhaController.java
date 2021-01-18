package br.com.campanhas.app.controller;

import br.com.campanhas.app.dto.request.CampanhaRequest;
import br.com.campanhas.app.dto.response.CampanhaResponse;
import br.com.campanhas.app.services.CampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/campanha")
@RequiredArgsConstructor
public class CampanhaController {

	private final CampanhaService service;

	@PostMapping
	public ResponseEntity<CampanhaResponse> inserir(@RequestBody CampanhaRequest campanhaRequest) {
		CampanhaResponse response = service.inserir(campanhaRequest);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();

		return ResponseEntity.created(location).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CampanhaResponse> obterCampnha(@PathVariable Long id){
		CampanhaResponse campanhaResponse = service.obterCampanha(id);

		return ResponseEntity.ok(campanhaResponse);
	}

	@GetMapping
	public ResponseEntity<List<CampanhaResponse>> listar() {
		List<CampanhaResponse> campanhaResponses = service.listar();
		return ResponseEntity.ok(campanhaResponses);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<CampanhaResponse> atualizar(@RequestBody CampanhaRequest campanhaRequest, @PathVariable Long id) {
		CampanhaResponse campanhaResponse = service.atualizar(id, campanhaRequest);

		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.buildAndExpand(campanhaResponse.getId()).toUri();

		return ResponseEntity.created(location).body(campanhaResponse);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}