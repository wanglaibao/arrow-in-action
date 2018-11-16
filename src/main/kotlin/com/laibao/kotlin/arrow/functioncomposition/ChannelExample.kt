package com.laibao.kotlin.arrow.functioncomposition

import arrow.syntax.function.andThen
import arrow.syntax.function.forwardCompose

/**
 * @author laibao wang
 */

data class Quote(val value: Double, val client: String, val item: String, val quantity: Int)

data class Bill(val value: Double, val client: String)

data class PickingOrder(val item: String, val quantity: Int)

fun calculatePrice(quote: Quote) = Bill(quote.value * quote.quantity, quote.client) to PickingOrder(quote.item, quote.quantity)

fun filterBills(billAndOrder: Pair<Bill, PickingOrder>): Pair<Bill, PickingOrder>? {
    val (bill, _) = billAndOrder
    return if (bill.value >= 100) {
        billAndOrder
    } else {
        null
    }
}

fun warehouse(order: PickingOrder) {
    println("Processing order = $order")
}

fun accounting(bill: Bill) {
    println("processing = $bill")
}

fun splitter(billAndOrder: Pair<Bill, PickingOrder>?) {
    if (billAndOrder != null) {
        warehouse(billAndOrder.second)
        accounting(billAndOrder.first)
    }
}

fun main(args: Array<String>) {
    val salesSystem: (quote: Quote) -> Unit = ::calculatePrice andThen ::filterBills andThen ::splitter

    salesSystem(Quote(20.0, "Foo", "Shoes", 1))

    salesSystem(Quote(20.0, "Bar", "Shoes", 200))

    salesSystem(Quote(2000.0, "Foo", "Motorbike", 1))

    val salesSystem1: (quote: Quote) -> Unit = ::calculatePrice forwardCompose  ::filterBills forwardCompose  ::splitter

    salesSystem1(Quote(200.0, "Fofasdfo", "Shoesfasfd", 16))

    salesSystem1(Quote(200.0, "Bafasr", "Shoefafs", 230))

    salesSystem1(Quote(20000.0, "Fsadfoo", "Motorfsafbike", 10))
}
