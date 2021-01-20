package br.com.campanhas.app.dto.response;

import br.com.campanhas.app.entities.Associado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CampanhaResponse {
    private Long id;

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;

    private ClubeResponse clube;

    private String message;

    private List<AssociadoResponse> associados;
}
