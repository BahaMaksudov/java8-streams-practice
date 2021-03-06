package com.example.streampracticetask.practice;

import com.example.streampracticetask.model.*;
import com.example.streampracticetask.service.*;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {

        List<Employee> allEmp = employeeService.readAll().stream()
                .collect(Collectors.toList());
        return allEmp;

        //return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {

        List<Country> allCon = countryService.readAll().stream()
                .collect(Collectors.toList());
        return allCon;
  //      return countryService.readAll();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {

        List<Department> allDepList = departmentService.readAll().stream()
                .collect(Collectors.toList());
        return allDepList;

        //return departmentService.readAll();

    }

    // Display all the jobs
    public static List<Job> getAllJobs() {

        List<Job> allJob= jobService.readAll().stream()
                .collect(Collectors.toList());
        return allJob;
 //       return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {

        List<Location> allLoc = locationService.readAll().stream()
                .collect(Collectors.toList());
        return allLoc;
//        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {

        List<Region> allReg = regionService.readAll().stream()
                .collect(Collectors.toList());
        return allReg;
  //      return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {

        List<JobHistory> jobHis = jobHistoryService.readAll().stream()
                .collect(Collectors.toList());
        return jobHis;

        //Another solution
      //return jobHistoryService.readAll();
    }

    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        return employeeService.readAll().stream()
                .map(Employee::getFirstName).collect(Collectors.toList());

    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        List<String> allConNames = countryService.readAll().stream()
                .map(Country::getCountryName).collect(Collectors.toList());
        return allConNames;

//        return countryService.readAll().stream()
//                .map(Country::getCountryName).collect(Collectors.toList());
    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        return departmentService.readAll().stream()
                .map(Department::getManager)
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
              return  departmentService.readAll().stream()
                      .filter(t->t.getManager().getFirstName().equals("Steven"))
                      .collect(Collectors.toList());
    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        return departmentService.readAll().stream()
                .filter(t->t.getLocation().getPostalCode().equals("98199"))
                .collect(Collectors.toList());
    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
//        return departmentService.readAll().stream()
//                .filter(g->g.getDepartmentName().equalsIgnoreCase("it"))
//                .map(department -> department.getLocation().getCountry().getRegion()).findAny().get();
//                .map(Department::getLocation)
//                .map(Location::getCountry)
//                .map(Country::getRegion).findAny().get();

        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                .findFirst().get().getLocation().getCountry().getRegion();

//        //Cundullah solution
//        return departmentService.readAll().stream()
//                .filter(department -> department.getDepartmentName().equals("IT"))
//                .findFirst().get().getLocation().getCountry().getRegion();
    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        return departmentService.readAll().stream()
                .filter(t->t.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(Collectors.toList());
    }


    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
//        return employeeService.readAll().stream()
//                .allMatch(employee -> employee.getSalary()>1000);

//        return getAllEmployees().stream()
//                .anyMatch(employee -> employee.getSalary()>=1000);
//
//        return !getAllEmployees().stream()
//                .anyMatch(employee -> employee.getSalary()<1000);

        //Cundullah solution
        return getAllEmployees().stream()
                .noneMatch(employee -> employee.getSalary()<1000);
    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
//        return employeeService.readAll().stream()
//                .filter(m->m.getDepartment().getDepartmentName().equals("IT"))
//                .anyMatch(n->n.getSalary()>2000);

        //Another solution
        return getAllEmployees().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                .noneMatch(employee -> employee.getSalary()<2000);

    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
//        return employeeService.readAll().stream()
//                .filter(b->b.getSalary()<5000)
//                .collect(Collectors.toList());

        return getAllEmployees().stream()
                .filter(employee -> employee.getSalary()<5000)
                .collect(Collectors.toList());
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
//        return employeeService.readAll().stream()
//                .filter(employee -> employee.getSalary()>6000)
//                .filter(employee -> employee.getSalary()<7000)
//                .collect(Collectors.toList());

        //Cundullah solution
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()<7000 && employee.getSalary()>6000)
                .collect(Collectors.toList());
    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
//        return employeeService.readAll().stream()
//                .filter(employee -> employee.getLastName().equalsIgnoreCase("grant")&&
//                        employee.getFirstName().equalsIgnoreCase("douglas"))
//                .map(Employee::getSalary).findAny().get();

        //Cundullah solution
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Douglas"))
                .filter(employee -> employee.getLastName().equals("Grant"))
                .findAny()
                .get().getSalary();

    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
