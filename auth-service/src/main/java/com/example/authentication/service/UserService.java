package com.example.authentication.service;

import com.example.authentication.config.JwtService;
import com.example.authentication.dto.DriverDTO;
import com.example.authentication.dto.LoginDTO;
import com.example.authentication.exception.STUserNotFoundException;
import com.example.authentication.model.STAdmin;
import com.example.authentication.model.STDriver;
import com.example.authentication.model.STEnterprise;
import com.example.authentication.model.STUser;
import com.example.authentication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IPermissionService permissionService;

    public DriverDTO addDriver(STDriver driver) {

        if (!driver.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") || !driver.getPhone().matches("^\\+216[295]\\d{7}$")) {
            throw new BadCredentialsException("Bad Credentials");
        }
        driver.setUsername(driver.getUsername().toLowerCase());
        driver.setPhone(driver.getPhone());
        driver.setEmail(driver.getEmail().toLowerCase());
        driver.setFirstname(driver.getFirstname().toLowerCase());
        driver.setLastname(driver.getLastname().toLowerCase());
        driver.setPassword(passwordEncoder.encode(driver.getPassword().toLowerCase()));
        driver.setHourRate(driver.getHourRate());
        STDriver persistedUser = userRepo.save(driver);
        permissionService.createUserDefaultPermission(persistedUser);

        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(persistedUser.getId());
        return driverDTO;
        // FIXME
//        return jwtService.generateToken(driver,permissionService.getPermissionsByUser(driver));
    }


    public void addEnterprise(STEnterprise enterprise) {

        if (!enterprise.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") || !enterprise.getPhone().matches("^\\+216[295]\\d{7}$")) {
            throw new BadCredentialsException("Bad Credentials");
        }
        enterprise.setUsername(enterprise.getUsername().toLowerCase());
        enterprise.setPhone(enterprise.getPhone());
        enterprise.setEmail(enterprise.getEmail().toLowerCase());
        enterprise.setFirstname(enterprise.getFirstname().toLowerCase());
        enterprise.setLastname(enterprise.getLastname().toLowerCase());
        enterprise.setPassword(passwordEncoder.encode(enterprise.getPassword().toLowerCase()));
        enterprise.setAddress(enterprise.getAddress().toLowerCase());
        enterprise.setMatriculeFiscale(enterprise.getMatriculeFiscale().toLowerCase());
        STEnterprise persistedUser = userRepo.save(enterprise);
        permissionService.createUserDefaultPermission(persistedUser);
        // FIXME
//        return jwtService.generateToken(enterprise,permissionService.getPermissionsByUser(enterprise));
    }

    public String register(STAdmin user) {

        if (!user.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") || !user.getPhone().matches("^\\+216[295]\\d{7}$")) {
            throw new BadCredentialsException("Bad Credentials");
        }

        user.setUsername(user.getUsername().toLowerCase());
        user.setPhone(user.getPhone());
        user.setEmail(user.getEmail().toLowerCase());
        user.setFirstname(user.getFirstname().toLowerCase());
        user.setLastname(user.getLastname().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword().toLowerCase()));
        STUser persistedUser = userRepo.save(user);
        permissionService.createUserDefaultPermission(persistedUser);

        return jwtService.generateToken(user,permissionService.getPermissionsByUser(user));
    }



    public String login(LoginDTO user) throws STUserNotFoundException {
        STUser userFound;
        String login = user.getLogin().toLowerCase();
        if (login.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            userFound = userRepo.findByEmail(login);

        } else if (login.matches("^(00|\\+)216[295]\\d{7}$\n")) {
            userFound = userRepo.findByPhone(login);
        } else {
            userFound = userRepo.findByUsername(login);
        }
        if (userFound == null) {
            throw new STUserNotFoundException();
        }
        if (!passwordEncoder.matches(user.getPassword().toLowerCase(), userFound.getPassword())
        ) {
            throw new BadCredentialsException("Bad credentials");
        }

        // TODO yosra; passer la liste des permissions par ecran (getPermissionsByUserGroupByScreen_label())
        return jwtService.generateToken(userFound,permissionService.getPermissionsByUser(userFound));
    }


    public boolean isAdmin(String email) throws STUserNotFoundException {
            STUser user = userRepo.findByEmail(email.toLowerCase());
            if (user != null) {
                boolean isAdmin = user instanceof STAdmin;
                System.out.println("Verification: " + isAdmin);
                return isAdmin;
            } else {
              throw new STUserNotFoundException("User not found");
            }
    }

    public boolean isDriver(String email) throws STUserNotFoundException {
        STUser user = userRepo.findByEmail(email.toLowerCase());
        if (user != null) {
            boolean isDriver = user instanceof STDriver;
            return isDriver;
        } else {
            throw new STUserNotFoundException("User not found");
        }
    }


        public boolean isEntreprise(String email) throws STUserNotFoundException {
        STUser user = userRepo.findByEmail(email.toLowerCase());
        if (user != null) {
            boolean isEntreprise = user instanceof STEnterprise;
            return isEntreprise;
        } else {
            throw new STUserNotFoundException("User not found");
        }
    }


    public List<STUser> getAllUsers() {
        List<STUser> users = userRepo.findAll();
        return users;
    }
}
