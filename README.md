# 🛠️ Sistema de Gestão de Chamados de Suporte

Aplicação full-stack para gerenciamento e controle de chamados técnicos, desenvolvida com API REST em Spring Boot no back-end e interface dinâmica em HTML5, Bootstrap 5 e JavaScript Vanilla no front-end.

---

## 🚀 Tecnologias Utilizadas

### Back-end
* **Java 17**
* **Spring Boot 3** (Spring Data JPA, Spring Web)
* **H2 Database** (Banco em memória)
* **Maven Wrapper**

### Front-end
* **HTML5 / CSS3**
* **Bootstrap 5** (Componentes visuais e badges de prioridade)
* **JavaScript (ES6+)** (Fetch API para consumo REST)

---

## 📌 Funcionalidades

* 📝 **Abertura de Chamados:** Cadastro de chamados com título, cliente, descrição e prioridade (Baixa, Média, Alta).
* 👨‍💻 **Atribuição de Técnico:** Vincular um técnico responsável e atualizar o status para `EM_ANDAMENTO`.
* ✅ **Finalização de Chamados:** Encerramento do chamado com registro automático do horário de conclusão.
* 🗑️ **Exclusão:** Remoção de chamados do sistema.
* 🎨 **Interface Intuitiva:** Exibição dinâmica de status e badges coloridas de prioridade.

---

## 📁 Estrutura do Repositório

```text
├── sistema-chamados/          # Projeto Back-end (Spring Boot)
├── sistema-chamados-frontend/ # Projeto Front-end (HTML, JS, CSS)
└── iniciar-backend.bat        # Script de inicialização rápida no Windows

👤 Autor
Desenvolvido por Marcos (CODEx)
