package hw5;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static final int CARS_COUNT = 4 ;
    private static final CountDownLatch cdlPrintStart = new CountDownLatch(CARS_COUNT);
    private static final CountDownLatch cdlPrintEnd = new CountDownLatch(CARS_COUNT);
    public static void main (String[] args) {
        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!" );
        Race race = new Race( new Road( 60, false ), new Tunnel(), new Road( 40, true ));
        Car[] cars = new Car[CARS_COUNT];
        for ( int i = 0 ; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + ( int ) (Math.random() * 10 ));
        }
        for ( int i = 0 ; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cdlPrintStart.await();
            System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!" );
            cdlPrintEnd.await();
            System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!" );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getCarsCount(){
        return CARS_COUNT;
    }

    public static void cdlPSCountDown(){
        cdlPrintStart.countDown();
    }

    public static void cdlPECountDown(){
        cdlPrintEnd.countDown();
    }

}