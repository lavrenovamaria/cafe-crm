package com.comname.cafecrm.property

import org.springframework.boot.context.properties.ConfigurationProperties
import kotlin.properties.Delegates

@ConfigurationProperties("paths.api")
class PathsProperty {

    //TODO Add validation

    var version by Delegates.notNull<Long>()

    lateinit var prefix: String

}