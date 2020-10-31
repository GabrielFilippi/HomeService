package br.univille.homeservice.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
  
import br.univille.homeservice.repository.UsuarioRepository;
import br.univille.homeservice.model.Usuario;
@Service
public class MyUserDetailsService implements UserDetailsService {
  
    @Autowired
    private UsuarioRepository repository; 
    @Autowired
    private PasswordEncoder passwordEncoder;
  
    public Usuario buscaUsuario(String nomeUsuario){
        return repository.findByLogin(nomeUsuario);
    }

    public Usuario buscaUsuarioSenha(String nomeUsuario, String senhaUsuario){
        return repository.findByLoginAndSenha(nomeUsuario, senhaUsuario);
    }
  
    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(nomeUsuario);
        return new User(usuario.getLogin(),usuario.getSenha(), new ArrayList<>());
    }  

    public void save(Usuario usuario){
        if (usuario.getSenha().length() != 0) 
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            repository.save(usuario);
    }
      
}