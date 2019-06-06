package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.model.Comentarios;
import com.example.SimulacroParcial.model.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios,Integer> {
}
