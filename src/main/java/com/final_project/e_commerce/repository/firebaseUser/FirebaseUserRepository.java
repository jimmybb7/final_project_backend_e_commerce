package com.final_project.e_commerce.repository.firebaseUser;

import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FirebaseUserRepository extends CrudRepository<FirebaseUserEntity, Integer> {

    @Query(nativeQuery = true,
    value = "select * from firebase_user where email = ?1")
    Optional<FirebaseUserEntity> getFirebaseUserByEmail(String email);
}
