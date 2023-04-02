package ru.croc.javaschool.accounting;

import ru.croc.javaschool.transport.Vehicle;
import ru.croc.javaschool.transport.autos.Auto;
import ru.croc.javaschool.transport.autos.Car;
import ru.croc.javaschool.transport.autos.Truck;
import ru.croc.javaschool.transport.flyings.BusinessJet;
import ru.croc.javaschool.transport.flyings.Flying;
import ru.croc.javaschool.transport.flyings.Helicopter;
import ru.croc.javaschool.transport.individuals.ElectricScooter;
import ru.croc.javaschool.transport.individuals.Individual;
import ru.croc.javaschool.transport.individuals.Unicycle;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Класс описывающий поведение арендного центра
 */
public class RentCenter {
    /**
     * Список транспорта на балансе
     */
   private Vehicle[] vehicles;


    /**
     * Контракты на аренду
     */
   private RentContract[] rentContracts;

    /**
     * Совершенные движения (приход/расход транспорта)
     */
   private Movement[] movements;
    /**
     * Актуальный идентификатор на случай, если будет добавлен еще транспорт (ему необходим уникальный id)
     */
   private int currentVehicleId;

    /**
     * Конструктор
     */
    public RentCenter() {
        this.vehicles = new Vehicle[]{};
        this.rentContracts = new RentContract[]{};
        this.currentVehicleId = 1;
        this.movements = new Movement[]{};

    }

    /**
     * получение транспорта по id
     * @param id - id транспорта
     * @return - транспорт
     */
    public Vehicle getVehicleById(int id){
        for (Vehicle vehicle: vehicles){
           if (vehicle.getId()==id){
               return vehicle;
           }
        }
        return null;
    }

    /**
     * @return Получение актуального id системе
     */
    public int getCurrentVehicleId() {
        return currentVehicleId;
    }

    /**
     * @return получение транспорта на балансе центра
     */
    public Vehicle[] getVehicles() {
        return vehicles;
    }

    /**
     * Добавление транспорнта на баланс
     * @param vehicle - добавляемый траснпорт
     */
    public void appendVehicle(Vehicle vehicle){
        if (vehicle.getId()==-1) {
            vehicles = Arrays.copyOf(vehicles, vehicles.length + 1);
            vehicles[vehicles.length - 1] = vehicle;
            vehicle.setId(this);
            currentVehicleId += 1;
            movements = Arrays.copyOf(movements, movements.length + 1);
            movements[movements.length - 1] = new Movement(true, vehicle);
        }
        else{
            System.out.println("Невозможно добавить транспорт, т.к. он уже зарегистрирован в системе");
        }
    }

    /**
     * Удаляет неисправный транспорт
     * @param destroyedVehicle - неисправный транспорт
     */
    public void deleteDestroyedVehicle(Vehicle destroyedVehicle){
        if(destroyedVehicle.isDestroyed()){
            Vehicle[] newVehicles = new Vehicle[]{};
            for (Vehicle vehicle : vehicles) {
                if (!vehicle.equals(destroyedVehicle)) {
                    newVehicles = Arrays.copyOf(newVehicles, newVehicles.length + 1);
                    newVehicles[newVehicles.length - 1] = vehicle;

                } else {
                    movements = Arrays.copyOf(movements, movements.length + 1);
                    movements[movements.length - 1] = new Movement(false, vehicle);
                }

            }

            vehicles = Arrays.copyOf(newVehicles, newVehicles.length);
        }

    }

    /**
     * Добавление договора в список
     * @param rentContract - добавляемый договор
     */
    public void appendRentContract(RentContract rentContract){
        boolean rentedFlag = false;
        boolean destroyedFlag = false;
        boolean excitingFlag = true;



        for (Vehicle vehicle: rentContract.getRentedVehicles()){
            if(!this.isAvailableToRent(vehicle,rentContract.getDateRentStart(),rentContract.getDateRentFinish())){
                rentedFlag = true;
                System.out.println("Невозможно заключить договор! Транспорт с id = "+
                        vehicle.getId()+" будет арендован в это время.");
            }
            if(vehicle.isDestroyed()){
                destroyedFlag = true;
                System.out.println("Невозможно заключить договор! Транспорт с id = "+
                        vehicle.getId()+" неисправен.");
            }
            if(this.getVehicleById(vehicle.getId())==null){
                excitingFlag = false;
                System.out.println("Невозможно заключить договор! Транспорт с id = "+
                        vehicle.getId()+" не не состоит на балансе филиала.");
            }

        }
        if(!rentedFlag && !destroyedFlag && excitingFlag ) {
            rentContracts = Arrays.copyOf(rentContracts, rentContracts.length + 1);
            rentContracts[rentContracts.length - 1] = rentContract;
        }
    }

