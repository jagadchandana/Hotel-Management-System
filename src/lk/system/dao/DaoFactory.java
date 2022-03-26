package lk.system.dao;

import lk.system.dao.custom.impl.RoomDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public enum DaoType{
        CUSTOMER, EMPLOYEE, SERVICES, ROOM
    }
    public static DaoFactory getInstance(){
        return (daoFactory==null?(daoFactory=new DaoFactory()):(daoFactory));
    }
    public <T> T getDao(DaoType type){
        switch (type){
            case ROOM:
                return (T) new RoomDaoImpl();
            default:
                return null;
        }
    }
}
