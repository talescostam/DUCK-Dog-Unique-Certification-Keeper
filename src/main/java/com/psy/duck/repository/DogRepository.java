package com.psy.duck.repository;

import com.psy.duck.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository <Dog, Long>, JpaRepository<Dog, Long> {

}
