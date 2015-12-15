import java.io.RandomAccessFile
import java.nio.channels.FileChannel.MapMode
import java.nio.ByteBuffer

object PametovyFile {

  //val N = 10024 * 1024 * 1024 * 256
  //val N = 1024l * 1024 * 1024 * 2 - 8
  val N = 1024l * 1024 * 10

  def main(args: Array[String]): Unit = {
    println("jedu1")
    val soub = new RandomAccessFile("a:\\xx\\pametove.bin", "rw");
    println("jedu2")
    soub.setLength(N)
    println(soub.length());
    val k = soub.getChannel.map(MapMode.READ_WRITE, 0, soub.length());
    println("Ma pole: " + k.hasArray())
    println("Is direct: " + k.isDirect())
    val ib = k.asIntBuffer()
    var i = 1;
    while (ib.hasRemaining()) {
      ib.put(i);
      i = i * 3;
    }

    val cb = k.asCharBuffer()
    while (cb.hasRemaining()) {
      cb.append("Drob")
    }
    println(soub.length());
    
    //val bc = ByteBuffer.wrap(new Array[Byte](1000))
    val bc = ByteBuffer.allocate(12000)
    println (bc.capacity + "*" + bc.isDirect() + "/" + bc.hasArray())
    
  }
}