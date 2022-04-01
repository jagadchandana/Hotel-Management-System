package lk.system.dao.custom.impl;

import lk.system.dao.custom.TitemDAO;
import lk.system.db.HibernateUtil;
import lk.system.entity.Titem;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitemDaoImpl implements TitemDAO {
    @Override
    public boolean save(Titem r) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(r);
        transaction.commit();
        return true;
    }

    @Override
    public boolean update(Titem r) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(r);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE titem WHERE id=:id");
        query.setParameter("id",s);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    @Override
    public Titem get(String s) throws SQLException, ClassNotFoundException {
        return HibernateUtil.openSession().get(Titem.class,s);
    }

    @Override
    public ArrayList<Titem> getAll(String text) throws SQLException, ClassNotFoundException {
            Session session = HibernateUtil.openSession();
            Query query = session.createQuery("FROM titem WHERE (id like :id OR :id like '' OR name like :id)");
            query.setParameter("id","%"+text+"%");
        System.out.println(text);
            List<Titem> list = query.list();
        System.out.println(list);
            ArrayList<Titem> entityList = new ArrayList<>();
            for (Titem t:list
            ) {
                Titem titem = new Titem(
                        t.getId(),
                        t.getName(),
                        t.getPrice()
                );
                entityList.add(titem);
            }
            return entityList;

    }
}
