<!-- CABEÇALHO -->
<div id="readme-top" align="center">
    <h1>
      👨‍💻 Courses API 📚
    </h1>
    <p>
        <a href="#%EF%B8%8F-sobre-o-projeto">Sobre o Projeto</a> •
        <a href="#-endpoints">Endpoints</a> •
        <a href="#%EF%B8%8F-tecnologias">Tecnologias</a> •
        <a href="#-autor">Autor</a>
    </p>
</div>

<!-- SOBRE O PROJETO -->

## 🖥️ Sobre o Projeto

> Projeto desenvolvido como desafio referente ao **Módulo 4** da Formação de Java da Rocketseat.

Esse projeto consiste em uma aplicação back-end de um sistema de cursos de programação.

As funcionalidades dessa aplicação são:

- [x] Criação de um novo curso
- [x] Listagem de todos os cursos
- [x] Atualização de um curso
- [x] Remoção de um curso
- [x] Ativação/Desativação de um curso 

<!-- ENDPOINTS -->

## 💡 Endpoints

| Método | Endpoint                  | Responsabilidade                                 | Regras de Negócio                                                                                                                         |
| ------ | ------------------------- | -------------------------------------------------| ----------------------------------------------------------------------------------------------------------------------------------------- |
| POST   | /courses                  | Cria um curso                                    | Campos `id`, `created_at` e `updated_at`devem ser preenchidos automaticamente                                                             |
| GET    | /courses                  | Lista todos os cursos                            | Também deve ser possível realizar busca, filtrando os cursos pelo `name` e `category`                                                     |                                                                                     
| PUT    | /courses/:courseId        | Atualiza um curso pelo `id`                      | Deve receber somente `name` e/ou `category`. Se for enviado somente o name, significa que o category não pode ser atualizado e vice-versa | 
| DELETE | /courses/:courseId        | Remove um curso pelo `id`                        |                                                                                                                                           |
| PATCH  | /courses/:courseId/active | Marcar se o cursos está ativo ou não             |                                                                                                                                           |

<!-- TECNOLOGIAS -->

## 🛠️ Tecnologias

Para o desenvolvimento desse projeto, as seguintes ferramentas foram utilizadas:

- **[Java](https://www.java.com/pt-BR/)**
- **[Spring Boot](https://spring.io/projects/spring-boot)**
- **[Docker](https://www.docker.com/)**
- **[PostgreSQL](https://www.postgresql.org/)**

## 👨‍💻 Autor

<img style="border-radius: 15%;" src="https://gitlab.com/uploads/-/system/user/avatar/8603970/avatar.png?width=400" width=70 alt="author-profile-picture"/>

Marcos Kenji Kuribayashi

---
