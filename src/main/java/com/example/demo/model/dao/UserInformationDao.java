package com.example.demo.model.dao;
import com.example.demo.model.dao.entity.UserInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserInformationDao extends JpaRepository<UserInformation,Integer> {
    final String FIND_USER_BY_EMAIL=
            "SELECT e from UserInformation e" +
                    " inner join UserInformationAccount f " +
                    " on e.idPk = f.userIdPk" +
                    " where e.email = :emailParam" +
                    " and e.deleteFlg = false" +
                    " and f.deleteFlg = false";

    final String FIND_USER_BY_USERNAME =
            "SELECT e.idPk, e.username, e.email, f.firstName,f.lastName " +
                    " from UserInformation e " +
                    " inner join UserInformationAccount f " +
                    " on e.idPk = f.userIdPk" +
                    " where e.username = :username" +
                    " and e.deleteFlg = false" +
                    " and f.deleteFlg = false";

    final String FIND_USER_INFO_BY_USERNAME =
            " select e from UserInformation e " +
                    " where e.username = :username " +
                    " and e.deleteFlg = false";

    @Query(value = FIND_USER_BY_EMAIL)
    public UserInformation matchedLoginCredentials(@Param("emailParam") String email) throws DataAccessException;

    @Query(value = FIND_USER_BY_USERNAME)
    public List<Object[]> matchedLoginCredentialsUsingUsername(@Param("username") String username) throws DataAccessException;

    @Query(value = FIND_USER_INFO_BY_USERNAME)
    public List<UserInformation> getUserInfoByUsername(@Param("username") String username) throws DataAccessException;
}