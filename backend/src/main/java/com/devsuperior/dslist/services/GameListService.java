package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dtos.GameListDto;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDto> findAll() {
        List<GameList> list = gameListRepository.findAll();
        return list.stream().map(gameList -> new GameListDto(gameList)).toList();
    }

    @Transactional
    //criou esse método no List por ser a lista de jogos e não no GameService
    public void replacementMove(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection obj = list.remove(sourceIndex); // removeu o elemento da list nesse momento
        list.add(destinationIndex, obj); // atualizou a posição nova
        //inseriu no GameListRepository uma consulta UPDATE
        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
            //vai percorrer a lista dos jogos pegando o id da lista, id do jogo, e colocar na posição i
        }
    }


}
