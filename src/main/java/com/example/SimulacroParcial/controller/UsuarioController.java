package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Comentarios;
import com.example.SimulacroParcial.model.Publicaciones;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.repository.ComentariosRepository;
import com.example.SimulacroParcial.repository.PublicacionesRepository;
import com.example.SimulacroParcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionesRepository publicacionesRepository;
    @Autowired
    private ComentariosRepository comentariosRepository;
    private static final String USUARIO_NOT_FOUND = "No se encontro el usuario";
    private static final String PUBLICACION_NOT_FOUND = "No se encontro la publicacion";

    @PostMapping("")
    public void addUsuario(@RequestBody final Usuario u){
        usuarioRepository.save(u);
    }
    @PostMapping("/borrarusuario/{idUsuario}")
    public void deleteUsuario(@PathVariable final Integer idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }
    @GetMapping("")
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    @GetMapping("/publicaciones")
    public List<Publicaciones> getPublicaciones(){
        return publicacionesRepository.findAll();
    }
    @GetMapping("/comentarios")
    public List<Comentarios> getComentarios(){
        return comentariosRepository.findAll();
    }
    @PostMapping("/comentarios")
    public void addComentario(@RequestBody final Comentarios c){
        comentariosRepository.save(c);
    }
    @PostMapping("/addPublicacion/{idUsuario}")
    public void addPublicacion(@PathVariable final Integer idUsuario, @RequestBody final Publicaciones p){
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND,idUsuario)));
        publicacionesRepository.save(p);
        u.getPublicacionesList().add(p);
        p.setUsuario(u);
        this.addUsuario(u);
    }

    @PostMapping("/addComentario/{idPublicacion}/{idUsuario}")
    public void addComentarios(@PathVariable final Integer idUsuario, @RequestBody final Comentarios c, @PathVariable final Integer idPublicacion){
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND,idUsuario)));
        Publicaciones p = publicacionesRepository.findById(idPublicacion).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PUBLICACION_NOT_FOUND,idPublicacion)));
        p.getComentariosList().add(c);
        c.setOwner(u.getNombre());
        this.addComentario(c);
    }


    }
