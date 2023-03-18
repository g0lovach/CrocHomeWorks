package org.example;

public class Entry {

    int id;
    String description;
    Car[] guests;

    public Entry(int id, String description, Car[] guests) {
        this.id = id;
        this.description = description;
        this.guests = guests;
    }
    public void appendGuest(Car car){
        Car[] tmp = new Car[this.guests.length+1];
        this.guests = tmp;
        guests[this.guests.length-1] = car;

    }

    public int getGuestsCount() {
        return guests.length;
    }
}
