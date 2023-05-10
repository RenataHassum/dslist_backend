package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dtos.GameListDto;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository repository;

    @Transactional(readOnly = true)
    public List<GameListDto> findAll() {
        List<GameList> list = repository.findAll();
        return list.stream().map(gameList -> new GameListDto(gameList)).toList();
    }

}
