package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.RewardRest;
import com.everis.d4i.tutorial.repositories.RewardRepository;
import com.everis.d4i.tutorial.services.RewardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<RewardRest> getRewardByTvShowId(Long tvShowId) throws NetflixException {
        try {
            return rewardRepository.findByTvShowId(tvShowId).stream()
                    .map(reward -> modelMapper.map(reward, RewardRest.class)).collect(Collectors.toList());
        } catch (
                EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }
}
