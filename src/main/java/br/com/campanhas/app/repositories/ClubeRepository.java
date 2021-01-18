package br.com.campanhas.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campanhas.app.entities.Clube;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {

	public Clube findByNomeIgnoreCase(String time);
}