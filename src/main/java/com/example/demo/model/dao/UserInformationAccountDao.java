package com.example.demo.model.dao;

import com.example.demo.model.dao.entity.UserInformationAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;

public interface UserInformationAccountDao extends JpaRepository<UserInformationAccount,Integer> {

    String FIND_USER_BY_USER_ID_PK =
            " select e from UserInformationAccount e " +
                    " inner join UserInformation f " +
                    " on e.userIdPk = f.idPk " +
                    " where e.userIdPk = :userIdPk " +
                    " and e.deleteFlg = false" +
                    " and f.deleteFlg = false ";


    @Query(value=FIND_USER_BY_USER_ID_PK)
    public UserInformationAccount getUserByUserIdPk(@Param("userIdPk") int userIdPk) throws SQLException;
}
