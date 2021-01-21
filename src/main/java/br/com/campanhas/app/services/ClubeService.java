package br.com.campanhas.app.services;

import br.com.campanhas.app.dto.request.ClubeRequest;
import br.com.campanhas.app.dto.response.ClubeResponse;
import br.com.campanhas.app.entities.Clube;
import br.com.campanhas.app.exceptions.ClubeDuplicadoException;
import br.com.campanhas.app.exceptions.NomeTimeNotExistException;
import br.com.campanhas.app.mappers.MapperClubeToClubeResponse;
import br.com.campanhas.app.repositories.ClubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClubeService {

    private final ClubeRepository clubeRepository;
    private final MapperClubeToClubeResponse mapperClubeToClubeResponse;

    public ClubeResponse inserir(ClubeRequest clubeRequest){
        String message = "";
        Clube clube = new Clube();

        verificaClube(clubeRequest.getNome());

        clube.setNome(clubeRequest.getNome().toUpperCase());

        clubeRepository.save(clube);

        message += "Clube inserido com sucesso!";

        ClubeResponse clubeResponse = mapperClubeToClubeResponse.toDtoResponse(clube);
        clubeResponse.setMessage((message));

        return clubeResponse;

    }
    public void verificaClube(String validaClube) {
        Clube clube = clubeRepository.findByNomeIgnoreCase(validaClube);
        if (Objects.nonNull(clube)) {
            throw new ClubeDuplicadoException("Clube já cadastrado!");
        }
    }

    public List<ClubeResponse> listar(){
        List<ClubeResponse> listaClubeResponse = new ArrayList<>();
        List<Clube> listaClubeBancoDeDados = clubeRepository.findAll();

        for (Clube clube : listaClubeBancoDeDados){

            Clube clubeSave = clubeRepository.save(clube);
            ClubeResponse clubeResponse = mapperClubeToClubeResponse.toDtoResponse(clube);
            listaClubeResponse.add(clubeResponse);
        }
        return listaClubeResponse;
    }

    public ClubeResponse obter(Long id){
        Clube clube = clubeRepository.getOne(id);

        ClubeResponse clubeResponse = mapperClubeToClubeResponse.toDtoResponse(clube);

        return clubeResponse;
    }

    public ClubeResponse atualizar(long id, ClubeRequest clubeRequest){
        Clube clube = clubeRepository.getOne(id);

        if (Objects.isNull(clube)) {
            throw new NomeTimeNotExistException("Nome do time não existe!");
        }

        clube.setNome(clubeRequest.getNome().toUpperCase());
        clubeRepository.save(clube);

        ClubeResponse clubeResponse = mapperClubeToClubeResponse.toDtoResponse(clube);
        clubeResponse.setMessage("Clube atualizado com sucesso!");

        return clubeResponse;
    }

    public void deletar(Long id) {
        clubeRepository.deleteById(id);
    }

}

























