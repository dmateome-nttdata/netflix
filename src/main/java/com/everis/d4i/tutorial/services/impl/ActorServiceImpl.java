package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
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
        if(actorRest.getName()!=null)
            actor.setName(actorRest.getName());
        else if(actorRest.getSurnames()!=null)
            actor.setSurnames(actorRest.getSurnames());
        else if(actorRest.getAge()!=null)
            actor.setAge(actorRest.getAge());
        else if (actorRest.getChapters()!=null)
            actor.setChapters(actorRest.getChapters());

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
        Actor actor = actorRepository.getOne(actorRest.getId());
        try {
            actorRepository.delete(actor);
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
        }
        return modelMapper.map(actor, ActorRest.class);
    }
}
