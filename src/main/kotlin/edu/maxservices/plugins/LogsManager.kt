package edu.maxservices.plugins

import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.logging.Logger

class LogsManager(
    private val caller: String = "",
    private val filePath: String = "logfile.txt",

) {
    private val fileObj: File = File(filePath)
    private val jLog = Logger.getLogger(caller)
    private val threadPool: ExecutorService = Executors.newFixedThreadPool(1)
    init {
        if (!fileObj.exists()) {
            fileObj.createNewFile()
        }
        fileObj.appendText(
            SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                    + " ${this.javaClass.name} info: Initialized LogsManager\n"
        )
    }
    fun log(str: String) {
        threadPool.submit {
            fileObj.appendText(
                SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                        + " $caller info: $str\n"
            )
            jLog.info(str)
        }
    }

//    fun warning(str: String) {
//        fileObj.appendText(SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
//                + " $caller warning: $str\n"
//        )
//        jLog.warning(str)
//    }
}