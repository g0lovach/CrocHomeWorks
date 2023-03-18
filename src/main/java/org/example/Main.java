package org.example;



public class Main {
    public static void main(String[] args) {
        Parking p = new Parking(new Entry[]{new Entry(0,"", new Car[]{})}, new Departure[]{new Departure(0,"", new Car[]{})}, 0 );
        Car car1 = new Car(1);

        p.doEntry(new Entry(0, "", new Car[]{}), car1);
        p.doDeparture(new Departure(0,"", new Car[]{} ), car1);

        System.out.println(p.getFailedTries().length);



    }
}