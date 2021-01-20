package br.com.campanhas.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssociadoResponse {

    private Long id;
    private String nomeCompletoAssociado;
    private String emailAssociado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDeNascimento;

    private String message;
}
