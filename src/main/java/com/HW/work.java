package com.HW;

import com.Library.Ens_Console.*;

import com.Library.Ens_RunTheard.RunTheardArrayClone;

import com.Library.Ens_RunTheard.RunTheard_default;

import com.Library.Ens_RunTheard.RunTheard_null;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;


public class work {

    private static Logger logger = Logger.getLogger(work.class);
    private static Integer peoiple = 5;
    private static Integer maxPeopleInLibrary = 1;
    private static Lock lockGate = new ReentrantLock();

    public static void main(String[] args) {

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//Param
        {
            logger.info("Start");
            Scanner scanner = new Scanner(System.in);
            final int maxPeople = 2000;

            new Enter_Array(
                    scanner,
                    null,
                    null,
                    "Entered defoult param for HW_8_1 Library :",
                    "Array not have this commands ! \nTry enter agan... ",
                    null,
                    ">-----<Exit>-----<",
                    "x",
                    new Enter_Integer(scanner, "Size people", "", "Enter Integer value",
                            "Not correct Integer number , you nid a format : \nNumbers of 1 to " + maxPeople,
                            null,
                            "<Cancel>",
                            "x",
                            peoiple) {

                        @Override
                        public Integer action() {
                            Integer i = super.action();

                            peoiple = this.getObject();
                            return i;
                        }

                        @Override
                        protected Integer isTrueFormat(String string) {
                            Integer i = super.isTrueFormat(string);
                            if (i != null && i > 0 && i <= maxPeople) {
                                return i;
                            }
                            return null;
                        }
                    },
                    new Enter_Integer(scanner, "Max size people of Library", "", "Enter Integer value",
                            "Not correct Integer number , you nid a format : \nNumbers of 1 to " + Integer.MAX_VALUE,
                            null,
                            "<Cancel>",
                            "x",
                            maxPeopleInLibrary) {
                        @Override
                        public Integer action() {
                            Integer i = super.action();

                            maxPeopleInLibrary = this.getObject();
                            return i;
                        }

                        @Override
                        protected Integer isTrueFormat(String string) {
                            Integer i = super.isTrueFormat(string);
                            if (i != null && i > 0) {
                                return i;
                            }
                            return null;
                        }
                    },
                    new Enter_Separator("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>"),
                    new Enter_default("Run", "r", "Run HW_8_1") {


                        @Override
                        public int getOrder() {
                            return Enter_Array.ORDER_EXIT;
                        }

                    }

            ) {
                @Override
                public Enter_null<Object> action() {
                    Enter_null enter_null = super.action();

                    if (enter_null == null) {
                        logger.info("Exit...");
                        scanner.close();
                        System.exit(0);

                    }

                    return enter_null;
                }
            }.action();


            scanner.close();

            logger.info("Select Param : " + "\nMax size people of Library : " + maxPeopleInLibrary + "\nSize people : " + peoiple);

        }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//Create runTheard


        Semaphore semaphore = new Semaphore(maxPeopleInLibrary);
        Random random = new Random();

        new RunTheardArrayClone(new RunTheard_default() {
            @Override
            public void run() {

                //.............................................................................................
                System.out.println(Thread.currentThread().getId() + " : " + " go to library ...");

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getId() + " : " + "Worth Near Library");

                if (semaphore.getQueueLength() > maxPeopleInLibrary) {
                    System.out.println(Thread.currentThread().getId() + " : " + "Waiting in line");
                }
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //.............................................................................................

                System.out.println(Thread.currentThread().getId() + " : " + "it was his turn , go to gate");


                gate();


                System.out.println(Thread.currentThread().getId() + " : " + "entered the library");

                //.............................................................................................
                long l = 1000 * random.nextInt(5);
                System.out.println(Thread.currentThread().getId() + " : " + "read the book (mills : " + l + ")");
                try {
                    sleep(l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + " : " + "go to exit");

                //.............................................................................................
                gate();
                semaphore.release();

                System.out.println(Thread.currentThread().getId() + " : " + "persona is dan all task");
                logger.info(Thread.currentThread().getName() + "exit");
                //.............................................................................................


            }
        }, peoiple) {

            protected RunTheard_null[] buldClones(RunTheard_null r, int rClonesSize) {

                if (rClonesSize < 0) {
                    rClonesSize = 0;
                }

                RunTheard_null[] ret = new RunTheard_null[rClonesSize];

                for (int i = 0; i < rClonesSize; i++) {
                    ret[i] = (RunTheard_null) r.clone();
                    logger.info("Create new persona id : " + ret[i].getThread().getId());
                }

                return ret;
            }

        }.start();


    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private static final void gate() {

        if (((ReentrantLock) lockGate).isLocked()) {
            System.out.println(Thread.currentThread().getId() + " : " + "waiting for his opportunity to enter");
        }

        lockGate.lock();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lockGate.unlock();


    }


}
