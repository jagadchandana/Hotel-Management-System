package lk.system.bo.custom;

import lk.system.bo.SuperBO;
import lk.system.dto.RoomDTO;
import lk.system.dto.ServicesDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServicesBO extends SuperBO {
    public boolean saveServices(ServicesDTO r) throws SQLException, ClassNotFoundException;
    public boolean updateServices(ServicesDTO r) throws SQLException, ClassNotFoundException;
    public boolean deleteServices(String id) throws SQLException, ClassNotFoundException;
    public ServicesDTO getServices(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<ServicesDTO> getAllServices(String text) throws SQLException, ClassNotFoundException;

}
