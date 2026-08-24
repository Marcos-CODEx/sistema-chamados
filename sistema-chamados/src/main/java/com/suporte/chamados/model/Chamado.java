package com.suporte.chamados.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

//@Entity: Avisa ao Spring JPA que esta classe representa uma tabela no Banco de Dados.
@Entity
//@Table: Define explicitamente o nome da tabela no banco.
@Table(name = "tb_chamados")
public class Chamado {
	
	// @Id: Marca este campo como a Chave Primária (Primary Key / PK) da tabela.
	// @GeneratedValue: Faz o banco de dados gerar os IDs sequenciais automaticamente.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Campos normais viram colunas simples na tabela.
	private String titulo;
	
	// @Column(columnDefinition = "TEXT"): Permite guardar descrições longas no banco de dados.
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private String cliente;	// Quem abriu o chamado
	private String tecnico;	// Quem está tratando o chamado
	
	
	// @Enumerated(EnumType.STRING): Salva o Enum como TEXTO no banco (ex: "ALTA") 
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTO;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;
	
	
	// Construtor padrão (obrigatório pelo JPA)
	public Chamado() {
		
	}
	// Construtor prático para criar um novo chamado
	public Chamado(String titulo, String descricao, String cliente, Prioridade prioridade, Status status,
			LocalDateTime dataAbertura) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.cliente = cliente;
		this.prioridade = prioridade;
		this.status = status;
		this.dataAbertura = dataAbertura;
	}
	
	// @PrePersist: Executado automaticamente pelo JPA BEM ANTES de gravar no banco.
    // Garante que todo novo chamado tenha data de abertura e status "ABERTO".
	@PrePersist
	protected void onCreate() {
		if (this.dataAbertura == null) {
			this.dataAbertura = LocalDateTime.now();
		}
		if (this.status == null) {
			this.status = Status.ABERTO;
		}
	}

	// --- GETTERS E SETTERS ---
    // (Permitem ao Spring ler e alterar os atributos privados da classe)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
	
	
	
	

}
