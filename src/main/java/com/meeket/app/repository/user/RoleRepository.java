package com.meeket.app.repository.user;



import com.meeket.app.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   // Role findByName(String name);


    @Transactional
    @Query(value= "SELECT * FROM public.role WHERE name = ?1", nativeQuery = true)
    Role getRoleByName(String name);

    @Transactional
    @Query(value= "SELECT name FROM public.role; ", nativeQuery = true)
    List<String> getAllRole();

    @Transactional
    @Query(value= "SELECT * FROM public.role WHERE name = ?1", nativeQuery = true)
    Role roleExits(String name);
}
