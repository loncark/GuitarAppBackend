package com.loncark.guitarapp.model;

public class MaliciousUserInfo extends UserInfo {

    public MaliciousUserInfo(Long id, String name, String password, String roles) {
        super(id, name, password, roles);
    }

    @Override
    public String printMessage() {
        return "MALICIOUS USER";
    }
}
