package bench


import bench.OptionBenchmark.MyState
import org.openjdk.jmh.annotations.{Benchmark, Scope, Setup, State}
import org.openjdk.jmh.infra.Blackhole

import scala.util.control.NonFatal
import scala.util.{Random, Try}


class OptionBenchmark {

  @Benchmark
  def checkWithTry(blackhole: Blackhole, state: MyState): Unit = {
    val result: Option[Boolean] = Try {
      if (state.check) {
        throw new RuntimeException()
      } else {
        state.check
      }
    }.toOption
    blackhole.consume(result)
  }

  @Benchmark
  def checkWithTryCatch(blackhole: Blackhole, state: MyState): Unit = {
    val result: Option[Boolean] = try {
      if (state.check) {
        throw new RuntimeException()
      } else {
        Some(state.check)
      }
    } catch {
      case NonFatal(e) => None
    }
    blackhole.consume(result)
  }

  @Benchmark
  def checkWithReturn(blackhole: Blackhole, state: MyState): Unit = {
    val result: Option[Boolean] =
      if (state.check) {
        None
      } else {
        Some(state.check)
      }

    blackhole.consume(result)
  }

}

object OptionBenchmark {
  @State(Scope.Thread) class MyState {

    var check: Boolean = _

    @Setup def doSetup(): Unit = {
      check = Random.nextBoolean()
    }

  }
}