    /**
     * Получение свободного транспорта с разбивкой по категориям на интервале врмени
     * @param dtStart - начало интервала
     * @param dtFinish - конец интервала
     */
    public void getFreeVehiclesForCategory(LocalDateTime dtStart, LocalDateTime dtFinish){
        Vehicle[] tmp = this.getFreeVehicles(dtStart, dtFinish);
        Auto[] autos = new Auto[]{};
        Flying[] flyings = new Flying[]{};
       Individual[] individuals = new Individual[]{};
       int freeAutoCount = 0;
        int freeFlyingCount = 0;
        int freeIndividualCount = 0;
       for (var vehicle: tmp){
           if (vehicle instanceof Auto){
               autos = Arrays.copyOf(autos, autos.length+1);
               autos[autos.length-1] = (Auto)vehicle;
               freeAutoCount+=1;
           }
           if (vehicle instanceof Individual){
               individuals = Arrays.copyOf(individuals, individuals.length+1);
               individuals[individuals.length-1] = (Individual)vehicle;
               freeIndividualCount+=1;
           }
           if (vehicle instanceof Flying){
               flyings = Arrays.copyOf(flyings, flyings.length+1);
               flyings[flyings.length-1] = (Flying)vehicle;
               freeFlyingCount+=1;
           }
       }
        System.out.println("*******************************************************");
        System.out.println("Свободный транспорт c " + dtStart + " по "+dtFinish+":\n");
       System.out.println("Автомобили "+"("+freeAutoCount+" свободно)"+": \n");
       for(Auto auto: autos){
           System.out.println(auto.toString());
       }
        System.out.println("Летательные трансп. средства "+"("+freeFlyingCount+" свободно)"+": \n");
        for(Flying flying: flyings){
            System.out.println(flying.toString());
        }
        System.out.println("Средства индивидуальной мобильности "+"("+freeIndividualCount+" свободно)"+": \n");
        for(Individual individual: individuals){
            System.out.println(individual.toString());
        }
        System.out.println("*******************************************************");


    }

