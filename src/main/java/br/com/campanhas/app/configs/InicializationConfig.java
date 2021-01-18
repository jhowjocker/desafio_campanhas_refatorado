package br.com.campanhas.app.configs;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import br.com.campanhas.app.entities.Clube;
import br.com.campanhas.app.repositories.ClubeRepository;

@Configuration
@RequiredArgsConstructor
public class InicializationConfig {

	private final ClubeRepository clubeRepository;

	@PostConstruct
	public void inicializacao() {
		
		if (clubeRepository.count() > 0) {
			return;			
		}
		
		Clube clube = new Clube();
		clube.setNome("Corinthians");
		clubeRepository.save(clube);
		
		clube = new Clube();
		clube.setNome("Palmeiras");
		clubeRepository.save(clube);
		
		clube = new Clube();
		clube.setNome("Santos");
		clubeRepository.save(clube);
	}
}
