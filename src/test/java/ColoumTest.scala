

object ColoumTest {


  def main(args: Array[String]): Unit = {
    val column = new Column("cf:w")


    println(column.family.toString)
    println(column.qualifier)
    //    for (x <- column.a) {
    //      println(x)
    //    }

    println("=======================")
    var a = 0;
    val numList = List(1,2,3,4,5,6,7,8,9,10);

    // for 循环
    var retVal = for{ a <- numList
                      if a != 3; if a < 8
    }yield a

    // 输出返回值
    for( a <- retVal){
      println( "Value of a: " + a );
    }
  }


  }

  class Column(column: String) extends java.io.Serializable {
    //val a = column.split(":", -1)
    private val Array(family_, qualifier_) = column.split(":", -1)
    val family = family_.trim.getBytes("utf-8")
    val qualifier = if (!"".equalsIgnoreCase(qualifier_)) {
      qualifier_.trim.getBytes("utf-8")
    } else {
      null
    }



}
