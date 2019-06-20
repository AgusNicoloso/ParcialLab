package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Publicaciones;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.repository.PublicacionesRepository;
import com.example.SimulacroParcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequestMapping("/publicaciones")
@RestController
public class PublicacionesController {
    @Autowired
    private PublicacionesRepository publicacionesRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private static final String USUARIO_NOT_FOUND = "No se encontro el usuario";
    @PostMapping("/{idUsuario}")
    public void addPublicacion(@PathVariable final Integer idUsuario, @RequestBody final Publicaciones p) {
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, idUsuario)));
        publicacionesRepository.save(p);
        u.getPublicacionesList().add(p);
        p.setUsuario(u);
        usuarioRepository.save(u);
    }
    @GetMapping("")
    public List<Publicaciones> getPublicaciones() {
        return publicacionesRepository.findAll();
    }
}
