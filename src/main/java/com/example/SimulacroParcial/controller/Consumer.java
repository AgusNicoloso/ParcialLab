package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Comentarios;
import com.example.SimulacroParcial.model.Publicaciones;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/consumer")
public class Consumer {



        @Autowired
        private ConsumerService consumerService;

        @GetMapping("/async")
        public ResponseEntity<?> getAsync() {
            CompletableFuture<List<Comentarios>> comentarios = consumerService.getComentarios();
            CompletableFuture<List<Publicaciones>> publicaciones = consumerService.getPublicaciones();
            CompletableFuture<List<Usuario>> usuario = consumerService.getUsuario();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(comentarios.join().toString()+ "\n" + publicaciones.join().toString() + "\n" + usuario.join().toString());
        }
    }

