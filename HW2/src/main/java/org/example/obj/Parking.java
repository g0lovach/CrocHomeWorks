package org.example.obj;

import java.util.Arrays;

/**
 * Класс описания парковок
 */
public class Parking {

    /**
     * массив въездов {@link EntryPoint}
     */
    private final EntryPoint[] entryPoints;
    /**
     * Массив выездов {@link DeparturePoint}
     */
    private final DeparturePoint[] departurePoints;
    /**
     * Количество мест на парковке
     */
    private final int parkingSidesCount;

    /**
     * Количество свободных мест на парковке
     */
    private int parkingFreeSidesCount;

    /**
     * Массив, содержащий информацию о попытках въезда {@link TryEntry}
     */
    private TryEntry[] triesEntry;


    /**
     * Неполный конструктор класса {@link Parking}.
     * Используется аналогично {@link DeparturePoint#DeparturePoint(int, String)}
     *
     * @param entryPoints - массив точек въезда
     * @param departurePoints - массив точек выезда
     * @param parkingSidesCount - кол-во парковочных мест
     */
    public Parking(EntryPoint[] entryPoints, DeparturePoint[] departurePoints, int parkingSidesCount) {
        this(entryPoints, departurePoints,parkingSidesCount, parkingSidesCount, new TryEntry[]{});

    }

    /**
     * Полный конструктор класса {@link Parking}.
     * Используется аналогично {@link DeparturePoint#DeparturePoint(int, String, Car[])}
     *
     * @param entryPoints - массив точек въезда
     * @param departurePoints - массив точек выезда
     * @param parkingSidesCount - кол-во парковочных мест
     * @param parkingFreeSidesCount - кол-во свободных парковочных мест
     * @param triesEntry - массив попыток въезда
     */
    public Parking(EntryPoint[] entryPoints, DeparturePoint[] departurePoints, int parkingSidesCount,
            int parkingFreeSidesCount, TryEntry[] triesEntry) {

        this.departurePoints = departurePoints;
        this.entryPoints = entryPoints;
        this.parkingSidesCount = parkingSidesCount;
        this.parkingFreeSidesCount = parkingFreeSidesCount;
        this.triesEntry = triesEntry;

    }


    /**
     * Геттер кол-ва свободных мест
     * @return - кол-во свободных мест
     */
    public int getParkingFreeSidesCount() {
        return parkingFreeSidesCount;
    }


    /**
     * Метод, фиксирующий въезд машины на парковку или фиксирующий неудачную попытку въезда
     * (завсисит от наличия свободных мест). Учитывает возможное дублирование
     * @param entryPoint  - въезд
     * @param carsRegNumber - регистрационный номер машины
     */
    public void doEntry(EntryPoint entryPoint, String carsRegNumber){
        triesEntry = Arrays.copyOf(triesEntry, triesEntry.length+1);
        boolean resultOfTry = false;
        if (parkingFreeSidesCount!=0) {
           parkingFreeSidesCount -= 1;
           boolean duplicateFlag = false;
           for (Car car: entryPoint.getGuests()){
               if (car.getRegNumber().equals(carsRegNumber)) {
                   duplicateFlag = true;
                   break;
               }
           }
           if(!duplicateFlag) {
               entryPoint.appendGuest(new Car(carsRegNumber));
           }
            resultOfTry = true;
        }
            triesEntry[triesEntry.length - 1] = new TryEntry(carsRegNumber, entryPoint, resultOfTry);



    }

    /**
     * Метод, фиксирующий факт выезда с парковки. Учитывает возможное дублирование
     * @param departurePoint - выезд
     * @param carsRegNumber - рег. номер авто
     */
    public void doDeparture(DeparturePoint departurePoint, String carsRegNumber){

        parkingFreeSidesCount+=1;
        boolean duplicateFlag = false;
        for (Car car: departurePoint.getGuests()){
            if (car.getRegNumber().equals(carsRegNumber)) {
                duplicateFlag = true;
                break;
            }
        }
        if(!duplicateFlag) {
            departurePoint.appendGuest(new Car(carsRegNumber));
        }


    }

    /**
     * Метод, проверящий доступность въезда (зависит от наличия свободных мест )
     * @return - доступность въезда
     */
    public boolean isAvailableToEntry(){

        return parkingFreeSidesCount!=0;

    }

    /**
     * Метод, возвращающий авто, успешно проехавшие через въезд. Учитывает возможное дублирование.
     * @param entryPoint - въезд
     * @return массив авто, проезжавших въезд
     */
    public Car[] getCarsByEntryPoint(EntryPoint entryPoint){
        Car[] result = {};
        for(TryEntry tE:triesEntry){
            if(tE.isResult() && tE.getEntryPoint().equals(entryPoint)){
                boolean duplicateFlag = false;
                String tmpRegNumber = tE.getCarsRegNumber();
                for(Car tmpCar: result){
                    if (tmpCar.getRegNumber().equals(tmpRegNumber)){
                        duplicateFlag = true;
                        break;
                    }
                }
                if (!duplicateFlag) {
                    result = Arrays.copyOf(result, result.length + 1);
                    result[result.length - 1] = entryPoint.getCarByRegNumber(tmpRegNumber);
                }

            }

        }

        return result;
    }

    /**
     * Метод, возвращающий массив авто, проехавших определенный выездю
     * @param departurePoint - выезд
     * @return массив авто, проехавших определенный выездю
     */
    public Car[] getCarsByDeparturePoint(DeparturePoint departurePoint){

        return departurePoint.getGuests();
    }


    /**
     * Метод, возвращающий массив неудачных попыток въезда.
     * @return - массив неудачных попыток въезда.
     */
    public TryEntry[] getFailedEntryTries(){
        TryEntry[] res = {};
        for (TryEntry tE: this.triesEntry){
                if (!tE.isResult()){

                    TryEntry[] tmp = new TryEntry[res.length+1];
                    res = tmp;
                    res[tmp.length-1] = tE;


                }

        }
        return res;

    }
    /**
     * Метод преобразования в String.
     * P.S. В задаче требований на добавление этого метода не было, но я его прописал для получения наглядной информации
     * об объекте
     * @return - строковое описание {@link Parking}
     */
    @Override
    public String toString(){

        String result = "Parking { entryPoints = " + Arrays.toString(entryPoints) + "; departurePoints = "
                + Arrays.toString(departurePoints) +
                "; parkingSidesCount = " +
                parkingSidesCount +
                "; parkingFreeSidesCount = " +
                parkingFreeSidesCount +
                "; triesEntry = " +
                Arrays.toString(triesEntry);

        return result +"}";
    }






}
