package com.test.showcase.data

import io.reactivex.Scheduler

interface RxSchedulers {

    fun main(): Scheduler

    fun single(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler
}
