object BiBaoTest {


  var result = 0
  var addInt = {kk:Int => result+=kk}


  def mengka(count:Int)(aa :Int => Unit): Unit ={
    for (i <- 1 to count){
      aa(i)
    }
  }

  def main(args: Array[String]): Unit = {
    mengka(10)(addInt)
    println(addInt(10))
    println("1+2+3+4+5+6+7+8+9+10="+result)
  }
}
