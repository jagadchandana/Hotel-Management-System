package lk.system;

import lk.system.db.DBConnection;
import lk.system.dto.CustomerDTO;
import lk.system.dto.EmployeeDTO;
import lk.system.dto.PItemDTO;
import lk.system.dto.RoomDTO;

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
            ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT id FROM services").executeQuery();
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
    //////
    /////////Room
    public boolean saveRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO room VALUES(?,?,?,?)");
        stm.setObject(1,dto.getId());
        stm.setObject(2,dto.getName());
        stm.setObject(3,dto.getType());
        stm.setObject(4,dto.getDescription());
        return stm.executeUpdate() > 0;
    }
    public boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE room SET naame=?,type=?,description=? WHERE id=?");
        stm.setObject(4,dto.getId());
        stm.setObject(1,dto.getName());
        stm.setObject(2,dto.getType());
        stm.setObject(3,dto.getDescription());
        return stm.executeUpdate()>0;
    }
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM room WHERE id='" + id + "'").executeUpdate() > 0;
    }
    public ArrayList<RoomDTO> getAllRoom(String text) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM room WHERE name LIKE ? OR type LIKE ? OR description LIKE ?");
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
                rst.getString(4)
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


}

















