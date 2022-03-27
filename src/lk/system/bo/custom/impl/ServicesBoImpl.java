package lk.system.bo.custom.impl;

import lk.system.bo.custom.ServicesBO;
import lk.system.dao.DaoFactory;
import lk.system.dao.custom.RoomDAO;
import lk.system.dao.custom.ServicesDAO;
import lk.system.dto.RoomDTO;
import lk.system.dto.ServicesDTO;
import lk.system.entity.Room;
import lk.system.entity.Services;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesBoImpl implements ServicesBO {

    ServicesDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SERVICES);

    @Override
    public boolean saveServices(ServicesDTO r) throws SQLException, ClassNotFoundException {
        return dao.save(
                new Services(r.getId(),r.getName(),r.getType(),r.getFood(),r.getBar(),
                        r.getTransport(),r.getPool(),r.getKidsPark(),r.getBeach(),r.getPackagePrice()));
    }

    @Override
    public boolean updateServices(ServicesDTO r) throws SQLException, ClassNotFoundException {
        return dao.update(
                new Services(r.getId(),r.getName(),r.getType(),r.getFood(),r.getBar(),
                        r.getTransport(),r.getPool(),r.getKidsPark(),r.getBeach(),r.getPackagePrice()));
    }

    @Override
    public boolean deleteServices(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public ServicesDTO getServices(String id) throws SQLException, ClassNotFoundException {
        Services r = dao.get(id);
        if (r!=null){
            return new ServicesDTO(
                    r.getId(),r.getName(),r.getType(),r.getFood(),r.getBar(),
                    r.getTransport(),r.getPool(),r.getKidsPark(),r.getBeach(),r.getPackagePrice());
        }
        return null;
    }

    @Override
    public ArrayList<ServicesDTO> getAllServices(String text) throws SQLException, ClassNotFoundException {
        ArrayList<Services> entityList =dao.getAll(text);
        ArrayList<ServicesDTO> dtoList = new ArrayList<>();
        for (Services r: entityList
        ) {
            ServicesDTO dto = new ServicesDTO(
                    r.getId(),r.getName(),r.getType(),r.getFood(),r.getBar(),
                    r.getTransport(),r.getPool(),r.getKidsPark(),r.getBeach(),r.getPackagePrice()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
}
