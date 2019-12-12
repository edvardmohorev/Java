package com.irontec.roomexample.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by axier on 7/2/18.
 */

@Entity
class Course constructor(uid: Int, name: String, usd_buy: String, usd_sell:String, eur_buy: String, eur_sell:String) {

    @PrimaryKey
    var uid: Int = uid

    @ColumnInfo(name = "name")
    var name: String? = name

    @ColumnInfo(name = "usd_buy")
    var usd_buy: String? = usd_buy
    @ColumnInfo(name = "usd_sell")
    var usd_sell: String? = usd_sell
    @ColumnInfo(name = "eur_buy")
    var eur_buy: String? = eur_buy
    @ColumnInfo(name = "eur_sell")
    var eur_sell: String? = eur_sell

}
