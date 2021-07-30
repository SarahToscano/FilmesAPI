
<h1 align="center">
    Projeto IDP: Filmes API
  <p><img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/> 
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white"/>
  <img src ="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/github/last-commit/SarahToscano/FilmesAPI?style=for-the-badge"></p>
  
</h1>


✍️ API Requirements
-----
### Must to have
- [x] Comunicar com uma API externa (desenvolvida pelo colaborador ou por terceiros).
- [x] Persistir dados num BD relacional (não relacional é "nice to have").
- [x] Apresentar alguns testes unitários e funcionais.

### Nice to have
- [x] Organização do código.
- [ ] Logging.
- [x] Segurança (ex: JWT).
- [x] Cache.

📝 Design Patterns
-----
  ### Model
  Detentor dos dados, recebe as informações do Controller, valida
  ou não e retorna a resposta adequada.

  ### Controller
  Fornece a comunicação entre o detentor dos dados e o cliente.

  ### Repository
  Interface de consulta e manipulação dos dados, utilizado para criar uma barreira de controle e segurança entre a aplicação e os dados.

  ### DTO
  Utilizado para transferir dados entre subsistemas do software.

  ### Form 
  Utilizado para receber dados entre subsistemas do software.


📚 Features
-----

  <table border="0" width="110%"
  >
  <tr>

  <td width="20%" valign="top" border="0">

  ## Usuario
  - [x] CREATE
  - [x] UPDATE
  - [x] LIST
  - [x] DELETE

  </td>
  <td width="20%" valign="top">

  ## Avaliacao
  - [x] CREATE
  - [x] LIST
  - [x] DELETE
  - [x] UPDATE

  </td>
  <td width="20%" valign="top">

  ## Playlist
  - [x] CREATE
  - [x] LIST
  - [x] DELETE
  - [x] UPDATE

  </td>

  <td width="40%" valign="top">
  
  ## Pesquisar Filmes
  - [x] SEARCH BY TITLE
  - [x] SEARCH BY IMDB_ID

  </td>

</tr>
</table>




💻 Setup
-----
- Clone o repositorio and abra o projeto na IDE Eclipse
- Instale as dependencias maven dependencies using IDE auto import or using the command ``mvn install``
- Browse ``http://localhost:8083``
    
   
📲 External Tools
----------------

 
  #### Spring Boot Admin
  ```
  https://codecentric.github.io/spring-boot-admin/2.3.1/
  ```
  
  #### Data-Imdb 
  ```
  https://rapidapi.com/SAdrian/api/data-imdb1/
  ```
  
  ## 🦸 Author

<table>
  <tr>   
    <td align="center"><a href="https://github.com/sSarahToscano/"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/33987228?v=4" width="100px;" alt=""/><br /><sub><b>Sarah Toscano</b></sub></a></td>  
  </tr>
</table>

---

## 📝 License

Este projeto esta sobe a licença [MIT](./LICENSE).

   <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">  
