package lk.system.dao.custom.impl;

import lk.system.dao.custom.ServicesDAO;
import lk.system.db.HibernateUtil;
import lk.system.entity.Room;
import lk.system.entity.Services;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicesDaoImpl implements ServicesDAO {
    @Override
    public boolean save(Services r) throws SQLException, ClassNotFoundException {
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
    public boolean update(Services r) throws SQLException, ClassNotFoundException {
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
        Query query = session.createQuery("DELETE services WHERE id=:id");
        query.setParameter("id",s);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    @Override
    public Services get(String s) throws SQLException, ClassNotFoundException {
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

        return HibernateUtil.openSession().get(Services.class, s);
    }

    @Override
    public ArrayList<Services> getAll(String text) throws SQLException, ClassNotFoundException {
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
        Query query = HibernateUtil.openSession().createQuery("FROM services");
        List<Services> list = query.list();
        ArrayList<Services> entityList = new ArrayList<>();
        for (Services r: list
        ) {
            Services services= new Services(
                    r.getId(),
                    r.getName(),
                    r.getType(),
                    r.getFood(),
                    r.getBar(),
                    r.getTransport(),
                    r.getPool(),
                    r.getKidsPark(),
                    r.getBeach(),
                    r.getPackagePrice()
            );
            entityList.add(services);
        }
        return entityList;
    }

   /* @Override
    public boolean saveRoom(Room r) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO room VALUES(?,?,?,?,?,?)",
                r.getId(),r.getName(),r.getType(),r.getPrice(),r.getQty(),
                r.getDescription());n null;*/

}
