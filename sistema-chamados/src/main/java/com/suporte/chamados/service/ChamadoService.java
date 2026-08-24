package com.suporte.chamados.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.suporte.chamados.model.Chamado;
import com.suporte.chamados.model.Prioridade;
import com.suporte.chamados.model.Status;
import com.suporte.chamados.repository.ChamadoRepository;

//@Service: Marca a classe como a camada de regras de negócio da aplicação.
@Service	
public class ChamadoService {

	private final ChamadoRepository repository;
	
	public ChamadoService(ChamadoRepository repository) {
		this.repository = repository;
	}
	// Retorna todos os chamados cadastrados.
	public List<Chamado> listarTodos(){
		return repository.findAll();
	}
	// Busca chamado por ID ou lança erro se não existir.
	public Chamado buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado com id: " + id));
    }
	// Filtra chamados pelo Status (ABERTO, EM_ANDAMENTO, FECHADO).
    public List<Chamado> buscarPorStatus(Status status) {
        return repository.findByStatus(status);
    }
    // Filtra chamados por Prioridade (BAIXA, MEDIA, ALTA).
    public List<Chamado> buscarPorPrioridade(Prioridade prioridade) {
        return repository.findByPrioridade(prioridade);
    }
    // Regra: Novo chamado nasce SEMPRE como ABERTO e com a data de abertura atual.
    public Chamado criar(Chamado chamado) {
        chamado.setStatus(Status.ABERTO);
        chamado.setDataAbertura(LocalDateTime.now());
        return repository.save(chamado);
    }

    // Regra: Atribuir técnico e alterar status para EM_ANDAMENTO
    public Chamado atribuirTecnico(Long id, String tecnico) {
        Chamado chamado = buscarPorId(id);
        chamado.setTecnico(tecnico);
        chamado.setStatus(Status.EM_ANDAMENTO);
        return repository.save(chamado);
    }

    // Regra: Finalizar chamado e registrar data de encerramento
    public Chamado finalizarChamado(Long id) {
        Chamado chamado = buscarPorId(id);
        chamado.setStatus(Status.FECHADO);
        chamado.setDataFechamento(LocalDateTime.now());
        return repository.save(chamado);
    }
    // Exclui o chamado do banco.
    public void deletar(Long id) {
        Chamado chamado = buscarPorId(id);
        repository.delete(chamado);
    }
}
