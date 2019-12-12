package com.irontec.roomexample.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.irontec.roomexample.database.entities.Course

/**
 * Created by axier on 7/2/18.
 */

@Dao
interface CourseDao {

    @get:Query("SELECT * FROM course")
    val all: List<Course>

    @get:Query("SELECT * FROM course WHERE usd_buy=(SELECT max(usd_buy) FROM course)")
    val usd_buy: List<Course>
    @get:Query("SELECT * FROM course WHERE usd_sell=(SELECT min(usd_sell) FROM course)")
    val usd_sell: List<Course>
    @get:Query("SELECT * FROM course WHERE eur_buy=(SELECT max(eur_buy) FROM course)")
    val eur_buy: List<Course>
    @get:Query("SELECT * FROM course WHERE eur_sell=(SELECT min(eur_sell) FROM course)")
    val eur_sell: List<Course>

    @Query("SELECT * FROM course WHERE uid IN (:customersId)")
    fun loadAllByIds(customersId: Array<Int>): List<Course>

    @Query("DELETE FROM course")
    fun deleteAll()

//    @Query("SELECT * FROM customer WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): Course

    @Insert
    fun insertAll(cours: List<Course>)

    @Delete
    fun delete(client: Course)

}
