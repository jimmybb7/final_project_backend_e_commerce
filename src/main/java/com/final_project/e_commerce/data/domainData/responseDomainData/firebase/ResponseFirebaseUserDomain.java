package com.final_project.e_commerce.data.domainData.responseDomainData.firebase;

import lombok.Data;

@Data
public class ResponseFirebaseUserDomain {
    private Integer uid;
    private String email;
    private String firebaseId;
}
