package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.RewardRest;

import java.util.List;

public interface RewardService {
    List<RewardRest> getRewardByTvShowId(Long id) throws NetflixException;
}
