package com.rz.rz.footballappfinal.api

import java.net.URL

/*
 * Created by Arif R Gilang
 * github.com/arifrgilang
 */

class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}