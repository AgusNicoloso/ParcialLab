package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Comentarios;
import com.example.SimulacroParcial.model.Publicaciones;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.repository.ComentariosRepository;
import com.example.SimulacroParcial.repository.PublicacionesRepository;
import com.example.SimulacroParcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ConsumerService {
    @Autowired
    private PublicacionesRepository publicacionesRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ComentariosRepository comentariosRepository;
        @Async("threadPoolTaskExecutor")
        public CompletableFuture<List<Comentarios>> getComentarios() {
            List<Comentarios> comentarios = comentariosRepository.findAll();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture(comentarios);
        }

        @Async("threadPoolTaskExecutor")
        public CompletableFuture<List<Publicaciones>> getPublicaciones() {

            List<Publicaciones> publicaciones = publicacionesRepository.findAll();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture(publicaciones);
        }
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Usuario>> getUsuario() {
            List<Usuario> usuarios = usuarioRepository.findAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(usuarios);
    }
}


