package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface TvShowController {

	//NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	NetflixResponse<TvShowRest> insertCategoryInTvShow(Long idShow,Long idCategory) throws NetflixException;
	NetflixResponse<TvShowRest> updateNameInTvShow(TvShowRest tvShowRest) throws NetflixException;

	NetflixResponse<TvShowRest> deleteTvShow(TvShowRest tvShowRest) throws NetflixException;

}
