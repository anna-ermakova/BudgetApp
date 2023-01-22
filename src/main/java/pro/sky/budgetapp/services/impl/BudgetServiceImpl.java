package pro.sky.budgetapp.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.budgetapp.model.Transaction;
import pro.sky.budgetapp.services.BudgetService;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class BudgetServiceImpl implements BudgetService {
    public static final int SALARY = 30_000;
    public static final int SAVING = 3_000;
    public static final int DAILY_BUDGET = (SALARY - SAVING) / LocalDate.now().lengthOfMonth();
    public static int balance = 0;
    private static long lastId = 0;
    public static final int AVG_SALARY = (10000 + 10000 + 10000 + 10000 + 10000 + 10000 + 10000 + 10000 + 15000 + 15000 + 15000 + 20000) / 12;
    public static final double AVG_DAYS = 29.3;

    private static Map<Month, Map<Long, Transaction>> transactions = new TreeMap<>();

    @Override
    public int getDailyBudget() {
        return DAILY_BUDGET;
    }

    @Override
    public int getBalance() {
        return SALARY - SAVING - gatAllSpend();
    }

    @Override
    public Long addTransaction(Transaction transaction) {
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        monthTransactions.put(lastId, transaction);
        transactions.put(LocalDate.now().getMonth(), monthTransactions);
        return lastId++;
    }

    @Override
    public Transaction editTransaction(long id, Transaction transaction) {
        for (Map<Long, Transaction> transactionsByMonth : transactions.values()) {
            if (transactionsByMonth.containsKey(id)) {
                transactionsByMonth.put(id, transaction);
                return transaction;
            }
        }
        return null;
    }

    @Override
    public boolean deleteTransaction(long id) {
        for (Map<Long, Transaction> transactionsByMonth : transactions.values()) {
            if (transactionsByMonth.containsKey(id)) {
                transactionsByMonth.remove(id);
                return true;
            }
        }
        return false;
    }
    @Override
    public void deleteAllTransaction() {
        transactions = new TreeMap<>();
    }

    @Override
    public Transaction getTransaction(long id) {
        for (Map<Long, Transaction> transactionsByMonth : transactions.values()) {
            Transaction transaction = transactionsByMonth.get(id);
            if (transaction != null) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public int getDailyBalance() {// сколько денег еще осталось на сегодня
        return DAILY_BUDGET * LocalDate.now().getDayOfMonth() - gatAllSpend();
    }

    private int gatAllSpend() {// все потраченные деньги
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());

        int sum = 0;
        for (Transaction transaction : monthTransactions.values()) {
            sum += transaction.getSum();
        }
        return sum;
    }

    @Override
    public int getVacationBonus(int daysCount) {
        double avgDaySalary = AVG_SALARY / AVG_DAYS;
        return (int) (daysCount * avgDaySalary);
    }

    @Override
    public int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth) {
        int salary = SALARY / workingDaysInMonth * (workingDaysInMonth - vacationDaysCount);
        return salary + getVacationBonus(vacationDaysCount);
    }
}

