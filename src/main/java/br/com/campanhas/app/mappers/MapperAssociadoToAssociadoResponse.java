package br.com.campanhas.app.mappers;

import br.com.campanhas.app.dto.response.AssociadoResponse;
import br.com.campanhas.app.entities.Associado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperAssociadoToAssociadoResponse {
    AssociadoResponse toDto(Associado associado);
}
