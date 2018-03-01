package com.webber.demos.database;

/**
 * Created by picher on 2018/2/6.
 * Describe：
 */

public class GreenDaoContract {
    interface View{
        void showData();
        void refreshData();
    }

    interface ViewModel{
        void addData(User user);
        void deleteData(String id);
        void updateData(String targetId, User user);
        void queryAllData();
    }

}
