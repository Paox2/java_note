# java_note

### concurrent

simulate postman and people (one to one) : [post](concurrent/src/com/post)

consumer/producer problem : [cp](concurrent/scr/com/cp)

deadlock : - use ***jps*** to track process id, use ***jstack*** check detail information about deadlock and list the reason of deadlock - use ***jconsole***

philosopher : [philosopher](concurrent/src/com/eat)

reentrantlock : ReentranLock(boolean fair);
                lock();  unlock();
                can have different condition to wait:  Condition newCondition = new Condition();   newCondition.await();   signal();   signalAll();
                can avoid deadlock:                    tryLock(time:"", unit:"");
                can be interrupted:                    lockInterruptibly();
