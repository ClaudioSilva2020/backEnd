package back.com.example.demo.services;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import back.com.example.demo.entity.Estudante;

import java.util.HashMap;

@Service
public class EstudanteService {
    private static Map<Long, Estudante> listaEstudante = new HashMap<>();

    public ResponseEntity<Estudante> buscaEstudanteforid(Long id){
        Estudante estudante = listaEstudante.get(id);
        if (estudante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(estudante);
    }

    public List<Estudante> buscaAllEstudante(){
        return new ArrayList<>(listaEstudante.values());

    }

    public ResponseEntity<Estudante> cadastraEstudante(Estudante estudante){
        listaEstudante.put(estudante.getId(), estudante);
        return ResponseEntity.status(HttpStatus.OK).body(estudante);
    }

    public ResponseEntity<Estudante> atualizaEstudante(Estudante estudante){
        Estudante estudanteEncontrado = listaEstudante.get(estudante.getId());
        if (estudanteEncontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        listaEstudante.put(estudante.getId(), estudante);
        return ResponseEntity.status(HttpStatus.OK).body(estudanteEncontrado);
    }

    public ResponseEntity<String> deletaEstudante(Long id){
        Estudante estudanteEncontrado = listaEstudante.get(id);
        if (estudanteEncontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        listaEstudante.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body("estudante deletado");
    }
}
