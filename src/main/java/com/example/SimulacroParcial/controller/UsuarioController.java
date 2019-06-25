package com.example.SimulacroParcial.controller;


import com.example.SimulacroParcial.model.PublicacionesxUsuario;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private static final String USUARIO_NOT_FOUND = "No se encontro el usuario";


    @PostMapping("")
    public void addUsuario(@RequestBody final Usuario u, HttpServletRequest request) {
        u.setBrowser(this.getUserAgent(request));
        usuarioRepository.save(u);
    }

    @GetMapping("")
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
    @GetMapping("/{idUsuario}")
    public Usuario getbyid(@PathVariable final Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, idUsuario)));
    }

    @DeleteMapping("/{idUsuario}")
    public void deleteUsuario(@PathVariable final Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    private String getUserAgent(HttpServletRequest request){
        return request.getHeader("user-agent");
    }

    @PatchMapping("/{idUsuario}")
    public void modificarusuario(@PathVariable final Integer idUsuario, @RequestBody final Usuario usuario){
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, idUsuario)));
        u.setApellido(usuario.getApellido());
        u.setNombre(usuario.getNombre());
        usuarioRepository.save(u);
    }

    @GetMapping("/getcant")
    public List<PublicacionesxUsuario> getCantComentariosxUsuarios(){
        return usuarioRepository.getCantPublixUsuario();
    }
}
