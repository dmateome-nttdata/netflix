package unitarios;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.impl.TvShowServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TvShowServiceImplTest {

    @Mock
    TvShowRepository tvShowRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    TvShowServiceImpl tvShowServiceImpl;

    private ModelMapper modelMapper = new ModelMapper();




    @Test
    public void getTvShowById() throws NetflixException {
        List<TvShow> tvshowList = new ArrayList<>();
        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");
        TvShow tvShow2 = new TvShow(2L, "Breaking Bad", "A high school chemistry teacher turns to cooking meth to provide for his family.", "Walter White, a high school chemistry teacher, learns he has cancer and starts cooking meth to provide for his family after he's gone. As he gets deeper into the drug trade, he becomes increasingly ruthless and dangerous.", Year.of(2008), (byte) 18, "Experience the groundbreaking series that changed TV forever.");
        TvShow tvShow3 = new TvShow(3L, "The Crown", "A historical drama about the reign of Queen Elizabeth II.", "The Crown follows the life and reign of Queen Elizabeth II, from her early days as a young princess to the present day. With a focus on the personal relationships and political challenges she faced, the show provides a glimpse into the inner workings of the British monarchy.", Year.of(2016), (byte) 12, "Step into the world of royalty with The Crown.");
        TvShow tvShow4 = new TvShow(4L, "Game of Thrones", "A fantasy epic set in a world of warring kingdoms.", "Game of Thrones takes place in a medieval world of warring kingdoms, where magic, dragons, and politics collide. With multiple storylines and characters, the show is a thrilling ride from start to finish.", Year.of(2011), (byte) 18, "Winter is coming...");
        TvShow tvShow5 = new TvShow(5L, "The Office", "A mockumentary about the daily lives of office employees.", "The Office is a mockumentary-style sitcom that follows the daily lives of the employees of Dunder Mifflin Paper Company. With a lovable cast of characters and hilarious storylines, the show has become a classic of modern television.", Year.of(2005), (byte) 12, "A comedy that will make you laugh out loud.");
        tvshowList.add(tvShow1);
        tvshowList.add(tvShow2);
        tvshowList.add(tvShow3);
        tvshowList.add(tvShow4);
        tvshowList.add(tvShow5);


        when(tvShowRepository.getOne(1L)).thenReturn(tvShow1);


        TvShowRest tvShowRest = tvShowServiceImpl.getTvShowById(1L);
        assertNotNull(tvShowRest);
        assertEquals("Stranger Things", tvShowRest.getName());
        assertNotEquals("The Crown", tvShowRest.getName());
        assertEquals("A group of friends search for their missing friend and uncover a government conspiracy.", tvShowRest.getShortDescription());

    }


    @Test
    void insertNewCategory() throws NetflixException {

        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");
        Category ca = new Category(9L, "Premios Oscars");
        tvShow1.setCategories(new ArrayList<>());
        List<Category> listCaRest = new ArrayList<>();

        when(tvShowRepository.findById(tvShow1.getId())).thenReturn(Optional.of(tvShow1));
        when(categoryRepository.findById(ca.getId())).thenReturn(Optional.of(ca));
        when(tvShowRepository.save(tvShow1)).thenReturn(tvShow1);

        TvShowRest tvShow2 = tvShowServiceImpl.insertNewCategory(1L, ca.getId());
        listCaRest.add(ca);
        assertEquals(tvShow1.getCategories(), listCaRest);

    }

    @Test
    void updateName() throws NetflixException {
        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");

        when(tvShowRepository.findById(tvShow1.getId())).thenReturn(Optional.of(tvShow1));
        when(tvShowRepository.save(tvShow1)).thenReturn(tvShow1);
        TvShowRest tvShow2 =tvShowServiceImpl.updateTvShow(modelMapper.map(tvShow1, TvShowRest.class));

        assertNotNull(tvShow2);
        assertEquals(modelMapper.map(tvShow1, TvShowRest.class), tvShow2);
        assertEquals(tvShow1.getName(), tvShow2.getName());

    }

    @Test
    void deleteTvShow() {
        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");

        when(tvShowRepository.getOne(1L)).thenReturn(null);
        tvShowRepository.deleteById(tvShow1.getId());

        TvShow tvShow2 = tvShowRepository.getOne(1L);

        assertNull(tvShow2);


    }


    /**
     * Código para el insertar un TvShow, que no esta implementado en el controller
     *
     * @throws NetflixException
     */
    @Test
    void insertNewTvShow() throws NetflixException {

        TvShow tvShow1 = new TvShow(1L, "Stranger Things", "A group of friends search for their missing friend and uncover a government conspiracy.", "In the small town of Hawkins, Indiana, a group of friends sets out on a quest to find their missing friend Will Byers. As they search for answers, they uncover a dark government conspiracy involving secret experiments and supernatural forces.", Year.of(2016), (byte) 14, "Watch the award-winning series everyone is talking about.");
        when(tvShowRepository.save(tvShow1)).thenReturn(tvShow1);
        TvShowRest tvShow2 = tvShowServiceImpl.createTvShow(modelMapper.map(tvShow1, TvShowRest.class));

        assertEquals(modelMapper.map(tvShow1, TvShowRest.class), tvShow2);



    }


}
