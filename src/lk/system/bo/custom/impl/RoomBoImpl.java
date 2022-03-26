package lk.system.bo.custom.impl;

import lk.system.bo.custom.RoomBO;
import lk.system.dao.DaoFactory;
import lk.system.dao.custom.RoomDAO;
import lk.system.dto.RoomDTO;
import lk.system.entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBoImpl implements RoomBO {

    RoomDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ROOM);

    @Override
    public boolean saveRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return dao.save(
                new Room(dto.getId(),dto.getName(),dto.getType(),dto.getPrice(),
                        dto.getQty(),dto.getDescription())
        );
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return  dao.update(
                new Room(dto.getId(),dto.getName(),dto.getType(),dto.getPrice(),
                        dto.getQty(),dto.getDescription())
        );
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public RoomDTO getRoom(String id) throws SQLException, ClassNotFoundException {
        Room r = dao.get(id);
        if (r!=null){
            return new RoomDTO(
                    r.getId(),r.getName(),r.getType(),r.getPrice(),r.getQty(),r.getDescription()
            );
        }
        return null;
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom(String text) throws SQLException, ClassNotFoundException {
        ArrayList<Room> entityList =dao.getAll(text);
        ArrayList<RoomDTO> dtoList = new ArrayList<>();
        for (Room r: entityList
        ) {
            RoomDTO dto = new RoomDTO(
                    r.getId(),r.getName(),r.getType(),r.getPrice(),r.getQty(),r.getDescription()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
}
