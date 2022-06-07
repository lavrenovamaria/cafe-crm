package com.comname.cafecrm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CafeCrmApplication

fun main(args: Array<String>) {
    runApplication<CafeCrmApplication>(*args)
}
