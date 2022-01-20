package Game;

public class ThreadTimeStorage extends Trade implements Runnable {

    static int tmp_howmanydays = 0;
    static boolean work = false;

    public static void reduce_storage(){

        for (int i = 0; i < Trade.rice.length; i++) {
            if (rice[i].stock != 0) {
                rice[i].storage -= 4;
                if (rice[i].storage < 0) {
                    rice[i].storage = 0;
                }
            }
        }
        for (int i = 0; i < Trade.ginseng.length; i++) {
            if (ginseng[i].stock != 0) {
                ginseng[i].storage -= 4;
                if (ginseng[i].storage < 0) {
                    ginseng[i].storage = 0;
                }
            }
        }
        for (int i = 0; i < tea.length; i++) {
            if (tea[i].stock != 0) {
                tea[i].storage -= 4;
                if (tea[i].storage < 0) {
                    tea[i].storage = 0;
                }
            }
        }
        for (int i = 0; i < Trade.horse.length; i++) {
            if (horse[i].stock != 0) {
                horse[i].storage -= 4;
                if (horse[i].storage < 0) {
                    horse[i].storage = 0;
                }
            }
        }
        for (int i = 0; i < Trade.medicine.length; i++) {
            if (medicine[i].stock != 0) {
                medicine[i].storage -= 4;
                if (medicine[i].storage < 0) {
                    medicine[i].storage = 0;
                }
            }
        }
        for (int i = 0; i < Trade.flavor.length; i++) {
            if (flavor[i].stock != 0) {
                flavor[i].storage -= 4;
                if (flavor[i].storage < 0) {
                    flavor[i].storage = 0;
                }
            }
        }
    }

    public void run() {

        while (true) {
            for (int i = 0; i < Trade.rice.length; i++) {
                if (rice[i].stock != 0) {
                    rice[i].storage--;
                    if (rice[i].storage < 0) {
                        rice[i].storage = 0;
                    }
                }
            }
            for (int i = 0; i < Trade.ginseng.length; i++) {
                if (ginseng[i].stock != 0) {
                    ginseng[i].storage--;
                    if (ginseng[i].storage < 0) {
                        ginseng[i].storage = 0;
                    }
                }
            }
            for (int i = 0; i < tea.length; i++) {
                if (tea[i].stock != 0) {
                    tea[i].storage--;
                    if (tea[i].storage < 0) {
                        tea[i].storage = 0;
                    }
                }
            }
            for (int i = 0; i < Trade.horse.length; i++) {
                if (horse[i].stock != 0) {
                    horse[i].storage--;
                    if (horse[i].storage < 0) {
                        horse[i].storage = 0;
                    }
                }
            }
            for (int i = 0; i < Trade.medicine.length; i++) {
                if (medicine[i].stock != 0) {
                    medicine[i].storage--;
                    if (medicine[i].storage < 0) {
                        medicine[i].storage = 0;
                    }
                }
            }
            for (int i = 0; i < Trade.flavor.length; i++) {
                if (flavor[i].stock != 0) {
                    flavor[i].storage--;
                    if (flavor[i].storage < 0) {
                        flavor[i].storage = 0;
                    }
                }
            }
            try {
                Thread.sleep(30000);
            } catch (Exception e) {
                e.getMessage();
            }
        }

    }

