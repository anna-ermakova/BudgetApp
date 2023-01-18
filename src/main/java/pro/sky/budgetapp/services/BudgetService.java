package pro.sky.budgetapp.services;

import pro.sky.budgetapp.model.Transaction;

public interface BudgetService {

    int getDailyBudget();// бюджет на день

    int getBalance();// сколько денег осталось

    Long addTransaction(Transaction transaction);

    Transaction editTransaction(long id, Transaction transaction);

    boolean deleteTransaction(long id);

    void deleteAllTransaction();

    Transaction getTransaction(long id);

    int getDailyBalance();

    int getVacationBonus(int daysCount);

    int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth);
}
