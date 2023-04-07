package ru.croc.javaschool.management;

import ru.croc.javaschool.tools.MapUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Класс, предоставляющий функционал, связанный с сотрудниками и компаниями
 */
public class ManagementHandler {

    /**
     * Поиск директора(высшего управялющего, у которого поле manager is null) для конкретного сотрудника
     * @param employee - сотрудник
     * @return - его директор
     */
    private static Employee searchDirectorForEmployee(Employee employee){
        while(employee.getManager()!=null){
            employee = employee.getManager();
        }
        return employee;
    }

    /**
     * Поиск компании, в которую должен быть определен сотрудник, отталкиваясь от его директора
     * @param employee - сотрудник
     * @param companies - список потенциальных компаний
     * @return компания, в которую определен сотрудник
     */
    private static Company searchCompanyForEmployee(Employee employee, List<Company> companies){
        if(employee.getManager()==null){
            for (Company company: companies){
                if (company.getDirector().equals(employee)){
                    return company;
                }
            }
        }
        return null;
    }

    /**
     * Получение списка руководителей сотрудника
     * @param employee - сотрудник
     * @return список его руководителей
     */
    private static List<Employee> getManagersOfEmployee(Employee employee){
        if(employee.getManager() == null){
            return null;
        }
        List<Employee> res = new LinkedList<>();
        while(employee.getManager()!=null){
            res.add(employee.getManager());
            employee = employee.getManager();
        }
        return res;
    }


    /**
     * Метод, возвращающий компании, по которым были распределены сотрудники
     * @param employees - список сотрудников
     * @return - список компаний
     */
    public static List<Company> getEmployeesSeparatedForCompanies(List<Employee> employees){
        if(employees == null){
            return null;
        }
        List<Company> res = new LinkedList<>();
        for(Employee employee: employees){
            Employee director = searchDirectorForEmployee(employee);
            Company company = searchCompanyForEmployee(director, res);
            if(company==null){
                Company newCompany = new Company(res.size()+1);
                newCompany.addEmployee(director);
                res.add(newCompany);
            }
            else{
                company.addEmployee(employee);
            }
        }

        return res;
    }

    /**
     * Метод, возвращающий мэп с руководителями, отсортированный сначала по количеству сотрудников в подчинении
     * (в порядке убывания), а затем по имени в алфавитном порядке
     * @param employees - список сотрудников
     * @return вышеупомяенутый мэп
     */
    public static Map<Employee,Integer> getRankedListOfDirectors(List<Employee> employees){
        if(employees == null){
            return null;
        }
        Map<Employee, Integer> res = new LinkedHashMap<>();
        for (Employee employee: employees){
            List<Employee> managers = getManagersOfEmployee(employee);
            if(managers!=null) {
                for (Employee manager : getManagersOfEmployee(employee)) {
                    Integer tmp = res.getOrDefault(manager, 0);
                    res.put(manager, tmp + 1);
                }
            }
        }
        return MapUtil.sortByValueKey(res);
    }

}
