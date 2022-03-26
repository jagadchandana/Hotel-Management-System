package lk.system;

import lk.system.dao.DaoFactory;
import lk.system.dao.custom.RoomDAO;
import lk.system.dao.custom.impl.RoomDaoImpl;
import lk.system.db.DBConnection;
import lk.system.dto.*;
import lk.system.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataBaseAccessCode {

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        String SQL="INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,dto.getCustomerId());
        stm.setObject(2,dto.getState());
        stm.setObject(3,dto.getName());
        stm.setObject(4,dto.getNic());
        stm.setObject(5,dto.getContact());
        stm.setObject(6,dto.getAddress());
        stm.setObject(7,dto.getRoomId());
        stm.setObject(8,dto.getServiceId());
        stm.setObject(9,dto.getOnDate());
        stm.setObject(10,dto.getOffDate());
        stm.setObject(11,dto.getOnTime());
        stm.setObject(12,dto.getOffTime());
        return stm.executeUpdate()>0;
    }
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        String SQL="UPDATE customer SET state=?,name=?,nic=?,contact=?,address=?,roomId=?,servicesId=?,onDate=?,offDate=?,onTime=?,offTime=? WHERE id=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(12,dto.getCustomerId());
        stm.setObject(1,dto.getState());
        stm.setObject(2,dto.getName());
        stm.setObject(3,dto.getNic());
        stm.setObject(4,dto.getContact());
        stm.setObject(5,dto.getAddress());
        stm.setObject(6,dto.getRoomId());
        stm.setObject(7,dto.getServiceId());
        stm.setObject(8,dto.getOnDate());
        stm.setObject(9,dto.getOffDate());
        stm.setObject(10,dto.getOnTime());
        stm.setObject(11,dto.getOffTime());
        return stm.executeUpdate()>0;
    }
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement()
                .executeUpdate("DELETE FROM customer WHERE id='"+id+"'")>0;
    }
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
      String SQL = "SELECT * FROM customer WHERE id=?";
      PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
      stm.setObject(1,id);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12)

            );
        }else{
            return null;
        }
    }
    public ArrayList<CustomerDTO> getAllCustomer(String text) throws SQLException, ClassNotFoundException {
        String SQL="SELECT * FROM customer WHERE id like ? OR name LIKE ? OR address LIKE ?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();
        while (rst.next()){
            CustomerDTO dto = new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12)
            );
            dtoList.add(dto);
        }
       return dtoList;
    }
    //-------------load roomid
    public ArrayList<String>loadAllRoomIds() throws SQLException, ClassNotFoundException {
         ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT id FROM room").executeQuery();
         ArrayList<String> idSet=new ArrayList<>();
         while (rst.next()){
             idSet.add(rst.getString(1));
         }
         return idSet;
        }
        //-------------load roomid
        //-------------load services
        public ArrayList<String>loadAllServies() throws SQLException, ClassNotFoundException {
            ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT id FROM service").executeQuery();
            ArrayList<String> idSet=new ArrayList<>();
            while (rst.next()){
                idSet.add(rst.getString(1));
            }
            return idSet;
        }
        //-------------load services


    //////////////////////////////////////////////////////////
    //Employee........

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?)");
        stm.setObject(1,dto.getId());
        stm.setObject(2,dto.getState());
        stm.setObject(3,dto.getName());
        stm.setObject(4,dto.getNic());
        stm.setObject(5,dto.getOntact());
        stm.setObject(6,dto.getAddress());
        stm.setObject(7,dto.getDob());
        stm.setObject(8,dto.getSattleDate());
        stm.setObject(9,dto.getJobType());
        return stm.executeUpdate() > 0;

    }
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE employee SET state=?,name=?,nic=?,contact=?,Address=?,DOB=?,sattleDate=?,jobType=? WHERE id=?");
        stm.setObject(9,dto.getId());
        stm.setObject(1,dto.getState());
        stm.setObject(2,dto.getName());
        stm.setObject(3,dto.getNic());
        stm.setObject(4,dto.getOntact());
        stm.setObject(5,dto.getAddress());
        stm.setObject(6,dto.getDob());
        stm.setObject(7,dto.getSattleDate());
        stm.setObject(8,dto.getJobType());
        return stm.executeUpdate()>0;

    }
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM employee WHERE id='" + id + "'").executeUpdate() > 0;
    }
    public ArrayList<EmployeeDTO>getAllEmployee(String text) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM employee WHERE name LIKE ? OR id LIKE ? OR Address LIKE ? ");
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();
        ArrayList<EmployeeDTO> dtoList = new ArrayList<>();
        while (rst.next()){
            EmployeeDTO dto = new EmployeeDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9));
            dtoList.add(dto);
        }
        return dtoList;
    }
    public ArrayList<String> loadAllJobs() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT jobPosition FROM job").executeQuery();
        ArrayList<String> jobSet = new ArrayList<>();
        while (rst.next()){
            jobSet.add(rst.getString(1));
        }
        return jobSet;
    }



    /////////Room======================
