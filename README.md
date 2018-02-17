
Very simple benchmark to check performance of various ways to return an `Option`.


### Results

```
Benchmark                                  Mode  Cnt          Score          Error  Units
OptionBenchmark.checkWithReturn            thrpt  200  287195403.054 ± 12354118.434  ops/s
OptionBenchmark.checkWithTry               thrpt  200  118274801.113 ± 27783534.402  ops/s
OptionBenchmark.checkWithTryCatch          thrpt  200  118771374.313 ± 27883958.369  ops/s

```