    /**
     * Получение арендованного транспорта по дате
     * @param dt - дата
     * @return - арендованный транспорт
     */
    public Vehicle[] getRentedVehicles(LocalDateTime dt){
        Vehicle[] res = new Vehicle[]{};
        for (RentContract rentContract: rentContracts){
            if (dt.isAfter(rentContract.getDateRentStart()) || dt.isEqual(rentContract.getDateRentStart())) {
                if (dt.isBefore(rentContract.getDateRentFinish()) || dt.isEqual(rentContract.getDateRentFinish())){
                    for (Vehicle rentedVehicle: rentContract.getRentedVehicles()){
                        boolean duplicateFlag = false;
                        for (Vehicle vehicle: res){
                            if (vehicle.getId() == rentedVehicle.getId()) {
                                duplicateFlag = true;
                                break;
                            }

                        }
                        if (!duplicateFlag){
                            res = Arrays.copyOf(res, res.length+1);
                            res[res.length-1] = rentedVehicle;

                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * Получение арендованного транспорта на интервале времени
     * @param dtStart - начало интервала
     * @param dtFinish - конец интервала
     * @return - арендованный транспорт
     */
    public Vehicle[] getRentedVehicles(LocalDateTime dtStart, LocalDateTime dtFinish){
        Vehicle[] res = new Vehicle[]{};
        Vehicle[] tmp = this.getVehiclesStateForDate(dtFinish);
        for (Vehicle vehicleTmp: tmp){
            if(!this.isAvailableToRent(vehicleTmp, dtStart,dtFinish)){
                boolean duplicateFlag = false;
                for (Vehicle vehicle: res){
                    if (vehicle.getId() == vehicleTmp.getId()) {
                        duplicateFlag = true;
                        break;
                    }

                }
                if (!duplicateFlag){
                    res = Arrays.copyOf(res, res.length+1);
                    res[res.length-1] = vehicleTmp;

                }

            }

        }


        return res;
    }

    /**
     * Получение свободного транспорта на интервале времени
     * @param dtStart - начало интервала
     * @param dtFinish - конец интервала
     * @return свободный транспорт
     */
    public Vehicle[] getFreeVehicles(LocalDateTime dtStart, LocalDateTime dtFinish){
        Vehicle[] res = new Vehicle[]{};
        Vehicle[] tmp = this.getVehiclesStateForDate(dtFinish);
        for (Vehicle vehicle: tmp){
            if(this.isAvailableToRent(vehicle, dtStart,dtFinish)){
                res = Arrays.copyOf(res, res.length+1);
                res[res.length-1] = vehicle;
            }
        }


        return res;
    }

    /**
     * Удаление договора.
     * Может понадобиться, но в задании не требуется
     * @param rentContract  - удаляемый договор
     */
    public void deleteRentContract(RentContract rentContract){
        RentContract[] res = new RentContract[]{};
        for (RentContract rent: rentContracts){
            if(!rent.equals(rentContract)){
                res = Arrays.copyOf(res, res.length+1);
                res[res.length-1] = rent;
            }
        }
        rentContracts = res;

    }

    /**
     * Проверка на возможность арендовать транспорт в некотором временном промежутке
     * @param vehicle - транспорт
     * @param dtStart - начало промежутка
     * @param dtFinish - конец промежутка
     * @return true/false
     */
    public boolean isAvailableToRent(Vehicle vehicle, LocalDateTime dtStart, LocalDateTime dtFinish){



        for (RentContract rentContract: rentContracts){
            boolean rentedFlag = false;
            for(Vehicle rentedVehicle: rentContract.getRentedVehicles()){
                if (rentedVehicle.equals(vehicle)){
                    rentedFlag = true;
                    break;
                }
            }
            if(rentedFlag){
               if(!((rentContract.getDateRentFinish().isAfter(dtFinish)&&rentContract.getDateRentStart().isAfter(dtFinish))
               || rentContract.getDateRentStart().isBefore(dtStart)&&rentContract.getDateRentFinish().isBefore(dtStart))){
                   return false;
               }

            }

        }

        for (Vehicle excitingVehicle: this.getVehiclesStateForDate(dtFinish)){
            if(vehicle.equals(excitingVehicle)){
                return true;
            }
        }
        return false;
    }
    public Vehicle[] getFreeVehicles(LocalDateTime dt){
        Vehicle[] res = new Vehicle[]{};
        Vehicle[] rented = this.getRentedVehicles(dt);
        for (Vehicle vehicle: this.getVehiclesStateForDate(dt)){
            boolean rentedFlag = false;
            for (Vehicle rentedVehicle: rented){
                if (rentedVehicle.equals(vehicle)) {
                    rentedFlag = true;
                    break;
                }

            }
            if(!rentedFlag){
                res = Arrays.copyOf(res, res.length+1);
                res[res.length-1] = vehicle;
            }

        }

        return res;
    }

    /**
     * Получение договоров
     * @return - договоры
     */
    public RentContract[] getRentContracts() {
        return rentContracts;
    }

    /**
     * @return Получение движений
     */
    public Movement[] getMovements() {
        return movements;
    }

    /**
     * Получение состояния баланса центра на момент времени
     * @param dt - момент времени
     * @return - актуальное на поределенный момент времени состояние баланса центра
     */
    public Vehicle[] getVehiclesStateForDate(LocalDateTime dt){
        Vehicle[] res = new Vehicle[]{};
        for (Movement movement: movements){
            if(!movement.getDtOfMovement().isAfter(dt)){
                if (movement.isComing()){
                    res = Arrays.copyOf(res, res.length+1);
                    res[res.length-1] = movement.getMovedVehicle();
                }
                else{
                    Vehicle[] tmp = new Vehicle[]{};
                    for (Vehicle vehicle: res){
                        if (!vehicle.equals(movement.getMovedVehicle())){
                            tmp = Arrays.copyOf(tmp, tmp.length+1);
                            tmp[tmp.length-1] = vehicle;
                        }
                    }
                    res = Arrays.copyOf(tmp, tmp.length);
                }
            }
            else{
                break;
            }
        }
        return res;
    }

    /**
     * Получение отчета со статистикой с разбивкой на котегории и виды (на временном интервале )
     * @param dtStart - начало интервала
     * @param dtFinish - конец интервала
     */
    public void getReport(LocalDateTime dtStart, LocalDateTime dtFinish){
        Vehicle[] freeVehicles = this.getFreeVehicles(dtStart, dtFinish);
        Vehicle[] rentedVehicles = this.getRentedVehicles(dtStart, dtFinish);
        Auto[] freeAutos = new Auto[]{};
        Auto[] rentedAutos = new Auto[]{};
        Flying[] freeFlyings = new Flying[]{};
        Flying[] rentedFlyings = new Flying[]{};
        Individual[] freeIndividuals = new Individual[]{};
        Individual[] rentedIndividuals = new Individual[]{};
        double avgCost = 0;
        double avgPlacesCount= 0;
        double avgPowerOfEngine= 0;
        double avgDoorsCount= 0;
        double avgTonnage= 0;
        double avgMaxHeight= 0;
        double avgBladesCount= 0;
        double avgEnginesCount= 0;
        double avgPowerReserve= 0;
        int freeCars = 0;
        int freeTrucks = 0;
        int freeHelicopters = 0;
        int freeBusinessJets = 0;
        int freeUnicycles = 0;
        int freeElectricScooters = 0;
        int rentedCars = 0;
        int rentedTrucks = 0;
        int rentedHelicopters = 0;
        int rentedBusinessJets = 0;
        int rentedUnicycles = 0;
        int rentedElectricScooters = 0;


        System.out.println("*******************************************************");
       System.out.println("Сводный отчет c " + dtStart + " по "+dtFinish+":\n");
        for (Vehicle vehicle: freeVehicles){
            avgCost+=vehicle.getCost();
            avgPlacesCount+=vehicle.getPlacesCount();
            if(vehicle instanceof Auto){
                avgPowerOfEngine+=((Auto) vehicle).getPowerOfEngine();
                freeAutos = Arrays.copyOf(freeAutos, freeAutos.length+1);
                freeAutos[freeAutos.length-1] = (Auto)vehicle;
                if (vehicle instanceof Car){
                    avgDoorsCount+=((Car) vehicle).getDoorsCount();
                    freeCars+=1;
                }
                if(vehicle instanceof Truck){
                    avgTonnage+=((Truck) vehicle).getTonnage();
                    freeTrucks+=1;
                }
            }
            if(vehicle instanceof Flying){
                avgMaxHeight+=((Flying) vehicle).getMaxHeight();
                freeFlyings = Arrays.copyOf(freeFlyings, freeFlyings.length+1);
                freeFlyings[freeFlyings.length-1] = (Flying)vehicle;
                if (vehicle instanceof Helicopter){
                    avgBladesCount+=((Helicopter) vehicle).getBladesCount();
                    freeHelicopters+=1;
                }
                if(vehicle instanceof BusinessJet){
                    avgEnginesCount+=((BusinessJet) vehicle).getEnginesCount();
                    freeBusinessJets+=1;
                }
            }
            if(vehicle instanceof Individual){
                avgPowerReserve+=((Individual) vehicle).getPowerReserve();
                freeIndividuals = Arrays.copyOf(freeIndividuals, freeIndividuals.length+1);
                freeIndividuals[freeIndividuals.length-1] = (Individual) vehicle;
                if (vehicle instanceof Unicycle){
                    freeUnicycles+=1;
                }
                if(vehicle instanceof ElectricScooter){
                    freeElectricScooters+=1;
                }
            }
        }
        for (Vehicle vehicle: rentedVehicles){
            avgCost+=vehicle.getCost();
            avgPlacesCount+=vehicle.getPlacesCount();
            if(vehicle instanceof Auto){
                avgPowerOfEngine+=((Auto) vehicle).getPowerOfEngine();
                rentedAutos = Arrays.copyOf(rentedAutos, rentedAutos.length+1);
                rentedAutos[rentedAutos.length-1] = (Auto)vehicle;
                if (vehicle instanceof Car){
                    avgDoorsCount+=((Car) vehicle).getDoorsCount();
                    rentedCars+=1;
                }
                if(vehicle instanceof Truck){
                    avgTonnage+=((Truck) vehicle).getTonnage();
                    rentedTrucks+=1;
                }
            }
            if(vehicle instanceof Flying){
                avgMaxHeight+=((Flying) vehicle).getMaxHeight();
                rentedFlyings = Arrays.copyOf(rentedFlyings, rentedFlyings.length+1);
                rentedFlyings[rentedFlyings.length-1] = (Flying)vehicle;
                if (vehicle instanceof Helicopter){
                    avgBladesCount+=((Helicopter) vehicle).getBladesCount();
                    rentedHelicopters+=1;
                }
                if(vehicle instanceof BusinessJet){
                    avgEnginesCount+=((BusinessJet) vehicle).getEnginesCount();
                    rentedBusinessJets+=1;
                }
            }
            if(vehicle instanceof Individual){
                avgPowerReserve+=((Individual) vehicle).getPowerReserve();
                rentedIndividuals = Arrays.copyOf(rentedIndividuals, rentedIndividuals.length+1);
                rentedIndividuals[rentedIndividuals.length-1] = (Individual) vehicle;
                if (vehicle instanceof Unicycle){
                    rentedUnicycles+=1;
                }
                if(vehicle instanceof ElectricScooter){
                    rentedElectricScooters+=1;
                }
            }
        }
        System.out.println("Статистика по Vehicle:\n"
                +"Всего: "+(freeVehicles.length+rentedVehicles.length) +"\n"+
                "Арендовано: " + rentedVehicles.length
                +"\nСвободно: "+ freeVehicles.length+
                "\nСредняя цена: "+(avgCost/(freeVehicles.length+rentedVehicles.length))+
                "\nСредняя вместимость пассажиров: "+(avgPlacesCount/(freeVehicles.length+rentedVehicles.length))+
                "\n\n\tСтатистика по Auto:\n"
                +"\tВсего: "+(freeAutos.length+rentedAutos.length) +"\n"+
                "\tАрендовано: " + rentedAutos.length
                +"\n\tСвободно: "+ freeAutos.length+
                "\n\tСредняя мощность двигателя: "+(avgPowerOfEngine/(freeAutos.length+rentedAutos.length))+
                "\n\n\t\tСтатистика по Car:\n"
                +"\t\tВсего: "+(freeCars+rentedCars) +"\n"+
                "\t\tАрендовано: " + rentedCars
                +"\n\t\tСвободно: "+ freeCars+
                "\n\t\tСреднее количество дверей: "+(avgDoorsCount/(freeCars+rentedCars))+
                "\n\n\t\tСтатистика по Truck:\n"
                +"\t\tВсего: "+(freeTrucks+rentedTrucks) +"\n"+
                "\t\tАрендовано: " + rentedTrucks
                +"\n\t\tСвободно: "+ freeTrucks+
                "\n\t\tСредний тоннаж: "+(avgTonnage/(freeTrucks+rentedTrucks))+
                "\n\n\tСтатистика по Flying:\n"
                +"\tВсего: "+(freeFlyings.length+rentedFlyings.length) +"\n"+
                "\tАрендовано: " + rentedFlyings.length
                +"\n\tСвободно: "+ freeFlyings.length+
                "\n\tСредняя максимальная высота: "+(avgMaxHeight/(freeFlyings.length+rentedFlyings.length))+
                "\n\n\t\tСтатистика по Helicopter:\n"
                +"\t\tВсего: "+(freeHelicopters+rentedHelicopters) +"\n"+
                "\t\tАрендовано: " + rentedHelicopters
                +"\n\t\tСвободно: "+ freeHelicopters+
                "\n\t\tСреднее количество лопастей: "+(avgBladesCount/(freeHelicopters+rentedHelicopters))+
                "\n\n\t\tСтатистика по BusinessJet:\n"
                +"\t\tВсего: "+(freeBusinessJets+rentedBusinessJets) +"\n"+
                "\t\tАрендовано: " + rentedBusinessJets
                +"\n\t\tСвободно: "+ freeBusinessJets+
                "\n\t\tСреднее кол-во двигателей: "+(avgEnginesCount/(freeBusinessJets+rentedBusinessJets))+
                "\n\n\tСтатистика по Individual:\n"
                +"\tВсего: "+(freeIndividuals.length+rentedIndividuals.length) +"\n"+
                "\tАрендовано: " + rentedIndividuals.length
                +"\n\tСвободно: "+ freeIndividuals.length+
                "\n\tСредний запас хода: "+(avgPowerReserve/(freeIndividuals.length+rentedIndividuals.length))+
                "\n\n\t\tСтатистика по Unicycle:\n"
                +"\t\tВсего: "+(freeUnicycles+rentedUnicycles) +"\n"+
                "\t\tАрендовано: " + rentedUnicycles
                +"\n\t\tСвободно: "+ freeUnicycles+
                "\n\n\t\tСтатистика по ElectricScooter:\n"
                +"\t\tВсего: "+(freeElectricScooters+rentedElectricScooters) +"\n"+
                "\t\tАрендовано: " + rentedElectricScooters
                +"\n\t\tСвободно: "+ freeElectricScooters);


        System.out.println("*******************************************************");
    }
}
