package com.example.demo.response;

import com.example.demo.obj.UserInformationObj;
import lombok.Data;
import java.util.List;

@Data
public class UserList {

    private List<UserInformationObj> userInformationObjList;
}
