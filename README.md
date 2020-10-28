# java_note

## concurrent

### code task
| name  |  track | discription |
| - | - | - |
| post | [post](concurrent/src/com/post) | simulate postman and people (one to one) |
| cp | [cp](concurrent/scr/com/cp) | consumer/producer problem |
| philosopher | [philosopher](concurrent/src/com/eat) | Dining-Philosophers Problem |
| alternate print | [alternatePrint](concurrent/src/com/alternatePrint) | control three threads alternater print three character several times
| order print | [orderPrint](concurrent/src/com/orderPrint) | control threads print in order |
| monitor thread | [monitorTh](concurrent/src/com/monitorTh) | monitor thread start or start show in html (missing main function) |
| double checked locking |  [dcl](concurrent/src/com/doubleCheckedLocking) | Implement thread-safe object creation (prevent multiple calls and multiple outputs) |
| test array | [test](concurrent/src/com/testArray) | commom function use to test if array type is thread safe |
| test atomic integer | [test](concurrent/src/com/testAtomicInteger) | test if AtomicInteger work and related function |
| atomic account | [account](concurrent/src/com/atomicAccount) | use atomic variable simulate account check if it is thread safe |
| atomic updater | [updater](concurrent/src/com/AtomicUpdater) | test  AtomicReferenceFieldUpdater |
| test Unsafe | [test](concurrent/src/com/testUnsafe) | use unsafe simulate cas and test it |
| connection pool | [pool](concurrent/src/com/connectionPool) | simulate connection pool |
| thread pool | [pool](concurrent/src/com/threadPool) | simulate thread pool based on origin code |

###  knowledge
deadlock : - use ***jps*** to track process id, use ***jstack*** check detail information about deadlock and list the reason of deadlock - use ***jconsole***

<pre>
reentrantlock : ReentranLock(boolean fair);
                lock();  unlock();
                can have different condition to wait:  Condition newCondition = new Condition();   newCondition.await();   signal();   signalAll();
                can avoid deadlock:                    tryLock(time:"", unit:"");
                can be interrupted:                    lockInterruptibly();
</pre>

longAdder: more effecient than AtomicLong

## JVM

### code task
| name  |  track | discription |
| - | - | - |
| string table |  [stringTable](JVM/code/stringtable) | including classic quiz questions and test code |
| gc | [gc](JVM/code/gc) | test gabage collection (Eden, FROM, TO, Old)|

### note
[here](JVM/note)
