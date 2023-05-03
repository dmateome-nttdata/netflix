package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public List<ActorRest> getActors() throws NetflixException {
        try {
            return actorRepository.findAll().stream().map(actor -> modelMapper.map(actor, ActorRest.class))
                    .collect(Collectors.toList());

        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ActorRest getActor(Long id) throws NetflixException {
        try {
            return modelMapper.map(actorRepository.getOne(id), ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public ActorRest createActor(ActorRest actorRest) throws NetflixException {
        Actor actor = modelMapper.map(actorRest, Actor.class);
        try {
            actor = actorRepository.save(actor);
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
        }
        return modelMapper.map(actor, ActorRest.class);
    }

    @Override
    public ActorRest updateActor(ActorRest actorRest) throws NetflixException {
        Actor actor = actorRepository.getOne(actorRest.getId());
        if (actorRest.getName() != null)
            actor.setName(actorRest.getName());
        else if (actorRest.getSurnames() != null)
            actor.setSurnames(actorRest.getSurnames());
        else if (actorRest.getAge() != null)
            actor.setAge(actorRest.getAge());


        try {
            actor = actorRepository.save(actor);
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
        }
        return modelMapper.map(actor, ActorRest.class);
    }

    @Override
    public ActorRest deleteActor(ActorRest actorRest) throws NetflixException {
        try {
            Actor actor = actorRepository.getOne(actorRest.getId());
            actorRepository.delete(actor);

            return modelMapper.map(actor, ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public Map<TvShowRest, List<ChapterRest>> getTvShowAndChapterOfAnActor(Long ActorId) throws NetflixException {
        Map<TvShowRest, List<ChapterRest>> listNeeded = new HashMap<>();
        Actor a = actorRepository.getOne(ActorId);
        List<Chapter> chapters = a.getChapters();
        for(Chapter ch : chapters){
            TvShowRest tvSR =  modelMapper.map(ch.getSeason().getTvShow(), TvShowRest.class);
            if(listNeeded.containsKey(tvSR)){
                listNeeded.get(tvSR).add(modelMapper.map(ch, ChapterRest.class));
            }else{
                List<ChapterRest> listChapters = new ArrayList<>();
                listChapters.add(modelMapper.map(ch, ChapterRest.class));
                listNeeded.put(tvSR , listChapters);
            }

        }

        return listNeeded;
    }
}
