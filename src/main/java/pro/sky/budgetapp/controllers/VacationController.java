package pro.sky.budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.budgetapp.services.BudgetService;

@RestController
@RequestMapping("/vacation")
public class VacationController {
private final BudgetService budgetService;

    public VacationController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public int vacationBonus(@RequestParam int vacationDays) {
        return budgetService.getVacationBonus(vacationDays);
    }

    @GetMapping("/salary")
    public int salaryWithVacation(@RequestParam int vacationDays, @RequestParam int workingDays, @RequestParam int vacWorkdays) {
        return budgetService.getSalaryWithVacation(vacationDays, vacWorkdays, workingDays);
    }
}
