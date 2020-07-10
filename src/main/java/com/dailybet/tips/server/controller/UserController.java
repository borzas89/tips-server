package com.dailybet.tips.server.controller;

import com.dailybet.tips.server.jwt.JwtTokenProvider;
import com.dailybet.tips.server.model.Role;
import com.dailybet.tips.server.model.TippCategory;
import com.dailybet.tips.server.model.User;
import com.dailybet.tips.server.service.BettingTipService;
import com.dailybet.tips.server.service.SingleTipService;
import com.dailybet.tips.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private BettingTipService bettingTipService;

    @Autowired
    private SingleTipService singleTipService;

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> getUser(Principal principal){

        if(principal == null){
            //logout will also use here so we should return ok http status.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(tokenProvider.generateToken(authenticationToken));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/api/user/bettingtips")
    public ResponseEntity<?> getAllBettingTips(){
        return new ResponseEntity<>(bettingTipService.findAllBettingTips(), HttpStatus.OK);
    }

    @GetMapping( "api/user/free-bettingtips")
    public ResponseEntity<?> getAllFreeBettingTips()
    {
        return new ResponseEntity<>(bettingTipService.findBettingTipsByCategory(TippCategory.FREE), HttpStatus.OK);
    }

    @GetMapping( "api/user/free-singletips")
    public ResponseEntity<?> getAllFreeSingleTips()
    {
        return new ResponseEntity<>(singleTipService.findAllSingleTips(), HttpStatus.OK);
    }

    @GetMapping( "api/user/free-bettingtips-by-date")
    public ResponseEntity<?> getAllFreeBettingTipsByCategoryAndDate( @RequestParam("date") String date)
    {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(date);
            System.out.println("date " + date);
            System.out.println("formatted date " + parsedDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bettingTipService.findTippsByCategoryAndDate(TippCategory.FREE,new Date()), HttpStatus.OK);
    }

    @GetMapping( "api/user/free-bettingtips-by-today")
    public ResponseEntity<?> getAllFreeBettingTipsByDate()
    {

        Date startDate = new Date();
        Date endDate = new  Date(startDate.getTime() + (1000 * 60 * 60 * 24));

        return new ResponseEntity<>(bettingTipService.getAllBettingTipsByCategoryAndBetweenDates(TippCategory.FREE,startDate,endDate), HttpStatus.OK);
    }
    @GetMapping( "api/user/vip-bettingtips-by-today")
    public ResponseEntity<?> getAllVipBettingTipsByDate()
    {

        Date startDate = new Date();
        Date endDate = new  Date(startDate.getTime() + (1000 * 60 * 60 * 24));

        return new ResponseEntity<>(bettingTipService.getAllBettingTipsByCategoryAndBetweenDates(TippCategory.VIP,startDate,endDate), HttpStatus.OK);
    }


    @GetMapping( "api/user/bettingtip/uid")
    public ResponseEntity<?> getBettingTipByUid( @RequestParam("tippuid") String tippuid)
    {
        return new ResponseEntity<>(bettingTipService.findByBettingTipByUid(tippuid), HttpStatus.OK);
    }

    @GetMapping( "api/user/bettingtip/free-tips-by-tippster")
    public ResponseEntity<?>  findFreeBettingTipByTippster( @RequestParam("name") String tipster)
    {
        return new ResponseEntity<>(bettingTipService.findBettingtipsByTippster(tipster,TippCategory.FREE), HttpStatus.OK);
    }

    @GetMapping( "api/user/bettingtip/vip-tips-by-tippster")
    public ResponseEntity<?>  findVIPBettingTipByTippster( @RequestParam("name") String tipster)
    {
        return new ResponseEntity<>(bettingTipService.findBettingtipsByTippster(tipster,TippCategory.VIP), HttpStatus.OK);
    }

    @GetMapping( "api/user/singletip/free-tips-by-tippster")
    public ResponseEntity<?>  findFreeSingleTipByTippster( @RequestParam("name") String tipster)
    {
        return new ResponseEntity<>(singleTipService.findSingleTipsByTippster(tipster,TippCategory.FREE), HttpStatus.OK);
    }

    @GetMapping( "api/user/singletip/vip-tips-by-tippster")
    public ResponseEntity<?>  findVIPSingleTipByTippster( @RequestParam("name") String tipster)
    {
        return new ResponseEntity<>(singleTipService.findSingleTipsByTippster(tipster,TippCategory.VIP), HttpStatus.OK);
    }

    @GetMapping( "api/user/old-bettingtips-in-week")
    public ResponseEntity<?> getAllBettingTipsByWeek()
    {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,12);
        Date endDate  = calendar.getTime();

        Date startDate= new  Date(endDate.getTime() - (1000 * 60 * 60 * 24 * 7));

        return new ResponseEntity<>(bettingTipService.getAllBetweenDates(startDate,endDate), HttpStatus.OK);
    }

    @GetMapping( "api/user/vip-singletips-by-today")
    public ResponseEntity<?> getAllVipSingleTipsToday()
    {

        Date startDate = new Date();
        Date endDate = new  Date(startDate.getTime() + (1000 * 60 * 60 * 24));

        return new ResponseEntity<>(singleTipService.getAllSingleTipsByCategoryAndBetweenDates(TippCategory.VIP,startDate,endDate), HttpStatus.OK);
    }

    @GetMapping( "api/user/free-singletips-by-today")
    public ResponseEntity<?> getAllFreeSingleTipsToday()
    {

        Date startDate = new Date();
        Date endDate = new  Date(startDate.getTime() + (1000 * 60 * 60 * 24));

        return new ResponseEntity<>(singleTipService.getAllSingleTipsByCategoryAndBetweenDates(TippCategory.FREE,startDate,endDate), HttpStatus.OK);
    }

}
