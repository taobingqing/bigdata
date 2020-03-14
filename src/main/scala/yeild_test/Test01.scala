package yeild_test

object Test01 {

  def main(args: Array[String]): Unit = {
   val a = for (i <- 1 to 10) yield i
    println(a.getClass)
    for (elem <- a) {
    println(elem)
    }
    println("================")
    val s = "a@#b@#c@#@#"
    val strings = s.split("@#")
    strings.foreach(println)
    println(strings.length)



  }



}
