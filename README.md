#  Sistema de Controle de Estoque (Java + SQL/JDBC)

Aplicação back-end desenvolvida em **Java** aplicando **Programação Orientada a Objetos (POO)**, padrão de projeto **DAO (Data Access Object)** e persistência em banco de dados relacional via **JDBC**, simulando a gestão de produtos e movimentação de estoque de um centro de distribuição/varejo.

---

##  Funcionalidades
- Cadastro, consulta, atualização e remoção de produtos (**CRUD completo**).
- Controle de entrada e saída de mercadorias com validação de saldo mínimo em estoque.
- Relatório de produtos com estoque crítico (abaixo do limite mínimo).
- Persistência em banco de dados relacional via **JDBC** utilizando `PreparedStatement` (prevenção contra SQL Injection).
- Rotina de testes automatizados das regras de negócio de movimentação de estoque.

---

##  Estrutura do Projeto

```text
sistema-controle-estoque-java/
├── sql/
│   └── schema.sql                  # Script de criação das tabelas e dados iniciais
├── src/
│   ├── model/
│   │   └── Produto.java            # Entidade de domínio (encapsulamento e validações)
│   ├── dao/
│   │   ├── ConnectionFactory.java  # Gerenciamento de conexão JDBC
│   │   └── ProdutoDAO.java         # Operações SQL (INSERT, SELECT, UPDATE, DELETE)
│   ├── test/
│   │   └── EstoqueTest.java        # Testes unitários das regras de negócio
│   └── Main.java                   # Fluxo principal de execução
└── README.md
```

---

##  Modelagem do Banco de Dados (`sql/schema.sql`)

```sql
CREATE DATABASE IF NOT EXISTS controle_estoque;
USE controle_estoque;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_barras VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(120) NOT NULL,
    categoria VARCHAR(60) NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0,
    estoque_minimo INT NOT NULL DEFAULT 10,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

##  Como Executar
1. Clone este repositório:
   ```bash
   git clone https://github.com/PedroFrota/sistema-controle-estoque-java.git
   ```
2. Execute o script `sql/schema.sql` no seu SGBD (MySQL ou PostgreSQL).
3. Configure a URL, usuário e senha do banco na classe `src/dao/ConnectionFactory.java`.
4. Adicione o driver JDBC correspondente ao *classpath* e execute `src/Main.java`.
