package ru.netology;

class Main {
    public static final int SLEEP = 1000;
    public static final int GET_BORED = 5;

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();

        Thread user = new Thread(null, taskUser(game), "Пользователь");
        user.start();
        Thread box = new Thread(null, taskBox(game), "Игрушка");
        box.start();

        user.join();
        box.interrupt();


    }

    public static Runnable taskBox(Game game) {
        return () -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (game.position) {
                    System.out.println(Thread.currentThread().getName()
                            + " выключает тумблер");
                    game.position = false;
                }
            }
        };
    }

    public static Runnable taskUser(Game game) {
        return () -> {
            for (int i = 0; i < GET_BORED; ) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!game.position) {
                    System.out.println(Thread.currentThread().getName()
                            + " включает тумблер" + i);
                    i++;
                    game.position = true;
                }

            }
        };
    }


}
