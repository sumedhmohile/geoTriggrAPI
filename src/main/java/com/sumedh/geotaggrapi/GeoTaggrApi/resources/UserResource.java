package com.sumedh.geotaggrapi.GeoTaggrApi.resources;

import com.sumedh.geotaggrapi.GeoTaggrApi.domain.User;
import com.sumedh.geotaggrapi.GeoTaggrApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> requestMap) {
        String name = (String) requestMap.get("name");
        String facebookId = (String) requestMap.get("facebookId");
        String fcmToken = (String) requestMap.get("fcmToken");

        User user = userService.loginUser(name, facebookId, fcmToken);

        String token = userService.generateUserJWT(user);

        Map<String, String> userDataMap = new HashMap<>();
        userDataMap.put("token", token);

        return new ResponseEntity<>(userDataMap, HttpStatus.OK);
    }

}
