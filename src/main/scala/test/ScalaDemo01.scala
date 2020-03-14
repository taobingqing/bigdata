package test

object ScalaDemo01 {
  def main(args: Array[String]): Unit = {

    //val a = (1 to 5: _*)
    println(sum(1 to 5: _*))

    var map: Map[Int, String] = Map(2 -> "bb")
    map += (1 -> "aa")
    //println(map(3))  如果为空的索引  报异常key not found: 3

    val b = 'name

    println(b.getClass.getName)
    val c ="aaa"
    println(c.getClass.getName)

  }

  def sum(para: Int*): Int = {
    var result = 0
    for (i <- para)
      result += i

    result
  }
}
