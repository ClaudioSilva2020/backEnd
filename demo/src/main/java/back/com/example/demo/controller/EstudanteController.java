package back.com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import back.com.example.demo.entity.Estudante;
import back.com.example.demo.services.EstudanteService;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/estudantes")
@AllArgsConstructor
public class EstudanteController {

    private EstudanteService estudanteService;

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscaEstudanteforid(@PathVariable Long id){
        return estudanteService.buscaEstudanteforid(id);
    }

    @GetMapping
    public List<Estudante> buscaAllEstudante(){
        return estudanteService.buscaAllEstudante();
    }


    @PostMapping
    public ResponseEntity<Estudante> cadastraEstudante(@RequestBody Estudante estudante){
        return estudanteService.cadastraEstudante(estudante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizaEstudante(@PathVariable Long id, @RequestBody Estudante estudante){
        return estudanteService.atualizaEstudante(estudante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaEstudante(@PathVariable Long id){
        return estudanteService.deletaEstudante(id);
    }
    
}
