# 🔐 API de Segurança com Spring Boot

Projeto desenvolvido com o objetivo de implementar autenticação e autorização utilizando **Spring Security**, com controle de acesso baseado em **roles (perfis)**.

---

## 🚀 Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Security
* Spring Data JPA
* H2 Database
* Lombok
* Swagger (OpenAPI)

---

## 🔑 Funcionalidades

* Cadastro de usuários
* Criptografia de senha com BCrypt
* Autenticação via Basic Auth
* Autorização com `@PreAuthorize`
* Controle de acesso por perfil (ADMIN / USUARIO)
* CRUD de produtos protegido por permissões

---

## 🔐 Segurança

O projeto utiliza:

* `Spring Security`
* `BCryptPasswordEncoder` para criptografia de senha
* Autenticação via **HTTP Basic**
* Autorização com `@EnableMethodSecurity`

### Perfis disponíveis:

* `ADMIN`
* `USUARIO`

---

## 📂 Endpoints principais

### 👤 Usuários (`/usuarios`)

* `POST /usuarios` → Cadastrar usuário
* `GET /usuarios` → Listar usuários
* `PUT /usuarios/{id}` → Atualizar usuário
* `DELETE /usuarios/{id}` → Deletar usuário

### 📦 Produtos (`/produtos`)

🔒 **Requer autenticação**

* `POST /produtos` → Apenas ADMIN
* `GET /produtos` → ADMIN e USUARIO
* `PUT /produtos/{id}` → Apenas ADMIN
* `DELETE /produtos/{id}` → Apenas ADMIN

---

## 🔑 Autenticação

A API utiliza **Basic Auth**.

### Exemplo:

```
Username: admin@email.com
Password: 123
```

---

## 📄 Documentação Swagger

Acesse a documentação interativa da API:

👉 https://github.com/eliaspinheiropereira/segurancabancodedadoslocal#

> Após rodar a aplicação localmente, normalmente estará disponível em:
>
> http://localhost:8080/swagger-ui.html
> ou
> http://localhost:8080/swagger-ui/index.html

---

## 🧪 Banco de dados

O projeto utiliza banco em memória **H2**.

### Acesso ao console:

```
http://localhost:8080/h2-console
```

### Configurações padrão:

* JDBC URL: `jdbc:h2:mem:testdb`
* User: `sa`
* Password: (vazio)

---

## ▶️ Como executar o projeto

```bash
# Clonar repositório
git clone git@github.com:eliaspinheiropereira/segurancabancodedadoslocal.git

# Entrar na pasta
cd segurancabancodedadoslocal

# Rodar aplicação
./mvnw spring-boot:run
```

---

## 🧠 Conceitos aplicados

* Injeção de dependência
* DTO Pattern
* Camadas (Controller, Service, Repository)
* Segurança com Spring
* Boas práticas REST

---

## 📌 Observações

* Senhas são armazenadas de forma criptografada
* Endpoints de produtos exigem autenticação
* Controle de acesso feito via annotations

---

## 👨‍💻 Autor

Desenvolvido por **Elias Pinheiro Pereira**

---

## 📄 Licença

Este projeto está sob a licença MIT.
