package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface TvShowService {

	//List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException;

	TvShowRest getTvShowById(Long id) throws NetflixException;
	TvShowRest inserNewCategory(Long idShow, Long idCategory) throws NetflixException;

	TvShowRest updateTvShow(TvShowRest tvShowRest) throws NetflixException;
}
