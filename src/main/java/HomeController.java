import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model){
        //First let's create an actor
        Actor actor = new Actor();
        actor.setName("Sandra Bullock");
        actor.setRealName("Sandra Mae Bullowski");

        //Now let's create a movie
        Movie movie1 = new Movie();
        movie1.setTitle("Miss Congeniality");
        movie1.setYear(2000);
        movie1.setDescription("A cop goes undercover as a pageant contestant.");

        Movie movie2 = new Movie();
        movie2.setTitle("The Blind Side");
        movie2.setYear(2009);
        movie2.setDescription("A woman adopts a teenage boy and fosters his talent as a football player.");

        Movie movie3 = new Movie();
        movie3.setTitle("Gravity");
        movie3.setYear(2013);
        movie3.setDescription("Sandra Bullock and George Clooney go to outer space.");

        //Add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        //Add the list of movies to the actor's movie list
        actor.setMovies(movies);

        //Save the actor to the database
        actorRepository.save(actor);

        //Grad all the actors from the database and send them to the template
        model.addAttribute("actors", actorRepository.findAll());
        return"index";
    }
}
