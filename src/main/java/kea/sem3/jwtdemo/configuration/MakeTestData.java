package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.Month;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public void makePlainUsers(){
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);

        Member mem1 = new Member("xxx","guy@stuff.dk","test12","Jan");
        mem1.addRole(Role.USER);

        memberRepository.save(mem1);

        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users");

    }
    public void makePlainCars(){
        Car car1 = new Car("Tesla", "X", 1000,500);
        Car car2 = new Car("Tesla", "Y", 1300,200);
        carRepository.save(car1);
        carRepository.save(car2);
    }
    public void makePlainReservations(){

        Car car1 = new Car("Tesla", "X", 1000,200);
        carRepository.save(car1);

        Member mem1 = new Member("fsdgfal","jjjddd@stuff.dk","pwhghgh","Jum");
        mem1.addRole(Role.USER);
        memberRepository.save(mem1);

        Reservation res = new Reservation(LocalDate.of(2022, 03, 03), mem1, car1);
        reservationRepository.save(res);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();
        memberRepository.deleteAll();
        reservationRepository.deleteAll();

        makePlainUsers();
        makePlainCars();
        makePlainReservations();
    }
}
