package com.devsuperior.dslist.projections;

public interface GameMinProjection {

    Long getId();

    String getTitle();

    Integer getGameYear(); // usou o que foi no AS game_year

    String getImgUrl();

    String getShortDescription();

    Integer getPosition();

}
