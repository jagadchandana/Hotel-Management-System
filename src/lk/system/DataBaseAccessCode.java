package lk.system;

import lk.system.db.DBConnection;
import lk.system.dto.CustomerDTO;

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

}

















