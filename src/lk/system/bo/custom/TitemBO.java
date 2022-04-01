package lk.system.bo.custom;

import lk.system.dto.RoomDTO;
import lk.system.dto.TitemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TitemBO {
    public boolean saveTitem(TitemDTO r) throws SQLException, ClassNotFoundException;
    public boolean updateTitem(TitemDTO r) throws SQLException, ClassNotFoundException;
    public boolean deleteTitem(String id) throws SQLException, ClassNotFoundException;
    public TitemDTO getTitem(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<TitemDTO> getAllTitem(String text) throws SQLException, ClassNotFoundException;

}
