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

import java.util.List;

@RestController
@RequestMapping( RestConstants.RESOURCE_TV_SHOW)
public class TvShowControllerImpl implements TvShowController {

    @Autowired
    private TvShowService tvShowService;


    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<TvShowRest>> getTvShowsByCategory(@RequestParam Long categoryId)
            throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.getTvShowsByCategory(categoryId));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> getTvShowById(@PathVariable Long id) throws NetflixException {
        TvShowRest tvshowAux = tvShowService.getTvShowById(id);
        tvshowAux.setRewards(null);
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvshowAux);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/admin/insertcategoryintvshows/{idShow}/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> insertCategoryInTvShow(@PathVariable Long idShow, @PathVariable Long idCategory) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.insertNewCategory(idShow, idCategory));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/admin/updatetvshow", produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> updateNameInTvShow(@RequestBody TvShowRest showRest) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.updateTvShow(showRest));

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/admin/deletetvshow", produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<TvShowRest> deleteTvShow(@RequestBody TvShowRest tvShowRest) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                tvShowService.deleteTvShow(tvShowRest));
    }
}
