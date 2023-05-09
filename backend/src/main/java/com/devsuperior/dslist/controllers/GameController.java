package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dtos.GameMinDto;
import com.devsuperior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {
    @Autowired
    GameService service;

    @GetMapping
    public ResponseEntity<List<GameMinDto>> findAll() {
        List<GameMinDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


}