//        return jobService.readAll().stream()
//                .map(Job::getMaxSalary)
//                .findAny().get();

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .max(Long::compare)
//                .orElse(null);

        //Cundullah solution
//        return employeeService.readAll().stream()
//                .max(Comparator.comparing(Employee::getSalary))
//                .get().getSalary();

//           return employeeService.readAll().stream()
//                   .map(Employee::getSalary)
//                   .reduce((a,b) -> a>b ? a : b)
//                   .get();

//           return employeeService.readAll().stream()
//                   .map(Employee::getSalary)
//                   .reduce(Long::max)
//                   .get();

//           return employeeService.readAll().stream()
//                   .sorted(Comparator.comparing(Employee::getSalary).reversed())  //reversed means max salary will be at the top (descending order)
//                   .findFirst().get().getSalary();

        return employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .findFirst().orElseThrow(()-> new Exception("No Employee Found")).getSalary();

    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {
//       return employeeService.readAll().stream()
//               .max(Comparator.comparing(Employee::getSalary))
//               .stream()
//               .collect(Collectors.toList());

        //Cundullah Solution
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getMaxSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());


    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {
//        return employeeService.readAll().stream()
//                .max(Comparator.comparing(Employee::getSalary))
//                .map(Employee::getJob)
//                .get();

        //Cundullah solution
//        return getMaxSalaryEmployee().get(0).getJob();

        //Cundullah best solution
        return employeeService.readAll().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new Exception ("Something went wrong")).getJob();

//        return getMaxSalaryEmployee().stream()
//                .map(Employee::getJob)
//                .findAny().orElseThrow();
    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        return employeeService.readAll().stream()
                .filter(t->t.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .map(Employee::getSalary).
                max(Comparator.comparing(Long::longValue)).get().longValue();

        //Cundullah solution
//        return employeeService.readAll().stream()
//                .filter(employee -> employee.getDepartment().getLocation().getCountry().getRegion().equals("Americas"))
//                .max(Comparator.comparing(Employee::getSalary))
//                .orElseThrow(()->new Exception("Something went wrong")).getSalary();

    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
//        return employeeService.readAll().stream()
//                .sorted(Comparator.comparing(Employee::getSalary).reversed())
//                .collect(Collectors.toList()).get(1).getSalary();

        //Cundullah solution
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((a,b)-> {
                    try {
                        return a >  b && a < getMaxSalary() ? a: b;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return -1L; //returning some value instead of null, we can use null as well
                }).orElseThrow(()->new Exception ("Employee not found")
                );
    }

    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
      //Cundullah solution
        return employeeService.readAll().stream()
              .filter(employee -> {
                  try {
                      return employee.getSalary().equals(getSecondMaxSalary());
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  return false;
              }).collect(Collectors.toList());


    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary() throws Exception {
        //TODO Implement the method
        //Cundullah solution
        return employeeService.readAll().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .get().getSalary();


    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        //TODO Implement the method
        //Cundullah Solution
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getMinSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());

    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() throws Exception {
        //TODO Implement the method
        //Cundullah solution
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((a,b) -> {
                    try {
                        return a < b && a>getMinSalary() ? a : b;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return -1l;  //return Long.MAX_VALUE
                }).orElseThrow(() -> new Exception("Something went wrong"));

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .sorted().distinct().limit(2).collect(Collectors.toList()).get(1);


    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        //TODO Implement the method
        //Cundullah solution
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getSecondMinSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());

    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        //TODO Implement the method
        //Cundullah Solution
        return employeeService.readAll().stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

//        return employeeService.readAll().stream()
//                .mapToDouble(Employee::getSalary)// not simple map !!!!
//                .average().orElse(0d);
    }

    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()>getAverageSalary())
                .collect(Collectors.toList());


    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        return employeeService.readAll().stream()
        .filter(employee -> employee.getSalary()<getAverageSalary())
                .collect(Collectors.toList());
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        return employeeService.readAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getId()));

    }

    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
 //       return departmentService.readAll().stream().count(); //with stream

        return (long) getAllDepartments().size();  //without stream

    }

    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Alyssa")&&
                        employee.getManager().getFirstName().equals("Eleni")&&
                        employee.getDepartment().getDepartmentName().equals("Sales"))
                .findAny().get();
    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .collect(Collectors.toList());
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getStartDate().isAfter(LocalDate.of(2005,1,1)))
                .collect(Collectors.toList());

    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getEndDate().equals(LocalDate.of(2007,12,31)))
                .filter(jobHistory -> jobHistory.getJob().getJobTitle().equals("Programmer"))
                .collect(Collectors.toList());
    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
