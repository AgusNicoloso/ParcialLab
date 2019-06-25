package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.model.PublicacionesxUsuario;
import com.example.SimulacroParcial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario,Integer>{
    String NATIVE_QUERY = "select publicaciones.descripcion as Publicacion, usuario.nombre as Nombre_Dueño, count(comentarios.id) as Cantidad_Comentarios from usuario inner join publicaciones on usuario.id = publicaciones.usuario_id inner join comentarios on publicaciones.id = comentarios.publicaciones_id group by publicaciones.id;";
    @Query(value = NATIVE_QUERY, nativeQuery = true)
    List<PublicacionesxUsuario> getCantPublixUsuario();
}
