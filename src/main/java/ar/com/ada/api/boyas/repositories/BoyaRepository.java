package ar.com.ada.api.boyas.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.boyas.entities.Boya;



@Repository
public interface BoyaRepository extends JpaRepository<Boya, Integer>{

   // @Query(value ="select boya_id from Boya where Boya.luz_color = ?1")
  //  Boya findByColor(Integer color);
    
  //  @Query("select u from User u where u.emailAddress = ?1")
 // User findByEmailAddress(String emailAddress);
   
    
}
