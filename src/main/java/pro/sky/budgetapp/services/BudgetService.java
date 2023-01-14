package pro.sky.budgetapp.services;

public interface BudgetService {

    int getDailyBudget();// бюджет на день

    int getBalance();// сколько денег осталось

    int getVacationBonus(int daysCount);

    int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth);
}
