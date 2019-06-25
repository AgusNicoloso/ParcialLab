package com.example.SimulacroParcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allmodel {

    private List<Comentarios> comentariosList;
    private List<Publicaciones> publicacionesList;
    private List<Usuario> usuarioList;

}
