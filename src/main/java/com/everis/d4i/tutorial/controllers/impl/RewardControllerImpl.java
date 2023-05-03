package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.controllers.RewardController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.RewardRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.RewardService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( RestConstants.RESOURCE_REWARD)
public class RewardControllerImpl implements RewardController {

    @Autowired
    private RewardService rewardService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE,path = "users/tvshowrewards/{id}")
    public NetflixResponse<List<RewardRest>> getTvShowRewards(@PathVariable Long id) throws NetflixException {

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                rewardService.getRewardByTvShowId(id));
    }
}