    public static void TimeDate (int stage, int go_port){
        work = true;
        if(work) {
            if (stage == 1) {
                if (go_port == 2) {
                    if (ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } else if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
//                            ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
//                            ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    }
                } else if (go_port == 3) {
                    if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 8 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 1;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 1;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 1;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 1;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 1;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 1;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 1;
                        tmp_howmanydays = 1;
                    } else if (ThreadWindDirection.wind_result == 3 | ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 5) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 6) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    }
                } else if (go_port == 4) {
                    if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 1;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 1;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 1;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 1;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 1;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 1;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 1;
                        tmp_howmanydays = 1;
                    } else if (ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    }
                }
            } else if (stage == 2) { // 대련
                if (go_port == 1) { // 조선
                    if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } // good
                    else if (ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
//                            ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } // bad
                    else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
//                            ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } // normal
                    else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    } // worst
                } else if (go_port == 3) { // 상해
                    if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } else if (ThreadWindDirection.wind_result == 3 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    }
                } else if (go_port == 4) { // 일본
                    if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    } else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    }
                }
            } else if (stage == 3) { // 상해
                if (go_port == 1) { // 조선
                    if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 1;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 1;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 1;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 1;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 1;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 1;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 1;
                        tmp_howmanydays = 1;
                    } // good
                    else if (ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
//                            ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } // bad
                    else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
//                            ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } // normal
                    else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } // worst
                } else if (go_port == 2) { // 대련
                    if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } else if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } else if (ThreadWindDirection.wind_result == 3 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    }
                } else if (go_port == 4) { // 일본
                    if (ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3 | ThreadWindDirection.wind_result == 4) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } else if (ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } else if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 5) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    }
                }
            } else if (stage == 4) {
                if (go_port == 1) {
                    if (ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 1;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 1;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 1;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 1;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 1;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 1;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 1;
                        tmp_howmanydays = 1;
                    } // good
                    else if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } // bad
                    else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } // normal
                    else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } // worst
                } else if (go_port == 2) {
                    if (ThreadWindDirection.wind_result == 5 | ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    } else if (ThreadWindDirection.wind_result == 4 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 6;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 6;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 6;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 6;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 6;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 6;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 6;
                        tmp_howmanydays = 6;
                    }
                } else if (go_port == 3) {
                    if (ThreadWindDirection.wind_result == 6 | ThreadWindDirection.wind_result == 7 | ThreadWindDirection.wind_result == 8) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 2;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 2;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 2;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 2;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 2;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 2;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 2;
                        tmp_howmanydays = 2;
                    } else if (ThreadWindDirection.wind_result == 2 | ThreadWindDirection.wind_result == 3 | ThreadWindDirection.wind_result == 4) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 4;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 4;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 4;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 4;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 4;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 4;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 4;
                        tmp_howmanydays = 4;
                    } else if (ThreadWindDirection.wind_result == 1 | ThreadWindDirection.wind_result == 5) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 3;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 3;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 3;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 3;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 3;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 3;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 3;
                        tmp_howmanydays = 3;
                    } else if (ThreadWindDirection.wind_result == 9) {
                        for (int i = 0; i < Trade.rice.length; i++) {
                            if (rice[i].stock != 0) {
                                rice[i].storage -= 5;
                                if (rice[i].storage < 0) {
                                    rice[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.ginseng.length; i++) {
                            if (ginseng[i].stock != 0) {
                                ginseng[i].storage -= 5;
                                if (ginseng[i].storage < 0) {
                                    ginseng[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < tea.length; i++) {
                            if (tea[i].stock != 0) {
                                tea[i].storage -= 5;
                                if (tea[i].storage < 0) {
                                    tea[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.horse.length; i++) {
                            if (horse[i].stock != 0) {
                                horse[i].storage -= 5;
                                if (horse[i].storage < 0) {
                                    horse[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.medicine.length; i++) {
                            if (medicine[i].stock != 0) {
                                medicine[i].storage -= 5;
                                if (medicine[i].storage < 0) {
                                    medicine[i].storage = 0;
                                }
                            }
                        }
                        for (int i = 0; i < Trade.flavor.length; i++) {
                            if (flavor[i].stock != 0) {
                                flavor[i].storage -= 5;
                                if (flavor[i].storage < 0) {
                                    flavor[i].storage = 0;
                                }
                            }
                        }
                        //ThreadCountTime.howmanydays += 5;
                        tmp_howmanydays = 5;
                    }
                }
            }

            work = false;
        }
    }
}
