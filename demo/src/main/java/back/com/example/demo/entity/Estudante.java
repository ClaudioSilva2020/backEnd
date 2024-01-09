package back.com.example.demo.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Cria todos os metodos get e set
@AllArgsConstructor  //cria construtor com todos os par
@NoArgsConstructor // cria construtor sem parametros
@Builder // instanciar de forma simples
public class Estudante {
    private long id;
    private String name;
    private String email;
    private LocalDate bornDate;
}
