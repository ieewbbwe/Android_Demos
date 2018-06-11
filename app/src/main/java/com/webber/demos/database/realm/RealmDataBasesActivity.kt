package com.webber.demos.database.realm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.webber.demos.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_realm_data_bases.*
import java.util.*

class RealmDataBasesActivity : AppCompatActivity() {
    var mRealm = Realm.getDefaultInstance()
    var mUsers: List<UserRealm> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm_data_bases)

        createBt.setOnClickListener({
            mRealm = Realm.getDefaultInstance()
        })

        closeBt.setOnClickListener({
            mRealm.close()
            /*  Log.d("picher","关闭后观测结果："+mUsers.size)
              Log.d("picher","关闭后观测结果："+mUsers.get(0).name)*/
        })
        insertBt.setOnClickListener({
            //demoInsert01()
            //demoInsert02()
            //demoInsert03()
            //demoInsert04()
            //demoInsert05()
            //demoInsert06()
            //demoInsert07()
            demoInsert08()
        })
        queryBt.setOnClickListener({
            //demoSearch01()
            //demoSearch02()
            demoSearch03()
            //demoSearch04()
          //  demoSearch05()
        })
        deleteBt.setOnClickListener({
            mRealm.executeTransaction({ realm ->
                realm.delete(UserRealm::class.java)
            })
        })
    }

    private fun demoInsert08() {
        mRealm.executeTransaction({realm ->
            var user:UserRealm
            for(i in 1..500){
                user = UserRealm()
                user.name = "name"+i
                realm.insert(user)
            }
        })
        mRealm.close()
    }

    private fun demoSearch05() {
        val c = Calendar.getInstance()
        c.add(Calendar.YEAR, -5)
        val fiveBeforeYear = c.time
        mRealm.executeTransaction({
            val currentTime = Date(System.currentTimeMillis())
            val users = mRealm.where(Book::class.java).between("createTime",
                    fiveBeforeYear,  Date(System.currentTimeMillis() - 5 * 1000)).findAll()
            Log.d("picher", "5年前數據：" + users.size)
        })
    }

    private fun demoSearch04() {
        mRealm.executeTransaction({
            val currentTime = Date(System.currentTimeMillis())
            val users = mRealm.where(Book::class.java).between("createTime",
                    Date(System.currentTimeMillis() - 5 * 1000), currentTime).findAll()
            Log.d("picher", "5秒内數據：" + users.size)
        })
    }

    private fun demoInsert07() {
        Log.d("picher", "isClose:" + mRealm.isClosed)
        mRealm.executeTransaction({
            val book = Book()
            book.name = "Android 9.0"
            book.createTime = Date(System.currentTimeMillis())
            mRealm.insert(book)

            Log.d("picher", "查找到：" + mRealm.where(Book::class.java).equalTo("name", "Android 9.0").findFirst()!!.name)
        })

    }

    private fun demoInsert06() {
        mRealm.executeTransaction({ realm ->
            val user = realm.createObject(UserRealm::class.java)
            user.name = "picher"
        })
    }

    private fun demoInsert05() {
        mRealm.executeTransaction({ realm ->
            val user: UserRealm = UserRealm()
            user.id = 111
            user.name = "insert05_demo"
            realm.insertOrUpdate(user)
        })
    }

    private fun demoSearch03() {
        mRealm = Realm.getDefaultInstance()
        mRealm.executeTransaction({ realm ->
            val users = realm.where(UserRealm::class.java).findAll()
            Log.d("picher", "User個數:" + users.size)
            for (i in users) {
                Log.d("picher", "User:" + i.name)
            }
        })
    }

    private fun demoInsert04() {
        mRealm.executeTransaction({ realm ->
            val user: UserRealm = realm.createObject(UserRealm::class.java)
            user.name = "insert04"
            user.age = 23
            user.birthday = Date(System.currentTimeMillis())
            realm.copyToRealm(user)
        })
    }

    private fun demoSearch02() {
        mRealm.where(UserRealm::class.java).findAll().asFlowable()
                /*.subscribeOn(Schedulers.io())*/.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    mUsers = mRealm.copyFromRealm(users)
                    Log.d("picher", "查询到的结果数：" + mUsers.size)
                    /*for (i in users) {m
                        Log.d("picher", "查询得结果：" + i.name)
                    }*/
                    var count = 0;
                    for (i in 0 until users.size) {
                        val user = users[i]
                        Log.d("picher", "结果：" + user!!.name)
                        count++
                    }
                    Log.d("picher", "循环了：" + count)
                    val iterator = users.listIterator()
                    while (iterator.hasNext()) {
                        Log.d("picher", "Itera：" + iterator.next().name)
                    }
                }, { throwable -> throwable.printStackTrace() })
    }

    private fun demoInsert03() {
        mRealm.executeTransactionAsync({ realm ->
            val user = UserRealm("Webber", 25, Date(System.currentTimeMillis()))
            Log.d("picher", "demoInsert03:" + user.name)

            realm.copyToRealm(user)
        })

    }

    private fun demoSearch01() {
        var users: RealmResults<UserRealm> = mRealm.where(UserRealm::class.java).findAll()
        for (i in users) {
            Log.d("realm", "searchData:" + i.name)
        }

    }

    private fun demoInsert02() {
        val user: UserRealm = UserRealm("Webber", 25, Date(System.currentTimeMillis()))
        mRealm.beginTransaction()
        mRealm.copyToRealm(user)
        mRealm.commitTransaction()
    }

    private fun demoInsert01() {
        mRealm.beginTransaction()
        val user: UserRealm = mRealm.createObject(UserRealm::class.java)
        user.name = "picher"
        user.age = 25
        user.birthday = Date(System.currentTimeMillis())
        mRealm.commitTransaction()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mRealm != null) {
            mRealm.close()
        }
    }
}
