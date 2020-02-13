package pl.javastart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import pl.javastart.model.Car;
import pl.javastart.repository.CarRepository;


@Configuration
@ComponentScan
public class SpringJpaApplication {

	
	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJpaApplication.class);
		CarRepository carRepo = ctx.getBean(CarRepository.class);
		
		List<Car> carList = new ArrayList<>();
		carList.add(new Car("A4", "Audi", 45000D));
        carList.add(new Car("Auris", "Toyota", 50040D));
        carList.add(new Car("New Beetle", "VW", 34040D));

        carList.forEach(carRepo::save);
        
        Car firstCar = carRepo.findById(1L).get();
        carRepo.delete(firstCar);
        
        carRepo.findAll().forEach(System.out::println);
        
        
        ctx.close();
	
	}
}
