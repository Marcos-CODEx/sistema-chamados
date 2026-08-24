package com.suporte.chamados.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suporte.chamados.model.Chamado;
import com.suporte.chamados.model.Prioridade;


@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long>{
	
	// O Spring Data JPA cria a consulta SQL automaticamente só pelo nome do método!
	List<Chamado> findByStatus(com.suporte.chamados.model.Status status);
	List<Chamado> findByPrioridade(Prioridade prioridade);

}
