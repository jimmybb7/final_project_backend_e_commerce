package com.final_project.e_commerce.service.firebaseUser;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;

public interface FirebaseUserService {
    FirebaseUserEntity getFirebaseUserByEmail(ReqFirebaseUserDomain reqFireBaseUserDomain);
}
