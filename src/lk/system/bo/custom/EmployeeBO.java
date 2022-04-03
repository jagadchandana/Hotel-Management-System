package lk.system.bo.custom;

import lk.system.bo.SuperBO;
import lk.system.dto.EmployeeDTO;
import lk.system.dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public boolean saveEmployee(EmployeeDTO r) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(EmployeeDTO r) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    public EmployeeDTO getEmployee(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<EmployeeDTO> getAllEmployee(String text) throws SQLException, ClassNotFoundException;

}