RoomDAO roomDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ROOM);

    public boolean saveRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
       /* PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO room VALUES(?,?,?,?,?,?)");
        stm.setObject(1,dto.getId());
        stm.setObject(2,dto.getName());
        stm.setObject(3,dto.getType());
        stm.setObject(4,dto.getPrice());
        stm.setObject(5,dto.getQty());
        stm.setObject(6,dto.getDescription());
        return stm.executeUpdate() > 0;*/

        return roomDAO.save(
                new Room(dto.getId(),dto.getName(),dto.getType(),dto.getPrice(),
                        dto.getQty(),dto.getDescription())
        );
    }
    public boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        /*PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE room SET name=?,type=?,price=?,qty=? ,description=? WHERE id=?");
        stm.setObject(6,dto.getId());
        stm.setObject(1,dto.getName());
        stm.setObject(2,dto.getType());
        stm.setObject(3,dto.getPrice());
        stm.setObject(4,dto.getQty());
        stm.setObject(5,dto.getDescription());
        return stm.executeUpdate()>0;*/

        return  roomDAO.update(
                new Room(dto.getId(),dto.getName(),dto.getType(),dto.getPrice(),
                        dto.getQty(),dto.getDescription())
        );
    }
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        //return DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM room WHERE id='" + id + "'").executeUpdate() > 0;

        return roomDAO.delete(id);
    }
    public RoomDTO getRoom(String id) throws SQLException, ClassNotFoundException {
        /*String SQL = "SELECT * FROM room WHERE id='"+id+"'";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
           return new RoomDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
        }else{
            return null;
        }*/

        Room r = roomDAO.get(id);
        if (r!=null){
            return new RoomDTO(
                    r.getId(),r.getName(),r.getType(),r.getPrice(),r.getQty(),r.getDescription()
            );
        }
        return null;
    }
    public ArrayList<RoomDTO> getAllRoom(String text) throws SQLException, ClassNotFoundException {
      /*  PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM room WHERE name LIKE ? OR type LIKE ? OR description LIKE ?");
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();
        ArrayList<RoomDTO> dtoList = new ArrayList<>();
        while (rst.next()){
            RoomDTO dto = new RoomDTO(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getDouble(4),
                rst.getInt(5),
                rst.getString(6)
            );
            dtoList.add(dto);
        }
        return dtoList;
*/
        ArrayList<Room> entityList =roomDAO.getAll(text);
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

    //////////////Permenent Invntory
    public boolean savePItem(PItemDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO pitem VALUES(?,?,?,?,?,?,?,?)");
        stm.setObject(1,dto.getCode());
        stm.setObject(2,dto.getName());
        stm.setObject(3,dto.getQty());
        stm.setObject(4,dto.getBrand());
        stm.setObject(5,dto.getCompany());
        stm.setObject(6,dto.getDate());
        stm.setObject(7,dto.getDescription());
        stm.setObject(8,dto.getWarrenty());
        return stm.executeUpdate() > 0;
    }
    public boolean updatePItem(PItemDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE pitem SET name=?,qty=?,brand=?,company=?,date=?,description=?,warrenty=? WHERE code=? ");
        stm.setObject(8,dto.getCode());
        stm.setObject(1,dto.getName());
        stm.setObject(2,dto.getQty());
        stm.setObject(3,dto.getBrand());
        stm.setObject(4,dto.getCompany());
        stm.setObject(5,dto.getDate());
        stm.setObject(6,dto.getDescription());
        stm.setObject(7,dto.getWarrenty());
        return stm.executeUpdate()>0;
    }
    public boolean deletePItem(String code) throws SQLException, ClassNotFoundException {
       return DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM pitem WHERE code='"+code+"'").executeUpdate()>0;
    }
    public PItemDTO getPItem(String code) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Pitem WHERE code=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,code);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new PItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            );
        }else{
            return null;
        }
    }
    public ArrayList<PItemDTO>getAllPItem(String text) throws SQLException, ClassNotFoundException {
        String  SQL="SELECT * FROM Pitem WHERE code LIKE ? OR name LIKE ? OR brand LIKE ?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();
        ArrayList<PItemDTO> dtoList = new ArrayList<>();
        while (rst.next()){
            PItemDTO dto = new PItemDTO(
              rst.getString(1),
              rst.getString(2),
              rst.getString(3),
              rst.getString(4),
              rst.getString(5),
              rst.getString(6),
              rst.getString(7),
              rst.getString(8)
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    //////////Cache Item===============================--------------

    public boolean saveCacheItem(CitemDTO dto) throws SQLException, ClassNotFoundException {
       String SQL="INSERT INTO citem VALUES(?,?,?,?,?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,dto.getCode());
        stm.setObject(2,dto.getName());
        stm.setObject(3,dto.getQuantity());
        stm.setObject(4,dto.getBrand());
        stm.setObject(5,dto.getDescription());
        return stm.executeUpdate()>0;
    }
    public boolean updateCacheItem(CitemDTO dto) throws SQLException, ClassNotFoundException {
        String SQL="UPDATE citem SET name=?,quntity=?,brand=?,description=? WHERE code=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(5,dto.getCode());
        stm.setObject(1,dto.getName());
        stm.setObject(2,dto.getQuantity());
        stm.setObject(3,dto.getBrand());
        stm.setObject(4,dto.getDescription());
        return stm.executeUpdate()>0;
    }
    public boolean deleteCacheItem(String code) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM citem WHERE code='" + code + "'").executeUpdate() > 0;
    }
    public ArrayList<CitemDTO> getAllCItem(String text) throws SQLException, ClassNotFoundException {
        String SQL="SELECT * FROM citem WHERE name LIKE ? OR brand LIKE ? OR code LIKE ?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();
        ArrayList<CitemDTO> dtoList = new ArrayList<>();
        while (rst.next()){
            CitemDTO dto = new CitemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    //////////jobs=====================================---------------

    public boolean saveJob(JobDTO dto) throws SQLException, ClassNotFoundException {
        String SQL="INSERT INTO job VALUES(?,?,?,?,?) ";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,dto.getId());
        stm.setObject(2,dto.getJobPosition());
        stm.setObject(3,dto.gethRate());
        stm.setObject(4,dto.getmRate());
        stm.setObject(5,dto.getDescription());
        return stm.executeUpdate()>0;
    }
    public boolean updateJob(JobDTO dto) throws SQLException, ClassNotFoundException {
        String SQL="UPDATE job SET jobPosition=?,hourRate=?,monthlyRate=?,description=? WHERE id=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(5,dto.getId());
        stm.setObject(1,dto.getJobPosition());
        stm.setObject(2,dto.gethRate());
        stm.setObject(3,dto.getmRate());
        stm.setObject(4,dto.getDescription());
        return stm.executeUpdate()>0;
    }
    public boolean deleteJob(String id) throws SQLException, ClassNotFoundException {
      String SQL="DELETE FROM job WHERE id='"+id+"'";
      return DBConnection.getInstance().getConnection().prepareStatement(SQL).executeUpdate()>0;
    }
    public ArrayList<JobDTO> getAllJobs(String text) throws SQLException, ClassNotFoundException {
        String SQL="SELECT * FROM job WHERE jobPosition LIKE ? OR id LIKE ? OR id LIKE ?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();
        ArrayList<JobDTO> jobDTOS = new ArrayList<>();
        while (rst.next()){
            JobDTO dto = new JobDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getString(5)
            );
            jobDTOS.add(dto);
        }
        return jobDTOS;
    }

    ///Services===========----------------

    public boolean updateCServices(ClassPackageDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE cpackage set luxury=?,semiLuxury=?,classA=?,classB=?,classC=?,normal=?,permanent=? WHERE id='" + 1 + "'");
        stm.setObject(1,dto.getLuxury());
        stm.setObject(2,dto.getSemiLuxury());
        stm.setObject(3,dto.getClassA());
        stm.setObject(4,dto.getClassB());
        stm.setObject(5,dto.getClassC());
        stm.setObject(6,dto.getNormal());
        stm.setObject(7,dto.getPermenent());
         return stm.executeUpdate()>0;

       // PreparedStatement stm2 = connection.prepareStatement("UPDATE spackage SET foodPrice=?,barPrice=?,transportPrice=?,poolPrice=?,kidsPrice=?,beachPrice=? WHERE id='"+1+"'");
      //  stm2.setObject(1,);

    }
    public boolean updateSServices(ServicesPackageDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE spackage SET foodPrice=?,barPrice=?,transportPrice=?,poolPrice=?,kidsPrice=?,beachPrice=? WHERE id='" + 1 + "'");
        stm.setObject(1,dto.getFoodprice());
        stm.setObject(2,dto.getBarPrice());
        stm.setObject(3,dto.getTransportPrice());
        stm.setObject(4,dto.getPoolPrice());
        stm.setObject(5,dto.getKidsPrice());
        stm.setObject(6,dto.getBeachPrice());
        return stm.executeUpdate()>0;
    }
    public ClassPackageDTO getAllClassPrice(int id) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM cpackage WHERE id='"+id+"'";
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(SQL).executeQuery();
        if (rst.next()){
            return new ClassPackageDTO(
                    rst.getInt(1),
                    rst.getDouble(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getDouble(8)

            );
        }else{
            return null;
        }
    }
    public ServicesPackageDTO getAllServicesPrice(int id) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM spackage WHERE id='"+id+"'";
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(SQL).executeQuery();
        if (rst.next()){
            return new ServicesPackageDTO(
                    rst.getInt(1),
                    rst.getDouble(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getDouble(7)

            );
        }else{
            return null;
        }
    }

    ///full Services-Package

    public boolean savePackage(ServicesDTO dto) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO service VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,dto.getId());
        stm.setObject(2,dto.getName());
        stm.setObject(3,dto.getType());
        stm.setObject(4,dto.getFood());
        stm.setObject(5,dto.getBar());
        stm.setObject(6,dto.getTransport());
        stm.setObject(7,dto.getPool());
        stm.setObject(8,dto.getKidsPark());
        stm.setObject(9,dto.getBeach());
        stm.setObject(10,dto.getPackagePrice());
        return stm.executeUpdate()>0;
    }
    public boolean updatePackage(ServicesDTO dto) throws SQLException, ClassNotFoundException {
        String SQL = "UPDATE service SET name=?,type=?,food=?,bar=?,transport=?,pool=?,kidsPark=?,beach=?,packagePrice=? WHERE id=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(10,dto.getId());
        stm.setObject(1,dto.getName());
        stm.setObject(2,dto.getType());
        stm.setObject(3,dto.getFood());
        stm.setObject(4,dto.getBar());
        stm.setObject(5,dto.getTransport());
        stm.setObject(6,dto.getPool());
        stm.setObject(7,dto.getKidsPark());
        stm.setObject(8,dto.getBeach());
        stm.setObject(9,dto.getPackagePrice());
        return stm.executeUpdate()>0;
    }
    public boolean deletePackage(String id) throws SQLException, ClassNotFoundException {
        String SQL="DELETE FROM service WHERE id='"+id+"'";
        return DBConnection.getInstance().getConnection().prepareStatement(SQL).executeUpdate()>0;
    }
    public ServicesDTO getService(String id) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM service WHERE id='"+id+"'";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        ResultSet rst = stm.executeQuery();
        if(rst.next()) {
           return new ServicesDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getDouble(9),
                    rst.getDouble(10)
            );

        }else{
            return null;
        }
    }
    public ArrayList<ServicesDTO> getAllServices(String text) throws SQLException, ClassNotFoundException {
        String SQL="SELECT * FROM service WHERE id like ? OR name LIKE ?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,text);
        stm.setObject(2,text);
        ResultSet rst = stm.executeQuery();
        ArrayList<ServicesDTO> dtoList = new ArrayList<>();
        while (rst.next()){
            ServicesDTO dto = new ServicesDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getDouble(9),
                    rst.getDouble(10)
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
    //

    ///Person Details

    public boolean savePDetails(PersonalDetailsDTO dto) throws SQLException, ClassNotFoundException {
        String SQL="INSERT INTO persondetails VALUES(?,?,?,?,?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(1,dto.getId());
        stm.setObject(2,dto.getNumOfAdult());
        stm.setObject(3,dto.getNumOfKids());
        stm.setObject(4,dto.getRoomQty());
        stm.setObject(5,dto.getDetails());
        return stm.executeUpdate()>0;
    }
    public boolean updatePDetails(PersonalDetailsDTO dto) throws SQLException, ClassNotFoundException {
        String SQL="UPDATE persondetails SET numOfAdult=?,numOfKids=?,qty=?,details=? WHERE id=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        stm.setObject(5,dto.getId());
        stm.setObject(1,dto.getNumOfAdult());
        stm.setObject(2,dto.getNumOfKids());
        stm.setObject(3,dto.getRoomQty());
        stm.setObject(4,dto.getDetails());
        return stm.executeUpdate()>0;
    }
    public ArrayList<String>getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT id FROM customer").executeQuery();
        ArrayList<String> idset = new ArrayList<>();
        while (rst.next()){
            idset.add(rst.getString(1));
        }
        return idset;
    }
    public PersonalDetailsDTO getAllDetails(String text) throws SQLException, ClassNotFoundException {
        String SQL="SELECT * FROM persondetails WHERE id LIKE '"+text+"'";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new PersonalDetailsDTO(
                    rst.getString(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getString(5)

            );
        }else {
            return null;
        }
    }
}

















