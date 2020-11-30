package br.univille.homeservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.homeservice.model.Cliente;

//repositorio de dados que eh onde colocamos as querys de SQL, select, update, insert and delete.
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findAll();
    Cliente findByUsuarioId(long idUser);
}