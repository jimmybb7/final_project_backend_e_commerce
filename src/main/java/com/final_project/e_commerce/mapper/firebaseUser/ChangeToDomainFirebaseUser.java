package com.final_project.e_commerce.mapper.firebaseUser;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class ChangeToDomainFirebaseUser {
    public ReqFirebaseUserDomain changeJwtToReqDomainFirebaseUser(Jwt jwt){
        ReqFirebaseUserDomain reqFireBaseUserDomain = new ReqFirebaseUserDomain();
        reqFireBaseUserDomain.setFirebaseId(jwt.getClaimAsString("user_id"));
        reqFireBaseUserDomain.setEmail(jwt.getClaimAsString("email"));
        return  reqFireBaseUserDomain;
    }
}
