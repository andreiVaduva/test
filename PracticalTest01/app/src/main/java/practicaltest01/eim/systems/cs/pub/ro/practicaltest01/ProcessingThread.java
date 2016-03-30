package practicaltest01.eim.systems.cs.pub.ro.practicaltest01;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

/**
 * Created by andrei on 30.03.2016.
 */
public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private double am;
    private double gm;

    public ProcessingThread(Context context, int firstNumber, int secondNumber) {
        this.context = context;

        am = (firstNumber + secondNumber) / 2;
        gm = Math.sqrt(firstNumber * secondNumber);
    }

    @Override
    public void run() {
        Log.d("[Processing Thread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[Processing Thread]", "Thread has stopped!");
    }

    public void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + am + " " + gm);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
