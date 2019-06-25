package com.example.SimulacroParcial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Descripcion;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDateTime fecha;
    private String owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicaciones_id", referencedColumnName = "id")
    @JsonIgnore
    private Publicaciones publicaciones;
    @PrePersist
    public void setearHora(){
        if(this.fecha==null){
            this.setFecha(LocalDateTime.now());
        }

    }

}
