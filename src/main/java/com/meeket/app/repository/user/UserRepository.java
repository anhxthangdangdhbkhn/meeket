package com.meeket.app.repository.user;


import com.meeket.app.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Transactional
    @Query(value= "SELECT * FROM public.\"user\" WHERE email = ?1 ", nativeQuery = true)
    User getUserByEmail(String email);

    @Transactional
    @Query(value= "SELECT * FROM public.\"user\" WHERE username = ?1 ", nativeQuery = true)
    User getUserByName(String username);

    @Transactional
    @Query(value= "SELECT * FROM public.\"user\" WHERE id = ?1 ", nativeQuery = true)
    User getUserById(Long id);

    @Transactional
    @Query(value= "UPDATE public.\"user\" SET  active=false WHERE id = ?1 RETURNING * ", nativeQuery = true)
    User deleteUserById(Long id);

    @Transactional
    @Query(value= "SELECT * FROM public.\"user\" where email = ?1 OR username = ?2", nativeQuery = true)
    User userExits(String email,String username);


    @Transactional
    @Query(value= "SELECT * FROM public.\"user\" where email = ?1 ", nativeQuery = true)
    User userExitsByEmail(String email);


    @Transactional
    @Query(value= "SELECT * FROM public.\"user\" where username = ?1 ", nativeQuery = true)
    User userExitsByUsername(String username);


    @Transactional
    @Query(value= "UPDATE public.\"user\" SET email = ?1 WHERE email = ?2 RETURNING * ", nativeQuery = true)
    User updateEmail(String newEmail,String oldEmail);

    @Transactional
    @Query(value= "UPDATE public.\"user\" SET password = ?2 WHERE email = ?1 RETURNING * ", nativeQuery = true)
    User updatePasswordByEmail(String email,String password);

    @Transactional
    @Query(value= "UPDATE public.\"user\" SET username = ?1 WHERE username = ?2 RETURNING * ", nativeQuery = true)
    User updateUserName(String newUserName,String oldUserName);

    @Transactional
    @Query(value= "UPDATE public.\"user\" SET username = ?1 WHERE username = ?2 RETURNING * ", nativeQuery = true)
    User updaateUserName(String newUserName,String oldUserName);


}
