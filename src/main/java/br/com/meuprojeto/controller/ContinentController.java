package br.com.meuprojeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuprojeto.service.ContinentService;

import java.util.List;

@RestController
@RequestMapping
public class ContinentController {

    private final ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/ListarNomeContinentes")
    public ResponseEntity<List<String>> listarNomeContinentes() {
        List<String> continentes = continentService.obterNomesContinentes();
        return ResponseEntity.ok(continentes);
    }

}
