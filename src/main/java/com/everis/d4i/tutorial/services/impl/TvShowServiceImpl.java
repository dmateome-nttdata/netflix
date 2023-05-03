package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TvShowServiceImpl implements TvShowService {

    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public TvShowRest getTvShowById(Long id) throws NetflixException {

        try {
            return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }

    }

    @Override
    public TvShowRest insertNewCategory(Long idShow, Long idCategory) throws NetflixException {
        try {
            TvShow tv = tvShowRepository.findById(idShow).orElse(null);
            Category c = categoryRepository.findById(idCategory).orElse(null);
            List<Category> list = tv.getCategories();
            if (!list.contains(c)) {
                list.add(c);
                tv.setCategories(list);
                tvShowRepository.save(tv);
                return modelMapper.map(tv, TvShowRest.class);
            } else {
                throw new NotFoundException("This already added");
            }
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public TvShowRest updateTvShow(TvShowRest tvShowRest) throws NetflixException {
        try {
            TvShow tv=tvShowRepository.findById(tvShowRest.getId()).orElse(null);
            if(tv != null){
                tv.setName(tvShowRest.getName());
                tvShowRepository.save(tv);
            }
            return modelMapper.map(tv,TvShowRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public TvShowRest deleteTvShow(TvShowRest tvShowRest) throws NetflixException {
        try {
            tvShowRepository.deleteById(tvShowRest.getId());
            return tvShowRest;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public TvShowRest createTvShow(TvShowRest show) throws NetflixException {
        tvShowRepository.save(modelMapper.map(show, TvShow.class));
        return show;
    }

}
