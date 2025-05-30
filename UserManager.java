package LibrarySystem;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserManager {
    private List<User> userList = new ArrayList<>();

    public void loadUsersFromExcel(String path, String type) {
        List<User> tempList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String extra = row.getCell(2).getStringCellValue();

                User u = type.equalsIgnoreCase("student")
                        ? new Student(id, name, extra)
                        : new Teacher(id, name, extra);
                tempList.add(u);
            }
            userList.addAll(tempList);  
        } catch (Exception e) {
            System.err.println("Error loading " + type + "s: " + e.getMessage());
        }
    }

    public User getUserById(int id) throws UserNotFoundException {
        for (User u : userList) {
            if (u.userId == id) return u;
        }
        throw new UserNotFoundException("User ID " + id + " not found");
    }
}
