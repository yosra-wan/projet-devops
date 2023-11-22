package com.example.authentication.service;

import com.example.authentication.dto.PermissionHandler;
import com.example.authentication.exception.STUserNotFoundException;
import com.example.authentication.model.Permission;
import com.example.authentication.model.STUser;

import java.util.Map;

public interface IPermissionService {

    void createUserDefaultPermission(STUser persistedUser);

    Map<String, Object> getPermissionsByUser(STUser user);

    Permission updatePermissions(Integer id, String label, PermissionHandler permHand) throws STUserNotFoundException;


}
