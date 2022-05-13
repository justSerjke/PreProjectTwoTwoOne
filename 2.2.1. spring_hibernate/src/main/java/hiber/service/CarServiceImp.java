package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImp implements CarService{

    private final CarDao carDao;

    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional
    public void addCar(Car car) {
        carDao.addCar(car);
    }
}
