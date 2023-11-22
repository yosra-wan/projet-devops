package com.example.authentication.repository;

import com.example.authentication.model.Permission;
import com.example.authentication.model.PermissionId;
import com.example.authentication.model.STUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PermissionRepo extends JpaRepository<Permission, PermissionId> {

    List<Permission> findByUser(STUser user);

    Permission findByUserAndScreen_Label(STUser user, String screenLabel);
}
