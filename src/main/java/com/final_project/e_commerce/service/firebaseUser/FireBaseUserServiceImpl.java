package com.final_project.e_commerce.service.firebaseUser;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.mapper.firebaseUser.ChangeToFirebaseUserEntity;
import com.final_project.e_commerce.repository.firebaseUser.FirebaseUserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FireBaseUserServiceImpl implements FirebaseUserService{

    private final FirebaseUserRepository firebaseUserRepository;
    private final ChangeToFirebaseUserEntity changeToFirebaseUserEntity;

    public FireBaseUserServiceImpl(FirebaseUserRepository firebaseUserRepository, ChangeToFirebaseUserEntity changeToFirebaseUserEntity) {
        this.firebaseUserRepository = firebaseUserRepository;
        this.changeToFirebaseUserEntity = changeToFirebaseUserEntity;
    }

    @Cacheable(cacheNames = "getFirebaseUserByEmail", key = "#reqFireBaseUserDomain.email")
    @Override
    public FirebaseUserEntity getFirebaseUserByEmail(ReqFirebaseUserDomain reqFireBaseUserDomain){
        FirebaseUserEntity firebaseUserEntity = changeToFirebaseUserEntity.changeToFirebaseUserEntity(reqFireBaseUserDomain);
        Optional<FirebaseUserEntity> firebaseUserByEmail = firebaseUserRepository.getFirebaseUserByEmail(reqFireBaseUserDomain.getEmail());
        if(firebaseUserByEmail.isEmpty()){
            firebaseUserRepository.save(firebaseUserEntity);
            return firebaseUserEntity;
        }
        return firebaseUserByEmail.get();
    }
}
