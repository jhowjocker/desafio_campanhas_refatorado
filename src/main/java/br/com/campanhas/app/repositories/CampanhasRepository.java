package br.com.campanhas.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campanhas.app.entities.Campanha;

@Repository
public interface CampanhasRepository extends  JpaRepository<Campanha, Long> {
    public Campanha findByNomeIgnoreCase(String nome);
}