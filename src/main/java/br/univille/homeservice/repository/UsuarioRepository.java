package br.univille.homeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.homeservice.model.Usuario;

//repositorio de dados que eh onde colocamos as querys de SQL select
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByLogin(String login);
    Usuario findByLoginAndSenha(String login, String senha);
}