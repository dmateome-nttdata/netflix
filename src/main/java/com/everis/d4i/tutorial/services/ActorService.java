package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;

import java.util.List;
import java.util.Map;

public interface ActorService {

    List<ActorRest> getActors() throws NetflixException;

    ActorRest getActor(Long id) throws NetflixException;

    ActorRest createActor(ActorRest actorRest) throws NetflixException;
    ActorRest updateActor(ActorRest actorRest) throws NetflixException;
    ActorRest deleteActor(ActorRest actorRest) throws NetflixException;
    Map<TvShowRest, List<ChapterRest>> getTvShowAndChapterOfAnActor(Long ActorId) throws NetflixException;
}
