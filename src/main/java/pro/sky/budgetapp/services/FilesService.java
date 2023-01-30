package pro.sky.budgetapp.services;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
}
