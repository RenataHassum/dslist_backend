package com.devsuperior.dslist.projections;

public interface GameMinProjection {

    Long getId();

    String getTitle();

    Integer getYear(); // usou o que foi no AS year

    String getImgUrl();

    String getShortDescription();

    Integer getPosition();

}
