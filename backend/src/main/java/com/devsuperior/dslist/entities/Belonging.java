package com.devsuperior.dslist.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_belonging")
public class Belonging {

    @EmbeddedId // representando 2 campos na tabela relacional
    private BelongingPK id = new BelongingPK();

    private Integer position;

    public Belonging() {
    }

    //recebeu o game e list (Belonging tem referencia pro Game e GameList, qdo criar um objeto Belonging vai passar Game e GameList)
    public Belonging(Game game, GameList list, Integer position) {
        id.setGame(game); // essa referencia de game joga dentro do BelongingPK
        id.setList(list); // essa referencia de list joga dentro do BelongingPK
        this.position = position;
    }

    public BelongingPK getId() {
        return id;
    }

    public void setId(BelongingPK id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    //Comparou só o ID do Belonging
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Belonging belonging)) return false;
        return Objects.equals(getId(), belonging.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
