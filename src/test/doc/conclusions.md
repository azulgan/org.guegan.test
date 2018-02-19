# String test: jvm improvements

## First test: String vs StringBuffer vs StringBuilder

Source: See org.guegan.test.strings.StringBufferAutoTest

Results :

jvm | test | nb iteratons |  time
 --- | --- | --- | ---
 jdk7 | string | 100 | 0ms
 jdk7 | buffer | 100 | 0ms
 jdk7 | builder | 100 | 0ms
 jdk7 | string | 1000 | 37ms
 jdk7 | buffer | 1000 | 2ms
 jdk7 | builder | 1000 | 1ms
 jdk7 | string | 10000 | 1212ms
 jdk7 | buffer | 10000 | 16ms
 jdk7 | builder | 10000 | 1ms
 jdk9 | string | 100 | 1ms
 jdk9 | buffer | 100 | 1ms
 jdk9 | builder | 100 | 0ms
 jdk9 | string | 1000 | 25ms
 jdk9 | buffer | 1000 | 1ms
 jdk9 | builder | 1000 | 0ms
 jdk9 | string | 10000 | 770ms
 jdk9 | buffer | 10000 | 4ms
 jdk9 | builder | 10000 | 1ms

This is obvious, StringBuilder wins largely, Buffer is only a little bit slower and String is out of the race
note that every test benefits obviously by some jvm9 optimization

## Second test: jdk compiler would improve your code behind the scene

Source: see org.guegan.test.strings.StringBufferAutoTest

Results :

jvm | test | nb iteratons |  time
 --- | --- | --- | ---
 jdk9 | string + | 1000 | 1ms
 jdk9 | string += | 1000 | 24ms
 jdk9 | builder | 100 | 2ms

One can see that the suite of += statements are run in the same time as the loop of '+' string in the first test
but we can see also that a very long statement of '+' concat string clearly benefits from the
compiler automatization, that has probably created a single StringBuilder instead of that series of '+'.

## Conclusion... 1: pure optimization point of view
For the moment, a long loop of string '+' or '+=' is not a perfect idea, one should continue
to think in place of the java compiler. See you in next jdk release !
[I did not test any other compile optimization, but in the same time Java is often run 'as is' by developpers.]

## Conclusion... 2: readability vs code optimization
One can argue than series of ").append(" confuse things, and is not easy to modify.

Let's suppose 3 differents scenari:
Scenario A-
The developer thinks his huge string concatenation from the start and likes to use StringBuilder/Buffer.
He can be sure to do it the best way, from an optimization point of view, and
manages directly the .append() series the best way possible.
=> Nothing to say, especially if code indentation is clear enough.

Scenario B and C:
The code is full of + strings and complex enough not to benefit from the compiler-optimization-behind-the-scenes.

Scenario B: the code is not in a critical part of the execution,
=> let's leave it like that, it is probably already tested and has run on production like that.

Scenario C:
It is not optimized and in a critical part.
=> Let's rewrite it, after making sure some junit code has been written properly. Why hasn't it already been written
like Scneario A ?