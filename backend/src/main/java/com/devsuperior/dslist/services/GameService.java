package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dtos.GameDto;
import com.devsuperior.dslist.dtos.GameMinDto;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository repository;

    @Transactional(readOnly = true)
    public List<GameMinDto> findAll() {
        List<Game> list = repository.findAll();
        return list.stream().map(game -> new GameMinDto(game)).toList();
    }

    @Transactional(readOnly = true)
    public GameDto findById(Long id) {
        Game game = repository.findById(id).get();
        return new GameDto(game);
    }

    @Transactional(readOnly = true) // fez essa função no GameService pq retorna games, porém no controller vai no GameList (/lists)
    public List<GameMinDto> findGamesByList(Long listId) {
        List<GameMinProjection> list = repository.searchByList(listId);
        return list.stream().map(game -> new GameMinDto(game)).toList();
    }


}
