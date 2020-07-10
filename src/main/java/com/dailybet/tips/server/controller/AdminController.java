package com.dailybet.tips.server.controller;

import com.dailybet.tips.server.model.BettingTip;
import com.dailybet.tips.server.model.SingleTip;
import com.dailybet.tips.server.model.StringResponse;
import com.dailybet.tips.server.model.User;
import com.dailybet.tips.server.service.BettingTipService;
import com.dailybet.tips.server.service.SingleTipService;
import com.dailybet.tips.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BettingTipService bettingTipService;

    @Autowired
    SingleTipService singleTipService;


    @PutMapping("/api/admin/user-update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User existUser = userService.findByUsername(user.getUsername());
        if (existUser != null && !existUser.getId().equals(user.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/user-delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        userService.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-all")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-number")
    public ResponseEntity<?> numberOfUsers(){
        Long number = userService.numberOfUsers();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        //to return it, we will use String Response because long is not a suitable response for rest api
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/api/admin/bettingtip-create")
    public ResponseEntity<?> createBettingTip(@RequestBody BettingTip bettingTip){
        return new ResponseEntity<>(bettingTipService.saveBettingTip(bettingTip), HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/singletip-create")
    public ResponseEntity<?> createSingleTip(@RequestBody SingleTip singleTip){
        return new ResponseEntity<>(singleTipService.saveSingleTip(singleTip), HttpStatus.CREATED);
    }

    @GetMapping("/api/admin/singletip-bytipp-uid")
    public ResponseEntity<?> getSingleTipByTipUid(@RequestBody String tippUid){
        return new ResponseEntity<>(singleTipService.findBySingleTipByUid(tippUid), HttpStatus.OK);
    }

    @PutMapping("/api/admin/singletip-update")
    public ResponseEntity<?> updateSingleTip(@RequestBody SingleTip singleTip){
        return new ResponseEntity<>(singleTipService.updateSingleTip(singleTip), HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/bettingtip-update")
    public ResponseEntity<?> updateBettingTip(@RequestBody BettingTip bettingtip){
        return new ResponseEntity<>(bettingTipService.updateBettingTip(bettingtip), HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/bettingtip-delete")
    public ResponseEntity<?> deleteProduct(@RequestBody BettingTip bettingtip){
        bettingTipService.deleteBettingTip(bettingtip.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/bettingtip-all")
    public ResponseEntity<?> findAllBettingTip(){
        return new ResponseEntity<>(bettingTipService.findAllBettingTips(), HttpStatus.OK);
    }

    @GetMapping("/api/admin/bettingtip-number")
    public ResponseEntity<?> numberOfBettingTips(){
        Long number = bettingTipService.numberOfBettingTips();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
