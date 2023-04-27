package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

import java.util.List;

public interface ActorController {

    NetflixResponse<List<ActorRest>> getActors() throws NetflixException;
    NetflixResponse <ActorRest> getActor(Long id) throws NetflixException;

    NetflixResponse<ActorRest> createCategory(ActorRest actorRest) throws NetflixException;
    NetflixResponse<ActorRest> updateCategory(ActorRest actorRest) throws NetflixException;
    NetflixResponse<ActorRest> deleteCategory(ActorRest actorRest) throws NetflixException;
}
