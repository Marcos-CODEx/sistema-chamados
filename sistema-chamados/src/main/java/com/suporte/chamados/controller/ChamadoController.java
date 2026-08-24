package com.suporte.chamados.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suporte.chamados.model.Chamado;
import com.suporte.chamados.model.Prioridade;
import com.suporte.chamados.model.Status;
import com.suporte.chamados.service.ChamadoService;

//@RestController: Indica ao Spring que esta classe expõe endpoints HTTP e retorna JSON.
@RestController
//@RequestMapping: Define o caminho/URL base para todos os endpoints deste controller.
@RequestMapping("/api/chamados")
//@CrossOrigin: Libera acesso para requisições do Front-end (evita erro de bloqueio de CORS).
@CrossOrigin(origins = "*") // Permite chamadas do Front-end sem bloquear por CORS
public class ChamadoController {

	private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }
    // GET /api/chamados -> Retorna a lista de todos os chamados.
    @GetMapping
    public ResponseEntity<List<Chamado>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
    // GET /api/chamados/{id} -> Busca um chamado específico pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    // GET /api/chamados/status/{status} -> Filtra por status (ex: /api/chamados/status/ABERTO).
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Chamado>> buscarPorStatus(@PathVariable Status status) {
        return ResponseEntity.ok(service.buscarPorStatus(status));
    }
    // GET /api/chamados/prioridade/{prioridade} -> Filtra por prioridade (ex: /api/chamados/prioridade/ALTA).
    @GetMapping("/prioridade/{prioridade}")
    public ResponseEntity<List<Chamado>> buscarPorPrioridade(@PathVariable Prioridade prioridade) {
        return ResponseEntity.ok(service.buscarPorPrioridade(prioridade));
    }
    // POST /api/chamados -> Recebe os dados de um novo chamado no corpo da requisição (@RequestBody).    
    @PostMapping
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado) {
        Chamado novoChamado = service.criar(chamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChamado);
    }

    // PUT /api/chamados/{id}/atribuir tecnico=Nome -> Atribui um técnico ao chamado e muda status para EM_ANDAMENTO.
    @PutMapping("/{id}/atribuir")
    public ResponseEntity<Chamado> atribuirTecnico(@PathVariable Long id, @RequestParam String tecnico) {
        return ResponseEntity.ok(service.atribuirTecnico(id, tecnico));
    }

    // PUT /api/chamados/{id}/finalizar -> Conclui o chamado, gravando a data de encerramento.    
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Chamado> finalizarChamado(@PathVariable Long id) {
        return ResponseEntity.ok(service.finalizarChamado(id));
    }
    
    // DELETE /api/chamados/{id} -> Remove o chamado do banco.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
	
}
