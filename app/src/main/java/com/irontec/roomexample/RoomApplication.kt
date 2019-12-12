package com.irontec.roomexample

import android.app.Application
import com.irontec.roomexample.database.AppDatabase
import com.irontec.roomexample.database.entities.Course
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 * Created by axier on 7/2/18.
 */

class RoomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        doAsync {
            val database = AppDatabase.getInstance(context = this@RoomApplication)

//            if (database.customerDao().all.isEmpty()) {
            database.courseDao().deleteAll()
            val document: Document = Jsoup.connect("https://myfin.by/currency/gomel").get() as Document
            val elements=document.select(".tr-tb")
            val cours: MutableList<Course> = mutableListOf()
            var i=1
            for (el: Element in elements) {
                val client = Course(i,el.child(0).text(), el.child(1).text() ,el.child(2).text(),el.child(3).text(),el.child(4).text())
                cours.add(client)
                i++
            }
            database.courseDao().insertAll(cours = cours)
//            for (cus in customers)
////                Log.d("Test",cus.name+"  "+cus.lastName)
////            }

//            if (database.providerDao().all.isEmpty()) {
//                val providers: MutableList<Provider> = mutableListOf()
//                for (index: Int in 0..20) {
//                    val provider = Provider(index, "Provider " + index)
//                    providers.add(index, provider)
//                }
//
//                database.providerDao().insertAll(providers = providers)
//            }
        }
    }

}
