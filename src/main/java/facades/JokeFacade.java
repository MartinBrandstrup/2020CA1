package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JokeFacade
{

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade()
    {
    }

    public static void main(String[] args) {
        JokeFacade jf = new JokeFacade();
        jf.addJoke("plat", "Hvad er det værste ved at blive fyret fra et jobcenter? Man skal også møde op dagen efter.", 4, "børnevenlig");
    }
    
    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }
    
    public Joke addJoke(String type, String j, int funnyness, String description){
     
           EntityManager em = emf.createEntityManager();
           
           Joke joke1 = new Joke(type, j, funnyness, description);
        try {
            
            em.getTransaction().begin();
            em.persist(joke1);
            em.getTransaction().commit();
            
        } finally{
            em.close();
        }
        
        return joke1;   
    }

    public long getJokeCount()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            long jokeCount = (long) em.createQuery("SELECT COUNT(r) FROM Joke r").getSingleResult();
            return jokeCount;
        }
        finally
        {
            em.close();
        }}

    /**
 * @author codemiles.
 */
 
// public List<JokeDTO> getAllJokes()
//    {
//        EntityManager em = getEntityManager();
//        try
//        {
//            List<JokeDTO> carDTOList = new ArrayList<>();
//            TypedQuery<Joke> query
//                    = em.createQuery("SELECT j FROM Joke j", Joke.class);
//            query.getResultList().forEach((j) ->
//            {
//                carDTOList.add(new JokeDTO());
//            });
//            return carDTOList;}
//        catch (Exception ex){
//            System.out.println("Operation failed. Be your own laugh!!");
//            return null;}
//        finally
//        {
//          em.close();
//        }}

   public Joke getByID(int id)
    {
        EntityManager em = getEntityManager();
        try
        { 
            Joke j = em.find(Joke.class, id);
            return j; }
        catch (Exception ex)
        { 
            System.out.println("Failed to find the specified Joke object. There's no fun in it!");
            return null;}
        finally
        { 
            em.close();}}
   
   
       public long getRandomJoke()
    {
        EntityManager em = getEntityManager();
        try
        {
            long jokeCount = (long) em.createQuery("SELECT COUNT(r) FROM Joke r").getSingleResult();
            return jokeCount;
        }
        finally
        {
            em.close();
        }
    }
   
//  EntityManager em = emf.
// 
//List<Song> songs = entityManager
//.createQuery(
//    "SELECT s " +
//    "FROM Song s " +
//    "ORDER BY random()", Song.class)
//.getResultList();
// 
//
//
}
