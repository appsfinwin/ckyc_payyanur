package com.finwin.payyanur.ckyc.home.customer_id_proof.model;

public class IdProofType {
    String IdProof,IdProofCode;

    public IdProofType(String idProof, String idProofCode) {
        IdProof = idProof;
        IdProofCode = idProofCode;
    }

    public String getIdProof() {
        return IdProof;
    }

    public void setIdProof(String idProof) {
        IdProof = idProof;
    }

    public String getIdProofCode() {
        return IdProofCode;
    }

    public void setIdProofCode(String idProofCode) {
        IdProofCode = idProofCode;
    }
}
