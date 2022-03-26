package lk.system.dao.custom.impl;

import lk.system.dao.CrudUtil;
import lk.system.dao.custom.RoomDAO;
import lk.system.db.DBConnection;
import lk.system.db.HibernateUtil;
import lk.system.dto.RoomDTO;
import lk.system.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDAO {
    @Override
    public boolean save(Room r) throws SQLException, ClassNotFoundException {
       /* return CrudUtil.execute("INSERT INTO room VALUES(?,?,?,?,?,?)",
                r.getId(),r.getName(),r.getType(),r.getPrice(),r.getQty(),
                r.getDescription());*/
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(r);
        transaction.commit();
        return true;
    }

    @Override
    public boolean update(Room r) throws SQLException, ClassNotFoundException {
       /* return CrudUtil.execute("UPDATE room SET name=?,type=?,price=?,qty=? ,description=? WHERE id=?",
                r.getName(),r.getType(),r.getPrice(),r.getQty(),
                r.getDescription(),r.getId());*/
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(r);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
       // return CrudUtil.execute("DELETE FROM room WHERE id=?",s);
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE room WHERE id=:id");
        query.setParameter("id",s);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    @Override
    public Room get(String s) throws SQLException, ClassNotFoundException {
      /*  ResultSet rst = CrudUtil.execute("SELECT * FROM room WHERE id=?", s);
        if (rst.next()) {
            return new Room(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
        } else {
            return null;
        }*/

        return HibernateUtil.openSession().get(Room.class, s);



    }
    @Override
    public ArrayList<Room> getAll(String text) throws SQLException, ClassNotFoundException {
       /* ResultSet rst = CrudUtil.execute("SELECT * FROM room WHERE name LIKE ? OR type LIKE ? OR description LIKE ?",text,text,text);
        ArrayList<Room> entityList = new ArrayList<>();
        while (rst.next()){
            Room dto = new Room(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
            entityList.add(dto);
        }
        return entityList;*/
        Query query = HibernateUtil.openSession().createQuery("FROM room");
        List<Room> list = query.list();
        ArrayList<Room> entityList = new ArrayList<>();
        for (Room r: list
             ) {
            Room room = new Room(
                    r.getId(),
                    r.getName(),
                    r.getType(),
                    r.getPrice(),
                    r.getQty(),
                    r.getDescription()
            );
            entityList.add(room);
        }
        return entityList;
    }

   /* @Override
    public boolean saveRoom(Room r) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO room VALUES(?,?,?,?,?,?)",
                r.getId(),r.getName(),r.getType(),r.getPrice(),r.getQty(),
                r.getDescription());
    }

    @Override
    public boolean updateRoom(Room r) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE room SET name=?,type=?,price=?,qty=? ,description=? WHERE id=?",
                r.getName(),r.getType(),r.getPrice(),r.getQty(),
                r.getDescription(),r.getId());
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM room WHERE id=?",id);
    }

    @Override
    public Room getRoom(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM room WHERE id='"+id+"'",id);
        if (rst.next()) {
            return new Room(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
        }else{
            return null;
        }

    }

    @Override
    public ArrayList<Room> getAllRoom(String text) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM room WHERE name LIKE ? OR type LIKE ? OR description LIKE ?",text,text,text);
        ArrayList<Room> entityList = new ArrayList<>();
        while (rst.next()){
            Room dto = new Room(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
            entityList.add(dto);
        }
        return entityList;
    }
*/
}
