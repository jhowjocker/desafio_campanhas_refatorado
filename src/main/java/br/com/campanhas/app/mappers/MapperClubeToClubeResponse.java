package br.com.campanhas.app.mappers;

import br.com.campanhas.app.dto.response.ClubeResponse;
import br.com.campanhas.app.entities.Clube;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperClubeToClubeResponse {
    ClubeResponse toResponse(Clube clube);
}
