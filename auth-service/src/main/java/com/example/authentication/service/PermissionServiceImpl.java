package com.example.authentication.service;

import com.example.authentication.dto.PermissionHandler;
import com.example.authentication.dto.UserDTO;
import com.example.authentication.dto.permissionDTO;
import com.example.authentication.exception.STUserNotFoundException;
import com.example.authentication.model.*;
import com.example.authentication.repository.PermissionRepo;
import com.example.authentication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    private static final String ADMIN_USERNAME = "yosra";
    @Autowired
    private IScreenService screenService;

    @Autowired
    private PermissionRepo permissionRepo;

  @Autowired
    private UserRepo userRepo;

    @Override
    public void createUserDefaultPermission(STUser persistedUser) {
        int defaultPermissionCode;
        if (persistedUser instanceof STDriver) {
                defaultPermissionCode = 1;
        } else if (persistedUser instanceof STEnterprise) {
                defaultPermissionCode = 4;
        } else {
                defaultPermissionCode = 7;
        }
        screenService.findAll().forEach(screen -> {
            Permission permission = new Permission();
            PermissionId id = new PermissionId();
            id.setUserId(persistedUser.getId());
            id.setScreenId(screen.getId());
            permission.setId(id);
            permission.setUser(persistedUser);
            permission.setScreen(screen);
            permission.setCode(defaultPermissionCode);
            permissionRepo.save(permission);
        });
    }


    @Override
    public Map<String, Object> getPermissionsByUser(STUser user) {
        List<Permission> permissions = permissionRepo.findByUser(user);

        HashMap<String, Object> permissionMap = new HashMap<>();
        for (Permission permission : permissions) {
            permissionDTO dto = new permissionDTO();
            dto.setScreen_label(permission.getScreen().getLabel());
            dto.setCode(permission.getCode());
           permissionMap.put(String.valueOf(dto.getScreen_label()), buildPermissionHandler(dto.getCode()));
//            permissionMap.put(String.valueOf(dto.getScreen_label()), dto.getCode());
        }


        return permissionMap;
    }

    @Override
    public Permission updatePermissions(Integer userId, String label, PermissionHandler permHand)  {
        //??
        STUser user = userRepo.findById(userId).get();

        Permission permission = permissionRepo.findByUserAndScreen_Label(user, label);
        if (permission != null) {
            int code = 0;

            if (permHand.isCanDelete()) {
                code += Math.pow(2, 2);
            }
            if (permHand.isCanAdd()) {
                code += Math.pow(2, 1);
            }
            if (permHand.isCanDisplay()) {
                code += Math.pow(2, 0);
            }
            permission.setCode(code);
            permissionRepo.save(permission);
            return permission;
        } else {
            System.out.println("Permission not found for the given user and label.");
        return null;
        }
    }

    private PermissionHandler buildPermissionHandler(int code) {
        if(code >= 4) return new PermissionHandler(true,true,true);
        else if(code >= 2) return new PermissionHandler(false,true,true);
        else if(code == 1) return new PermissionHandler(false,false,true);
        else return new PermissionHandler(false,false,false);

    }



}



