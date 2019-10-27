package com.training.petfood.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Integer> {

   List<UserDto> findByName(String name);

}
