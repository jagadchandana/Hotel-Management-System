package lk.system.bo.custom.impl;

import lk.system.bo.custom.TitemBO;
import lk.system.dao.DaoFactory;
import lk.system.dao.custom.TitemDAO;
import lk.system.dto.TitemDTO;
import lk.system.entity.Titem;

import java.sql.SQLException;
import java.util.ArrayList;

public class TitemBoImpl implements TitemBO {
    TitemDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.TITEM);
    @Override
    public boolean saveTitem(TitemDTO r) throws SQLException, ClassNotFoundException {
        return dao.save(new Titem(r.getId(),r.getName(),r.getPrice()));
    }

    @Override
    public boolean updateTitem(TitemDTO r) throws SQLException, ClassNotFoundException {
        return dao.update(new Titem(r.getId(),r.getName(),r.getPrice()));
    }

    @Override
    public boolean deleteTitem(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public TitemDTO getTitem(String id) throws SQLException, ClassNotFoundException {
        Titem titem = dao.get(id);
        if (titem!=null){
            return new TitemDTO(titem.getId(),titem.getName(),titem.getPrice());
        }
        return null;
    }

    @Override
    public ArrayList<TitemDTO> getAllTitem(String text) throws SQLException, ClassNotFoundException {
        ArrayList<Titem> entityList = dao.getAll(text);
        ArrayList<TitemDTO> titemDTOS = new ArrayList<>();
        for (Titem t:entityList
             ) {
            TitemDTO dto = new TitemDTO(t.getId(),t.getName(),t.getPrice());
            titemDTOS.add(dto);
        }
        return titemDTOS;
    }
}
