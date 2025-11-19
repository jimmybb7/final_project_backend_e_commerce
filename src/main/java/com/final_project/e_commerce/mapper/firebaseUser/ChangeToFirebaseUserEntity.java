package com.final_project.e_commerce.mapper.firebaseUser;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import org.springframework.stereotype.Component;

@Component
public class ChangeToFirebaseUserEntity {

    public FirebaseUserEntity changeToFirebaseUserEntity(ReqFirebaseUserDomain reqFireBaseUserDomain) {
        FirebaseUserEntity firebaseUserEntity = new FirebaseUserEntity();
        firebaseUserEntity.setEmail(reqFireBaseUserDomain.getEmail());
        firebaseUserEntity.setFirebaseId(reqFireBaseUserDomain.getFirebaseId());
        return firebaseUserEntity;
    }
}
