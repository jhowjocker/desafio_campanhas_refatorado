package br.com.campanhas.app.repositories;


import br.com.campanhas.app.entities.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
        public Associado findByNomeCompletoAssociadoIgnoreCase(String nomeCompletoAssociado);


}