package com.ortin.gesturetranslator.app.app

import android.util.Log
import timber.log.Timber

/**
 * Custom Logging Tree extends Timber's DebugTree and adapts its logging behavior to use the Android Log class.
 *
 * @constructor creates an instance of the Console Logging Tree.
 */
class ConsoleLoggingTree : Timber.DebugTree() {
    /**
     * This function is used to log messages to the Android system console based on the provided
     * priority level. If a throwable (exception) is provided, it will be included in the log entry.
     *
     * @param [priority] the priority of the log message, such as Log.DEBUG or Log.ERROR.
     * @param [tag] a custom tag for the log message, or null to use the default tag.
     * @param [message] the log message text.
     * @param [t] an optional Throwable object associated with the log message, or null if no exception.
     */
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (t == null) {
            when (priority) {
                // Processing logs without exception
                Log.DEBUG -> Log.d(tag, message)
                Log.VERBOSE -> Log.v(tag, message)
                Log.INFO -> Log.i(tag, message)
                Log.WARN -> Log.w(tag, message)
                Log.ERROR -> Log.e(tag, message)
            }
        } else {
            when (priority) {
                // Processing logs with exception
                Log.DEBUG -> Log.d(tag, message, t)
                Log.VERBOSE -> Log.v(tag, message, t)
                Log.INFO -> Log.i(tag, message, t)
                Log.WARN -> Log.w(tag, message, t)
                Log.ERROR -> Log.e(tag, message, t)
            }
        }
    }
}
