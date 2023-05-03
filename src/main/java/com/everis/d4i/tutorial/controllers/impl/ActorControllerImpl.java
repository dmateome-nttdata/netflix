package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.controllers.ActorController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping( RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController {

    @Autowired
    private ActorService actorService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/users/actors")
    public NetflixResponse<List<ActorRest>> getActors() throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getActors());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE,path = "/users/actor/{id}")
    public NetflixResponse<ActorRest> getActor(@PathVariable Long id) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getActor(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/admin/createactor",produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> createCategory(@RequestBody ActorRest actorRest) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.createActor(actorRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/admin/updateactor",produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> updateCategory(@RequestBody ActorRest actorRest) throws NetflixException {
        System.out.println(actorRest);
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.updateActor(actorRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/admin/deleteactor",produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> deleteCategory(@RequestBody ActorRest actorRest) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.deleteActor(actorRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/users/tvShows/{idActor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<Map<TvShowRest, List<ChapterRest>>> getTvShowAndChapterOfAnActor(@PathVariable Long idActor) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getTvShowAndChapterOfAnActor(idActor));
    }
}
