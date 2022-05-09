package com.meeket.app.controllers.user;



import com.meeket.app.helper.IUserLogged;
import com.meeket.app.models.ResponseObject;
import com.meeket.app.models.dto.UserChangeEmailDto;
import com.meeket.app.models.dto.UserChangePassDto;
import com.meeket.app.models.dto.UserChangeUserNameDto;
import com.meeket.app.models.dto.UserCreateDto;
import com.meeket.app.models.user.User;
import com.meeket.app.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("v1/api/users")
public class UserController {

//    private final UserService userService;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private IUserLogged userLogged;
//
//
//    @GetMapping
//    public ResponseEntity<ResponseObject> getUsers() {
//
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("TRUE", "Get all  OK", userService.getUsers())
//        );
//    }
//
//    @RequestMapping(value = "/page/{pageNo}", method = GET)
//    @Operation(summary = "pageで　bankの情報を取得")
//    @ResponseBody
//    ResponseEntity<ResponseObject> getUserPage(@PathVariable int pageNo) {
//        Pageable pageable = PageRequest.of(pageNo, 5);
//        Page<User> userPage = userService.findAll(pageable);
//        if (!userPage.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("TRUE", "Get page at " + pageNo, userPage)
//            );
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE", "Cannot find list  user with pageNo= " + pageNo, "")
//            );
//        }
//    }
//
//    @GetMapping(value = "{id}")
//    @Operation(summary = "idで　userの情報を取得")
//    ResponseEntity<ResponseObject> getUserById(@Parameter(description = "idを入力して userの情報を取得の情報を取得")
//                                               @PathVariable("id") long id) {
//        User UserById = userService.getUserById(id);
//        if (!Objects.isNull(UserById)) {
//            UserById.getRoles().forEach(role -> {
//                System.out.println(role.getName());
//            });
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("TRUE", "Get user  by id " + id, UserById)
//            );
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE", "Get user  at id " + id + " not found", "")
//            );
//        }
//
//    }
//
//    @DeleteMapping(value = "{id}")
//    @Operation(summary = "delete a user by id")
//    ResponseEntity<ResponseObject> deleteUserById(@Parameter(description = "delete a user by id")
//                                                  @PathVariable("id") long id) {
//
//        User userExits = userService.getUserById(id);
//        if (!Objects.isNull(userExits)) {
//
//            User userDeleteById = userService.deleteUserById(id);
//            userDeleteById.getRoles().forEach(role -> {
//                System.out.println(role);
//            });
//
//
//            if (!Objects.isNull(userDeleteById)) {
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("TRUE", "Deleted user at " + id, userDeleteById)
//                );
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("FALSE", "Get user  by code  " + id + " not found", "")
//                );
//            }
//
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE", "Get user  by code  " + id + " not found", "")
//            );
//        }
//
//
//    }
//
//    @GetMapping(value = "/email/{email}")
//    @Operation(summary = "email　emailの情報を取得")
//    ResponseEntity<ResponseObject> findByEmail(@Parameter(description = "emailを入力して userの情報を取得の情報を取得")
//                                               @PathVariable("email") String email) {
//        User userByEmail = userService.getUserByEmail(email);
//        if (!Objects.isNull(userByEmail)) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("TRUE", "Get bank  by code " + email, userByEmail)
//            );
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE", "Get bank  by code  " + email + " not found", "")
//            );
//        }
//
//    }
//
//    @PostMapping("")
//    @Operation(summary = "Create a new user")
//    ResponseEntity<ResponseObject> createNewUser(@RequestBody UserCreateDto userCreateDto) {
//
//        boolean userExist = userService.userExits(userCreateDto.getEmail(), userCreateDto.getUsername());
//
//        if (userExist) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    new ResponseObject("FALSE", "username or email exist ", "")
//            );
//        } else {
//
//            User userNew = User.builder()
//                    .username(userCreateDto.getUsername())
//                    .email(userCreateDto.getEmail())
//                    .password(passwordEncoder.encode(userCreateDto.getPassword()))
//                    .active(true)
//                    .roles(new ArrayList<>())
//                    .build();
//            userService.saveUser(userNew);
//            userService.addRoleToUser(userCreateDto.getUsername(), "ROLE_USER");
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(
//                    new ResponseObject("TRUE", "Create new user ", userService.getUser(userCreateDto.getUsername()))
//            );
//
//        }
//    }
//
//    @PutMapping("/email")
//    @Operation(summary = "changeEmail")
//    ResponseEntity<ResponseObject> changeEmail(@RequestBody UserChangeEmailDto userChangeEmailDto) {
//
//        boolean newEmailExist = userService.userExitsByEmail(userChangeEmailDto.getEmail());
//
//        if (newEmailExist) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    new ResponseObject("FALSE", "new email is exist ", "")
//            );
//        }
//
//        boolean checkPass = bCryptPasswordEncoder.matches(userChangeEmailDto.getPassword(),userLogged.getPassword());
//        if(checkPass){
//            User userUpdate  = userService.updateEmail(userChangeEmailDto.getEmail(),userLogged.getEmail());
//
//            if(Objects.isNull(userUpdate)){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("FALSE", "update is fail ", ""));
//            }else{
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("TRUE", "Email is updated ", ""));
//            }
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE", "Password is fail ", ""));
//        }
//
//    }
//
//    @PutMapping("/username")
//    @Operation(summary = "changeUsername")
//    ResponseEntity<ResponseObject> changeUsername(@RequestBody UserChangeUserNameDto userChangeUserNameDto) {
//
//        boolean newUserNameExist = userService.userExitsByUsername(userChangeUserNameDto.getUsername());
//
//        if (newUserNameExist) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    new ResponseObject("FALSE", "new username is exist ", "")
//            );
//        }
//
//        boolean checkPass = bCryptPasswordEncoder.matches(userChangeUserNameDto.getPassword(),userLogged.getPassword());
//        if(checkPass){
//
//            User userUpdate  = userService.updateUserName(userChangeUserNameDto.getUsername(),userLogged.getUsername());
//
//            if(Objects.isNull(userUpdate)){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("FALSE", "update is fail ", ""));
//            }else{
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("TRUE", "Username is updated ", ""));
//            }
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE", "Password is fail ", ""));
//        }
//
//    }
//
//
//    @PutMapping("/password")
//    @Operation(summary = "changePassword")
//    ResponseEntity<ResponseObject> changePassword(@RequestBody UserChangePassDto userChangePassDto) {
//
//
//        boolean checkPass = bCryptPasswordEncoder.matches(userChangePassDto.getOldPassword(),userLogged.getPassword());
//        if(checkPass){
//            String pass = passwordEncoder.encode(userChangePassDto.getNewPassword());
//            User userUpdate  = userService.updatePasswordByEmail(userLogged.getEmail(),pass);
//
//            if(Objects.isNull(userUpdate)){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("FALSE", "update is fail ", ""));
//            }else{
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("TRUE", "Password is updated ", ""));
//            }
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE", "Password is fail ", ""));
//        }
//
//    }
};