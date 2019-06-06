package com.example.SimulacroParcial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publicaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "publicaciones")
    private List<Comentarios> comentariosList;
    private String titulo;
    private String descripcion;
    private String foto;
    private LocalDateTime fechapublicacion;
    private Integer liked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;
    @PrePersist
    public void setearHora(){
        if(this.fechapublicacion==null){
            this.fechapublicacion = LocalDateTime.now().plusHours(3).plusMinutes(20);
        }

    }



}
