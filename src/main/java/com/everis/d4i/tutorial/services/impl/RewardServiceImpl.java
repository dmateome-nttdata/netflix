package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Reward;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.RewardRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.RewardRepository;
import com.everis.d4i.tutorial.services.RewardService;
import com.everis.d4i.tutorial.services.TvShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private TvShowService tvShowService;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<RewardRest> getRewardByTvShowId(Long tvShowId) throws NetflixException {
        try {
            TvShowRest tv = tvShowService.getTvShowById(tvShowId);

            List<Reward> listR = tv.getRewards();
            return listR.stream().map(reward -> modelMapper.map(reward, RewardRest.class)).collect(Collectors.toList());
        } catch (
                EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }
}
