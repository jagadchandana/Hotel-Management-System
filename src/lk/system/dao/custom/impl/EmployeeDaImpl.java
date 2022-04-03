package lk.system.dao.custom.impl;

import lk.system.dao.custom.EmployeeDAO;
import lk.system.db.HibernateUtil;
import lk.system.entity.Titem;
import lk.system.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee r) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(r);
        transaction.commit();
        return true;
    }

    @Override
    public boolean update(Employee r) throws SQLException, ClassNotFoundException {
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
        Query query = session.createQuery("DELETE employee WHERE id=:id");
        query.setParameter("id",s);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    @Override
    public Employee get(String s) throws SQLException, ClassNotFoundException {
        return HibernateUtil.openSession().get(Employee.class,s);
    }

    @Override
    public ArrayList<Employee> getAll(String text) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM employee WHERE (id like :id OR :id like '' OR name like :id)");
        query.setParameter("id","%"+text+"%");
        System.out.println(text);
        List<Employee> list = query.list();
       // System.out.println(list);
        ArrayList<Employee> entityList = new ArrayList<>();
        for (Employee t:list
        ) {
            Employee employee= new Employee(
                    t.getId(),
                    t.getState(),
                    t.getName(),
                    t.getNic(),
                    t.getContact(),
                    t.getAddress(),
                    t.getDob(),
                    t.getSattleDate(),
                    t.getJobType()
            );
            entityList.add(employee);
        }
        return entityList;
    }
}