//        return jobHistoryService.readAll().stream()
//                .filter(jobHistory -> jobHistory.getStartDate().equals(LocalDate.of(2007,1,1)))
//                .filter(jobHistory -> jobHistory.getEndDate().equals(LocalDate.of(2007,12,31)))
//                .filter(jobHistory -> jobHistory.getDepartment().getDepartmentName().equals("Shipping"))
//                .map(JobHistory::getEmployee)
//                .findAny().get();

        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getStartDate().equals(LocalDate.of(2007,1,1)))
                .filter(jobHistory -> jobHistory.getEndDate().equals(LocalDate.of(2007,12,31)))
                .filter(jobHistory -> jobHistory.getDepartment().getDepartmentName().equals("Shipping"))
                .findFirst().get().getEmployee();
    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        return employeeService.readAll().stream()
                .filter(n->n.getFirstName().startsWith("A"))
                .collect(Collectors.toList());

    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getJob().getId().contains("IT"))
                .collect(Collectors.toList());
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getJob().getJobTitle().equals("Programmer")&&employee
                        .getDepartment().getDepartmentName().equals("IT"))
                .count();

    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
//        return employeeService.readAll().stream()
//                .filter(employee -> employee.getDepartment().getId()==50||employee.getDepartment().getId()==80||employee.getDepartment().getId()==100)
//                .collect(Collectors.toList());

        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getId().equals(50L)||
                        employee.getDepartment().getId().equals(80L)||
                        employee.getDepartment().getId().equals(100L))
                .collect(Collectors.toList());
//        return new ArrayList<>();
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
//        return employeeService.readAll().stream()
//                .map(n->n.getFirstName().substring(0,1)+n.getLastName().substring(0,1))
//                .collect(Collectors.toList());

        return employeeService.readAll().stream()
                .map(employee -> {
                    String firstInitial = employee.getFirstName().substring(0,1);
                    String secondInitial = employee.getLastName().substring(0,1);
                    return firstInitial+secondInitial;
                }).collect(Collectors.toList());

    }

    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
//        return employeeService.readAll().stream()
//            .map(employee -> employee.getFirstName()
//                    .substring(0) +" "+employee.getLastName()
//                    .substring(0))
//                    .collect(Collectors.toList());

        return employeeService.readAll().stream()
                .map(employee -> {
                    String firstName = employee.getFirstName();
                    String secondName = employee.getLastName();
                    String fullName = firstName + " " + secondName;
                    return fullName;
                }).collect(Collectors.toList());

    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
//        return employeeService.readAll().stream()
//                .map(employee -> employee.getFirstName().length() +employee.getLastName().length()+1)
//                .max(Integer::compareTo).get();

        Employee employee = employeeService.readAll().stream()
                .reduce((employee1, employee2) ->
                        employee1.getFirstName().length()
                        + employee1.getLastName().length()
                                > employee2.getFirstName().length()
                                        + employee2.getLastName().length() ? employee1 : employee2)
                .orElseThrow(() -> new Exception ("Something Went Wrong"));

        return employee.getFirstName().length() + employee.getLastName().length()+1;

    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
       return employeeService.readAll().stream()
               .filter(employee -> {
                   try {
                       return employee.getFirstName().length() + employee.getLastName().length() + 1 == getLongestNameLength();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   return false;
               }).collect(Collectors.toList());
    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
//        return employeeService.readAll().stream()
//                .map(employee -> {
//                    Long departmentId = employee.getDepartment().getId();
//                    if (departmentId.equals(90L) || departmentId.equals(60L) || departmentId.equals(100L) || departmentId.equals(120L)
//                    || departmentId.equals(130L)) {
//                        return employee;
//                    } else {
//                        return null;
//                    }
//                }).collect(Collectors.toList());


        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getId().equals(90L)||
                        employee.getDepartment().getId().equals(60L)||
                        employee.getDepartment().getId().equals(100L)||
                        employee.getDepartment().getId().equals(120L)||
                        employee.getDepartment().getId().equals(130L))
                .collect(Collectors.toList());
    }



    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        return employeeService.readAll().stream()
                .filter(employee -> !(employee.getDepartment().getId().equals(90L))&&
                        !(employee.getDepartment().getId().equals(60L))&&
                        !(employee.getDepartment().getId().equals(100L))&&
                        !(employee.getDepartment().getId().equals(120L))&&
                        !(employee.getDepartment().getId().equals(130L)))
                .collect(Collectors.toList());
    }

}
