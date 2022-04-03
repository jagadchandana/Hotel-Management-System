package lk.system.bo.custom.impl;

import lk.system.bo.custom.EmployeeBO;
import lk.system.dao.DaoFactory;
import lk.system.dao.custom.EmployeeDAO;
import lk.system.dto.EmployeeDTO;
import lk.system.dto.RoomDTO;
import lk.system.entity.Employee;
import lk.system.entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBO {

    EmployeeDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Employee(dto.getId(),dto.getState(), dto.getName(),
               dto.getNic(),dto.getOntact(),dto.getAddress(),dto.getDob(),dto.getSattleDate(),
                dto.getJobType()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Employee(dto.getId(),dto.getState(), dto.getName(),
                dto.getNic(),dto.getOntact(),dto.getAddress(),dto.getDob(),dto.getSattleDate(),
                dto.getJobType()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public EmployeeDTO getEmployee(String id) throws SQLException, ClassNotFoundException {
       Employee e = dao.get(id);
       if (e!=null){
           return new EmployeeDTO(e.getId(), e.getState(), e.getName(),
                   e.getNic(),e.getContact(),e.getAddress(),e.getDob(),e.getSattleDate(),
                   e.getJobType());
       }
       return null;

    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee(String text) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> entityList =dao.getAll(text);
        ArrayList<EmployeeDTO> dtoList = new ArrayList<>();
        for (Employee e: entityList
        ) {
            EmployeeDTO dto = new EmployeeDTO(
                    e.getId(), e.getState(), e.getName(),
                    e.getNic(),e.getContact(),e.getAddress(),e.getDob(),e.getSattleDate(),
                    e.getJobType()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
}
