package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.controllers.TvShowController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW)
public class TvShowControllerImpl implements TvShowController {

    @Autowired
    private TvShowService tvShowService;

    /*@Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<TvShowRest>> getTvShowsByCategory(@RequestParam Long categoryId)
            throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.getTvShowsByCategory(categoryId));
    }*/

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> getTvShowById(@PathVariable Long id) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.getTvShowById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> insertCategoryInTvShow(@PathVariable Long idShow, @PathVariable Long idCategory) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.inserNewCategory(idShow, idCategory));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> updateNameTvShow(@RequestBody TvShowRest tvShowRest) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.updateTvShow(tvShowRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> deleteTvShow(@RequestBody TvShowRest tvShowRest) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.deleteTvShow(tvShowRest));
    }
}
