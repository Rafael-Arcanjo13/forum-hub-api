📚 Forum Hub API

API REST desenvolvida em Java com Spring Boot para gerenciamento de um fórum de discussões, permitindo que usuários criem tópicos, respondam perguntas e marquem respostas como solução.
O objetivo do projeto é simular o funcionamento de um fórum semelhante ao utilizado em plataformas de aprendizado, permitindo a organização de dúvidas por cursos e a interação entre usuários.

🚀 Funcionalidades

A API permite:

👤 Usuários

Cadastro de usuários
Associação de perfis (permissões) aos usuários
Controle de acesso baseado em perfis

📚 Cursos

Cadastro de cursos
Organização de tópicos por categoria de curso

📝 Tópicos

Criação de tópicos no fórum
Listagem de tópicos
Alteração do status do tópico
Desativação lógica de tópicos

💬 Respostas

Responder um tópico
Listar respostas de um tópico
Marcar uma resposta como solução

🏗️ Arquitetura do Projeto

A aplicação segue uma arquitetura baseada em API REST com Spring Boot, utilizando:
Controller → responsável por receber requisições HTTP
Service → regras de negócio
Repository → acesso ao banco de dados
Entity → representação das tabelas do banco

🗄️ Modelo de Dados

O sistema possui as seguintes entidades principais:

Usuário

Representa os usuários da plataforma.

Relacionamentos:

Muitos usuários podem possuir vários perfis.

Perfil

Define permissões do usuário no sistema.

Exemplo:

ROLE_ADMIN

ROLE_USER

Relacionamento:

Muitos usuários podem ter muitos perfis.

Curso

Representa o curso ao qual o tópico pertence.

Representa uma dúvida ou discussão criada por um usuário.

Relacionamentos:

pertence a um usuário
pertence a um curso
possui várias respostas

Resposta

Representa uma resposta a um tópico.

Relacionamentos:

pertence a um tópico
pertence a um usuário

🛠️ Tecnologias Utilizadas

Java 21; Spring Boot; Spring Data JPA; Hibernate; Flyway (migrations do banco); MySQL; Maven

⚙️ Configuração do Ambiente
1️⃣ Clonar o repositório
git clone https://github.com/seu-usuario/forum-hub-api.git
2️⃣ Configurar o banco de dados

Criar um banco no MySQL:

CREATE DATABASE forum_hub;
3️⃣ Configurar o application.properties

Exemplo:

spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub
spring.datasource.username=root
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=validate

spring.flyway.enabled=true
🧬 Migrations

O projeto utiliza Flyway para controle de versão do banco de dados.

As migrations ficam em:

src/main/resources/db/migration

Exemplo de migrations existentes:

V1__create-table-usuarios.sql
V2__create-table-perfis.sql
V3__create-table-usuario-perfil.sql
V4__create-table-cursos.sql
V5__create-table-topicos.sql
V6__create-table-respostas.sql

Sempre que a aplicação inicia, o Flyway verifica e aplica automaticamente novas migrations.

📌 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de praticar:

- Desenvolvimento de APIs REST
- Modelagem de banco de dados relacional
- Uso de JPA e Hibernate
- Controle de versão do banco com Flyway
- Arquitetura em camadas com Spring Boot
