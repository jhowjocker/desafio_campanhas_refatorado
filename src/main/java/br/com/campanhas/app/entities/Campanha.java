package br.com.campanhas.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
public class Campanha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataFim;

	@ManyToOne
	@JoinColumn(name = "IdClube")
	private Clube clube;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "campanha_associados",
			joinColumns = {
				@JoinColumn(name = "id_campanha", referencedColumnName = "id", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
				@JoinColumn(name = "id_associado", referencedColumnName = "id", nullable = false, updatable = false)
			}
	)
	private List<Associado> associados = new ArrayList<>();
}
