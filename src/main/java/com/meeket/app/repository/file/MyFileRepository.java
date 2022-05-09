package com.meeket.app.repository.file;


import com.meeket.app.models.file.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface MyFileRepository extends JpaRepository<MyFile,Integer> {

    @Transactional
    @Query(value= "SELECT * FROM myfile WHERE id = ?1 ", nativeQuery = true)
    MyFile getMyFileById(Integer id);

    @Transactional
    @Query(value= "SELECT * FROM myfile WHERE code = ?1 ", nativeQuery = true)
    MyFile getMyFileByCode(String path);

    @Transactional
    @Query(value= "SELECT * FROM myfile WHERE user_id = ?1 ", nativeQuery = true)
    List<MyFile> getListForUser(long userId);

    @Transactional
    @Query(value= "SELECT * FROM myfile WHERE user_id = ?1 AND code = ?2 ", nativeQuery = true)
    List<MyFile> getMyFileForUserByCode(long userId,String code);



    @Transactional
    @Query(value= "SELECT * FROM public.bank WHERE kata LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<MyFile> searchMyFileByName(String code);


}
