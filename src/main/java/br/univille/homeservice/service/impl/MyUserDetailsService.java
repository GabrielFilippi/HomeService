package br.univille.homeservice.service.impl;
 
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
  
import br.univille.homeservice.repository.UsuarioRepository;
import br.univille.homeservice.model.Usuario;
@Service
public class MyUserDetailsService implements UserDetailsService {
  
    @Autowired
    private UsuarioRepository repository; 
  
    public Usuario buscaUsuarioSenha(String nomeUsuario, String senhaUsuario){
        return repository.findByLoginAndSenha(nomeUsuario, senhaUsuario);
    }
  
    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(nomeUsuario);
        return new User(usuario.getLogin(),usuario.getSenha(), new ArrayList<>());
    }  
      
}