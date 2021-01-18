package br.com.campanhas.app.services;

import java.time.LocalDate;
import java.util.*;

import br.com.campanhas.app.dto.response.CampanhaResponse;
import br.com.campanhas.app.entities.Campanha;
import br.com.campanhas.app.exceptions.CampanhaDuplicadaException;
import br.com.campanhas.app.exceptions.NomeTimeNotExistException;
import br.com.campanhas.app.mappers.MapperCampanhaToCampanhaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.campanhas.app.entities.Clube;
import br.com.campanhas.app.repositories.CampanhasRepository;
import br.com.campanhas.app.repositories.ClubeRepository;
import br.com.campanhas.app.dto.request.CampanhaRequest;

@Service
@RequiredArgsConstructor
public class CampanhaService {

	private final CampanhasRepository campanhaRepository;

	private final ClubeRepository clubeRepository;

	private final MapperCampanhaToCampanhaResponse mapperCampanhaToCampanhaResponse;

	private Object conexao;

	public CampanhaResponse inserir(CampanhaRequest campanhaRequest) {
		String message = "";

		Campanha camp = new Campanha();

		verificaNome(campanhaRequest.getNome());

		if ( validacoesDiversas(campanhaRequest) ) {
			message = "Data da vigencia já existe. Será acrescentado um dia na data final "
					+ "da campanha já cadastrada e será mantido a data cadastrada agora!";

			adicionaDia();
		}

		Clube clube = clubeRepository.findByNomeIgnoreCase(campanhaRequest.getTime());

		if ( Objects.isNull(clube) ) {
			throw new NomeTimeNotExistException("Nome do time não existe!");
		}

		camp.setNome(campanhaRequest.getNome().toUpperCase());
		camp.setClube(clube);
		camp.setDataInicio(campanhaRequest.getDataInicio());
		camp.setDataFim(campanhaRequest.getDataFim());

		campanhaRepository.save(camp);

		message += " Campanha inserida com sucesso.";

		CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toDto(camp);
		campanhaResponse.setMessage(message);

		return campanhaResponse;
	}

	public void verificaNome(String nomeCampanha) {
		Campanha campanha = campanhaRepository.findByNomeIgnoreCase(nomeCampanha);

		if( Objects.nonNull(campanha) ){
			throw new CampanhaDuplicadaException("Campanha existente. Tente outro nome!");
		}
	}

	//ENTENDIMENTO > SIMPLICIDADE
	//SIMPLICIDADE > COMPLEXO
	public boolean validacoesDiversas(CampanhaRequest campanhas) {
		List<Campanha> listaTodasCampanhas = campanhaRepository.findAll();

		for (Campanha seleciona : listaTodasCampanhas) {
			if (seleciona.getDataFim().equals(campanhas.getDataFim())) {
				return true;
			}
		}

		return false;
	}

	public List<Campanha> adicionaDia() {
		List<Campanha> novaListaCampanha = new ArrayList<>();

		List<Campanha> listaCampnhaBancoDeDados = campanhaRepository.findAll();

		for (Campanha seleciona : listaCampnhaBancoDeDados) {
			seleciona.setDataFim(seleciona.getDataFim().plusDays(1));
			Campanha save = campanhaRepository.save(seleciona);
		}

		return novaListaCampanha;
	}

	public List<CampanhaResponse> listar() {
		List<CampanhaResponse> listaCampanhaResponse = new ArrayList<>();

		List<Campanha> ListaCampanhaBanco = campanhaRepository.findAll();

		for (Campanha campanha : ListaCampanhaBanco) {
			if (campanha.getDataFim().isAfter(LocalDate.now())) {
				Campanha campanhaSave = campanhaRepository.save(campanha);

				CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toDto(campanhaSave);

				listaCampanhaResponse.add(campanhaResponse);

			}
		}

		return listaCampanhaResponse;
	}

	public CampanhaResponse obterCampanha(Long id) {
		Campanha campanha = campanhaRepository.getOne(id);

		CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toDto(campanha);

		return campanhaResponse;
	}

	public CampanhaResponse atualizar(Long id, CampanhaRequest campanhaRequest) {
		Campanha campanha = campanhaRepository.getOne(id);

		Clube clube = clubeRepository.findByNomeIgnoreCase(campanhaRequest.getTime());

		if ( Objects.isNull(clube) ) {
			throw new NomeTimeNotExistException("Nome do time não existe!");
		}

		campanha.setId(id);
		campanha.setNome(campanhaRequest.getNome().toUpperCase());
		campanha.setClube(clube);
		campanha.setDataInicio(campanhaRequest.getDataInicio());
		campanha.setDataFim(campanhaRequest.getDataFim());

		campanhaRepository.save(campanha);

		CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toDto(campanha);
		campanhaResponse.setMessage("Campanha atualizada com sucesso!");

		return campanhaResponse;
	}

	public void deletar(Long id) {
		campanhaRepository.deleteById(id);
	}
}