package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dtos.GameDto;
import com.devsuperior.dslist.dtos.GameMinDto;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import com.devsuperior.dslist.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<Game> objOptional = repository.findById(id);
        Game game = objOptional.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado, id: " + id + " não existe, informe um id válido"));
        return new GameDto(game);
    }

    @Transactional(readOnly = true) // fez essa função no GameService pq retorna games, porém no controller vai no GameList (/lists)
    public List<GameMinDto> findGamesByList(Long listId) {
        List<GameMinProjection> list = repository.searchByList(listId);
        return list.stream().map(game -> new GameMinDto(game)).toList();
    }


}
