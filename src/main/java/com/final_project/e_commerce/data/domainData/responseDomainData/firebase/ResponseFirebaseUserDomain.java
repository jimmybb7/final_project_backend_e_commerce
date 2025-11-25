package com.final_project.e_commerce.data.domainData.responseDomainData.firebase;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseFirebaseUserDomain implements Serializable {
    private Integer uid;
    private String email;
    private String firebaseId;
}
