package com.webber.demos.database.room

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.webber.demos.R
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_room_date_bases.*
import java.util.*

class RoomDateBasesActivity : AppCompatActivity() {
    var userDao: UserDao = AndroidDataBases.getInstance().userDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_date_bases)

        insetBt.setOnClickListener({
            Log.d("picher","click insert")
            val user: UserRoom = UserRoom("admin2", 25, Date(System.currentTimeMillis()))

            //insertCompletable(user)
            AsyncTask.execute({
                var raw = userDao.insertUser(user)
                Log.d("picher","操作行号:"+raw)
            })
        })

        searchBt.setOnClickListener({
            //flowableDemo()
            //observableSearch()
            //deleteByName("admin2")
            searchLatest()
            /* AsyncTask.execute(Runnable {
                 var raws = userDao.queryForAll()
                 Log.d("picher","数据库："+raws.size)
             })*/
        })
    }

    private fun searchLatest() {

        userDao.queryWithinHour().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { users->
                    Log.d("picher","查詢出:"+users.size)
                    for(u in users){
                        Log.d("picher","名字："+u.name)
                    }
                }
    }

    private fun deleteByName(s: String) {
        Observable.create(ObservableOnSubscribe<Integer> {e-> e.onNext(Integer(userDao.deleteByName(s))) }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { raw ->  Log.d("picher",""+raw)})

    }

    private fun observableSearch() {
        Flowable.create(FlowableOnSubscribe<List<UserRoom>> { e -> e.onNext(userDao.queryForAll()) }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {users-> Log.d("picher",users.size.toString()) }
    }

    private fun insertCompletable(user: UserRoom) {
        Completable.fromAction({
               var raw = userDao.insertUser(user)
               Log.d("picher","插入完成："+raw)
               //throw IllegalStateException("sss")
           }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                   .subscribe({ Log.d("picher","插入成功") }, { ths-> Log.d("picher","插入失败"+ths.message) })
    }

    private fun flowableDemo() {
         Log.d("picher","click search")
           userDao.queryAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                   .map({ users -> var names = ""
                       Log.d("picher","数据库："+users.size)
                       for (item in users) names += item.name
                       names
                   }).subscribe({ names -> showTv.text = names })
    }
}
