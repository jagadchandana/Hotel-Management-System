package lk.system.dao;

import lk.system.dao.custom.impl.RoomDaoImpl;
import lk.system.dao.custom.impl.ServicesDaoImpl;
import lk.system.dao.custom.impl.TitemDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public enum DaoType{
        CUSTOMER, EMPLOYEE, SERVICES, ROOM,TITEM
    }
    public static DaoFactory getInstance(){
        return (daoFactory==null?(daoFactory=new DaoFactory()):(daoFactory));
    }
    public <T> T getDao(DaoType type){
        switch (type){
            case ROOM:
                return (T) new RoomDaoImpl();
            case SERVICES:
                return (T) new ServicesDaoImpl();
            case TITEM:
                return (T) new TitemDaoImpl();
            default:
                return null;
        }
    }
}
