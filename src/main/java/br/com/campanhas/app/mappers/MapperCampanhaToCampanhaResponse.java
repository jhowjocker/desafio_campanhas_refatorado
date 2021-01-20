package br.com.campanhas.app.mappers;

import br.com.campanhas.app.dto.response.CampanhaResponse;
import br.com.campanhas.app.entities.Campanha;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MapperClubeToClubeResponse.class, MapperAssociadoToAssociadoResponse.class})
public interface MapperCampanhaToCampanhaResponse {
    CampanhaResponse toDto(Campanha campanha);
}
