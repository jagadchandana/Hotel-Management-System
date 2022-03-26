package lk.system.bo.custom;

import lk.system.bo.SuperBO;
import lk.system.dto.RoomDTO;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    public boolean saveRoom(RoomDTO r) throws SQLException, ClassNotFoundException;
    public boolean updateRoom(RoomDTO r) throws SQLException, ClassNotFoundException;
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;
    public RoomDTO getRoom(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<RoomDTO> getAllRoom(String text) throws SQLException, ClassNotFoundException;

}
