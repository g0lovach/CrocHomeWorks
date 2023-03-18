package org.example;

public class Parking {

    Entry[] entries;
    Departure[] departures;
    int parkingSidesCount;

    int parkingFreeSidesCount;

    TryEntry[] tryEntries;

    Parking(Entry[] entries, Departure[] departures, int parkingSidesCount) {
        this(entries, departures,parkingSidesCount, parkingSidesCount, new TryEntry[]{});

    }
    Parking(Entry[] entries, Departure[] departures, int parkingSidesCount,
            int parkingFreeSidesCount, TryEntry[] tryEntries) {

        this.departures = departures;
        this.entries = entries;
        this.parkingSidesCount = parkingSidesCount;
        this.parkingFreeSidesCount = parkingFreeSidesCount;
        this.tryEntries = tryEntries;

    }

    void doEntry(Entry entry, Car car){
        TryEntry[] tmp = new TryEntry[this.tryEntries.length + 1];
        this.tryEntries = tmp;
        if (parkingFreeSidesCount!=0) {
            tryEntries[tmp.length - 1] = new TryEntry(car, entry, true);
            this.parkingFreeSidesCount -= 1;
            this.entries[entry.id].appendGuest(car);
        }
        else{
            tryEntries[tmp.length - 1] = new TryEntry(car, entry, false);

        }

    }

    void doDeparture(Departure departure, Car car){

        this.parkingFreeSidesCount+=1;
        this.departures[departure.id].appendGuest(car);

    }

    boolean isAvailableToEntry(){

        return parkingFreeSidesCount!=0;

    }

    int getParkingFreeSidesCount(){

        return this.parkingFreeSidesCount;
    }

    TryEntry[] getFailedTries(){
        TryEntry[] res = {};
        for (TryEntry tE: this.tryEntries){
                if (!tE.result){

                    TryEntry[] tmp = new TryEntry[res.length+1];
                    res = tmp;
                    res[tmp.length-1] = tE;


                }

        }
        return res;

    }

    /*int getCountCarsForEntry(Entry entry){

        for (Entry e: this.entries) {
            if (entry == e){
                    return e.guests.length;
            }

        }
        return -1;

    }

    int getCountCarsForDeparture(Departure departure){

        for (Departure d: this.departures) {
            if (departure == d){
                return d.guests.length;
            }

        }
        return -1;

    }*/





}
