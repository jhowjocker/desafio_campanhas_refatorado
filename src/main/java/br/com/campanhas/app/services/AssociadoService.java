package br.com.campanhas.app.services;

import br.com.campanhas.app.dto.request.AssociadoRequest;
import br.com.campanhas.app.dto.response.AssociadoResponse;
import br.com.campanhas.app.entities.Associado;
import br.com.campanhas.app.entities.Campanha;
import br.com.campanhas.app.entities.Clube;
import br.com.campanhas.app.exceptions.AssociadoDuplicadoException;
import br.com.campanhas.app.exceptions.NomeTimeNotExistException;
import br.com.campanhas.app.mappers.MapperAssociadoToAssociadoResponse;
import br.com.campanhas.app.repositories.AssociadoRepository;
import br.com.campanhas.app.repositories.ClubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final ClubeRepository clubeRepository;

    private final MapperAssociadoToAssociadoResponse mapperAssociadoToAssociadoResponse;

    public AssociadoResponse inserir(AssociadoRequest associadoRequest) {
        String message = "";
        Associado associado = new Associado();
        Campanha campanha = new Campanha();

        verificaNomeAssociado(associadoRequest.getNomeCompleto());

        if (verificaEmailAssociado(associadoRequest)) {
            throw new AssociadoDuplicadoException("Email já cadastrado! Tente novamente.");
        }

        Clube clube = clubeRepository.findByNomeIgnoreCase(associadoRequest.getTime());

        if (Objects.isNull(clube)) {
            throw new NomeTimeNotExistException("Nome do time não existe!");
        }

        associado.setNomeCompleto(associadoRequest.getNomeCompleto().toUpperCase());
        associado.setEmail(associadoRequest.getEmail());
        associado.setDataDeNascimento(associadoRequest.getDataNascimento());
        associado.setClube(clube);

        associadoRepository.save(associado);

        message += "Associado inserido com sucesso!";

        AssociadoResponse associadoResponse = mapperAssociadoToAssociadoResponse.toDto(associado);
        associadoResponse.setMessage(message);

        return associadoResponse;
    }

    public void verificaNomeAssociado(String nomeAssociado) {
        Associado associado = associadoRepository.findByNomeCompletoIgnoreCase(nomeAssociado);

        if (Objects.nonNull(associado)) {
            throw new AssociadoDuplicadoException("Cadastro existente!");
        }
    }

    public boolean verificaEmailAssociado(AssociadoRequest associadoRequest) {
        List<Associado> listaDeEmailsBancoDeDados = associadoRepository.findAll();
        for (Associado seleciona : listaDeEmailsBancoDeDados) {
            if (seleciona.getEmail().equals(associadoRequest.getEmail())) {
                return true;
            }
        }

        return false;
    }

    public List<AssociadoResponse> listar() {
        List<AssociadoResponse> listaAssociadoResponse = new ArrayList<>();
        List<Associado> ListaAssociadoBancoDeDados = associadoRepository.findAll();

        for (Associado associado : ListaAssociadoBancoDeDados) {

            Associado associadoSave = associadoRepository.save(associado);
            AssociadoResponse associadoResponse = mapperAssociadoToAssociadoResponse.toDto(associado);
            listaAssociadoResponse.add(associadoResponse);
        }
        return listaAssociadoResponse;
    }

    public AssociadoResponse obter(Long id) {
        Associado associado = associadoRepository.getOne(id);

        AssociadoResponse associadoResponse = mapperAssociadoToAssociadoResponse.toDto(associado);

        return associadoResponse;
    }

    public AssociadoResponse atualizar(Long id, AssociadoRequest associadoRequest) {
        Associado associado = associadoRepository.getOne(id);

        Clube clube = clubeRepository.findByNomeIgnoreCase(associadoRequest.getTime());

        if (Objects.isNull(clube)) {
            throw new NomeTimeNotExistException("Nome do time não existe!");
        }

        associado.setId(id);
        associado.setNomeCompleto(associadoRequest.getNomeCompleto().toUpperCase());
        associado.setDataDeNascimento(associadoRequest.getDataNascimento());
        associado.setEmail(associadoRequest.getEmail());
        associado.setClube(clube);

        associadoRepository.save(associado);

        AssociadoResponse associadoResponse = mapperAssociadoToAssociadoResponse.toDto(associado);
        associadoResponse.setMessage("Associado atualizada com sucesso!");

        return associadoResponse;
    }

    public void deletar(Long id) {
        associadoRepository.deleteById(id);
    }
}



