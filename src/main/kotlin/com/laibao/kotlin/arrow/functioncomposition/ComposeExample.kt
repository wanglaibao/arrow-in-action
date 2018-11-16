package com.laibao.kotlin.arrow.functioncomposition

import arrow.syntax.function.andThen
import arrow.syntax.function.compose
import arrow.syntax.function.forwardCompose
import java.util.*

/**
 * @author laibao wang
 */

/*
    复合函数的有关方法说明
    compose : Takes the result of invoking the right-hand function as the parameter for the left-hand function.

    forwardCompose : Takes the result of invoking the left-hand function as the parameter for the right-hand function

    andThen : Is an alias for forwardCompose

 */

val p: (String) -> String = {body -> "<p>$body</p>"}

val span: (String) -> String = {body -> "<span>$body</span>"}

val div: (String) -> String = {body -> "<div>$body</div>"}

val strong: (String) -> String = {body -> "<strong>$body</strong>"}

val randomNames: () -> String = {if (Random().nextInt() % 2 == 0) "foo" else "bar"}

fun main(args: Array<String>) {
    println(p("金戈"))

    println(span("金戈"))

    println(div("金戈"))

    println(strong("金戈"))
    println()

    val divStrong: (String) -> String = div.compose(strong)
    println(divStrong("Hello composition world!"))   //<div><strong>Hello composition world! </strong></div>
    println()

    val spanP: (String) -> String = p.forwardCompose(span)
    println(spanP("金戈大傻子"))

    val randomStrong: () -> String = randomNames.andThen(strong)
    println(randomStrong)
}

