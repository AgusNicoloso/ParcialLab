package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Comentarios;
import com.example.SimulacroParcial.model.Publicaciones;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.repository.ComentariosRepository;
import com.example.SimulacroParcial.repository.PublicacionesRepository;
import com.example.SimulacroParcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequestMapping("/comentarios")
@RestController
public class ComentariosController {
    @Autowired
    private ComentariosRepository comentariosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionesRepository publicacionesRepository;
    private static final String PUBLICACION_NOT_FOUND = "No se encontro la publicacion";
    private static final String USUARIO_NOT_FOUND = "No se encontro el usuario";
    @GetMapping("")
    public List<Comentarios> getComentarios() {
        return comentariosRepository.findAll();
    }

    @PostMapping("/{idPublicacion}/{idUsuario}")
    public void addComentarios(@PathVariable final Integer idUsuario, @RequestBody final Comentarios c, @PathVariable final Integer idPublicacion) {
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, idUsuario)));
        Publicaciones p = publicacionesRepository.findById(idPublicacion).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PUBLICACION_NOT_FOUND, idPublicacion)));

        p.getComentariosList().add(c);
        c.setOwner(u.getNombre());
        c.setPublicaciones(p);
        comentariosRepository.save(c);
    }

    @Scheduled(cron="${cronExpression}")
    public void borrarcomentario(){
        List<Comentarios> list = comentariosRepository.findAll();
        for (Comentarios c : list) {
            comentariosRepository.deleteById(c.getId());
        }
    }
}
