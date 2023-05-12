package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dtos.GameListDto;
import com.devsuperior.dslist.dtos.GameMinDto;
import com.devsuperior.dslist.dtos.ReplacementDto;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    GameListService gameListService;

    @Autowired
    GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameListDto>> findAll() {
        List<GameListDto> list = gameListService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{listId}/games") // chama o gameService pq o path é lists e não games, por isso usou esse controller.
    public ResponseEntity<List<GameMinDto>> findGamesByList(@PathVariable Long listId) {
        List<GameMinDto> list = gameService.findGamesByList(listId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{listId}/replacement") // chama o gameService pq o path é lists e não games, por isso usou esse controller.
    public ResponseEntity<Void> replacementMove(@PathVariable Long listId, @RequestBody ReplacementDto bodyDto) {
        gameListService.replacementMove(listId, bodyDto.getSourceIndex(), bodyDto.getDestinationIndex() );
        return ResponseEntity.noContent().build();
    }

}

