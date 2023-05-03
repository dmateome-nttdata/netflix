package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

import java.util.List;
import java.util.Map;

public interface ActorController {

    NetflixResponse<List<ActorRest>> getActors() throws NetflixException;
    NetflixResponse <ActorRest> getActor(Long id) throws NetflixException;

    NetflixResponse<ActorRest> createActor(ActorRest actorRest) throws NetflixException;
    NetflixResponse<ActorRest> updateActor(ActorRest actorRest) throws NetflixException;
    NetflixResponse<ActorRest> deleteActor(Long id) throws NetflixException;
    NetflixResponse<Map<TvShowRest, List<ChapterRest>>> getTvShowAndChapterOfAnActor(Long idActor)
            throws NetflixException;
}
