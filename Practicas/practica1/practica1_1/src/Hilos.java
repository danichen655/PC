public class Hilos extends Thread{

    int id;
    int restTime;

    public Hilos (int identificador, int t){

        this.id = identificador;
        this.restTime = t;
    }

    public void run() {

        System.out.println("Soy el hilo:" + id);

        try {
            sleep(restTime);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Soy el hilo:" + id);
    }
}